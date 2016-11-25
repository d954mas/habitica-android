package com.habitrpg.android.habitica.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.habitrpg.android.habitica.HabiticaApplication;
import com.habitrpg.android.habitica.dagger.singleton.components.AppComponent;

import butterknife.ButterKnife;

public abstract class BaseActivityOld extends AppCompatActivity {

    private boolean destroyed;

    protected abstract int getLayoutResId();

    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectActivity(getHabiticaApplication().getComponent());
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
    }

    protected abstract void injectActivity(AppComponent component);



    @Override
    protected void onDestroy() {
        destroyed = true;
        super.onDestroy();
    }

    public HabiticaApplication getHabiticaApplication() {
        return (HabiticaApplication) getApplication();
    }
}
