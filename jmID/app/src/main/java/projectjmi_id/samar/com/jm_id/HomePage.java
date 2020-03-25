package projectjmi_id.samar.com.jm_id;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePage extends Fragment {



    TextView tf,counter;
    Button button;
    DatabaseReference databaseReference;
    String date1;
    long a;

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(),"resume",Toast.LENGTH_SHORT).show();
        counter.setText(a+"");

    }


    public HomePage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home_page, container, false);
        tf=(TextView)v.findViewById(R.id.datehai);
        counter=(TextView)v.findViewById(R.id.count);
        //button=(Button)v.findViewById(R.id.listStudent);


        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar=Calendar.getInstance();
        String date=simpleDateFormat.format(calendar.getTime());

        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar1=Calendar.getInstance();
        date1=simpleDateFormat1.format(calendar1.getTime());




        databaseReference= FirebaseDatabase.getInstance().getReference("gateNumber").child(date1);

        tf.setText(date);

        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dp:dataSnapshot.getChildren()){

                   // Toast.makeText(getContext(),dp.getValue()+"sam",Toast.LENGTH_SHORT).show();
                    a+= (long) Integer.parseInt(dp.getValue().toString());




                }
               // Toast.makeText(getContext(),a+"sam",Toast.LENGTH_SHORT).show();
                counter.setText(a+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return v;
    }

}
