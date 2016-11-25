package com.habitrpg.android.habitica.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.habitrpg.android.habitica.APIHelper;
import com.habitrpg.android.habitica.HabiticaBaseApplication;
import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.ui.fragments.setup.IntroFragmentBuilder;
import com.viewpagerindicator.CirclePageIndicator;

import javax.inject.Inject;

import butterknife.BindView;

public class IntroActivity extends BaseActivity {
    @Inject public APIHelper apiHelper;
    @BindView(R.id.vp) ViewPager pager;
    @BindView(R.id.vpIndicator) CirclePageIndicator indicator;
    @BindView(R.id.btnSkip) Button skipButton;
    @BindView(R.id.btnFinish) Button finishButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewInContainer(R.layout.activity_intro);
        HabiticaBaseApplication.getComponent().inject(this);
        setupIntro();
        indicator.setViewPager(pager);
        skipButton.setOnClickListener(this::finishIntro);
        finishButton.setOnClickListener(this::finishIntro);
    }

    private void setupIntro() {
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0: {
                        int id = R.drawable.intro_1;
                        String title = getString(R.string.intro_1_title);
                        String description = getString(R.string.intro_1_description, getString(R.string.habitica_user_count));
                        return IntroFragmentBuilder.newIntroFragment(description, id, title);
                    }
                    case 1: {
                        int id = R.drawable.intro_2;
                        String title = getString(R.string.intro_2_title);
                        String description = getString(R.string.intro_2_description);
                        return IntroFragmentBuilder.newIntroFragment(description, id, title);
                    }
                    case 2: {
                        int id = R.drawable.intro_3;
                        String title = getString(R.string.intro_3_title);
                        String description = getString(R.string.intro_3_description);
                        return IntroFragmentBuilder.newIntroFragment(description, id, title);
                    }
                    default:
                        throw new RuntimeException("unknown page");
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        pager.addOnPageChangeListener(new PageChangeListener());
    }


    private void finishIntro(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    private final class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //pass
        }

        @Override public void onPageSelected(int position) {
            if (position == 2) {
                finishButton.setVisibility(View.VISIBLE);
            } else {
                finishButton.setVisibility(View.GONE);
            }
        }

        @Override public void onPageScrollStateChanged(int state) {
            //pass
        }
    }
}
