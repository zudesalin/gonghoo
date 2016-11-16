package com.gonghoo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.gonghoo.R;

/**
 * Created by zudesalin on 2016/7/27.
 */
public class loadingActivity extends Activity {
    ImageView imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        imageView = (ImageView) findViewById(R.id.loading);
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(2000);
        imageView.startAnimation(animation);
        animation.setAnimationListener(new LoadAnimationListener());
    }

    private class LoadAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            imageView.setBackgroundResource(R.drawable.loading);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Intent intent = new Intent(loadingActivity.this, JianghuActivity.class);
            startActivity(intent);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
