package projectjmi_id.samar.com.jm_id;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ListFragmentMain extends Fragment {

    ListView lv;


    String[] item={"View details","Alter details","Register new gaurd","Register students","Add new Admin","view Existing admins"};


    public ListFragmentMain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list__main, container, false);

        lv=(ListView)v.findViewById(R.id.dynamic);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,android.R.id.text1,item);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemposition = position;

                if (itemposition==4){
                    FragmentManager fragmentManager=getFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    AddAdmin ad=new AddAdmin();
                    fragmentTransaction.replace(R.id.fragment,ad).commit();
                    fragmentTransaction.addToBackStack(null);


                }
                if(itemposition==2){

                    FragmentTransaction fg=getFragmentManager().beginTransaction();
                    Registergaurd rg=new Registergaurd();
                    fg.replace(R.id.fragment,rg).commit();
                    fg.addToBackStack(null);
                }

                if (itemposition==3){

                    FragmentManager fragmentManager=getFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    position3fragment p3f=new position3fragment();
                    fragmentTransaction.replace(R.id.fragment,p3f).commit();
                    fragmentTransaction.addToBackStack(null);

                    //Intent intent =new Intent(Main3Activity.this,Main4Activity.class);
                    //startActivity(intent);
                }
                if (itemposition==0){
                    FragmentTransaction ftr=getFragmentManager().beginTransaction();
                    viewDetails vd=new viewDetails();
                    ftr.replace(R.id.fragment,vd).commit();
                    ftr.addToBackStack(null);
                }

                String itemvalue = (String)lv.getItemAtPosition(position);

                Toast.makeText(getContext(),"position:"+itemposition +" and the item clicked : "+itemvalue,Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }

}
