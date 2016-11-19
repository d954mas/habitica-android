package com.habitrpg.android.habitica;

import com.habitrpg.android.habitica.dagger.singleton.components.AppComponent;
import com.habitrpg.android.habitica.dagger.singleton.components.DaggerDebugAppComponent;
import com.habitrpg.android.habitica.dagger.singleton.modules.AppModule;
import com.habitrpg.android.habitica.dagger.singleton.modules.DebugDeveloperModule;
import com.habitrpg.android.habitica.dagger.singleton.modules.DebugNetModule;

public class HabiticaApplication extends HabiticaBaseApplication {

    @Override
    protected AppComponent initDagger() {
        return DaggerDebugAppComponent.builder()
                .appModule(new AppModule(this))
                .developerModule(new DebugDeveloperModule())
                .netModule(new DebugNetModule())
                .build();
    }

}
