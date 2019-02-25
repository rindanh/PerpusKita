package com.example.perpuskita;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BooksFragment extends Fragment {

    SearchView search;
    RecyclerView recyclerView;
    private FloatingActionButton focusButton;


    public BooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_books, container, false);
        recyclerView = v.findViewById(R.id.recyclerview);
        Books book = new Books();
        book.setTitle("Totto-chan");
        book.setDetails("Menceritakan seorang anak bernama totto");
        book.setPlace("Perpustakaan Pusat ITB");

        ArrayList<Books> listOfBooks = new ArrayList<Books>();
        listOfBooks.add(book);
        listOfBooks.add(book);
        listOfBooks.add(book);
        listOfBooks.add(book);
        listOfBooks.add(book);
        listOfBooks.add(book);
        listOfBooks.add(book);

        RecyclerViewBooksAdapter recyclerViewBooksAdapter= new RecyclerViewBooksAdapter(listOfBooks);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager (getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(recyclerViewBooksAdapter);

        focusButton = (FloatingActionButton) v.findViewById(R.id.fab);
        focusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FocusActivity.class));
            }
        });

        return v;

    }

}
