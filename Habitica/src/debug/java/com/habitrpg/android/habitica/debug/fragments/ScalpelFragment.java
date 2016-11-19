package com.habitrpg.android.habitica.debug.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.debug.mvp.presenter.ScalpelPresenter;
import com.habitrpg.android.habitica.debug.mvp.view.ScalpelView;
import com.jakewharton.scalpel.ScalpelFrameLayout;

import butterknife.BindView;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;


public class ScalpelFragment extends BaseDebugFragment implements ScalpelView {
    @BindView(R.id.toggle_enable_scalpel) Switch scalpelSwitch;
    @BindView(R.id.toggle_disable_graphics) Switch graphicsSwitch;
    @BindView(R.id.toggle_show_id) Switch idsSwitch;
    @SuppressFBWarnings("UWF")
    ScalpelFrameLayout scalpelFrameLayout;

    @InjectPresenter ScalpelPresenter scalpelPresenter;

    @Nullable @Override public View onCreateView(LayoutInflater inflater,
                                                 @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.debug_scalpel_fragment, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("Scalpel");
        scalpelSwitch.setOnClickListener(v -> scalpelPresenter.setEnableScalpel(scalpelSwitch.isChecked()));
        graphicsSwitch.setOnClickListener(v -> scalpelPresenter.setShowGraphics(graphicsSwitch.isChecked()));
        idsSwitch.setOnClickListener(v -> scalpelPresenter.setShowIds(idsSwitch.isChecked()));
        scalpelFrameLayout = (ScalpelFrameLayout) getActivity().findViewById(R.id.debug_content);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        scalpelFrameLayout = null;
    }

    @Override public void enableScalpel(boolean enable) {
        scalpelFrameLayout.setLayerInteractionEnabled(enable);
    }

    @Override public void disableGraphics(boolean disable) {
        scalpelFrameLayout.setDrawViews(!disable);
    }

    @Override public void showIds(boolean show) {
        scalpelFrameLayout.setDrawIds(show);
    }
}
