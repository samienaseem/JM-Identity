package projectjmi_id.samar.com.jm_id;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GateWise extends Fragment {

    List Gatelist;
    GateList adapter;
    ArrayList arrayList;
    ListView listView;
    DatabaseReference databaseReference;


    public GateWise() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_gate_wise, container, false);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
        Calendar c=Calendar.getInstance();
        String date=simpleDateFormat.format(c.getTime());

        databaseReference= FirebaseDatabase.getInstance().getReference("gateNumber").child(date);
        listView=(ListView)v.findViewById(R.id.gateList1);
        Gatelist=new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dp:dataSnapshot.getChildren()){
                    Toast.makeText(getContext(),"for loop",Toast.LENGTH_SHORT).show();
                       Gatelist.add(dataSnapshot);
                }

                adapter = new GateList(getActivity(), Gatelist);
                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return v;
    }

}
