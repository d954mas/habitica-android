package com.habitrpg.android.habitica.api;

import android.os.Build;

import com.habitrpg.android.habitica.BuildConfig;
import com.magicmicky.habitrpgwrapper.lib.models.Tag;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.UUID;

import rx.observers.TestSubscriber;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.M)
@RunWith(RobolectricGradleTestRunner.class)
public class TagAPITests extends BaseAPITests {

    @Test
    public void shouldCreateTag() {
        TestSubscriber<Tag> testSubscriber = new TestSubscriber<>();
        Tag tag = new Tag();
        tag.setName("foo");
        apiHelperOld.apiService.createTag(tag).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertValueCount(1);

    }

    @Test
    public void shouldUpdateTag() {
        TestSubscriber<Tag> testSubscriber = new TestSubscriber<>();

        Tag t = new Tag();
        String newname = "BAR";
        t.setId(String.valueOf(UUID.randomUUID()));
        t.setName(newname);

        //Attempt to update the test user's first tag
        String testId = getUser().getTags().get(0).getId();
        apiHelperOld.apiService.updateTag(testId, t).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        Assert.assertEquals(newname,testSubscriber.getOnNextEvents().get(0).getName());

    }

    @Test
    public void shouldDeleteTag() {
        TestSubscriber<Void> testSub = new TestSubscriber<>();

        String testId = getUser().getTags().get(0).getId();
        apiHelperOld.apiService.deleteTag(testId).subscribe(testSub);
        testSub.assertNoErrors();
        testSub.assertCompleted();
    }

}
