package com.example.cooking;

import android.app.Application;
import android.content.Context;

import com.example.cooking.data.database.databaseImpl.RoomDatabaseImpl;
import com.facebook.flipper.android.AndroidFlipperClient;
import com.facebook.flipper.android.utils.FlipperUtils;
import com.facebook.flipper.core.FlipperClient;
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin;
import com.facebook.flipper.plugins.inspector.DescriptorMapping;
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin;
import com.facebook.soloader.SoLoader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application {
    public static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(4);
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        SoLoader.init(this, false);
        if(BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)){
            final FlipperClient flipperCLient = AndroidFlipperClient.getInstance(this);
            flipperCLient.addPlugin(new InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()));
            flipperCLient.addPlugin(new DatabasesFlipperPlugin(context));
            flipperCLient.start();
        }

    }
    public static Context getAppContext(){
        return context;
    }
}
