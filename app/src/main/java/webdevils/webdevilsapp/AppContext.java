package webdevils.webdevilsapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Kevin on 2/18/2017.
 */
public class AppContext extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        AppContext.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return AppContext.context;
    }

}
