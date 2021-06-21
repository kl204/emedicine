package com.example.emedicine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.emedicine.databinding.ActivityMainBinding;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements MyAdapter.ListItemClickListener{

    private ActivityMainBinding binding;
    private UserProfileDatabase db;

    private RecyclerView recyclerView;
    private List<Person> personList = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setTitle("마이페이지");

        db = Room.databaseBuilder(this,UserProfileDatabase.class, "userprofile1").allowMainThreadQueries().build();
// -------------------------------> 데이터베이스 테이블 이름
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btn3 = findViewById(R.id.button3);
        ImageButton btn4 = findViewById(R.id.imageButton3);
        Button btn5 = findViewById(R.id.button4);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivity2 에서 MainActivity 로 이동 경로
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);//액티비티 이동
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivity2 에서 MainActivity 로 이동 경로
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);

                startActivity(intent);//액티비티 이동
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivity2 에서 MainActivity 로 이동 경로
                Intent intent = new Intent(MainActivity2.this, login_act.class);

                startActivity(intent);//액티비티 이동
            }
        });

        if(db != null) {
          fetchUserProfileList();
        }
        else{
            Toast.makeText(this, "등록된 약이 없습니다!", Toast.LENGTH_SHORT).show();
        }
        adapter = new MyAdapter(personList, this);
        recyclerView.setAdapter(adapter);


    }


    private void fetchUserProfileList(){
        List<UserProfile1> userProfileList = db.getUserProfileDao().getAll();
        for(UserProfile1 userProfile : userProfileList){
            personList.add(new Person(userProfile.code, userProfile.med_name, userProfile.ent_name));
        }

    }




    @Override
    public void listItemClick(int position) {

        Toast.makeText(this, personList.get(position).getCode() + "\n" +
                personList.get(position).getMed_name(), Toast.LENGTH_SHORT).show();
    }
}