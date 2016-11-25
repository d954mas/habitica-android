package com.d954mas.habitrpgerapper.api.content.models.item;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Item {
    abstract String text();

    abstract String notes();

    abstract int value();

    abstract int type();

    abstract String key();

    abstract String set();

    abstract int klass();

    abstract String index();

    abstract int str();

    abstract int intelect();

    abstract int per();

    abstract int con();

    abstract boolean twoHanded();

    abstract boolean last();

    abstract int specialClass();

    abstract String mystery();
}
