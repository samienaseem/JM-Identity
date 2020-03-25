package projectjmi_id.samar.com.jm_id;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StudentViewList extends AppCompatActivity {

    studentList adapter;
    String b;
    public List<student> studentList1;
    public student std;
    DatabaseReference databaseReference;
    ListView listView;
    Activity activity;
    ArrayList arrayList;


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,v.getId(),0,"Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuinfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        if(item.getTitle()=="Delete"){
            student std=studentList1.get(menuinfo.position);
            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Students").child(std.iid);
            databaseReference.removeValue();
            //Toast.makeText(getApplicationContext(),std.iid+"sa",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_list);
        setTitle("List");
        activity=this;

        arrayList=new ArrayList();
        databaseReference= FirebaseDatabase.getInstance().getReference("Students");

        listView=(ListView)findViewById(R.id.StudentNnID1);
        studentList1=new ArrayList<>();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentList1.clear();

                /*if (b!=null){

                    databaseReference=FirebaseDatabase.getInstance().getReference("Students").child(b);
                    std=dataSnapshot.getValue(student.class);
                    studentList.add(std);
                }*/
                //else {
                for (DataSnapshot studentsnapshot : dataSnapshot.getChildren()) {

                    std = studentsnapshot.getValue(student.class);
                    studentList1.add(std);

                    arrayList.add(std.first_Name);
                   // Toast.makeText(getApplicationContext(),studentsnapshot+"",Toast.LENGTH_SHORT).show();

                }
                //}


                adapter = new studentList(activity, studentList1);
                listView.setAdapter(adapter);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Toast.makeText(getApplicationContext(),"CLICK HUA BHAI",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"POS"+position +"  "+"ID"+ id ,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),view + "VIEW",Toast.LENGTH_SHORT).show();*/
                student std=studentList1.get(position);
                Toast.makeText(getApplicationContext(),std.first_Name,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),std.iid,Toast.LENGTH_SHORT).show();
                String newid=std.iid;
                Intent intent=new Intent(getApplicationContext(),UpdateStudent.class);
                intent.putExtra("ID", newid);
                startActivity(intent);




            }
        });

        registerForContextMenu(listView);


        /*listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),"LONGGGGG",Toast.LENGTH_SHORT).show();
                registerForContextMenu(listView);
                return true;
            }
        });*/


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.barSearch);
        final SearchView searchView= (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                searchView.clearFocus();

                if((databaseReference=FirebaseDatabase.getInstance().getReference("Students").child(query))!=null&& Pattern.matches("\\d*",query)){
                      //  Toast.makeText(getApplicationContext(),query+"",Toast.LENGTH_SHORT).show();
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        int a=0;
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot dp:dataSnapshot.getChildren()){
                                if (query.equals(dp.getValue(Boolean.parseBoolean(std.iid)))){
                                    studentList1.clear();
                                    std=dataSnapshot.getValue(student.class);
                                    studentList1.add(std);
                                    a++;
                                }

                                //Toast.makeText(getApplicationContext(),dp.getKey(),Toast.LENGTH_SHORT).show();


                            }
                            if(a==0){
                                Toast.makeText(getApplicationContext(),query + "Not Found11",Toast.LENGTH_SHORT).show();

                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
                else if (Pattern.matches("\\D*",query)){
                    databaseReference=FirebaseDatabase.getInstance().getReference("Students");

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            studentList1.clear();

                            for(DataSnapshot dp:dataSnapshot.getChildren()){
                                std=dp.getValue(student.class);
                                    String abc=std.first_Name;
                                //Toast.makeText(getApplicationContext(), abc+"", Toast.LENGTH_SHORT).show();
                                long i=dp.getChildrenCount();
                                Toast.makeText(getApplicationContext(),i+"",Toast.LENGTH_SHORT).show();
                                    if (query.equals(abc)) {


                                       // std=dataSnapshot.getValue(student.class);
                                        studentList1.add(std);
                                          Toast.makeText(getApplicationContext(),query+"ddvdvdvd",Toast.LENGTH_SHORT).show();

                                    } else {
                                        //  Toast.makeText(getApplicationContext(),"for loop agya",Toast.LENGTH_SHORT).show();

                                    }

                                }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                   // Toast.makeText(getApplicationContext(),query+"",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),query + "Not Found" ,Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


}
