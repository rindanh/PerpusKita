package com.example.perpuskita;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class RecyclerViewBorrowAdapter extends RecyclerView.Adapter<RecyclerViewBorrowAdapter.ViewHolder> {

    public List<Books> list;
    private Context context;

    public RecyclerViewBorrowAdapter(List<Books> list, Context context) {
        this.list = list;
        this.context = context;
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

        viewHolder.btnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Sharing to twitter..", Toast.LENGTH_SHORT).show();
                String str = "Aku telah meminjam " + books.getTitle() + " dari " + books.getPlace() + ". Ayo membaca!";
                shareTwitter(str);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView return_date;
        TextView place;
        Button btnTwitter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title= itemView.findViewById(R.id.title);
            return_date= itemView.findViewById(R.id.return_date);
            place=itemView.findViewById(R.id.place);
            btnTwitter = itemView.findViewById(R.id.btn_twitter);

        }
    }

    private void shareTwitter(String message) {
        Intent tweetIntent = new Intent(Intent.ACTION_SEND);
        tweetIntent.putExtra(Intent.EXTRA_TEXT, message);
        tweetIntent.setType("text/plain");

        PackageManager packManager = context.getPackageManager();
        List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);

        boolean resolved = false;
        for (ResolveInfo resolveInfo : resolvedInfoList) {
            if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
                tweetIntent.setClassName(
                        resolveInfo.activityInfo.packageName,
                        resolveInfo.activityInfo.name);
                resolved = true;
                break;
            }
        }
        if (resolved) {
            context.startActivity(tweetIntent);
        } else {
            Intent i = new Intent();
            i.putExtra(Intent.EXTRA_TEXT, message);
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://twitter.com/intent/tweet?text=" + urlEncode(message)));
            context.startActivity(i);
            Toast.makeText(context, "Twitter app isn't found", Toast.LENGTH_LONG).show();
        }
    }

    private String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.wtf("AdapterBorrow", "UTF-8 should always be supported", e);
            return "";
        }
    }

}
