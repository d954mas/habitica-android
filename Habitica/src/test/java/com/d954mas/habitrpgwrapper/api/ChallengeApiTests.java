package com.d954mas.habitrpgwrapper.api;

import com.d954mas.habitrpgerapper.api.UserId;
import com.d954mas.habitrpgerapper.api.challenge.ChallengeService;
import com.d954mas.habitrpgerapper.api.user.UserService;
import com.d954mas.habitrpgwrapper.DaggerTestComponent;
import com.d954mas.habitrpgwrapper.TestComponent;
import com.habitrpg.android.habitica.dagger.singleton.modules.ApiModule;
import com.habitrpg.android.habitica.dagger.singleton.modules.NetModule;
import com.magicmicky.habitrpgwrapper.lib.models.UserAuthResponse;

import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import rx.functions.Action1;
import rx.observers.TestSubscriber;
import timber.log.Timber;

public class ChallengeApiTests {
    @Inject ChallengeService challengeService;
    @Inject UserService userService;
    @Inject UserId userId;

    @Before public void setUp() {
        TestComponent component = DaggerTestComponent.builder()
                .apiModule(new ApiModule())
                .netModule(new NetModule())
                .build();
        component.inject(this);
        TestSubscriber<UserAuthResponse> testSubscriber = new TestSubscriber<>();
        userService.login(TestApiConstants.USER).subscribe(new Action1<UserAuthResponse>() {
            @Override public void call(UserAuthResponse userAuthResponse) {
                Timber.d("");
            }
        });
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
    }

    @Test
    public void createChallengeTest() {

    }
}
