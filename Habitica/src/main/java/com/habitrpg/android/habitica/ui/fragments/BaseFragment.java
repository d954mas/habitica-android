package com.habitrpg.android.habitica.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.habitrpg.android.habitica.HabiticaBaseApplication;
import com.habitrpg.android.habitica.debug.iface.LeakCanaryProxy;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressWarnings("PMD.AbstractClassWithoutAnyMethod")

public abstract class BaseFragment extends MvpAppCompatFragment {
    private final Handler mainThreadHandler;
    private Unbinder viewBinder;
    @Inject LeakCanaryProxy leakCanaryProxy;

    public BaseFragment() {
        mainThreadHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewBinder = ButterKnife.bind(this, view);
        HabiticaBaseApplication.getComponent().inject(this);
    }

    protected void runOnUiThreadIfFragmentAlive(@NonNull Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper() && isFragmentAlive()) {
            runnable.run();
        } else {
            mainThreadHandler.post(() -> {
                if (isFragmentAlive()) {
                    runnable.run();
                }
            });
        }
    }

    private boolean isFragmentAlive() {
        return getActivity() != null && isAdded() && !isDetached() && getView() != null && !isRemoving();
    }

    @Override
    public void onDestroyView() {
        if (viewBinder != null) {
            viewBinder.unbind();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        leakCanaryProxy.watch(this);
        super.onDestroy();
    }
}
