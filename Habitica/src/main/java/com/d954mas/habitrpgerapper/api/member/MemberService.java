package com.d954mas.habitrpgerapper.api.member;


import com.d954mas.habitrpgerapper.api.member.models.ChallengeProgress;
import com.d954mas.habitrpgerapper.api.member.models.Invite;
import com.d954mas.habitrpgerapper.api.member.models.Member;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface MemberService {
    @GET("challenges/{challengeId}/members/{memberId}")
    Observable<ChallengeProgress> getChallengeProgress(@Path("challengeId") String challengeId, @Path("memberId") String memberId);

    @GET("members/{memberId}")
    Observable<Member> getMemberProfile(@Path("memberId") String memberId);

    @GET("challenges/{challengeId}/members/{memberId}")
    Observable<List<Invite>> getGroupInvites(@Path("challengeId") String challengeId, @Path("memberId") String memberId);

    @GET("challenges/{challengeId}/members/")
    Observable<List<Member>> getChallengeMembers(@Path("challengeId") String challengeId);

    @GET("{groupId}/members/")
    Observable<List<Member>> getGroupMembers(@Path("groupId") String groupId);

    @GET("members/transfer-gems")
    Observable<Void> sendGems(@Body Void body);

    @GET("members/send-private-message")
    Observable<Void> sendPrivateMessage(@Body Void body);


}
