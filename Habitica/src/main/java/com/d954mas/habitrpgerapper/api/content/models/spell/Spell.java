package com.d954mas.habitrpgerapper.api.content.models.spell;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Spell {
    abstract boolean previousPurchase();

    abstract boolean immediateUse();
}
