package com.d954mas.habitrpgerapper.api.heroes;


import com.d954mas.habitrpgerapper.api.heroes.models.Hero;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface HeroesService {
    @GET("hall/heroes")
    Observable<List<Hero>> getHeroes();

    @GET("hall/patrons")
    Observable<List<Hero>> getHeroes(@Query("page") int page);

    // @GET("hall/heroes/{heroId}")
    // Observable<Hero> getHero(@Path("heroId") String heroId);

}
