package projectjmi_id.samar.com.jm_id;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Main2Activity extends AppCompatActivity {
    Button b3;
    ImageButton imb1;
    EditText ed1;
    String str="";
    String p;
    int counter;
    DatabaseReference databaseReference;


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        b3=(Button)findViewById(R.id.verify);
        ed1=(EditText)findViewById(R.id.editText);
        imb1=(ImageButton)findViewById(R.id.imageButton);
        databaseReference=FirebaseDatabase.getInstance().getReference("Students");
        final Activity activity=this;

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p=ed1.getText().toString();
                counter=1;

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dp:dataSnapshot.getChildren()){
                          String i=  dp.getKey();
                          //Toast.makeText(getApplicationContext(),i,Toast.LENGTH_LONG).show();
                          if (i.equals(p)){

                              Intent intent=new Intent(Main2Activity.this,VerifyStdDetails.class);
                              intent.putExtra("id",p);
                              startActivity(intent);
                              counter=0;
                          }
                        }
                        if (counter==1){
                            Toast.makeText(getApplicationContext(),p+" does not exist",Toast.LENGTH_LONG).show();

                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });






               /* Intent intent=new Intent(Main2Activity.this,VerifyStdDetails.class);
               *//* Bundle b=new Bundle();
                b.putString("id",p);
                intent.putExtras(b);*//*
               intent.putExtra("id",p);

               // Toast.makeText(getApplicationContext(),"id"+p,Toast.LENGTH_LONG).show();
               startActivity(intent);
*/



            }
        });




        imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator=new IntentIntegrator(activity);
                intentIntegrator.setDesiredBarcodeFormats(intentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setPrompt("scan");
                intentIntegrator.setCameraId(0);
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setBarcodeImageEnabled(false);
                intentIntegrator.initiateScan();
                intentIntegrator.setOrientationLocked(false);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (result!=null) {
            if (result.getContents()==null){
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
            }
            else
                str=result.getContents();
                ed1.setText(str);
               // Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();

        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
