package com.seoul314.seoul314;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RoomDialog extends Dialog {
    private SeekBar time, personCount;
    private Button btnCreatRoom;
    private EditText title;
    private DatabaseReference reference;
    private int sTime, sPerson;

    public RoomDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.createroomdialog);
        time = findViewById(R.id.createRoomTime);
        personCount = findViewById(R.id.personCount);
        btnCreatRoom= findViewById(R.id.btnCreateRoom);
        title = findViewById(R.id.createRoomTitle);
        reference = FirebaseDatabase.getInstance().getReference("RoomList");

        time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sTime = progress;
                Log.i("test2",sTime+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        personCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sPerson = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

            btnCreatRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (title.getText().toString().equals("")) {
                        Toast.makeText(getContext(), "방 제목을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        int time = 0;
                        int distance = 0;
                        switch (sTime){
                            case 0:
                                time = 30;
                                distance = (sPerson+2) * 2;
                                break;
                            case 1:
                                time = 60;
                                distance = (sPerson+2) * 4;
                                break;
                            case 2:
                                time = 90;
                                distance = (sPerson+2) * 6;
                                break;

                        }
                        List<String> user = new ArrayList<>();
                        user.add(UtilDaoImple.getInstance().getLoginId());
                        WithSeoul with = new WithSeoul(title.getText().toString(),UtilDaoImple.getInstance().getLoginId(),sPerson + 2,distance,time,user,null,null,"waitting",null);
                    reference.push().setValue(with);
                    dismiss();
                    }
                }
            });


    }
}
