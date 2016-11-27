package com.habitrpg.android.habitica.ui.fragments.social;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitrpg.android.habitica.APIHelperOld;
import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.dagger.singleton.components.AppComponent;
import com.habitrpg.android.habitica.databinding.FragmentGroupInfoBinding;
import com.habitrpg.android.habitica.databinding.ValueBarBinding;
import com.habitrpg.android.habitica.helpers.QrCodeManager;
import com.habitrpg.android.habitica.ui.adapter.social.QuestCollectRecyclerViewAdapter;
import com.habitrpg.android.habitica.ui.fragments.BaseFragmentOld;
import com.magicmicky.habitrpgwrapper.lib.models.Group;
import com.magicmicky.habitrpgwrapper.lib.models.HabitRPGUser;
import com.magicmicky.habitrpgwrapper.lib.models.inventory.QuestContent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupInformationFragment extends BaseFragmentOld {


    FragmentGroupInfoBinding viewBinding;

    @Inject
    APIHelperOld apiHelperOld;

    @BindView(R.id.questMemberView)
    LinearLayout questMemberView;

    @BindView(R.id.collectionStats)
    RecyclerView collectionStats;

    @BindView(R.id.qrLayout)
    @Nullable
    LinearLayout qrLayout;

    @BindView(R.id.qrWrapper)
    @Nullable
    CardView qrWrapper;

    private View view;
    private Group group;
    private HabitRPGUser user;
    private QuestContent quest;
    private ValueBarBinding bossHpBar;
    private ValueBarBinding bossRageBar;

    private QuestCollectRecyclerViewAdapter questCollectViewAdapter;
    public GroupInformationFragment() {

    }

    public static GroupInformationFragment newInstance(Group group, HabitRPGUser user) {
        Bundle args = new Bundle();

        GroupInformationFragment fragment = new GroupInformationFragment();
        fragment.setArguments(args);
        fragment.group = group;
        fragment.user = user;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_group_info, container, false);
        questCollectViewAdapter = new QuestCollectRecyclerViewAdapter();

        viewBinding = DataBindingUtil.bind(view);
        viewBinding.setHideParticipantCard(false);

        if (user != null) {
            viewBinding.setUser(user);
        }

        if (group != null) {
            setGroup(group);
        }

        unbinder = ButterKnife.bind(this, view);

        collectionStats.setLayoutManager(new LinearLayoutManager(getContext()));
        collectionStats.setAdapter(questCollectViewAdapter);
        bossHpBar = DataBindingUtil.bind(view.findViewById(R.id.bossHpBar));
        bossRageBar = DataBindingUtil.bind(view.findViewById(R.id.bossRageBar));

        if (this.group == null) {
            QrCodeManager qrCodeManager = new QrCodeManager(this.getContext());
            qrCodeManager.setUpView(qrLayout);

            if (user != null && user.getInvitations().getParty() != null && user.getInvitations().getParty().getId() != null) {
                viewBinding.setInvitation(user.getInvitations().getParty());
            }
        }

        return view;
    }

    @Override
    public void injectFragment(AppComponent component) {
        component.inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setGroup(Group group) {
        if (viewBinding != null) {
            viewBinding.setGroup(group);
        }

        if (questMemberView != null) {
            updateQuestMember(group);
        }

        this.group = group;

        if (isAdded()) {
            updateQuestProgress(group, quest);
        }
    }

    public void setQuestContent(QuestContent quest) {
        if (viewBinding != null) {
            viewBinding.setQuest(quest);
        }

        updateQuestProgress(group, quest);

        this.quest = quest;
    }

    private void updateQuestProgress(Group group, QuestContent quest) {
        if (group == null || quest == null) {
            return;
        }
        if (questCollectViewAdapter != null) {
            questCollectViewAdapter.setQuestContent(quest);
            if (group.quest.getProgress() != null) {
                questCollectViewAdapter.setQuestProgress(group.quest.getProgress());
            }
        }

        bossHpBar.valueBarLayout.setVisibility((quest.boss != null && quest.boss.hp > 0) ? View.VISIBLE : View.GONE);
        bossRageBar.valueBarLayout.setVisibility((quest.boss != null && quest.boss.rage_value > 0) ? View.VISIBLE : View.GONE);

        if (group.quest.members == null) {
            viewBinding.setHideParticipantCard(true);
        }
    }

    private void updateQuestMember(Group group) {
        questMemberView.removeAllViewsInLayout();
        if (group.quest == null || group.quest.key == null) return;
        Context context = getContext();
        if (context == null && group.quest.members != null) {
            viewBinding.setHideParticipantCard(true);
            return;
        }
        if (group.quest.members == null || group.members == null) {
            viewBinding.setHideParticipantCard(true);
            return;
        }
        viewBinding.setHideParticipantCard(false);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (HabitRPGUser member : group.members) {
            final LinearLayout itemView = (LinearLayout) layoutInflater.inflate(R.layout.party_member_quest, questMemberView, false);
            TextView questResponse = (TextView) itemView.findViewById(R.id.rsvpneeded);
            TextView userName = (TextView) itemView.findViewById(R.id.username);
            userName.setText(member.getProfile().getName());

            if (!group.quest.members.containsKey(member.getId()))
                continue;
            Boolean questresponse = group.quest.members.get(member.getId());
            if (group.quest.active) {
                questResponse.setText("");
            } else if (questresponse == null) {
                questResponse.setText(R.string.quest_pending);
            } else if (questresponse) {
                questResponse.setText(R.string.quest_accepted);
                questResponse.setTextColor(ContextCompat.getColor(context, R.color.good_10));
            } else {
                questResponse.setText(R.string.quest_rejected);
                questResponse.setTextColor(ContextCompat.getColor(context, R.color.worse_10));
            }
            questMemberView.post(() -> {
                if (questMemberView != null) {
                    questMemberView.addView(itemView);
                }
            });
        }
    }


    @OnClick(R.id.btnQuestAccept)
    public void onQuestAccept() {
        apiHelperOld.apiService.acceptQuest(group.id)
                .compose(apiHelperOld.configureApiCallObserver())
                .subscribe(aVoid -> {
                    user.getParty().getQuest().RSVPNeeded = false;
                    group.quest.members.put(user.getId(), true);
                    setGroup(group);
                    viewBinding.setUser(user);
                }, throwable -> {
                });
    }


    @OnClick(R.id.btnQuestReject)
    public void onQuestReject() {
        apiHelperOld.apiService.rejectQuest(group.id)
                .compose(apiHelperOld.configureApiCallObserver())
                .subscribe(aVoid -> {
                    user.getParty().getQuest().RSVPNeeded = false;
                    group.quest.members.put(user.getId(), false);
                    setGroup(group);
                    viewBinding.setUser(user);
                }, throwable -> {
                });
    }


    @OnClick(R.id.btnQuestLeave)
    public void onQuestLeave() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setMessage("Are you sure you want to leave the active quest? All your quest progress will be lost.")
                .setPositiveButton("Yes", (dialog, which) -> {
                    apiHelperOld.apiService.leaveQuest(group.id)
                            .compose(apiHelperOld.configureApiCallObserver())
                            .subscribe(aVoid -> {
                                group.quest.members.remove(user.getId());
                                setGroup(group);
                            }, throwable -> {
                            });
                }).setNegativeButton("No", (dialog, which) -> {

                });
        builder.show();
    }

    @OnClick(R.id.btnQuestBegin)
    public void onQuestBegin() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setMessage(R.string.quest_begin_message)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    apiHelperOld.apiService.forceStartQuest(group.id, group)
                            .compose(apiHelperOld.configureApiCallObserver())
                            .subscribe(quest -> {
                                group.quest = quest;
                                setGroup(group);
                            }, throwable -> {
                            });
                }).setNegativeButton(R.string.no, (dialog, which) -> {

                });
        builder.show();
    }

    @OnClick(R.id.btnQuestCancel)
    public void onQuestCancel() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setMessage(R.string.quest_cancel_message)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    apiHelperOld.apiService.cancelQuest(group.id)
                            .compose(apiHelperOld.configureApiCallObserver())
                            .subscribe(aVoid -> {
                                setGroup(group);
                                setQuestContent(null);
                            }, throwable -> {
                            });
                }).setNegativeButton(R.string.no, (dialog, which) -> {

                });
        builder.show();
    }

    @OnClick(R.id.btnQuestAbort)
    public void onQuestAbort() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setMessage("Are you sure you want to abort this mission? It will abort it for everyone in your party and all progress will be lost. The quest scroll will be returned to the quest owner.")
                .setPositiveButton("Yes", (dialog, which) -> {
                    apiHelperOld.apiService.abortQuest(group.id)
                            .compose(apiHelperOld.configureApiCallObserver())
                            .subscribe(quest -> {
                                group.quest = quest;
                                setGroup(group);
                                setQuestContent(null);
                            }, throwable -> {
                            });
                }).setNegativeButton("No", (dialog, which) -> {

                });
        builder.show();
    }

    @OnClick(R.id.btnPartyInviteAccept)
    public void onPartyInviteAccepted() {
        apiHelperOld.apiService.joinGroup(user.getInvitations().getParty().getId())
                .compose(apiHelperOld.configureApiCallObserver())
                .subscribe(group -> {
                    setGroup(group);
                    viewBinding.setInvitation(null);
                }, throwable -> {});
    }

    @OnClick(R.id.btnPartyInviteReject)
    public void onPartyInviteRejected() {
        apiHelperOld.apiService.rejectGroupInvite(user.getInvitations().getParty().getId())
                .compose(apiHelperOld.configureApiCallObserver())
                .subscribe(aVoid -> {
                    viewBinding.setInvitation(null);
                }, throwable -> {});
    }
}
