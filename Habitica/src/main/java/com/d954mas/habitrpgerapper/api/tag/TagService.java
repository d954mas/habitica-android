package com.d954mas.habitrpgerapper.api.tag;


import com.d954mas.habitrpgerapper.api.tag.models.Tag;

import java.util.List;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface TagService {
    @POST("tags")
    Observable<Tag> createTag();

    @GET("tags/{tagId}")
    Observable<Tag> getTag(@Path("tagId") String tagId);

    @DELETE("tags/{tagId}")
    Observable<Tag> deleteTag(@Path("tagId") String tagId);

    @GET("tags")
    Observable<List<Tag>> getTags();

    @POST("reorder-tags")
    Observable<Void> setTagPosition(@Query("tagId") String tagId, @Query("to") int position);

    @PUT("tags/{tagId}")
    Observable<Tag> updateTag(@Path("tagId") String tagId);
}
