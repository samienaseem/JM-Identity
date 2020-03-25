package jm_id.com.samar.projectjmi_id.jm_id_gaurds;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class verification_Activity extends AppCompatActivity {
    Button b3;
    ImageButton imb1;
    EditText ed1;
    String str="";
    AlertDialog.Builder builder1;
    AlertDialog.Builder builder;
    String p;
    TextView tv;
   //    int a;

    int counter;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
     final Activity activity=this;
     String date;
     String gateid;


    @Override
    public void onBackPressed() {

        builder.setMessage("Do you really want to go back").setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(),Gaurd_Login.class));
                        finish();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.setTitle("Confirmation Alert");
        alertDialog.show();
        //super.onBackPressed();


    }

    @Override
    protected void onStart() {

       super.onStart();
        //startActivity(new Intent(this,Gaurd_Login.class));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_verification_);

        builder=new AlertDialog.Builder(this);



        tv=(TextView)findViewById(R.id.textViewn);
        b3=(Button)findViewById(R.id.verify);
        ed1=(EditText)findViewById(R.id.editText);
        imb1=(ImageButton)findViewById(R.id.imageButton);
        databaseReference=FirebaseDatabase.getInstance().getReference("Students");
       // databaseReference1=FirebaseDatabase.getInstance().getReference();
        databaseReference1=FirebaseDatabase.getInstance().getReference("gateNumber");

        String num=getIntent().getStringExtra("number");
        tv.setText(num);


        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //
            }

            @Override
            public void afterTextChanged(Editable s) {
                     String p=ed1.getText().toString();



                if (p.length() == 8) {

                    /*Toast.makeText(getApplicationContext(), p+"", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), VerifyStdDetails.class);
                    intent.putExtra("id1", p);
                    startActivity(intent);*/
                    DatabaseFunction();
                }
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
                intentIntegrator.setOrientationLocked(true);

            }
        });







    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        Toast.makeText(getApplicationContext(), result.getContents(), Toast.LENGTH_SHORT).show();



        if (result!=null) {
            if (result.getContents()==null){
                Toast.makeText(getApplicationContext(), result.getContents(), Toast.LENGTH_SHORT).show();
            }
            else{
                str =result.getContents();
                ed1.setText(str+"");
                DatabaseFunction();
               /* Intent intent=new Intent(getApplicationContext(),VerifyStdDetails.class);
                startActivity(intent);*/
                Toast.makeText(getApplicationContext(), result.getContents(), Toast.LENGTH_SHORT).show();


            }
            // Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();

        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void DatabaseFunction() {

        p=ed1.getText().toString();

         gateid=getIntent().getStringExtra("gate");
        counter=1;

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dp:dataSnapshot.getChildren()){

                    String i=dp.getKey();
                    //
                    if (i.equals(p)){

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
                        Calendar calendar=Calendar.getInstance();
                         date=simpleDateFormat.format(calendar.getTime())   ;
                        databaseReference1=FirebaseDatabase.getInstance().getReference("gateNumber").child(date).child(gateid);


                        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String s=dataSnapshot.getValue().toString();
                                //Toast.makeText(getApplicationContext(),s + "samie",Toast.LENGTH_LONG).show();
                                int a=Integer.parseInt(s);
                                a++;
                                databaseReference1.setValue(a);
                                tv.setText(String.valueOf(a));


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        method1();


                        /*databaseReference.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                 s=dataSnapshot.getValue().toString();
                                 a=Integer.parseInt(s);
                                 a++;
                                 databaseReference.setValue(45);


                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });*/

                       /* databaseReference1.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                String s=dataSnapshot.getValue().toString();
                                Toast.makeText(getApplicationContext(),s+"",Toast.LENGTH_LONG).show();
                                a=Integer.parseInt(s);
                                //a++;
                                databaseReference1.setValue(++a);


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
*/
                        Intent intent=new Intent(getApplicationContext(),VerifyStdDetails.class);
                        intent.putExtra("id",p);
                        startActivity(intent);
                        counter=0;
                    }
                }
                if (counter==1){
                    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                     builder1=new AlertDialog.Builder(activity);
                    builder1.setMessage("JM-ID -"+ p +" Does not Exist").setCancelable(false).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog23=builder.create();
                    alertDialog23.setTitle("Error");

                    alertDialog23.show();
                    Toast.makeText(getApplicationContext(),p+" does not exist",Toast.LENGTH_LONG).show();

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        ed1.setText("");
    }

    private void method1() {

        DatabaseReference dp=FirebaseDatabase.getInstance().getReference("ActiveStudents").child(date).child(p).child("Active");
         /*DatabaseReference dp=databaseReference.child(p).child("Active");
         dp.setValue(1);*/
         dp.setValue(1);

        /*dp.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dpc:dataSnapshot.getChildren()){
                    Toast.makeText(getApplicationContext(),dpc.getKey()+"samie",Toast.LENGTH_SHORT).show();
                    String s=dpc.getKey();
                    if (s.equals("Active")){
                        DatabaseReference dp1=dp.child(s);
                        dp1.setValue(1);
                        Toast.makeText(getApplicationContext(),"samie",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }

}

