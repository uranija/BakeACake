package com.example.android.bakingapp.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public class UpdateBakeService extends IntentService {
    public static String INGREDIENTS_LIST = "INGREDIENTS_LIST";
    public static String MEASURES_LIST = "MEASURES_LIST";

    public UpdateBakeService() {
        super("UpdateBakeService");
    }

    public static void startBakingService(Context context, ArrayList<String> ingredientsList) {
        Intent intent = new Intent(context, UpdateBakeService.class);
        intent.putExtra(INGREDIENTS_LIST, ingredientsList);

        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ArrayList<String> ingredientsList = intent.getExtras().getStringArrayList(INGREDIENTS_LIST);
            ArrayList<String> measureList = intent.getExtras().getStringArrayList(MEASURES_LIST);

            handleActionUpdateBakingWidgets(ingredientsList, measureList);

        }
    }


    private void handleActionUpdateBakingWidgets(ArrayList<String> ingredientsList, ArrayList<String> measureList) {
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.putExtra(INGREDIENTS_LIST, ingredientsList);
        intent.putExtra(MEASURES_LIST, measureList);

        sendBroadcast(intent);
    }

}



