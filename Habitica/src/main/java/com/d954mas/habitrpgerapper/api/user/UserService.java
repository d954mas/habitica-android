package com.d954mas.habitrpgerapper.api.user;


import com.d954mas.habitrpgerapper.api.user.models.UserLoginBody;
import com.d954mas.habitrpgerapper.api.user.models.UserRegisterBody;
import com.magicmicky.habitrpgwrapper.lib.models.UserAuthResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface UserService {
    @POST("user/auth/local/register")
    Observable<UserAuthResponse> registerUser(@Body UserLoginBody body);

    @POST("user/auth/local/login")
    Observable<UserAuthResponse> connectLocal(@Body UserRegisterBody body);

}
