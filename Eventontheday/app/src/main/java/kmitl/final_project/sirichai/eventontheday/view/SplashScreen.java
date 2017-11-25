package kmitl.final_project.sirichai.eventontheday.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import kmitl.final_project.sirichai.eventontheday.R;

public class SplashScreen extends AppCompatActivity {
    private TextView appName;
    private ImageView logo;
    private Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initInstances();
        startAnimation();
        delay();
    }

    private void delay() {

        Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                    final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }

    private void startAnimation() {
        appName.startAnimation(animation);
        logo.startAnimation(animation);
    }

    private void initInstances() {
        appName = (TextView) findViewById(R.id.appName);
        logo = (ImageView) findViewById(R.id.logo);
        animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
    }
}
