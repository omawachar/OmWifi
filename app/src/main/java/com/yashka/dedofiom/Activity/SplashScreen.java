package com.yashka.dedofiom.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.yashka.dedofiom.Job.GetCountry;
import com.yashka.dedofiom.R;


public class SplashScreen extends Activity {
    private static int SPLASH_TIME_OUT = 1000;
    private AnimationDrawable animationDrawable;
    private Context context;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT < 16) {
            getWindow().setFlags(1024, 1024);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(4);
        }
        setContentView((int) R.layout.splash_screen);
        ImageView imageView = (ImageView) findViewById(R.id.anim);
//        imageView.setBackgroundResource(R.drawable.splash_animation);
//        this.animationDrawable = (AnimationDrawable) imageView.getBackground();
        this.context = getApplicationContext();
//        new GetCountry(this.context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SplashScreen.this.startActivity(new Intent(SplashScreen.this, MainActivity.class));
                SplashScreen.this.finish();
            }
        }, (long) SPLASH_TIME_OUT);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
//        this.animationDrawable.start();
    }
}
