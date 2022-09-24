package com.wiut.studentapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class User extends AppCompatActivity {
    String name,surname,email,login,password;
    public static final int PICK_IMAGE = 1;
    ImageView circularImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        circularImageView = findViewById(R.id.imguseruser);
        ImageView imguserlogout = findViewById(R.id.imguserlogout);
        Button btnuseredit = findViewById(R.id.btnuseredit);
        EditText ediusername = findViewById(R.id.ediusername);
        EditText ediusersurname = findViewById(R.id.ediusersurname);
        EditText ediuseremail = findViewById(R.id.ediuseremail);
        TextView txtusernname = findViewById(R.id.txtusernname);

        SharedPreferences data = this.getSharedPreferences("student.text", Context.MODE_PRIVATE);
        name = data.getString("name", "");
        surname = data.getString("surname", "");
        email = data.getString("phone", "");
        login = data.getString("login", "");
        password = data.getString("passs", "");

        txtusernname.setText(name);
        ediusername.setText(name);
        ediusersurname.setText(surname);
        ediuseremail.setText(email);

        circularImageView.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        });
        imguserlogout.setOnClickListener(v -> {
            SharedPreferences sharedPreferences1 = User.this.getSharedPreferences("student.text", Context.MODE_PRIVATE);
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences1.edit();
            editor.putString("name", "");
            editor.putString("surname", "");
            editor.putString("phone", "");
            editor.putString("login", "");
            editor.putString("passs", "");
            editor.apply();
            startActivity(new Intent(User.this, Login.class));
            finish();

        });
        btnuseredit.setOnClickListener(v -> {
            if (btnuseredit.getText().toString().equals(getString(R.string.save))){
                btnuseredit.setText(getString(R.string.edit));
                String name1 = ediusername.getText().toString();
                String surname1 = ediusersurname.getText().toString();
                String email1 = ediuseremail.getText().toString();
                if (name1.length() == 0|| surname1.length() == 0|| email1.length() == 0){
                    ediusername.setError("error");
                    ediusersurname.setError("error");
                    ediuseremail.setError("error");
                    return;
                }

                SharedPreferences sharedPreferences1 = User.this.getSharedPreferences("student.text", Context.MODE_PRIVATE);
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("name", name1);
                editor.putString("surname", surname1);
                editor.putString("phone", email1);
                editor.putString("login", login);
                editor.putString("passs", password);
                editor.apply();
                Toast.makeText(User.this, "updated", Toast.LENGTH_SHORT).show();
            }else {
                btnuseredit.setText(getString(R.string.save));
                ediusername.setEnabled(true);
                ediusersurname.setEnabled(true);
                ediuseremail.setEnabled(true);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            circularImageView.setImageURI(uri);
        }
    }
}
