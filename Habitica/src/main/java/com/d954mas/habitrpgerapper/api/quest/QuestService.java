package com.d954mas.habitrpgerapper.api.quest;


import com.d954mas.habitrpgerapper.api.quest.models.Quest;

import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface QuestService {
    @POST("groups/{groupId}/quests/abort")
    Observable<Quest> abortQuest(@Path("groupId") String groupId);

    @POST("groups/{groupId}/quests/accept")
    Observable<Quest> acceptQuest(@Path("groupId") String groupId);

    @POST("groups/{groupId}/quests/cancel")
    Observable<Quest> cancelNotActiveQuest(@Path("groupId") String groupId);

    @POST("groups/{groupId}/quests/force-start")
    Observable<Quest> forceStartQuest(@Path("groupId") String groupId);

    @POST("groups/{groupId}/quests/invite")
    Observable<Quest> inviteToQuest(@Path("groupId") String groupId, @Query("questKey") String questKey);

    @POST("groups/{groupId}/quests/leave")
    Observable<Quest> leaveQuest(@Path("groupId") String groupId);

    @POST("groups/{groupId}/quests/reject")
    Observable<Quest> rejectQuest(@Path("groupId") String groupId);
}
