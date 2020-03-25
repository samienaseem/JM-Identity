package projectjmi_id.samar.com.jm_id;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class viewDetails extends Fragment {
DatabaseReference databaseReference;
ListView listView;
SearchView searchView;
static boolean flag=true;
    studentList adapter;
String b;
public List<student> studentList;
    public student std;

    public viewDetails() {
        // Required empty public constructor
    }
   @SuppressLint("ValidFragment")
   public viewDetails(SearchView searchView){
        this.searchView=searchView;
    }




    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater=getActivity().getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.barSearch);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);
        super.onCreateOptionsMenu(menu, inflater);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_view_details, container, false);

        databaseReference=FirebaseDatabase.getInstance().getReference("Students");

        listView=(ListView)view.findViewById(R.id.StudentNnID);
        studentList=new ArrayList<>();



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentList.clear();

                /*if (b!=null){

                    databaseReference=FirebaseDatabase.getInstance().getReference("Students").child(b);
                    std=dataSnapshot.getValue(student.class);
                    studentList.add(std);
                }*/
                //else {
                for (DataSnapshot studentsnapshot : dataSnapshot.getChildren()) {
                    std = studentsnapshot.getValue(student.class);
                    studentList.add(std);

                }
            //}


                adapter = new studentList(getActivity(), studentList);
                listView.setAdapter(adapter);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        return view;
    }





    @Override
    public void onStart() {
        super.onStart();
        Bundle b1=getArguments();
        b=b1.getString("query");

        /*if (b!=null&&flag==true){


            Toast.makeText(getContext(),"its okie"+b,Toast.LENGTH_LONG).show();
            flag=false;

        }*/
   //     else {

            /*databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    studentList.clear();

                    if (b!=null){

                        databaseReference=FirebaseDatabase.getInstance().getReference("Students").child(b);
                        std=dataSnapshot.getValue(student.class);
                        studentList.add(std);
                    }
                    else {
                        for (DataSnapshot studentsnapshot : dataSnapshot.getChildren()) {
                            std = studentsnapshot.getValue(student.class);
                            studentList.add(std);

                        }
                    }

                     adapter = new studentList(getActivity(), studentList);
                    listView.setAdapter(adapter);



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });*/
        //}


    }

    @Override
    public void onResume() {
        super.onResume();


    }
}
 /*class samar implements SearchView.OnQueryTextListener {
    SearchView searchView;
    student st;
    samar(SearchView s){

        this.searchView=s;
    }

     public samar(student std) {
        st=std;

     }

     @Override
     public boolean onQueryTextSubmit(String query) {
        searchView.clearFocus();
        *//*if(){

        }*//*
         return false;
     }

     @Override
     public boolean onQueryTextChange(String newText) {
         return false;
     }


 }
*/