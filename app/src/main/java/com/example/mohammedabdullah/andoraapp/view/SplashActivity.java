package com.example.mohammedabdullah.andoraapp.view;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.example.mohammedabdullah.andoraapp.R;
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 3000;

    private final Handler mHandler = new Handler();
    private final Launcher mLauncher = new Launcher();

    private class Launcher implements Runnable {
        @Override
        public void run() {
            launch();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.postDelayed(mLauncher, SPLASH_DELAY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStop() {
        mHandler.removeCallbacks(mLauncher);
        super.onStop();
    }

    private void launch() {
        if (!isFinishing()) {
            Intent intentMain = new Intent(SplashActivity.this, CategoryActivity.class);
            startActivity(intentMain);
            finish();
        }
    }
}
