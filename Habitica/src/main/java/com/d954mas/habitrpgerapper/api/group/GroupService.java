package com.d954mas.habitrpgerapper.api.group;


import com.d954mas.habitrpgerapper.api.group.models.Group;
import com.d954mas.habitrpgerapper.api.group.models.GroupInviteBody;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface GroupService {
    @POST("groups")
    Observable<Group> createGroup();

    @GET("groups/{groupId}")
    Observable<Group> getGroup(@Path("groupId") String groupId);

    @GET("groups")
    Observable<List<Group>> getIserGroups();

    //todo check response
    @POST("groups/{groupId}/invite")
    Observable<String> inviteUsers(@Path("groupId") String groupId, GroupInviteBody groupInviteBody);

    @POST("groups/{groupId}/join")
    Observable<Group> joinGroup(@Path("groupId") String groupId);

    @POST("groups/{groupId}/leave")
    Observable<Void> leaveGroup(@Path("groupId") String groupId);

    @POST("groups/{groupId}/reject")
    Observable<Void> rejectInvitation(@Path("groupId") String groupId);

    @POST("groups/{groupId}/removeMember/{memberId}")
    Observable<Void> removeMemberFromGroup(@Path("groupId") String groupId, @Path("memberId") String memberId);

    @PUT("groups/{groupId}")
    Observable<Group> updateGroup(@Path("groupId") String groupId, @Body Group group);


}
