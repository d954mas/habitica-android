package com.d954mas.habitrpgerapper.api.development;


import retrofit2.http.POST;
import rx.Observable;

public interface DevelopService {
    @POST("debug/add-hourglass")
    Observable<Void> addHourglass();

    @POST("debug/add-ten-gems")
    Observable<Void> addTenGems();

    @POST("debug/quest-progress")
    Observable<Void> accelerateQuest();

    //@GET("debug/modify-inventory")//i need to send inventory?
    // Observable<Void> modifyInventory();

    @POST("debug/make-admin")
    Observable<Void> makeAdmin();
}
