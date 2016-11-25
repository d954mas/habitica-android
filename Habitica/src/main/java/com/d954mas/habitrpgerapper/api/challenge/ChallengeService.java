package com.d954mas.habitrpgerapper.api.challenge;


import com.d954mas.habitrpgerapper.api.challenge.models.Challenge;

import java.util.List;

import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

//no https://habitica.com/api/v3/challenges/:challengeId/export/csv because not needed
public interface ChallengeService {
    @POST("challenges")
    Observable<Void> createChallenge();

    @DELETE("challenges/{challengeId}")
    Observable<Void> deleteChallenge(@Path("challengeId") String challengeId);

    @POST("challenges/{challengeId}")
    Observable<Challenge> getChallenge(@Path("challengeId") String challengeId);

    @POST("challenges/groups/{groupId}")
    Observable<List<Challenge>> getChallengesForGroup(@Path("groupId") String groupId);

    @POST("challenges/user")
    Observable<List<Challenge>> getChallengesForUser();

    @POST("challenges/{challengeId}/join")
    Observable<Challenge> joinChallenge(@Path("challengeId") String challengeId);

    @POST("challenges/{challengeId}/leave")
    Observable<Challenge> leaveChallenge(@Path("challengeId") String challengeId);

    @POST("challenges/{challengeId}/selectWinner/{winnerId}")
    Observable<Void> selectChallengeWinner(@Path("challengeId") String challengeId,
                                           @Path("winnerId") String winnerId);
}