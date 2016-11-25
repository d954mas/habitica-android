package com.d954mas.habitrpgerapper.api.group.models;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class GroupInviteBody {
    abstract List<Email> emails();

    abstract List<String> uuids();
}
