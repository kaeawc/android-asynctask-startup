package io.kaeawc.longstartupapp;

import android.app.Application;
import android.util.Log;

public class App extends Application {

    private static final String TAG = App.class.getSimpleName();

    private boolean mInProgress;

    protected static App instance;

    public static App getInstance() {
        return instance;
    }

    private ApplicationListener mListener;
    public void setListener(ApplicationListener listener) {
        mListener = listener;
    }

    public interface ApplicationListener {
        void onCommandEnded();
        void onCommandStarted();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "entered onCreate");
        instance = this;

        mListener = new ApplicationListener() {
            @Override
            public void onCommandEnded() {
                mInProgress = false;
            }

            @Override
            public void onCommandStarted() {
                mInProgress = true;
            }
        };
        StartupTask sleep = new StartupTask(mListener);
        sleep.execute();
        Log.d(TAG, "exited onCreate");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "entered onTerminate");
        Log.d(TAG, "exited onTerminate");
    }

    public boolean inProgress() {
        return mInProgress;
    }
}
