package com.habitrpg.android.habitica.api;


import com.habitrpg.android.habitica.APIHelperOld;
import com.habitrpg.android.habitica.BuildConfig;
import com.habitrpg.android.habitica.HostConfig;
import com.magicmicky.habitrpgwrapper.lib.models.HabitRPGUser;
import com.magicmicky.habitrpgwrapper.lib.models.UserAuthResponse;

import org.junit.After;
import org.junit.Before;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.UUID;

import rx.observers.TestSubscriber;

public class BaseAPITests {

    public APIHelperOld apiHelperOld;
    public HostConfig hostConfig;

    public String username;
    public final String password = "password";

    @Before
    public void setUp() {
        if (BuildConfig.BASE_URL.contains("habitica.com")) {
            throw new InvalidParameterException("Can't test against production server.");
        }
        hostConfig = new HostConfig(BuildConfig.BASE_URL,
                "",
                "");
        apiHelperOld = new APIHelperOld(APIHelperOld.createGsonFactory(), hostConfig);
        generateUser();
    }

    public void generateUser() {
        TestSubscriber<UserAuthResponse> testSubscriber = new TestSubscriber<>();
        username = UUID.randomUUID().toString();
        apiHelperOld.registerUser(username, username + "@example.com", password, password)
        .subscribe(testSubscriber);
        testSubscriber.assertCompleted();
        UserAuthResponse response = testSubscriber.getOnNextEvents().get(0);
        hostConfig.setUser(response.getId());
        hostConfig.setApi(response.getApiToken() != null ? response.getApiToken() : response.getToken());
    }

    public HabitRPGUser getUser() {
        TestSubscriber<HabitRPGUser> userSubscriber = new TestSubscriber<>();

        apiHelperOld.apiService.getUser().subscribe(userSubscriber);
        userSubscriber.assertNoErrors();
        userSubscriber.assertCompleted();
        List<HabitRPGUser> users = userSubscriber.getOnNextEvents();

        return users.get(0);
    }

    @After
    public void tearDown() {
    }
}
