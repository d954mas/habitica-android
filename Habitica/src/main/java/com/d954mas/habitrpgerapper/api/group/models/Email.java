package com.d954mas.habitrpgerapper.api.group.models;


import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Email {
    abstract String email();

    @Nullable abstract String name();


}
