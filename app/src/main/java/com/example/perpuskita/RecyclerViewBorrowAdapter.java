package com.example.perpuskita;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewBorrowAdapter extends RecyclerView.Adapter<RecyclerViewBorrowAdapter.ViewHolder> {

    public List<Books> list;

    public RecyclerViewBorrowAdapter(java.util.List<Books> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_books_borrow, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Books books = list.get(i);

        viewHolder.title.setText(books.getTitle());
        viewHolder.return_date.setText(books.getReturnDate());
        viewHolder.place.setText(books.getPlace());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView return_date;
        TextView place;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title= itemView.findViewById(R.id.title);
            return_date= itemView.findViewById(R.id.return_date);
            place=itemView.findViewById(R.id.place);

        }
    }

}
