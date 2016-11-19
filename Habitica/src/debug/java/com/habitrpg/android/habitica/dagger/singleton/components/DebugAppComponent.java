package com.habitrpg.android.habitica.dagger.singleton.components;

import com.habitrpg.android.habitica.dagger.singleton.modules.ApiModule;
import com.habitrpg.android.habitica.dagger.singleton.modules.AppModule;
import com.habitrpg.android.habitica.dagger.singleton.modules.DeveloperModule;
import com.habitrpg.android.habitica.debug.fragments.LynxDebugFragment;
import com.habitrpg.android.habitica.debug.impl.ViewContainerImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, DeveloperModule.class})
public interface DebugAppComponent extends AppComponent {
    void inject(ViewContainerImpl viewContainer);

    void inject(LynxDebugFragment fragment);
}
