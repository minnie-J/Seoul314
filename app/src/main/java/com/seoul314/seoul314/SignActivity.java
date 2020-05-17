package com.seoul314.seoul314;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignActivity extends AppCompatActivity {

    private EditText signId, signPwd, signName;
    private Button btnSignUp;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        signId = findViewById(R.id.signId);
        signPwd = findViewById(R.id.signPwd);
        signName = findViewById(R.id.signName);
        btnSignUp = findViewById(R.id.btnSignUp);
        auth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(signId.getText().toString().equals("") || signPwd.getText().toString().equals("") || signName.getText().toString().equals(""))){
                    auth.createUserWithEmailAndPassword(signId.getText().toString(),signPwd.getText().toString()).addOnCompleteListener(SignActivity.this
                            , new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful()){
                                        User user = new User(signId.getText().toString(),signPwd.getText().toString(),signName.getText().toString(),null,0,null,0,0,0,0,0);
                                        reference.push().setValue(user);
                                        Toast.makeText(SignActivity.this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else{
                                        if(task.getException().toString().indexOf("formatted") != -1){

                                            Toast.makeText(SignActivity.this, "아이디가 이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show();
                                        }else if(task.getException().toString().indexOf("Password") != -1){
                                            Toast.makeText(SignActivity.this, "비밀번호는 6글자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
                                        }else if(task.getException().toString().indexOf("account") != -1){
                                            Toast.makeText(SignActivity.this, "아이디가 존재합니다.", Toast.LENGTH_SHORT).show();
                                        }


                                    }
                                }
                            });
                }else{
                    Toast.makeText(SignActivity.this, "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
