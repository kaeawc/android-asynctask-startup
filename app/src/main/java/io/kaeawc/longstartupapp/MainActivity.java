package io.kaeawc.longstartupapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private App mApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "entered onCreate");
        mApp = App.getInstance();
        mApp.setListener(new App.ApplicationListener() {
            @Override
            public void onCommandEnded() {
                Log.d(TAG, String.format("Watching Applications progress: %s", mApp.inProgress() ? "working" : "not working"));
                Toast.makeText(getBaseContext(), "Done loading", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCommandStarted() {
                Log.d(TAG, String.format("Watching Applications progress: %s", mApp.inProgress() ? "working" : "not working"));
                Toast.makeText(getBaseContext(), "Started loading", Toast.LENGTH_SHORT).show();
            }
        });

        super.onCreate(savedInstanceState);
        Log.d(TAG, "finished super onCreate");
        setContentView(R.layout.activity_main);
        Log.d(TAG, "exited onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "entered onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d(TAG, "exited onCreateOptionsMenu");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "entered onOptionsItemSelected");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.d(TAG, "entered onOptionsItemSelected");
            return true;
        }

        Log.d(TAG, "entered onOptionsItemSelected");
        return super.onOptionsItemSelected(item);
    }
}
