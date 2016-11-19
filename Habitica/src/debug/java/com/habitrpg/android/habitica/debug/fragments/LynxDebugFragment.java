package com.habitrpg.android.habitica.debug.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.habitrpg.android.habitica.HabiticaBaseApplication;
import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.dagger.singleton.components.DaggerDebugAppComponent;
import com.habitrpg.android.habitica.debug.iface.LynxProxy;

import javax.inject.Inject;

import butterknife.BindView;

//do not use mvp because logic is very simple
public class LynxDebugFragment extends BaseDebugFragment {
    @BindView(R.id.show_logs) Button showLogBtn;
    @Inject LynxProxy lynxProxy;

    @Nullable @Override public View onCreateView(LayoutInflater inflater,
                                                 @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.debug_lynx_fragment, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("Logs");
        ((DaggerDebugAppComponent) HabiticaBaseApplication.getComponent()).inject(this);
        showLogBtn.setOnClickListener(v -> lynxProxy.showLynx(getContext()));
    }
}
