package com.habitrpg.android.habitica.debug.fragments;


import android.widget.TextView;

import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.ui.fragments.BaseFragment;

import butterknife.BindView;

public class BaseDebugFragment extends BaseFragment {
    @BindView(R.id.debug_title) TextView debugTitleLabel;

    protected void setTitle(String title) {
        debugTitleLabel.setText(title);
    }
}
