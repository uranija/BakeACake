package com.example.android.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.R;

import java.util.ArrayList;

import static com.example.android.bakingapp.widget.IngWidgetProvider.ingredientsList;
import static com.example.android.bakingapp.widget.IngWidgetProvider.measuresList;

public class IngWidgetService extends RemoteViewsService {

    ArrayList<String> remoteViewingredientsList;
    ArrayList<String> remoteViewMeasuresList;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewsFactory(this.getApplicationContext(), intent);
    }


    class GridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        Context mContext = null;

        public GridRemoteViewsFactory(Context context, Intent intent) {
            mContext = context;

        }

        @Override
        public void onCreate() {
        }

        @Override
        public void onDataSetChanged() {
            remoteViewingredientsList = ingredientsList;
            remoteViewMeasuresList = measuresList;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {

            return remoteViewingredientsList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_view_item);

            views.setTextViewText(R.id.widget_list_view_ingredient_item, remoteViewingredientsList.get(position));

           // views.setTextViewText(R.id.widget_list_view_measure_item, remoteViewMeasuresList.get(position));


            Intent fillInIntent = new Intent();
            views.setOnClickFillInIntent(R.id.widget_list_view_ingredient_item, fillInIntent);

            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }


    }


}




