package com.wiut.studentapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ForgatPass extends AppCompatActivity {
    String name1, surname1, phone1, login1, passs1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgatpass);
        EditText ediforemail = findViewById(R.id.ediforemail);
        EditText ediforlogin = findViewById(R.id.ediforlogin);
        EditText ediforpass1 = findViewById(R.id.ediforpass1);
        EditText ediforpass2 = findViewById(R.id.ediforpass2);
        Button btnforsave = findViewById(R.id.btnforsave);

        SharedPreferences data = this.getSharedPreferences("student.text",
                Context.MODE_PRIVATE);
        btnforsave.setOnClickListener(v -> {
            name1 = data.getString("name", "");
            surname1 = data.getString("surname", "");
            phone1 = data.getString("phone", "");
            login1 = data.getString("login", "");
            passs1 = data.getString("passs", "");

            String email = ediforemail.getText().toString();
            String login = ediforlogin.getText().toString();
            String pass1 = ediforpass1.getText().toString();
            String pass2 = ediforpass2.getText().toString();
            if (email.isEmpty() || login.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
                ediforemail.setError("empty");
                ediforlogin.setError("empty");
                ediforpass1.setError("empty");
                ediforpass2.setError("empty");
            } else if (!pass1.equals(pass2)) {
                ediforpass1.setError("error");
                ediforpass2.setError("error");
            } else {
                ediforemail.setError(null);
                ediforlogin.setError(null);
                ediforpass1.setError(null);
                ediforpass2.setError(null);
                if (email.equals(phone1)){
                    SharedPreferences sharedPreferences1 = ForgatPass.this.getSharedPreferences("student.text", Context.MODE_PRIVATE);
                    @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putString("name", name1);
                    editor.putString("surname", surname1);
                    editor.putString("phone", phone1);
                    editor.putString("login", login);
                    editor.putString("passs", pass1);
                    editor.apply();
                    Toast.makeText(ForgatPass.this, "password updated", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
