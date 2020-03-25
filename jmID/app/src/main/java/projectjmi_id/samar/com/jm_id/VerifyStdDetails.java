package projectjmi_id.samar.com.jm_id;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VerifyStdDetails extends AppCompatActivity {
    DatabaseReference databaseReference;
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_std_details);

        ed1=(EditText)findViewById(R.id.editText4);
        ed2=(EditText)findViewById(R.id.editText5);
        ed3=(EditText)findViewById(R.id.fname);
        ed4=(EditText)findViewById(R.id.CurrentCourse);
        ed5=(EditText)findViewById(R.id.currentfaculty);
        ed6=(EditText)findViewById(R.id.issue);
        ed7=(EditText)findViewById(R.id.expiry);
        /*Bundle b=getIntent().getExtras();
         String id =b.getString("id");*/
      String id=getIntent().getStringExtra("id");
        //Toast.makeText(getApplicationContext(), "id"+id, Toast.LENGTH_SHORT).show();

        databaseReference=FirebaseDatabase.getInstance().getReference("Students").child(id);





    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //
                // Toast.makeText(getApplicationContext(), "value "+dataSnapshot, Toast.LENGTH_SHORT).show();

                student std=dataSnapshot.getValue(student.class);
                Toast.makeText(getApplicationContext(), std.first_Name, Toast.LENGTH_SHORT).show();

                ed1.setText(dataSnapshot.getKey());
                ed2.setText(std.first_Name);
                ed3.setText(std.last_Name);
                ed4.setText(std.course);
                ed5.setText(std.faculty);
                ed6.setText(std.issueDate);
                ed7.setText(std.expiryDate);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
