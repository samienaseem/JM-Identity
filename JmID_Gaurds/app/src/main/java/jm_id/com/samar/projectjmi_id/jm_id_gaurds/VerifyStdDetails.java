    package jm_id.com.samar.projectjmi_id.jm_id_gaurds;



    import android.content.Intent;
    import android.net.Uri;
    import android.os.Bundle;
    import android.support.annotation.NonNull;
    import android.support.design.widget.BottomSheetBehavior;
    import android.support.v7.app.AppCompatActivity;
    import android.view.View;
    import android.view.Window;
    import android.view.WindowManager;
    import android.widget.ImageView;
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


    public class VerifyStdDetails extends AppCompatActivity {
        DatabaseReference databaseReference;
        StorageReference storageReference;
        TextView ed1,ed2,ed3,ed4,ed5,ed6,ed7,bts;
        String fullanme="";
        ImageView img;
         View bottomSheet;

        String id;

        @Override
        public void onBackPressed() {
            this.finish();
           // startActivity(new Intent(getApplicationContext(),verification_Activity.class));

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            super.onCreate(savedInstanceState);
            getSupportActionBar().hide();
            setContentView(R.layout.activity_verify_std_details);
            //bottomSheet=findViewById(R.id.design_bottom_sheet);
          //  BottomSheetBehavior behavior=BottomSheetBehavior.from(bottomSheet);

            ed1=(TextView) findViewById(R.id.jmi_sid);
           // ed2=(TextView)findViewById(R.id.editText5);
            ed3=(TextView)findViewById(R.id.fullName);
            ed4=(TextView)findViewById(R.id.course_std);
            ed5=(TextView)findViewById(R.id.faculty);
            ed6=(TextView)findViewById(R.id.issue);
            ed7=(TextView)findViewById(R.id.expiry);
            img=(ImageView)findViewById(R.id.imageView);
            bts=(TextView)findViewById(R.id.bottomSheet123);

             id=getIntent().getStringExtra("id");


            databaseReference=FirebaseDatabase.getInstance().getReference("Students").child(id);
            storageReference= FirebaseStorage.getInstance().getReference("Student");








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
                  //  Toast.makeText(getApplicationContext(), std.first_name, Toast.LENGTH_SHORT).show();
                     fullanme=std.first_Name+" "+std.last_Name;

                    ed1.setText(dataSnapshot.getKey());
                   // ed2.setText(std.first_name);
                    ed3.setText(fullanme);
                    ed4.setText(std.course);
                    ed5.setText(std.faculty);
                    ed6.setText(std.issueDate);
                    ed7.setText(std.expiryDate);



                    Picasso.get().load(std.pic).into(img);






                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
