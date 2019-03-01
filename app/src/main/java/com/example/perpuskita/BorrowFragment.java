package com.example.perpuskita;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BorrowFragment extends Fragment {

    RecyclerView recyclerView;
    private CardView cardDetails;
    private FloatingActionButton btnAdd;

    public BorrowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_borrow, container, false);
        recyclerView = v.findViewById(R.id.recyclerview);

        Books book = new Books();
        book.setTitle("Totto-chan");
        book.setReturnDate("25 Februari 2019");
        book.setPlace("Perpustakaan Pusat ITB");

        ArrayList<Books> listOfBooks = new ArrayList<Books>();
        listOfBooks.add(book);
        listOfBooks.add(book);
        listOfBooks.add(book);
        listOfBooks.add(book);
        listOfBooks.add(book);
        listOfBooks.add(book);
        listOfBooks.add(book);

        RecyclerViewBorrowAdapter recyclerViewBorrowAdapter= new RecyclerViewBorrowAdapter(listOfBooks, getContext());
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager (getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(recyclerViewBorrowAdapter);

        btnAdd= v.findViewById(R.id.add);
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkGPStoBorrow();
//                        startActivity(new Intent(getContext(), QRCodeActivity.class));
                    }
                }
        );
        return v;
    }

    public void checkGPStoBorrow(){
        final LocationManager manager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        if (manager == null) return;

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            final AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity());
            final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
            final String message = "Enable either GPS or any other location"
                    + " service to find current location.  Click OK to go to"
                    + " location services settings to let you do so.";


            builder.setMessage(message)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface d, int id) {
                                    getActivity().startActivity(new Intent(action));
                                    d.dismiss();
                                }
                            });

            builder.create().show();
        } else borrow();
    }

    public  void borrow(){
        FusedLocationProviderClient fusedLocationProviderClient;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        if (ActivityCompat.checkSelfPermission(
                getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return ;

        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            // Logic to handle location object
                            Log.d("LocationPresenter", "Latitude: " + location.getLatitude());
                            Log.d("LocationPresenter", "Longitude: " + location.getLongitude());

                            if (location.getLatitude()>= -6.888004 && location.getLatitude()<= -6.888674) {
                                if (location.getLongitude()>=107.610785 && location.getLongitude()<= 107.611489 ){
                                    startActivity(new Intent(getContext(), QRCodeActivity.class));

                                }
                            }else{
                                Toast.makeText(getContext(), "Anda sedang tidak berada di perpustakaan pusat ITB", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d("LocationPresenter", "onSuccess: Location null");
                            // Create a Uri from an intent string. Use the result to create an Intent.
                            Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");

                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            mapIntent.setPackage("com.google.android.apps.maps");

                            Toast.makeText(getContext(), "Opening Maps! for calulate LatLng from rama", Toast.LENGTH_SHORT).show();

                            getContext().startActivity(mapIntent);
                        }
                    }
                });
    }

}
