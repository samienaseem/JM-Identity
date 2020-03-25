package jm_id.com.samar.projectjmi_id.jm_id_gaurds;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static java.lang.System.exit;

public class Gaurd_Login extends AppCompatActivity {
        TextInputEditText idg,pass,gate;
        DatabaseReference databaseReference;
    DatabaseReference d12;
        //TextView Login;
        ImageView Login;
        String date;
        static  int counter=0;
        String showNo;
        String id,passw,gatenos;
        int number=0;
        AlertDialog.Builder builder;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        exit(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_gaurd__login);

        idg=(TextInputEditText)findViewById(R.id.gID);
        //imgs=(ImageView)findViewById(R.id.imageView5) ;
        pass=(TextInputEditText)findViewById(R.id.gpass);
        gate=(TextInputEditText)findViewById(R.id.gGate);
        Login=(ImageView) findViewById(R.id.imageView5);
        builder=new AlertDialog.Builder(this);
       // DatabaseReference dp=FirebaseDatabase.getInstance().getReference("Students");

        databaseReference=FirebaseDatabase.getInstance().getReference("Students");

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd",Locale.US);
        Calendar calendar=Calendar.getInstance();
        date=simpleDateFormat.format(calendar.getTime());


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(final DataSnapshot dps:dataSnapshot.getChildren()){
                  //  Toast.makeText(getApplicationContext(),dps.getKey(),Toast.LENGTH_SHORT).show();

                    final DatabaseReference dp=FirebaseDatabase.getInstance().getReference("ActiveStudents").child(date).child(dps.getKey()).child("Active");
                   // Toast.makeText(getApplicationContext(),dp.getKey()+"sasasa",Toast.LENGTH_SHORT).show();
                    dp.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if ((dataSnapshot.getValue())==null){
                                dp.setValue(0);
                            }
                            else if ((dataSnapshot.getValue())!=null){
                                dp.setValue(dataSnapshot.getValue());
                            }
                            else{
                                dp.setValue(0);
                            }

                            //Toast.makeText(getApplicationContext(),dataSnapshot.getValue()+"000",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //databaseReference.child("gateNumber").












        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new
                final String gateno = gate.getText().toString();
                id = idg.getText().toString();
                passw = pass.getText().toString();
                gatenos = gate.getText().toString();
                boolean b = Validation();
                //new
                if (b==true){
                    Toast.makeText(getApplicationContext(),b+"",Toast.LENGTH_SHORT).show();

                    LoginFunction();

                  /*  databaseReference = FirebaseDatabase.getInstance().getReference("gateNumber").child(date);


                counter = 1;


                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dp1 : dataSnapshot.getChildren()) {
                            String a = dp1.getKey();

                            if (a.equals(gateno)) {
                                counter = 0;

                                Toast.makeText(getApplicationContext(), "counters is set", Toast.LENGTH_LONG).show();

                                databaseReference = FirebaseDatabase.getInstance().getReference("gateNumber").child(date).child(gateno);

                                databaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        int value = Integer.parseInt(dataSnapshot.getValue().toString());
                                        if (value != 0) {
                                            databaseReference.setValue(value);
                                            Toast.makeText(getApplicationContext(), value + "", Toast.LENGTH_LONG).show();
                                            showNo = String.valueOf(value);

                                        } else {
                                            databaseReference.setValue(value);
                                        }


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                *//*databaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        int value=Integer.parseInt(dataSnapshot.getValue().toString());
                                        if (value!=0){
                                            databaseReference.setValue(value);

                                        }
                                        else {
                                            databaseReference.setValue(0);
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });*//*

                            }


                            //Toast.makeText(Gaurd_Login.this, a+"", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/
            }

               /* if(counter==1){
                    Toast.makeText(getApplicationContext(),"ye chala hai",Toast.LENGTH_LONG).show();
                    databaseReference.child(gateno).setValue(0);
                    stat=0;


                }*/


                //databaseReference.child("gateNumber").child(date).child(gateno).setValue(0);
                /*Intent intent=new Intent(getApplicationContext(),verification_Activity.class);
                intent.putExtra("gate",gateno);
                intent.putExtra("number",showNo);
                    startActivity(intent);*/
            }
        });





    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Toast.makeText(getApplicationContext(),"Counter set to 0",Toast.LENGTH_SHORT).show();
        counter=0;
        number=0;
    }

    private void LoginFunction() {

        databaseReference = FirebaseDatabase.getInstance().getReference("gateNumber").child(date);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dp:dataSnapshot.getChildren()){
                    String a=dp.getKey();
                    if(gatenos.equals(a)){
                        counter=1;
                        Toast.makeText(getApplicationContext(),"Gate number already exist",Toast.LENGTH_SHORT).show();
                        databaseReference=databaseReference.child(gatenos);
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                Toast.makeText(getApplicationContext(),dataSnapshot.getValue()+"**",Toast.LENGTH_SHORT).show();
                                if((Integer.parseInt(dataSnapshot.getValue().toString()))!=0){
                                    Toast.makeText(getApplicationContext(),"Some value are present",Toast.LENGTH_SHORT).show();
                                   /// openActivity();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"its 0",Toast.LENGTH_SHORT).show();
                                    //openActivity();
                                    //databaseReference.setValue(dataSnapshot.getValue().toString());

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                        /*databaseReference=databaseReference.child(gatenos);
                        databaseReference.setValue(0);
                        break;*/
                    }
                }
                if (counter==0){
                    Toast.makeText(getApplicationContext(),"counter = 0 code executed",Toast.LENGTH_LONG).show();
                    databaseReference.child(gatenos).setValue(0);
                   // openActivity();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void openActivity() {

        Intent intent=new Intent(this,verification_Activity.class);
        intent.putExtra("gate",gatenos);
        intent.putExtra("number",showNo);
        startActivity(intent);
        finish();
    }


    private boolean Validation() {

        if(TextUtils.isEmpty(id)){
            idg.setError("please input your id");
            return false;
        }
        else if (TextUtils.isEmpty(passw)){
            pass.setError("Please input your password");
            return false;
        }
        else if (TextUtils.isEmpty(gatenos)){
            gate.setError("Enter Gate number");
            return false;
        }
        else{
            DatabaseGuard();
        return true;
        }
    }

    private void DatabaseGuard() {

        d12=FirebaseDatabase.getInstance().getReference("GuardDB");
        d12.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot1) {
                d12=d12.child(id).child("password");
                d12.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot2) {

                        //Toast.makeText(getApplicationContext(),dataSnapshot2.getValue()+"",Toast.LENGTH_SHORT).show();
                        for(DataSnapshot dp:dataSnapshot1.getChildren()) {

                            //String password=dataSnapshot.getValue().toString();

                            if (dp.getKey().equals(id)) {
                                number=1;
                                //Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();

                                if ((dataSnapshot2.getValue().toString()).equals(passw)) {

                                    openActivity();

                                } else {
                                    builder.setMessage("Password is incorrect").setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            pass.setText("");
                                        }
                                    });

                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.setTitle("Error");
                                    alertDialog.show();


                                }

                            } /*else {
                                builder.setMessage("ID is incorrect").setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog alertDialog1 = builder.create();
                                alertDialog1.setTitle("Error");
                                alertDialog1.show();

                            }*/
                        }
                        if (number!=1){
                            builder.setMessage("ID is incorrect").setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alertDialog1 = builder.create();
                            alertDialog1.setTitle("Error");
                            alertDialog1.show();

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
