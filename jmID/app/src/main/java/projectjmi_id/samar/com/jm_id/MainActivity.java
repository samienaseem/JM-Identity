package projectjmi_id.samar.com.jm_id;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.xml.validation.Validator;

public class MainActivity extends AppCompatActivity {


    TextInputEditText tx1,tx2;
    FirebaseAuth mauth;
    Button b1,b2;
    AlertDialog.Builder builder;
    DatabaseReference databaseReference;
    String date;


    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        tx1=(TextInputEditText)findViewById(R.id.Username);
        tx2=(TextInputEditText)findViewById(R.id.passwordAdmin);

        b1=(Button)findViewById(R.id.login);
        b1.setEnabled(false);
        builder=new AlertDialog.Builder(this);


        databaseReference= FirebaseDatabase.getInstance().getReference("Students");

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        Calendar calendar=Calendar.getInstance();
        date=simpleDateFormat.format(calendar.getTime());


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(final DataSnapshot dps:dataSnapshot.getChildren()){
                    Toast.makeText(getApplicationContext(),dps.getKey(),Toast.LENGTH_SHORT).show();

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

                            Toast.makeText(getApplicationContext(),dataSnapshot.getValue()+"000",Toast.LENGTH_SHORT).show();
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
        //b2=(Button)findViewById(R.id.admin);

        CheckForEmpty();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Home.class);
                startActivity(intent);
               // ValidationInput();

            }
        });

    }

    private void CheckForEmpty() {
        tx1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                checkForEmailPassword();
                /*if (t.isEmpty())
                b1.setBackgroundColor(Color.RED);

                else
                    b1.setBackgroundColor(Color.GREEN);*/

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tx2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //s2=tx2.getText().toString();
                checkForEmailPassword();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void checkForEmailPassword() {
        String s1,s2;
        s1=tx1.getText().toString();
        s2=tx2.getText().toString();
        if (s1!=null&&s2.isEmpty()){
            //Toast.makeText(getApplicationContext(),(s1!=null)+"",Toast.LENGTH_SHORT).show();
            b1.setBackgroundColor(Color.RED);
           // s2=tx2.getText().toString();
        }
        else if(!(s1.isEmpty()&&!(s2.isEmpty()))){
            b1.setBackgroundColor(Color.GREEN);
           // Toast.makeText(getApplicationContext(),!(s2.isEmpty())+"s",Toast.LENGTH_SHORT).show();
            b1.setEnabled(true);

        }
        else {
            b1.setBackgroundColor(Color.RED);
        }



    }

    private void ValidationInput() {
        String a=tx1.getText().toString();
        String b=tx2.getText().toString();

        mauth=FirebaseAuth.getInstance();

        mauth.signInWithEmailAndPassword(a,b).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                //Toast.makeText(getApplicationContext(),"successfull",Toast.LENGTH_SHORT).show();
                if(task.isSuccessful()){

                    Intent intent = new Intent(MainActivity.this,Home.class);
                    startActivity(intent);
                    finish();


                }
                else
                {
                    Toast.makeText(getApplicationContext(),"falsee",Toast.LENGTH_SHORT).show();
                    builder.setMessage("Email/Password Does not match").setCancelable(false)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.setTitle("Confirmation Dialog");
                    alertDialog.show();
                }
            }
        });



    }
}
