package com.example.voting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Console;

public class DisplaySearch extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore database;
    private FirestoreRecyclerAdapter adapter;
    String name;
    String date;
    Button Back;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_history);

        recyclerView = findViewById(R.id.searchlist);
        Back = findViewById(R.id.goback);
        database = FirebaseFirestore.getInstance();

        adapter = setUpAdapter(database);
        setUpRecyclerView(recyclerView,adapter);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                name= null;
                date = null;
            } else {
                name= extras.getString("STRING");
                date = extras.getString("DATE");
            }
        } else {
            name= (String) savedInstanceState.getSerializable("STRING");
            date= (String) savedInstanceState.getSerializable("DATE");
        }


    }

    private void setUpRecyclerView(RecyclerView rv, FirestoreRecyclerAdapter adapter)
    {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(manager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }

    private FirestoreRecyclerAdapter setUpAdapter(FirebaseFirestore db)
    {
        Query query = db.collection("elections").startAt(name).orderBy(date).limit(10);
        FirestoreRecyclerOptions<ElectionName> options = new FirestoreRecyclerOptions.Builder<ElectionName>()
                .setQuery(query,ElectionName.class)
                .build();

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<ElectionName,SearchViewHolder>(options)
        {
            //For each item in the database connect it to the view
            @Override
            public void onBindViewHolder(SearchViewHolder holder, int position, final ElectionName model)
            {

                holder.nameOfElection.setText(model.name);
                holder.date.setText(model.date);

            }

            @Override
            public SearchViewHolder onCreateViewHolder (ViewGroup group,int i)
            {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.search_entry, group, false);
                return new SearchViewHolder(view);

            }
        };

        return adapter;

    }


    //Method called every time the activity starts.
    @Override
    protected void onStart() {
        super.onStart();
        //Tells the adapter to start listening for changes in the database
        adapter.startListening();
    }

    //Method called every time the activity stops
    @Override
    protected void onStop() {
        super.onStop();
        //Tells the adapter to stop listening since we are not using this activity
        //  anymore. Otherwise the adapter would still exist in the background draining battery
        //  with useful cycles...
        adapter.stopListening();
    }
}
