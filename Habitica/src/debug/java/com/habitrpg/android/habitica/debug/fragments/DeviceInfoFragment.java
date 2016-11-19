package com.habitrpg.android.habitica.debug.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.debug.mvp.presenter.DeviceInfoPresenter;
import com.habitrpg.android.habitica.debug.mvp.view.DeviceInfoView;

import butterknife.BindView;

public class DeviceInfoFragment extends BaseDebugFragment implements DeviceInfoView {
    @BindView(R.id.make_text) TextView makeLabel;
    @BindView(R.id.model_text) TextView modelLabel;
    @BindView(R.id.resolution_text) TextView resolutionLabel;
    @BindView(R.id.density_text) TextView densityLabel;
    @BindView(R.id.release_text) TextView releaseLabel;
    @BindView(R.id.api_text) TextView apiLabel;
    @InjectPresenter DeviceInfoPresenter deviceInfoPresenter;

    @Nullable @Override public View onCreateView(LayoutInflater inflater,
                                                 @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.debug_device_info_fragment, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("Device Info");
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deviceInfoPresenter.calculateValues(getContext());
    }

    @Override public void setMake(String make) {
        makeLabel.setText(make);
    }

    @Override public void setModel(String model) {
        modelLabel.setText(model);
    }

    @Override public void setResolution(String resolution) {
        resolutionLabel.setText(resolution);
    }

    @Override public void setDensity(String density) {
        densityLabel.setText(density);
    }

    @Override public void setRelease(String release) {
        releaseLabel.setText(release);
    }

    @Override public void setApi(String api) {
        apiLabel.setText(api);
    }
}
