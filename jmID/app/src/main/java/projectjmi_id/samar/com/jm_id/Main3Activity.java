package projectjmi_id.samar.com.jm_id;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*requestWindowFeature(getWindow().FEATURE_NO_TITLE);*/
        super.onCreate(savedInstanceState);
        /*getSupportActionBar().hide();*/
        setContentView(R.layout.activity_main3);


        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        ListFragmentMain list=new ListFragmentMain();
        //Dashboard d=new Dashboard();
        fragmentTransaction.add(R.id.fragment,list);
        fragmentTransaction.commit();








        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemposition = position;

                if (itemposition==3){



                   *//* BlankFragment bf=new BlankFragment();
                    android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction transaction=manager.beginTransaction();
                    transaction.add(R.id.fragment,bf,"bf_tag");
                    transaction.commit();*//*

                   Intent intent =new Intent(Main3Activity.this,Main4Activity.class);
                    startActivity(intent);
                }

                String itemvalue = (String)lv.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(),"position:"+itemposition +" and the item clicked : "+itemvalue,Toast.LENGTH_LONG).show();
            }
        });*/
    }
}
