package com.habitrpg.android.habitica.debug.mvp.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.habitrpg.android.habitica.debug.mvp.view.ScalpelView;


@InjectViewState
public class ScalpelPresenter extends MvpPresenter<ScalpelView> {
    private boolean enableScalpel, showGraphics, showIds;

    public void setEnableScalpel(boolean enable) {
        if (enableScalpel != enable) {
            enableScalpel = enable;
            getViewState().enableScalpel(enableScalpel);
        }
    }

    public void setShowGraphics(boolean show) {
        if (showGraphics != show) {
            showGraphics = show;
            getViewState().disableGraphics(show);
        }
    }

    public void setShowIds(boolean show) {
        if (showIds != show) {
            showIds = show;
            getViewState().showIds(showIds);
        }
    }

}
