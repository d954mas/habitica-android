package com.habitrpg.android.habitica.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.habitrpg.android.habitica.APIHelper;
import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.dagger.singleton.components.AppComponent;
import com.habitrpg.android.habitica.events.commands.SelectMemberCommand;
import com.habitrpg.android.habitica.ui.adapter.social.PartyMemberRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;

public class SkillMemberActivity extends BaseActivityOld {

    private PartyMemberRecyclerViewAdapter viewAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    public APIHelper apiHelper;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_skill_members;
    }

    @Override
    protected void injectActivity(AppComponent component) {
        component.inject(this);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        loadMemberList();
    }

    private void loadMemberList() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewAdapter = new PartyMemberRecyclerViewAdapter();
        viewAdapter.context = this;
        recyclerView.setAdapter(viewAdapter);

        apiHelper.apiService.getGroup("party")
                .compose(this.apiHelper.configureApiCallObserver())
                .subscribe(group -> {
                            if (group == null) {
                                return;
                            }

                            apiHelper.apiService.getGroupMembers(group.id, true)
                                    .compose(apiHelper.configureApiCallObserver())
                                    .subscribe(members -> {
                                                viewAdapter.setMemberList(members, true);
                                            },
                                            throwable -> {
                                            });
                        },
                        throwable -> {
                        });
    }

    @Subscribe
    public void onEvent(SelectMemberCommand evt){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("member_id", evt.MemberId);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
