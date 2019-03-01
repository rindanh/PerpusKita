package com.example.perpuskita;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewBooksAdapter extends RecyclerView.Adapter<RecyclerViewBooksAdapter.ViewHolder> {

    public List<Book> list;

    public RecyclerViewBooksAdapter(List<Book> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_books_books, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Book books = list.get(i);

        viewHolder.title.setText(books.getName());
        viewHolder.details.setText(books.getName());
        viewHolder.place.setText(books.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView details;
        TextView place;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title= itemView.findViewById(R.id.title);
            details= itemView.findViewById(R.id.details);
            place=itemView.findViewById(R.id.place);

        }
    }

}
