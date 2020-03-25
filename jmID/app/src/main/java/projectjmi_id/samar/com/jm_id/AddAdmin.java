package projectjmi_id.samar.com.jm_id;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddAdmin extends Fragment {

    EditText ed1;
    TextInputEditText ed2;
    Button signup;
   // ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    public AddAdmin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_add_admin, container, false);

        firebaseAuth=FirebaseAuth.getInstance();
        ed1=(EditText)v.findViewById(R.id.Username);
        ed2=(TextInputEditText)v.findViewById(R.id.password);
        signup=(Button)v.findViewById(R.id.save);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 final String user=ed1.getText()+"";
                String pass=ed1.getText()+"";

              // progressDialog.setMessage("Registering Admin...");
               // progressDialog.show();



                firebaseAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(getActivity(),new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getContext(),"register success",Toast.LENGTH_LONG).show();
                                   /* ed1.setText(" ");
                                    ed2.setText(" ");*/
                                    FragmentManager fragmentManager=getFragmentManager();
                                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                                    AddAdmin ad=new AddAdmin();
                                    fragmentTransaction.replace(R.id.fragment,ad).commit();
                                }

                                else {
                                    Toast.makeText(getContext(),"failed",Toast.LENGTH_LONG).show();
                                }

                    }
                });
            }
        });

        return v;
    }



}
