package com.d954mas.habitrpgerapper;


import com.d954mas.habitrpgerapper.api.challenge.ChallengeService;
import com.d954mas.habitrpgerapper.api.user.UserService;

import javax.inject.Inject;

public class ApiHelper {
    @Inject UserService userService;
    @Inject ChallengeService challengeService;

    public ApiHelper(UserService userService, ChallengeService challengeService) {
        this.userService = userService;
        this.challengeService = challengeService;
    }
}
