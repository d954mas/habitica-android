package com.d954mas.habitrpgerapper.api.task;


import com.d954mas.habitrpgerapper.api.task.models.Task;
import com.d954mas.habitrpgerapper.api.task.models.UserStats;

import java.util.List;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface TaskService {
    @POST("tasks/{taskId}/tags/{tagId}")
    Observable<Task> addTask(@Path("taskId") String taskId, @Path("tagId") String tagId);

    @POST("tasks/{taskId}/checklist")
    Observable<Task> addItemToTaskChecklist(@Path("taskId") String taskId);

    @POST("tasks/{taskId}/approve/{userId}")
    Observable<Task> approveUser(@Path("taskId") String taskId, @Path("userId") String userId);

    @POST("tasks/{taskId}/assign/{assignedUserId}")
    Observable<Task> assignTaskToUser(@Path("taskId") String taskId);

    //todo can have idfferent inout object or array
    @POST("tasks/challenge/{challengeId}")
    Observable<Task> createChallenegTask(@Path("challengeId") String challengeId);

    //todo can have idfferent inout object or array
    @POST("tasks/user")
    Observable<Task> createUserTask();

    @DELETE("tasks/{taskId}")
    Observable<Void> removeTask(@Path("taskId") String taskId);

    //todo what is it
    @POST("tasks/clearCompletedTodos")
    Observable<Void> clearCompletedTodos();

    @GET("tasks/challenge/{challengeId}")
    Observable<List<Task>> getChallengeTasks(@Path("challengeId") String challengeId, @Query("type") String type);

    @GET("tasks/{taskId}")
    Observable<Task> getTask(@Path("taskId") String taskId);

    @GET("tasks/user")
    Observable<List<Task>> getUserTasks();

    @POST("tasks/{taskId}/move/to/{position}")
    Observable<List<Task>> moveTaskToNewPosition(@Path("taskId") String pathId, @Path("position") int position);

    @DELETE("tasks/{taskId}/checklist/{itemId}")
    Observable<Task> removeChecklistItem(@Path("taskId") String taskId, @Path("itemId") String itemId);

    @DELETE("tasks/{taskId}/tags/{tagId}")
    Observable<Task> removeTagFromTask(@Path("taskId") String taskId, @Path("tagId") String tagId);

    @POST("tasks/{taskId}/checklist/{itemId}/score")
    Observable<Task> scoreChecklistItem(@Path("taskId") String taskId, @Path("itemId") String itemId);

    //direction up/down mb constant int
    @POST("tasks/{taskId}/score/{direction}")
    Observable<UserStats> scoreTask(@Path("taskId") String taskId, @Path("direction") int direction);

    @POST("tasks/{taskId}/unassign/{assignedUserId}")
    Observable<Task> unassignTaskToUser(@Path("taskId") String taskId);

    @POST("tasks/unlink-one/{taskId}")
    Observable<Void> unlinkChallengeTask(@Path("taskId") String taskId);

    @POST("tasks/unlink-all/{challengeId}")
    Observable<Void> unlinkAllChallengeTask(@Path("challengeId") String challengeId);

    @PUT("tasks/{taskId}/checklist/{itemId}")
    Observable<Task> updateChecklistItem(@Path("taskId") String taskId, @Path("itemId") String itemId);

    @PUT("tasks/{taskId}")
    Observable<Task> updateTask(@Path("taskId") String taskId);


}
