package com.d954mas.habitrpgerapper.api.content;


import com.d954mas.habitrpgerapper.api.content.models.Content;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ContentService {
    @GET("content")
    Observable<Content> getContent(@Query("language") String language);
}
