package com.d954mas.habitrpgwrapper;


import com.d954mas.habitrpgwrapper.api.ChallengeApiTests;
import com.habitrpg.android.habitica.dagger.singleton.modules.ApiModule;
import com.habitrpg.android.habitica.dagger.singleton.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, NetModule.class})
public interface TestComponent {

    void inject(ChallengeApiTests challengeApiTests);
}
