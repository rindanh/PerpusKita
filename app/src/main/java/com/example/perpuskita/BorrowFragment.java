package com.example.perpuskita;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
                        startActivity(new Intent(getContext(), QRCodeActivity.class));
                    }
                }
        );
        return v;
    }

}
