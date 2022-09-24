package com.wiut.studentapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {
    String login,password,login1,password1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button btnlogsignup = findViewById(R.id.btnlogsignup);
        Button btnlogsignin = findViewById(R.id.btnlogsignin);
        EditText ediloglogin = findViewById(R.id.ediloglogin);
        EditText edilogpassword = findViewById(R.id.edilogpassword);
        TextView txtlogforgatpass = findViewById(R.id.txtlogforgatpass);

        /*Call<List<Currency>> call = ApiClient.getUserService().userTokenRequest();
        call.enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                if (response.isSuccessful()){
                    List<Currency> currency = response.body();
                    for (Currency cur : currency){
                        Toast.makeText(Login.this, cur.getCcyNm_UZC(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {

            }
        });*/
        txtlogforgatpass.setOnClickListener(v -> startActivity(new Intent(Login.this,ForgatPass.class)));
        btnlogsignup.setOnClickListener(v -> startActivity(new Intent(Login.this,Register.class)));
        btnlogsignin.setOnClickListener(v -> {
            login = ediloglogin.getText().toString().trim();
            password = edilogpassword.getText().toString().trim();
            SharedPreferences data = this.getSharedPreferences("student.text",
                    Context.MODE_PRIVATE);

            login1 = data.getString("login", "");
            password1 = data.getString("passs", "");

            if (login.length() == 0){
                ediloglogin.setError("empty");
                return;
            }
            if (password.length() == 0){
                edilogpassword.setError("empty");
                return;
            }
            if (login.equals(login1)&&password.equals(password1)){
                startActivity(new Intent(Login.this,AddProduct.class));
                finish();
            }else {
                ediloglogin.setError("error");
                edilogpassword.setError("error");
            }
        });
    }
}
