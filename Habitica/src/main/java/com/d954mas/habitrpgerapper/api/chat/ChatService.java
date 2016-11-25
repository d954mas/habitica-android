package com.d954mas.habitrpgerapper.api.chat;


import com.d954mas.habitrpgerapper.api.chat.models.ChatMessage;

import java.util.List;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ChatService {
    @POST("groups/{groupId}/chat/{chatId}/clearflags")
    Observable<Void> clearFlag(@Path("groupId") String groupId, @Path("chatId") String chatId);

    //todo can use previousMsg i don't understand logic
    @DELETE("groups/{groupId}/chat/{chatId}")
    Observable<Void> deleteMessage(@Path("groupId") String groupId, @Path("chatId") String chatId);

    @POST("groups/{groupId}/chat/{chatId}/flag")
    Observable<ChatMessage> flagMessage(@Path("groupId") String groupId, @Path("chatId") String chatId);

    @GET("groups/{groupId}/chat")
    Observable<List<ChatMessage>> getGroupChat(@Path("groupId") String groupId);

    @POST("groups/{groupId}/chat/{chatId}/like")
    Observable<ChatMessage> likeMessage(@Path("groupId") String groupId, @Path("chatId") String chatId);

    @POST("groups/{groupId}/chat/seen")
    Observable<ChatMessage> markAllMessagesAsRead(@Path("groupId") String groupId);

    @POST("groups/{groupId}/chat")
    Observable<ChatMessage> sendMessage(@Path("groupId") String groupId, @Query("message") String message);


}
