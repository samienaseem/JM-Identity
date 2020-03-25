package projectjmi_id.samar.com.jm_id;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.telephony.SmsManager;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.Manifest;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Registergaurd extends Fragment {

    EditText id,fname,lname,phone,email;
    Button add;
     static String d;
    DatabaseReference firebaseDatabase;
    private static final int MY_PERMISSION_REQUEST_SEND_SMS=1;


    public Registergaurd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_registergaurd, container, false);

        id=(EditText)v.findViewById(R.id.gid);
        fname=(EditText)v.findViewById(R.id.gfname);
        lname=(EditText)v.findViewById(R.id.glname);
        phone=(EditText)v.findViewById(R.id.gphone);
        email=(EditText)v.findViewById(R.id.gemail);
        add=(Button)v.findViewById(R.id.gadd);

        firebaseDatabase=FirebaseDatabase.getInstance().getReference();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a=id.getText().toString();
                String b=fname.getText().toString();
                String c=lname.getText().toString();
                 d=phone.getText().toString();
                String e=email.getText().toString();
                writeUser(a,b,c,d,e);
                id.setText("");
                fname.setText("");
                lname.setText("");
                phone.setText("");
                email.setText("");




            }
        });


        return v;
    }

  /*  public void SendSMSMessage(String a) {

        try {
            SmsManager sms=SmsManager.getDefault();
            sms.sendTextMessage("+919716946677",null,"hi",null,null);
            Toast.makeText(getContext(),"send",Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(),"nai hua send",Toast.LENGTH_LONG).show();
        }





    }*/

    private void writeUser(String a, String b, String c, final String d, String e) {

        ADDGaurd ad=new ADDGaurd(b,c,d,e);
        firebaseDatabase.child("GuardDB").child(a).setValue(ad).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(getContext(),"success",Toast.LENGTH_LONG).show();
                    requestPermissions(d);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"Failed",Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        /*super.onRequestPermissionsResult(requestCode, permissions, grantResults);*/
        switch (requestCode){
            case MY_PERMISSION_REQUEST_SEND_SMS:{
                if (grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(d,null,"heello",null,null);
                    Toast.makeText(getContext(),"send ho gya hai",Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(getContext(),"failed hogya",Toast.LENGTH_LONG).show();

                    return;
                }

            }
        }
    }

    public void requestPermissions(String phone) {

        if (ContextCompat.checkSelfPermission(getContext() ,Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED){

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.SEND_SMS )){

            }
            else {
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest .permission.SEND_SMS},MY_PERMISSION_REQUEST_SEND_SMS);
            }


        }
        else{
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phone,null,"hello",null,null );
            Toast.makeText(getContext(),"send hogya",Toast.LENGTH_LONG).show();
        }


    }

}
