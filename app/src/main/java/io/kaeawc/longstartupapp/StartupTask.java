package io.kaeawc.longstartupapp;

import android.os.AsyncTask;
import android.util.Log;

public class StartupTask extends AsyncTask<Void, Void, Double> {

    private App.ApplicationListener mListener;

    public StartupTask(App.ApplicationListener listener) {
        mListener = listener;
    }

    private static final String TAG = App.class.getSimpleName();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mListener != null) {
            mListener.onCommandStarted();
        }
    }

    @Override
    protected Double doInBackground(Void... params) {
        try {
            Log.d(TAG, "Started sleeping");
            Thread.sleep(5000);
            Log.d(TAG, "Slept succesfully");
        } catch (Exception ex) {
            Log.d(TAG, "Woke up with an error");
        } finally {
            Log.d(TAG, "Finished sleeping");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Double aDouble) {
        if (mListener != null) {
            mListener.onCommandEnded();
        }

        super.onPostExecute(aDouble);
    }
}