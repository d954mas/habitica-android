package com.d954mas.habitrpgerapper.api.user;


import com.d954mas.habitrpgerapper.api.task.models.UserStats;
import com.d954mas.habitrpgerapper.api.user.models.User;
import com.d954mas.habitrpgerapper.api.user.models.UserInboxBlocks;
import com.d954mas.habitrpgerapper.api.user.models.UserInboxMessages;
import com.d954mas.habitrpgerapper.api.user.models.UserItems;
import com.d954mas.habitrpgerapper.api.user.models.UserItemsQuests;
import com.d954mas.habitrpgerapper.api.user.models.UserLoginBody;
import com.d954mas.habitrpgerapper.api.user.models.UserRegisterBody;
import com.magicmicky.habitrpgwrapper.lib.models.UserAuthResponse;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface UserService {
    @POST("user/allocate-naw")
    Observable<UserStats> allocateNow();

    @POST("user/allocate")
    Observable<UserStats> allocate(@Query("stat") String stat);

    @POST("user/block/{uuid}")
    Observable<UserInboxBlocks> blockUnblockUser(@Path("uuid") String uuid);

    @POST("user/buy-health-potion")
    Observable<UserStats> buyHealthPotion();

    //TODO return complex object
    @POST("user/buy-mystery-set/{key}")
    Observable<Void> buyMysterySet(@Path("key") String key);

    //TODO return complex object
    @POST("user/buy-gear/{key}")
    Observable<Void> buyGear(@Path("key") String key);

    @POST("user/buy-quest/{key}")
    Observable<UserItemsQuests> buyQuest(@Path("key") String key);

    //TODO return complex object
    @POST("user/buy-armoire")
    Observable<Void> buyArmoire();

    //TODO return different objects depend on key(gear,potion,armoire)
    @POST("user/buy/{key}")
    Observable<Void> buyItem(@Path("key") String key);

    //TODO return complex object
    @POST("user/buy-special-spell/{key}")
    Observable<Void> buySpecialSpell(@Path("key") String key);

    //TODO return different objects
    @POST("user/class/cast/{spellId}")
    Observable<Void> castSpell(@Path("spellId") String spellId, @Query("targetId") String uuid);

    //TODO return complex object
    @POST("user/change-class")
    Observable<Void> changeClass(@Query("class") String clazz);

    @DELETE("user/messages/{id}")
    Observable<UserInboxMessages> deleteMessage(@Path("id") String id);

    @DELETE("user/messages")
    Observable<UserInboxMessages> deleteAllMessages();

    //delete user account/social authentication no impl

    //TODO complex object
    @POST("user/disable-classes")
    Observable<Void> disableClasses();

    @POST("user/equip/{type}/{key}")
    Observable<Void> equip(@Path("type") String type, @Path("key") String key);

    //TODO what is pet number?
    @POST("user/feed/{pet}/{food}")
    Observable<Integer> feedPet(@Path("pet") String pet, @Path("food") String food);

    @GET("user/anonymized")
    Observable<Object> getAnonymizedUser();

    @GET("user")
    Observable<User> getUser();

    @GET("user/inventory/buy")
    Observable<Object> getBuyList();

    @GET("user/hatch/{egg}/{potion}")
    Observable<Object> hatchPet(@Path("egg") String egg, @Path("potion") String potion);

    @POST("user/auth/local/login")
    Observable<UserAuthResponse> login(@Body UserLoginBody body);

    @POST("user/sleep")
    Observable<Boolean> sleep();

    @POST("user/mark-pms-red")
    Observable<UserInboxMessages> markPmsRead();

    @POST("user/open-mystery-item")
    Observable<Object> openMysteryItem();

    @POST("user/purchase/{type}/{key}")
    Observable<Object> buyByGems(@Path("type") String type, @Path("key") String key);

    @POST("user/purchase-hourglass/{type}/{key}")
    Observable<Object> buyHourglass(@Path("type") String type, @Path("key") String key);

    @POST("user/read-card/{cardType}")
    Observable<Object> readCard(@Path("cardType") String cardType);

    @POST("user/auth/local/register")
    Observable<User> register(@Body UserRegisterBody body);

    @POST("user/release-mounts")
    Observable<Object> releaseMounts();

    @POST("user/release-mounts")
    Observable<Object> releaseMountsAndPets();

    @POST("user/reroll")
    Observable<Object> rerollByPotion();

    @POST("user/reset-password")
    Observable<String> resetPassword(@Body String email);

    @POST("user/revive")
    Observable<UserItems> revive();

    @POST("user/sell/{type}/{key}")
    Observable<Object> sellItem(@Path("type") String type, @Path("key") String key);

    @POST("user/custom-day-start")
    Observable<Void> customDayStart();

    @POST("user/unlock")
    Observable<Object> unlock();

    // @POST("auth/update-email")
    //Observable<Void> updateEmail(String email)

    @PUT("user")
    Observable<User> updateUser();

    @PUT("user/auth/update-username")
    Observable<Void> updateUserName(@Body UserLoginBody userLoginBody);

    @POST("user/rebirth")
    Observable<Object> rebirth();
}
