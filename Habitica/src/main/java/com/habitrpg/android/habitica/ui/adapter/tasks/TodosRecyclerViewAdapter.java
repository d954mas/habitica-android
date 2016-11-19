package com.habitrpg.android.habitica.ui.adapter.tasks;

import android.content.Context;
import android.view.ViewGroup;

import com.habitrpg.android.habitica.HabiticaBaseApplication;
import com.habitrpg.android.habitica.dagger.singleton.components.AppComponent;
import com.habitrpg.android.habitica.helpers.TagsHelper;
import com.habitrpg.android.habitica.ui.viewHolders.tasks.TodoViewHolder;

public class TodosRecyclerViewAdapter extends SortableTasksRecyclerViewAdapter<TodoViewHolder> {

    public TodosRecyclerViewAdapter(String taskType, TagsHelper tagsHelper, int layoutResource,
                                    Context newContext, String userID, SortTasksCallback sortCallback) {
        super(taskType, tagsHelper, layoutResource, newContext, userID, sortCallback);
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TodoViewHolder(getContentView(parent));
    }

    @Override
    protected void injectThis(AppComponent component) {
        HabiticaBaseApplication.getComponent().inject(this);
    }
}
