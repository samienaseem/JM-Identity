package projectjmi_id.samar.com.jm_id;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {

    GridLayout gd;



    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_dashboard, container, false);

        gd=(GridLayout)v.findViewById(R.id.grids);

        return v;
    }

}
