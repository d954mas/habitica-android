package com.habitrpg.android.habitica.debug.iface;

import android.support.annotation.NonNull;

/**
 * Tiny interface to hide LeakCanary from main source set.
 * Yep LeakCanary has it's own no-op impl, but
 * this approach is universal and applicable to any libraries you want to
 * use in debug builds but not in release.
 */
public interface LeakCanaryProxy {
    void init();

    void watch(@NonNull Object object);
}
