package com.example.emedicine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


import com.example.emedicine.databinding.ActivityMainBinding;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;


import java.io.InputStreamReader;
import java.util.List;

//-------------------------------알약 조회 화면--------------------------------------

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;
    private UserProfileDatabase db;

    ImageView imView;
    String imageUrl ="";

    EditText edit;
    TextView text;

    XmlPullParser xpp;
    String key="neEs%2FUJypuwYcCxOnoD20%2FlCNSWBqldITbxKogLt55ypbQ8EHp5MMNRPcMsyAq7DvkDo9tlqbMGrgWgwXKrgow%3D%3D";
    String data;
    String none =" ";
    String medid = "none";
    String num = null;
    final String[] a = new String[1];
    final String[] b = new String[1];
    final String[] c = new String[1];
    final String[] d = new String[1];
    final String[] e_ = new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//-----------------------------------------------------------------------------
        Intent intent = getIntent(); //데이터 수신

        num = intent.getExtras().getString("code"); // 데이터 수신 받음


        if(num == "0") {

            Toast.makeText(this, "화면 전환!", Toast.LENGTH_SHORT).show();
        }else{

            medid = num;        // 화면 전환으로 넘어온 코드로 알약 조회

            //Toast.makeText(this, medid, Toast.LENGTH_SHORT).show();

            new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    data = getXmlData();//아래 메소드를 호출하여 XML data를 파싱해서 String 객체로 얻어오기

                    //UI Thread(Main Thread)를 제외한 어떤 Thread도 화면을 변경할 수 없기때문에
                    //runOnUiThread()를 이용하여 UI Thread가 TextView 글씨 변경하도록 함
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            // text.setText(data); //TextView에 문자열  data 출력
                        }
                    });
                }
            }).start();
        }


   //---------------------------------------------------------------------------------





        getSupportActionBar().setTitle("알약 검색하기");

        db = Room.databaseBuilder(this,UserProfileDatabase.class, "userprofile1").allowMainThreadQueries().build();
// -------------------------------> 데이터베이스 테이블 이름
       // fetchUserProfileList();

        edit = (EditText) findViewById(R.id.edit);
        text = (TextView) findViewById(R.id.result);

        Button btn1 = (Button)findViewById(R.id.button);
        imView = (ImageView)findViewById(R.id.imgV);
        text.setMovementMethod(new ScrollingMovementMethod());
        Button btn2 = (Button)findViewById(R.id.button1);
        Button btn10 = (Button)findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //검색창에서 검색한 약 아이디 넣기
                text.setText(none);         // 화면 초기화
                medid = edit.getText().toString();
                Toast.makeText(getApplicationContext(), "버튼 클릭 됨",Toast.LENGTH_SHORT).show();
                mOnClick(v);
            }
        }); // 약 검색

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //MainActivity 에서 SecondActivity 로 이동 경로
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                startActivity(intent);//액티비티 이동
            }
        }); // 마이페이지 이동

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(db != null) {
                    addUserProfile(v);

                    Toast.makeText(MainActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        }); // 내 알약 저장

    }
    private void fetchUserProfileList(){
        List<UserProfile1> userProfileList = db.getUserProfileDao().getAll();
        String userListText = "사용자 목록";
        for(UserProfile1 userProfile : userProfileList){
            userListText += "\n" + userProfile.code + "\n" + userProfile.med_name + "\n" + userProfile.ent_name;
        }
        Toast.makeText(this, "here!", Toast.LENGTH_SHORT).show();
        binding.result.setText(userListText);
    }


    public void addUserProfile(View view){
        UserProfile1 userProfile = new UserProfile1();
        userProfile.code = a[0];
        userProfile.med_name = b[0];
        userProfile.ent_name = c[0];
        db.getUserProfileDao().insert(userProfile);
        //fetchUserProfileList();

    }



    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();

        }


    public void mOnClick(View v){
        switch( v.getId() ){
            case R.id.button:
                    //Android 4.0 이상 부터는 네트워크를 이용할 때 반드시 Thread 사용해야 함
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            data = getXmlData();//아래 메소드를 호출하여 XML data를 파싱해서 String 객체로 얻어오기

                            //UI Thread(Main Thread)를 제외한 어떤 Thread도 화면을 변경할 수 없기때문에
                            //runOnUiThread()를 이용하여 UI Thread가 TextView 글씨 변경하도록 함
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                   // text.setText(data); //TextView에 문자열  data 출력
                                }
                            });
                        }
                    }).start();
                break;
        }
    }//mOnClick method..


    String getXmlData(){

        StringBuffer buffer=new StringBuffer();

  //      String str= edit.getText().toString();//EditText에 작성된 Text얻어오기
 //       String location = URLEncoder.encode(str);//한글의 경우 인식이 안되기에 utf-8 방식으로 encoding..
 //       String query = "neEs%2FUJypuwYcCxOnoD20%2FlCNSWBqldITbxKogLt55ypbQ8EHp5MMNRPcMsyAq7DvkDo9tlqbMGrgWgwXKrgow%3D%3D";

        String queryUrl="http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?serviceKey="+ key
                +"&itemSeq="+ medid ;

        try {
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();

            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기

                        if(tag.equals("itemSeq")) // 첫번째 검색결과
                            {
                                xpp.next();
                                if(!xpp.getText().equals(medid)){
                                    buffer.delete(0,buffer.toString().length());
                                    imView.setImageResource(0);//생성된 이미지 제거
                                    buffer.append("찾는 알약이 없습니다.");
                                    return buffer.toString();
                                }
                                else{
                                    buffer.append("약 번호 : ");
                                    buffer.append(xpp.getText());
                                    buffer.append("\n");
                                    text.setText(buffer);
                                    a[0] = xpp.getText();
                                }
                            }
                        else if(tag.equals("itemImage")){ //알약 이미지
                            xpp.next();
                            imageUrl = xpp.getText();
                            new DownloadFilesTask().execute(imageUrl);


                        }
                        else if(tag.equals("itemName")){
                            buffer.append("알약 이름 : ");
                           xpp.next();
                            buffer.append(stripHtml(stripHtml(xpp.getText())));
                            buffer.append("\n");
                            text.setText(buffer);
                            b[0] = xpp.getText();
                        }
                        else if(tag.equals("entpName")){
                            buffer.append("제조사 : ");
                            xpp.next();
                            buffer.append(stripHtml(stripHtml(xpp.getText())));
                            buffer.append("\n");
                            text.setText(buffer);
                            c[0] = xpp.getText();
                        }

                        else if(tag.equals("efcyQesitm")){
                            buffer.append("약의 효능 : ");
                            xpp.next();
                            buffer.append(stripHtml(xpp.getText()));
                            buffer.append("\n");
                            text.setText(buffer);
                            d[0] = xpp.getText();
                        }
                        else if(tag.equals("useMethodQesitm")){
                            buffer.append("약 복용법 : ");
                            xpp.next();
                            buffer.append(stripHtml(xpp.getText()));
                            buffer.append("\n");
                            text.setText(buffer);
                            e_[0] = xpp.getText();
                        }



                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기

                        if(tag.equals("itemSeq")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;
                }

                eventType= xpp.next();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch blocke.printStackTrace();
        }
        medid = "none";
        return buffer.toString();//StringBuffer 문자열 객체 반환

    }//getXmlData method....

    class DownloadFilesTask extends AsyncTask<String,Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null;
            try {
                String img_url = strings[0]; //url of the image
                URL url = new URL(img_url);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            // doInBackground 에서 받아온 total 값 사용 장소
            imView.setImageBitmap(result);
        }
    }


@Override
protected void onDestroy() {

    super.onDestroy();
    }
}