package com.example.mainproject0531;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    // MainActivity에서 사용할 변수 선언
    private TextView Text_Date, Text_Time;
    private Button Btn_Tonghak, Btn_Callben;

    // 팝업 화면
    Dialog Select_Popup;

    // 팝업 화면의 버튼을 위한 변수 선언
    private Button Btn_FirstPrintArea, Btn_SecondPrintArea, Btn_ThirdPrintArea, Btn_FourthPrintArea, Btn_FifthPrintArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // activity_main.xml에서 설정된 View들을 가져옴

        // activity_main.xml에서 설정된 View들을 가져와서 정의
        Text_Date = findViewById(R.id.Text_Date);
        Text_Time = findViewById(R.id.Text_Time);

        Btn_Tonghak = findViewById(R.id.Btn_Tonghak);
        Btn_Callben = findViewById(R.id.Btn_Callben);

        // 팝업 화면 정의
        Select_Popup = new Dialog(MainActivity.this); // 팝업 화면을 현재 액티비티(MainActivity)에서 띄우겠다.
        Select_Popup.requestWindowFeature(Window.FEATURE_NO_TITLE); // 팝업 화면의 제목을 지워준다.
        Select_Popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // 팝업 화면의 배경을 투명하게 만들어 준다.
        Select_Popup.setContentView(R.layout.activity_regional_popup); // 팝업 화면의 정보는 activity_regional_popup.xml에서 가져온다.

        // 팝업 화면의 버튼 정의
        Btn_FirstPrintArea = Select_Popup.findViewById(R.id.Btn_FirstPrintArea);
        Btn_SecondPrintArea = Select_Popup.findViewById(R.id.Btn_SecondPrintArea);
        Btn_ThirdPrintArea = Select_Popup.findViewById(R.id.Btn_ThirdPrintArea);
        Btn_FourthPrintArea = Select_Popup.findViewById(R.id.Btn_FourthPrintArea);
        Btn_FifthPrintArea = Select_Popup.findViewById(R.id.Btn_FifthPrintArea);

        // 시간 실시간으로 보여주기 위한 Thread
        new Thread(){
            public void run(){
                while(!Thread.interrupted()){
                    try{
                        Thread.sleep(500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Text_Date.setText(getDate());
                                Text_Time.setText(getTime());
                            }
                        });
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        // 통학 버튼 클릭 시
        Btn_Tonghak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Select_Popup.show(); // 팝업 화면을 보여주겠다

                Btn_FirstPrintArea.setText("등교"); // 첫번째 버튼의 텍스트를 "등교"로 함
                Btn_SecondPrintArea.setText("하교"); // 두번째 버튼의 텍스트를 "하교"로 함
                Btn_ThirdPrintArea.setVisibility(View.GONE); // 세번째 버튼 비활성화
                Btn_FourthPrintArea.setVisibility(View.GONE); // 네번째 버튼 비활성화
                Btn_FifthPrintArea.setVisibility(View.GONE); // 다섯번째 버튼 비활성화

                // 첫번째 버튼(등교) 클릭 시
                Btn_FirstPrintArea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,BoardingPoint.class); // 현재 액티비티(MainActivity)에서 탑승지 액티비티(BoardingPoint)로 연결
                        startActivity(intent); // 탑승지 액티비티(BoardingPoint)를 열어준다.
                        Select_Popup.cancel();
                    }
                });

                // 두번째 버튼(하교) 클릭 시
                Btn_SecondPrintArea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Destination.class); // 현재 액티비티(MainAcitivity)에서 목적지 액티비티(Destination)로 연결
                        startActivity(intent); // 목적지 액티비티(Destination)를 열어준다.
                        Select_Popup.cancel();
                    }
                });
            }
        });
        
        // 콜벤(탑승 모집) 버튼 클릭 시
        Btn_Callben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Select_Popup.show(); // 팝업 화면을 보여주겠다.

                Btn_FirstPrintArea.setText("콜벤 전화번호"); // 첫번째 버튼의 텍스트를 "콜벤 전화번호"로 함
                Btn_SecondPrintArea.setText("합승 모집"); // 두번째 버튼의 텍스트를 "합승 모집"으로 함
                Btn_ThirdPrintArea.setVisibility(View.GONE); // 세번째 버튼 비활성화
                Btn_FourthPrintArea.setVisibility(View.GONE); // 네번째 버튼 비활성화
                Btn_FifthPrintArea.setVisibility(View.GONE); // 다섯번째 버튼 비활성화

                // 첫번째 버튼(콜벤 전화번호) 클릭 시
                Btn_FirstPrintArea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,CallbenNumber.class); // 현재 액티비티(MainActivity)에서 콜벤 전화번호 액티비티(CallbenNumber)로 연결
                        startActivity(intent);
                        Select_Popup.cancel();
                    }
                });

                // 두번째 버튼(합승 모집) 클릭 시
                Btn_SecondPrintArea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, CallbenMain.class);
                        startActivity(intent);
                        Select_Popup.cancel();
                    }
                });
            }
        });
    }

    // 날짜 정보 불러오기
    private String getDate(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년MM월dd일");
        String getDate = dateFormat.format(date);
        return getDate;
    }

    // 시간 정보 불러오기
    private String getTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh시mm분ss초");
        String getTime = dateFormat.format(date);
        return getTime;
    }

}
