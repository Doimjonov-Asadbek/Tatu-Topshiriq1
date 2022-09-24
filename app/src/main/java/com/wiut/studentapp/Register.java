package com.wiut.studentapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regster);

        EditText ediregname = findViewById(R.id.ediregname);
        EditText ediregsurname = findViewById(R.id.ediregsurname);
        EditText ediregphone = findViewById(R.id.ediregphone);
        EditText edireglogin = findViewById(R.id.edireglogin);
        EditText ediregpass = findViewById(R.id.ediregpass);

        Button btnregsave = findViewById(R.id.btnregsave);

        btnregsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ediregname.getText().toString();
                String surname = ediregsurname.getText().toString();
                String phone = ediregphone.getText().toString();
                String login = edireglogin.getText().toString();
                String passs = ediregpass.getText().toString();

                if (name.length() ==0 ){
                    return;
                }
                if (surname.length() == 0){
                    return;
                }
                if (phone.length() == 0){
                    return;
                }
                if (login.length() == 0){
                    return;
                }
                if (passs.length() == 0){
                    return;
                }
                SharedPreferences sharedPreferences1 = Register.this.getSharedPreferences("student.text", Context.MODE_PRIVATE);
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("name", name);
                editor.putString("surname", surname);
                editor.putString("phone", phone);
                editor.putString("login", login);
                editor.putString("passs", passs);
                editor.apply();
                Toast.makeText(Register.this, "Congratulations on your successful registration", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
