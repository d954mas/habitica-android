package com.habitrpg.android.habitica;


import com.habitrpg.android.habitica.dagger.singleton.components.AppComponent;
import com.habitrpg.android.habitica.dagger.singleton.components.DaggerAppComponent;
import com.habitrpg.android.habitica.dagger.singleton.modules.AppModule;
import com.habitrpg.android.habitica.dagger.singleton.modules.DeveloperModule;

public class HabiticaApplication extends HabiticaBaseApplication {
    @Override
    protected AppComponent initDagger() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .developerModule(new ReleaseDeveloperModule())
                .build();
    }

}
