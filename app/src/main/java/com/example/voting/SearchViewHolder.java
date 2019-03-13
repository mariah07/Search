package com.example.voting;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

class SearchViewHolder extends RecyclerView.ViewHolder {

    public TextView nameOfElection;
    public TextView date;
    public Button goback;

    public SearchViewHolder(View view) {
        super(view);
        nameOfElection = view.findViewById(R.id.name);
        date = view.findViewById(R.id.date);
        goback = view.findViewById(R.id.goback);
    }

}
