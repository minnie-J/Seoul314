package com.seoul314.seoul314;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextView loginId, loginPwd;
    private Button btnLogin, btnSign;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginId = findViewById(R.id.loginId);
        loginPwd = findViewById(R.id.loginPwd);
        btnLogin = findViewById(R.id.btnLogin);
        btnSign = findViewById(R.id.btnSign);
        auth = FirebaseAuth.getInstance();

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignActivity.class);
                startActivity(intent);

            }
        });
        // GPS 권한 관련
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
                    ,Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissions, 21);
        }

        // 로그인 관련련
       btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(loginId.getText().toString().equals("") || loginPwd.getText().toString().equals(""))){
                    auth.signInWithEmailAndPassword(loginId.getText().toString(),loginPwd.getText().toString()).addOnCompleteListener(LoginActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(LoginActivity.this,MapsActivity.class);
                                        UtilDaoImple.getInstance().setLoginId(loginId.getText().toString());
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        if(task.getException().toString().indexOf("password") != -1){
                                            Toast.makeText(LoginActivity.this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                                        }else if(task.getException().toString().indexOf("no user") != -1){
                                            Toast.makeText(LoginActivity.this, "존재하지 않는 아이디 입니다.", Toast.LENGTH_SHORT).show();
                                        }else if(task.getException().toString().indexOf("email") != -1){
                                            Toast.makeText(LoginActivity.this, "아이디가 이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show();
                                        }
                                        Log.i("test1",task.getException().toString());
                                    }

                                }
                            });
                }else{
                    Toast.makeText(LoginActivity.this, "이메일과 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            checkGps();
        }
    }

    @SuppressLint("MissingPermission")
    private void checkGps() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS 서비스가 꺼져 있습니다. 설정 화면으로 이동 합니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(intent);
        }
    }
}
