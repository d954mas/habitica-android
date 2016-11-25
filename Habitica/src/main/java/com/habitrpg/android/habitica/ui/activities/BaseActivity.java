package com.habitrpg.android.habitica.ui.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.habitrpg.android.habitica.HabiticaBaseApplication;
import com.habitrpg.android.habitica.debug.iface.ViewContainer;

import javax.inject.Inject;

import butterknife.ButterKnife;

@SuppressWarnings("PMD.AbstractClassWithoutAnyMethod")
public abstract class BaseActivity extends MvpAppCompatActivity {
    @Inject ViewContainer viewContainer;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HabiticaBaseApplication.getComponent().inject(this);
    }

    protected void setContentViewInContainer(@LayoutRes int layout) {
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup container = viewContainer.forActivity(this);
        inflater.inflate(layout, container);
        viewContainer.initDebugView(this);
        ButterKnife.bind(this);
    }

    protected void setupToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(false);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(false);
            }
        }
    }
}

