package com.lenamunir.knowgoaustralia;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(SplashscreenActivity.this)
                .withFullScreen()
                .withSplashTimeOut(2000)
                .withTargetActivity(Starting_Activity.class)
                .withBeforeLogoText("Know & Go AUSTRALIA Quiz")
                .withLogo(R.drawable.aus_desert);

        config.getBeforeLogoTextView().setTextColor(Color.WHITE);

        View splashScr = config.create();
        setContentView(splashScr);
    }
}
