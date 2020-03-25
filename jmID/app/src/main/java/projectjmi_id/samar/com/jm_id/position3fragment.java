package projectjmi_id.samar.com.jm_id;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;


public class position3fragment extends Fragment {

    private static final int CAMERA_REQUEST =1888 ;
    private static final int PICK_IMAGE = 1234;
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9;

    //String Act;
private DatabaseReference databaseReference;
StorageReference storageReference;

ContentResolver c;
Uri filepath;
ImageView img;
AlertDialog.Builder builder;
String a,u,b,ci,d,e,f,g,h,i,date;
    public static final int READ_STORAGE_PERMISSION_REQUEST_CODE=100;

Button register;
    public position3fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_position3fragment, container, false);

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        Calendar calendar=Calendar.getInstance();
        date=simpleDateFormat.format(calendar.getTime());

        databaseReference=FirebaseDatabase.getInstance().getReference();
        c=getContext().getContentResolver();

        ed1=(EditText)v.findViewById(R.id.editText4);
        ed2=(EditText)v.findViewById(R.id.editText5);
        ed3=(EditText)v.findViewById(R.id.fname);
        ed4=(EditText)v.findViewById(R.id.CurrentCourse);
        ed5=(EditText)v.findViewById(R.id.currentfaculty);
        ed6=(EditText)v.findViewById(R.id.issue);
        ed7=(EditText)v.findViewById(R.id.expiry);
        ed8=(EditText)v.findViewById(R.id.Email);
        ed9=(EditText)v.findViewById(R.id.phone);

        register=(Button)v.findViewById(R.id.sregister);
        img=(ImageView)v.findViewById(R.id.imageView);
        storageReference= FirebaseStorage.getInstance().getReference();

        builder=new AlertDialog.Builder(getContext());


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 a=ed1.getText().toString();
                /*String b=ed2.getText().toString();
                String c=ed3.getText().toString();
                String d=ed4.getText().toString();
                String e=ed5.getText().toString();
                String f=ed6.getText().toString();
                String g=ed7.getText().toString();
                String h=ed8.getText().toString();
                String i=ed9.getText().toString();*/

                b=ed2.getText().toString();
                 ci=ed3.getText().toString();
                 d=ed4.getText().toString();
                 e=ed5.getText().toString();
                 f=ed6.getText().toString();
                 g=ed7.getText().toString();
                 h=ed8.getText().toString();
                 i=ed9.getText().toString();

                imageUpload();
                //WriteUser(a,b,c,d,e,f,g,h,i,u);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setMessage("select to open").setCancelable(true)
                        .setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent cameraintent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraintent,CAMERA_REQUEST);
                            }
                        }).setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent().setType("image/*")
                                .setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"Select picture"),PICK_IMAGE);
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.setTitle("Select an option");
                alertDialog.show();

            }
        });




        return v;


    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Toast.makeText(getContext(),"okie",Toast.LENGTH_LONG).show();


    }

    private void requestPermissionForExternalStorage()
    {
        try {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_STORAGE_PERMISSION_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    private boolean checkPermissionForReadExtertalStorage() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        }
        return false;

    }

    private void imageUpload() {

        boolean t= checkPermissionForReadExtertalStorage();
        Toast.makeText(getContext(),t+"",Toast.LENGTH_LONG).show();
        if (t==false){

            requestPermissionForExternalStorage();

        }

        if (filepath != null) {

            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("uploading...");
            progressDialog.show();

            final StorageReference ref = storageReference.child("Student/"+ UUID.randomUUID().toString());
            try {
                        /*ref.putFile(filepath);
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                progressDialog.dismiss();
                                Upload u= new Upload(uri);
                                mdatabase.child("imagees").child("Image" + i).setValue(u);//.addOnCompleteListener();
                            }
                        })*/
                ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();

                        Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_LONG).show();
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                new student(uri.toString());
                                 u = uri.toString();
                                Toast.makeText(getContext(), ref.getDownloadUrl()+"", Toast.LENGTH_LONG).show();

                                Toast.makeText(getContext(),"url wala code chal gya",Toast.LENGTH_LONG).show();
                                WriteUser(a,b,ci,d,e,f,g,h,i,u);


                                //databaseReference.child("Students").child(a).setValue(uri);//.addOnCompleteListener();



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getContext(), "agya error", Toast.LENGTH_LONG).show();
                            }
                        });
                        //Uri uri=taskSnapshot.getDownloadUrl();

                    }
                })
                        .addOnFailureListener(new OnFailureListener () {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(getContext(), "failed", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progressDialog.setMessage("uploaded" + " " + (int) progress + "%");
                    }
                });

            }
            catch (Exception e){
                e.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(getContext(),"ni hua",Toast.LENGTH_LONG).show();

            }
        }
    }

    private void WriteUser(String a, String b, String c, String d, String e, String f, String g,String h,String i,String u) {


            student s=new student(a,b,c,d,e,f,g,h,i,u);
            databaseReference.child("Students").child(a).setValue(s);
            DatabaseReference dq=FirebaseDatabase.getInstance().getReference("ActiveStudents").child(date).child(a).child("Active");
            dq.setValue(0);
            /*dq.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    //dataSnapshot.
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
*/
        /*FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment,new position3fragment()).commit();*/
        Toast.makeText(getContext(),"Register success",Toast.LENGTH_LONG).show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==CAMERA_REQUEST){




            try {
                filepath=data.getData();
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                // Bitmap photo1=MediaStore.Images.Media.getContentUri();

                img.setImageBitmap(photo);
                /*filepath=data.getData();*/
                Toast.makeText(getContext(), filepath+"", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                e.printStackTrace();

            }

        }
        else if (requestCode==PICK_IMAGE){
            filepath=data.getData();
            Toast.makeText(getContext(), filepath+"", Toast.LENGTH_SHORT).show();

            try{
                Bitmap pic=MediaStore.Images.Media.getBitmap(c,filepath);
                img.setImageBitmap(pic);
            }
            catch (IOException e){
                e.printStackTrace();

            }
        }

    }



}
