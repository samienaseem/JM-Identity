package jm_id.com.samar.projectjmi_id.jm_id_gaurds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import static java.lang.Thread.sleep;
public class Splash extends AppCompatActivity {
    TextView sc,t1,t2,t3,t4,t5;
    ImageView imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sc=(TextView)findViewById(R.id.scout);
        t1=(TextView)findViewById(R.id.shah);
        t2=(TextView)findViewById(R.id.samie);
        t3=(TextView)findViewById(R.id.sakib);
        t4=(TextView)findViewById(R.id.vishu);
        t5=(TextView)findViewById(R.id.Developed);
        imm=(ImageView)findViewById(R.id.imagelogo);
        final Animation myanam= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        sc.startAnimation(myanam);
        t1.startAnimation(myanam);
        t2.startAnimation(myanam);
        t3.startAnimation(myanam);
        t4.startAnimation(myanam);
        t5.startAnimation(myanam);
        imm.startAnimation(myanam);
        final Intent home=new Intent(this,Gaurd_Login.class);
        Thread timer=new Thread() {

            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                    startActivity(home);
                }
            }
        };
        timer.start();
    }
}

