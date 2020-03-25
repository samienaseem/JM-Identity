package projectjmi_id.samar.com.jm_id;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class UpdateStudent extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9;
    String std;
    DatabaseReference databaseReference;
    ImageView img;

   /* UpdateStudent(student std){
        this.std=std;
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        setTitle("Update/View Details");
        ed1=(EditText)findViewById(R.id.editText4);
        ed2=(EditText)findViewById(R.id.editText5);
        ed3=(EditText)findViewById(R.id.fname);
        ed4=(EditText)findViewById(R.id.CurrentCourse);
        ed5=(EditText)findViewById(R.id.currentfaculty);
        ed6=(EditText)findViewById(R.id.issue);
        ed7=(EditText)findViewById(R.id.expiry);
        ed8=(EditText)findViewById(R.id.Email);
        ed9=(EditText)findViewById(R.id.phone);
        img=(ImageView)findViewById(R.id.imageView);

       std=getIntent().getStringExtra("ID");

       databaseReference= FirebaseDatabase.getInstance().getReference("Students").child(std);

       databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               student stds=dataSnapshot.getValue(student.class);

               ed1.setText(stds.iid);
               ed2.setText(stds.first_Name);
               ed3.setText(stds.last_Name);
               ed4.setText(stds.course);
               ed5.setText(stds.faculty);
               ed6.setText(stds.issueDate);
               ed7.setText(stds.expiryDate);
               ed8.setText(stds.email);
               ed9.setText(stds.number);
               Picasso.get().load(stds.pic).into(img);
           }
           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });





    }
}
