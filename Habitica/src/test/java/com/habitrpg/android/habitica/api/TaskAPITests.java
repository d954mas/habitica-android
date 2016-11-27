package com.habitrpg.android.habitica.api;


import android.os.Build;

import com.habitrpg.android.habitica.BuildConfig;
import com.magicmicky.habitrpgwrapper.lib.models.TaskDirectionData;
import com.magicmicky.habitrpgwrapper.lib.models.tasks.Task;
import com.magicmicky.habitrpgwrapper.lib.models.tasks.TaskList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.M)
@RunWith(RobolectricGradleTestRunner.class)
public class TaskAPITests extends BaseAPITests {

    private Task habit1;
    private Task daily1;
    private Task todo1;
    private Task reward1;

    @Override
    public void setUp() {
        super.setUp();
        TestSubscriber<TaskList> oldTaskSubscriber = new TestSubscriber<>();
        apiHelperOld.apiService.getTasks().subscribe(oldTaskSubscriber);
        TaskList tasks = oldTaskSubscriber.getOnNextEvents().get(0);
        for (Task task : tasks.tasks.values()) {
            apiHelperOld.apiService.deleteTask(task.getId()).subscribe(new TestSubscriber<>());
        }

        List<Task> randomTasks = new ArrayList<>();
        randomTasks.add(createRandomTask("1", "habit"));
        habit1 = randomTasks.get(0);
        randomTasks.add(createRandomTask("2", "habit"));
        randomTasks.add(createRandomTask("3", "daily"));
        daily1 = randomTasks.get(2);
        randomTasks.add(createRandomTask("4", "daily"));
        randomTasks.add(createRandomTask("5", "todo"));
        todo1 = randomTasks.get(4);
        randomTasks.add(createRandomTask("6", "todo"));
        randomTasks.add(createRandomTask("7", "reward"));
        reward1 = randomTasks.get(6);
        randomTasks.add(createRandomTask("8", "reward"));
        TestSubscriber<List<Task>> testSubscriber = new TestSubscriber<>();
        apiHelperOld.apiService.createTasks(randomTasks).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
    }

    private Task createRandomTask(String number, String type) {
        Task task = new Task();
        task.setId(String.valueOf(UUID.randomUUID()));
        task.setText("task-"+number);
        task.setType(type);
        task.setTags(new ArrayList<>());
        task.setChecklist(new ArrayList<>());
        task.setReminders(new ArrayList<>());
        return task;
    }

    @Test
    public void shouldLoadAllTasksFromServer() {
        TestSubscriber<TaskList> testSubscriber = new TestSubscriber<>();
        apiHelperOld.apiService.getTasks().subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        TaskList taskList = testSubscriber.getOnNextEvents().get(0);
        assertEquals(8, taskList.tasks.size());
    }

    @Test
    public void shouldBeAbleToScoreTaskUp() {
        TestSubscriber<TaskDirectionData> testSubscriber = new TestSubscriber<>();
        apiHelperOld.apiService.postTaskDirection(habit1.getId(), "up").subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        TaskDirectionData data = testSubscriber.getOnNextEvents().get(0);
        assertTrue(data.getDelta() > 0);
    }

    @Test
    public void shouldBeAbleToScoreTaskDown() {
        TestSubscriber<TaskDirectionData> testSubscriber = new TestSubscriber<>();
        apiHelperOld.apiService.postTaskDirection(habit1.getId(), "down").subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        TaskDirectionData data = testSubscriber.getOnNextEvents().get(0);
        assertTrue(data.getDelta() < 0);
    }

    @Test
    public void shouldBeAbleToDeleteATask() {
        TestSubscriber<Void> testSubscriber = new TestSubscriber<>();
        apiHelperOld.apiService.deleteTask(habit1.getId()).subscribe(testSubscriber);
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        TestSubscriber<TaskList> newTaskListSubscriber = new TestSubscriber<>();
        apiHelperOld.apiService.getTasks().subscribe(newTaskListSubscriber);
        TaskList taskList = newTaskListSubscriber.getOnNextEvents().get(0);
        assertEquals(7, taskList.tasks.size());
    }

}
