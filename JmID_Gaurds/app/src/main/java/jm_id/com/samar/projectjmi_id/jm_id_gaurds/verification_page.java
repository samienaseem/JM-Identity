/*
package jm_id.com.samar.projectjmi_id.jm_id_gaurds;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


*/
/**
 * A simple {@link Fragment} subclass.
 *//*

public class verification_page extends Fragment {

    Button b3;
    ImageButton imb1;
    EditText ed1;
    String str="";
    String p;
    int counter;
   DatabaseReference databaseReference;


    public verification_page() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_verification_page, container, false);

        b3=(Button)v.findViewById(R.id.verify);
        ed1=(EditText)v.findViewById(R.id.editText);
        imb1=(ImageButton)v.findViewById(R.id.imageButton);
        databaseReference=FirebaseDatabase.getInstance().getReference("Students");
        */
/*final Activity activity=getActivity();*//*



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



        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p=ed1.getText().toString();
                counter=1;

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dp:dataSnapshot.getChildren()){
                            String i=dp.getKey();
                            //Toast.makeText(getApplicationContext(),i,Toast.LENGTH_LONG).show();
                            if (i.equals(p)){

                                Intent intent=new Intent(getContext(),VerifyStdDetails.class);
                                intent.putExtra("id",p);
                                startActivity(intent);
                                counter=0;
                            }
                        }
                        if (counter==1){
                            Toast.makeText(getContext(),p+" does not exist",Toast.LENGTH_LONG).show();

                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });



        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        Toast.makeText(getContext(), result.getContents(), Toast.LENGTH_SHORT).show();



        if (result!=null) {
            if (result.getContents()==null){
                Toast.makeText(getContext(), result.getContents(), Toast.LENGTH_SHORT).show();
            }
            else{
                str =result.getContents();
                //ed1.setText(str+"");
                Toast.makeText(getContext(), result.getContents(), Toast.LENGTH_SHORT).show();


            }
            // Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();

        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }




}

*/
