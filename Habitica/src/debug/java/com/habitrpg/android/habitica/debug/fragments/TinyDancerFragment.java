package com.habitrpg.android.habitica.debug.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.debug.mvp.presenter.TinyDancerPresenter;
import com.habitrpg.android.habitica.debug.mvp.view.TinyDancerView;

import butterknife.BindView;

public class TinyDancerFragment extends BaseDebugFragment implements TinyDancerView {
    @BindView(R.id.toggle_tiny_dancer) Switch tinyDancerSwitcher;
    @InjectPresenter TinyDancerPresenter tinyDancerPresenter;

    @Nullable @Override public View onCreateView(LayoutInflater inflater,
                                                 @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.debug_tiny_dancer_fragment, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("TinyDancer");
        tinyDancerSwitcher.setOnClickListener(v -> tinyDancerPresenter.toggleTinyDancer(getContext(), tinyDancerSwitcher.isChecked()));
    }

    @Override public void showTinyDancer(boolean show) {
        tinyDancerSwitcher.setChecked(show);
    }
}
