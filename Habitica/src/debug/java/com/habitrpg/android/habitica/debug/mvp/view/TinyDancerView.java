package com.habitrpg.android.habitica.debug.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface TinyDancerView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showTinyDancer(boolean show);
}
