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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class BorrowFragment extends Fragment {

    RecyclerView recyclerView;
    private CardView cardDetails;
    private FloatingActionButton btnAdd;
    private TextView date_return;

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
        book.setReturnDate(convertToDate("2019-02-25"));
        book.setPlace("Perpustakaan Pusat ITB");

        ArrayList<Books> listOfBooks = new ArrayList<Books>();
        listOfBooks.add(book);
        listOfBooks.add(book);
        listOfBooks.add(book);
        listOfBooks.add(book);
        listOfBooks.add(book);
        listOfBooks.add(book);
        listOfBooks.add(book);

        Books book2 = new Books();
        book2.setTitle("Kalkulus");
        book2.setReturnDate(convertToDate("2019-03-01"));
        book2.setPlace("Perpustakaan Pusat ITB");
        listOfBooks.add(book2);

        RecyclerViewBorrowAdapter recyclerViewBorrowAdapter= new RecyclerViewBorrowAdapter(listOfBooks, getContext());
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager (getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(recyclerViewBorrowAdapter);

        //menampilkan tanggalnya
//        date_return = (TextView) v.findViewById(R.id.return_date);
//        date_return.setText(dateToString(listOfBooks.get(0)));

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

    public Date convertToDate(String date) {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Date t;
        try {
            t = ft.parse(date);
            return t;
        } catch (ParseException e) {
            System.out.println("Unparseable using " + ft);
            return null;
        }
    }


}
