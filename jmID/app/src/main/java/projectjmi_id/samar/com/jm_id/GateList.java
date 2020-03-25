package projectjmi_id.samar.com.jm_id;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class GateList extends ArrayAdapter {
    Activity context;
    List gatelist;
    TextView tv1,tv2;
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
        View listViewItem=layoutInflater.inflate(R.layout.gate_lsit, null,true);
        tv1=(TextView)listViewItem.findViewById(R.id.Gatenumbers);
        tv2=(TextView)listViewItem.findViewById(R.id.gateValue);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
        Calendar c=Calendar.getInstance();
        String date=simpleDateFormat.format(c.getTime());
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("gateNumber").child(date);

        //gatelist.get(position);
        Toast.makeText(getContext(),position+"sasasa",Toast.LENGTH_SHORT).show();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataSnapshot dp= (DataSnapshot) gatelist.get(position);


                    tv1.setText(dp.getKey());
                    tv2.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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
        });
        //tv1.setText(gatelist.get(position).toString());


        return  listViewItem;
    }

    public GateList(@NonNull Activity context, List resource) {
        super(context,R.layout.gate_lsit,resource);

        this.context=context;
        this.gatelist=resource;
    }
}
