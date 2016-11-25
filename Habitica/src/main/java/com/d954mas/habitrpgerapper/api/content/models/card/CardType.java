package com.d954mas.habitrpgerapper.api.content.models.card;


public abstract class CardType {
    public static final int GREETING = 0;
    public static final int NYE = 1;
    public static final int THANK_YOU = 2;
    public static final int VALENTINE = 3;
    public static final int BIRTHDAY = 4;

    abstract int key();

    abstract int messageOptions();

    abstract boolean yearRound();
}
