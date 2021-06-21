package com.example.emedicine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emedicine.databinding.ActivityMainBinding;

import java.util.List;

//-------------------------------로그인 화면--------------------------------------

public class login_act extends AppCompatActivity  {

    private ActivityMainBinding binding;
    private UserProfileDatabase db;

    String Table_name = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_login);

        db = Room.databaseBuilder(this,UserProfileDatabase.class, "userprofile1").allowMainThreadQueries().build();
// -------------------------------> 데이터베이스 테이블 이름

        getSupportActionBar().setTitle("나의 알약 찾기"); // 타이틀 바 이름

        Button btn3 = findViewById(R.id.button5);
        EditText login_id = findViewById(R.id.log_id);


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] logs = new String[1];
                logs[0] = login_id.getText().toString(); // 입력 받은 로그인 아이디 문자열 저장

                if(Table_name.equals(logs[0])==true){// 만약 로그 아이디와 DB 테이블 이름이 일치 시 로그인 됨
                    Toast.makeText(getApplicationContext(), "로그인 되었습니다!",Toast.LENGTH_SHORT).show();

                    //login_act 에서 MainActivity2(마이페이지) 로 이동
                   Intent intent = new Intent(login_act.this, MainActivity2.class);
                    startActivity(intent);//액티비티 이동
                }
                else{
                    logs[0] = null;
                    Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다!",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void init(){
        List<UserProfile1> userProfileList = db.getUserProfileDao().getAll();

        UserProfile1 userProfile2 = new UserProfile1();

        for(UserProfile1 userProfile : userProfileList){
            userProfile2.code = null;
            userProfile2.med_name = null;
            userProfile2.ent_name = null;
            db.getUserProfileDao().insert(userProfile2);
        }
        Toast.makeText(this, "Delete comp!", Toast.LENGTH_SHORT).show();

    }





}
