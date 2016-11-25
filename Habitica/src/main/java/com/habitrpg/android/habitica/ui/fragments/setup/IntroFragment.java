package com.habitrpg.android.habitica.ui.fragments.setup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.ui.fragments.BaseFragment;
import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import butterknife.BindView;

@FragmentWithArgs
public class IntroFragment extends BaseFragment {
    @BindView(R.id.tvTitle) TextView titleTextView;
    @BindView(R.id.tvDescription) TextView descriptionTextView;
    @BindView(R.id.iv) ImageView imageView;
    @Arg int imageId;
    @Arg String title;
    @Arg String description;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intro, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentArgs.inject(this);
        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), imageId));
        titleTextView.setText(title);
        descriptionTextView.setText(description);
    }
}
