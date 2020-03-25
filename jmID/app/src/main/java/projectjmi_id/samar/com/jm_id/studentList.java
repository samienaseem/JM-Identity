package projectjmi_id.samar.com.jm_id;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static java.sql.DriverManager.println;

public class studentList extends ArrayAdapter<student> {
    private Activity context;
    public List<student> studentList;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    public student std;
    String date;
   // int id[]={0};

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();

        View listViewItem=layoutInflater.inflate(R.layout.student_list_layout, null,true);
        databaseReference=FirebaseDatabase.getInstance().getReference("Students");
        storageReference= FirebaseStorage.getInstance().getReference("Student");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        Calendar calendar=Calendar.getInstance();
        date=simpleDateFormat.format(calendar.getTime());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               // for (DataSnapshot dp:dataSnapshot.getChildren())
                 //id[position]=Integer.parseInt(dp.getKey());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        TextView tc=(TextView)listViewItem.findViewById(R.id.Namestd);
        TextView tc2=(TextView)listViewItem.findViewById(R.id.idStd);
        ImageView imgg=(ImageView)listViewItem.findViewById(R.id.imageshow1);
        final TextView tc3=(TextView)listViewItem.findViewById(R.id.dotbox);

         std = studentList.get(position);
         DatabaseReference dp=FirebaseDatabase.getInstance().getReference("ActiveStudents").child(date).child(std.iid).child("Active");
         dp.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 //String abc=dataSnapshot.getValue()+"";
                 Toast.makeText(getContext(),dataSnapshot.getValue()+"",Toast.LENGTH_SHORT).show();
                        String a=dataSnapshot.getValue().toString();
                 if (a.equals("1")){
                     Toast.makeText(getContext(),"color",Toast.LENGTH_SHORT).show();
                     tc3.setBackgroundColor(Color.GREEN);
                 }
                 else{
                     tc3.setBackgroundColor(Color.RED);
                 }

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });


        tc.setText(std.first_Name);
        tc2.setText(std.iid);
        Picasso.get().load(std.pic).into(imgg);
        /*String s=std.Active;
        if(s.equals("1")){
            tc3.setBackgroundColor(5);
        }*/


        //new samar(std,context);

        return listViewItem;
    }

    public studentList(Activity context, List<student> studentList){
        super(context,R.layout.student_list_layout,studentList);
        this.context=context;
        this.studentList=studentList;
        //new samar(studentList);

    }

}


