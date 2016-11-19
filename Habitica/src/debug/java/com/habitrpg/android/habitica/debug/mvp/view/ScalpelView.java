package com.habitrpg.android.habitica.debug.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ScalpelView extends MvpView {

    void enableScalpel(boolean enable);

    void disableGraphics(boolean disable);

    void showIds(boolean show);
}
