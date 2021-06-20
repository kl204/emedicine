package com.example.emedicine;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements MyAdapter.ListItemClickListener{

    private RecyclerView recyclerView;
    private List<Person> personList = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for(int i = 0; i < 20; i++){
            personList.add(new Person("name"+i, 20 + i));
        }

        adapter = new MyAdapter(personList, this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void listItemClick(int position) {

        Toast.makeText(this, personList.get(position).getName() + "\n" +
                personList.get(position).getAge(), Toast.LENGTH_SHORT).show();
    }
}