package com.example.perpuskita;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private Button btnLogout;
    private ImageView settings;
    private FirebaseAuth auth;
    private static final String LOG_TAG = LoginActivity.class.getSimpleName();
    private TextView mName;
    private SharedPreferences myPrefs;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_profile, container, false);
        btnLogout = (Button) v.findViewById(R.id.logout_button);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "LOGOUT BUTTON CLICKED");
                auth.signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });

        myPrefs = getContext().getSharedPreferences("prefID", Context.MODE_PRIVATE);
        boolean check = myPrefs.getBoolean("prefKey", false);


        settings= v.findViewById(R.id.settings);

        settings.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getContext(), SettingsActivity.class));
                    }
                }
        );
        if (!check) {
            FirebaseUser mFirebaseUser = auth.getCurrentUser();
            mName = (TextView) v.findViewById(R.id.name);
            mName.setText(mFirebaseUser.getEmail());
        }
        return v;
    }

}
