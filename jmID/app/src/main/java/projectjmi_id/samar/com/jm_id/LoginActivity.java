package projectjmi_id.samar.com.jm_id;

import android.content.Intent;
import android.sax.StartElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
        TabHost tabHost;
        Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        b1=(Button)findViewById(R.id.login);
        b2=(Button)findViewById(R.id.login1);
        tabHost=(TabHost)findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec spec=tabHost.newTabSpec("Login");

        spec.setContent(R.id.tab1);
        spec.setIndicator("Login");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Main2Activity.class));
            }
        });
        tabHost.addTab(spec);

        spec=tabHost.newTabSpec("Admin");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Admin");
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Main3Activity.class));
            }
        });
        tabHost.addTab(spec);
    }
}
