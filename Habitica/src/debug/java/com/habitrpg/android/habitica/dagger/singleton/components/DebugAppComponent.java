package com.habitrpg.android.habitica.dagger.singleton.components;

import com.habitrpg.android.habitica.dagger.singleton.modules.ApiModuleOld;
import com.habitrpg.android.habitica.dagger.singleton.modules.AppModule;
import com.habitrpg.android.habitica.dagger.singleton.modules.DeveloperModule;
import com.habitrpg.android.habitica.dagger.singleton.modules.NetModule;
import com.habitrpg.android.habitica.debug.fragments.LynxDebugFragment;
import com.habitrpg.android.habitica.debug.impl.ViewContainerImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModuleOld.class, DeveloperModule.class, NetModule.class})
public interface DebugAppComponent extends AppComponent {
    void inject(ViewContainerImpl viewContainer);

    void inject(LynxDebugFragment fragment);
}
