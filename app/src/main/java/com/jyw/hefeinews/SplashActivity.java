package com.jyw.hefeinews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.jyw.hefeinews.activity.GuideActivity;
import com.jyw.hefeinews.activity.MainActivity;
import com.jyw.hefeinews.utils.CacheUtils;

public class SplashActivity extends Activity {

    public static final String START_MAIN = "start_main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RelativeLayout rl_splash_page = (RelativeLayout) findViewById(R.id.rl_splash_page);

        /*创建动画集合*/
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setFillAfter(true);

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setFillAfter(true);

        RotateAnimation ra=new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        ra.setFillAfter(true);

        AnimationSet set=new AnimationSet(false);
        set.addAnimation(aa);
        set.addAnimation(sa);
        set.addAnimation(ra);

        set.setDuration(2000);
        set.setFillAfter(true);
        set.setAnimationListener(new myAnimationListener());
        rl_splash_page.setAnimation(set);



    }
    /*动画结束的监听*/
    class myAnimationListener implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            /*判断是否进入过引导页*/
            /*
            如果进入过引导页进入主界面 否则进入引导页
            */
            Intent intent;
            if(CacheUtils.getBoolean(SplashActivity.this, START_MAIN)){
                intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }else{
                intent=new Intent(SplashActivity.this,GuideActivity.class);
                startActivity(intent);
            }
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
