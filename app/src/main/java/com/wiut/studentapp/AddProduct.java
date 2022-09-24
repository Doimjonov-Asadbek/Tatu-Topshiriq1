package com.wiut.studentapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddProduct extends AppCompatActivity {
    private DBHelper sqlData;
    private ListView list_item;
    ModelListAdapter adapter;
    DrawerLayout drawerLayout;
    String tit1 = "0", product_name, onedata;
    ArrayList<Constructor> dataModalArrayList;
    public static final int PICK_IMAGE = 1;
    ImageView imageView3;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        sqlData = new DBHelper(this);
        list_item = findViewById(R.id.list_item);
        SearchView searchView = findViewById(R.id.usersearchView);
        TextView txtnavvalyuta = findViewById(R.id.txtnavvalyuta);
        TextView txtnavuser = findViewById(R.id.txtnavuser);
        ImageView imageView = findViewById(R.id.imageView);
        ImageView imgval = findViewById(R.id.imgval);
        ImageView imgmenu = findViewById(R.id.imgmenu);
        imageView3 = findViewById(R.id.imageView3);
        drawerLayout = findViewById(R.id.mylayout);

        dataModalArrayList = new ArrayList<>();
        adapter = new ModelListAdapter(dataModalArrayList, this);
        list_item.setDivider(null);
        list_item.setDividerHeight(20);
        SharedPreferences filterdata = this.getSharedPreferences("studentfile.text",
                Context.MODE_PRIVATE);
        product_name = filterdata.getString("txt", "");
        SharedPreferences sharedPreferences1 = this.getSharedPreferences("studentfile.text", Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences1.edit();
        SharedPreferences sharedPreferences = this.getSharedPreferences("studentfile.text", Context.MODE_PRIVATE);
        String tit = sharedPreferences.getString("txt", "");
        onedata = filterdata.getString("one", "");
        setTitle(tit);
        if (onedata.equals("")) {
            setTitle("All Product");
            sqlData.SqlInsert("Laptop", "990700", "0", "2000-01-01", "0", "0", "ALL");
            sqlData.SqlInsert("Book", "990", "0", "2001-01-01", "0", "0", "ALL");
            sqlData.SqlInsert("Phone", "100000", "0", "2002-01-01", "0", "0", "ALL");
            sqlData.SqlInsert("Car", "939970", "0", "2003-01-01", "0", "0", "ALL");
            sqlData.SqlInsert("Burger", "20000", "0", "2004-01-01", "0", "0", "ALL");
            sqlData.SqlInsert("apple", "10000", "0", "2005-01-01", "0", "0", "ALL");
            sqlData.SqlInsert("chery", "50000", "0", "2006-01-01", "0", "0", "ALL");
            sqlData.SqlInsert("Cat", "50000", "0", "2007-01-01", "0", "0", "ALL");
            sqlData.SqlInsert("Home", "98259970", "0", "2008-01-01", "0", "0", "ALL");
            sqlData.SqlInsert("Dog", "20870", "0", "2009-01-01", "0", "0", "ALL");
            editor.putString("one", "1");
            editor.putString("txt", getString(R.string.all));
            product_name = getString(R.string.all);
            editor.apply();
        }
        readsql();
        ccheckdata();

        searchView.setOnCloseListener(() -> {
            searchView.setQuery("", false);
            return false;
        });
        imgmenu.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });
        imageView3.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        });
        txtnavuser.setOnClickListener(v -> startActivity(new Intent(AddProduct.this, User.class)));
        imgval.setOnClickListener(v -> startActivity(new Intent(AddProduct.this, Valyuta.class)));
        txtnavvalyuta.setOnClickListener(v -> startActivity(new Intent(AddProduct.this, Valyuta.class)));
        searchView.setOnClickListener(v -> searchView.setIconified(false));
        searchView.setIconified(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    String text = query.substring(1);
                    adapter.getFilter().filter(text);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    String text = newText.substring(1);
                    adapter.getFilter().filter(text);
                }
                return true;
            }
        });

        imageView.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(AddProduct.this, imageView);
            popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.all:
                        setTitle("All Product");
                        editor.putString("txt", getString(R.string.all));
                        product_name = getString(R.string.all);
                        editor.apply();
                        dataModalArrayList.clear();
                        list_item.setAdapter(null);
                        readsql();
                        break;
                    case R.id.Book:
                        setTitle("Food");
                        editor.putString("txt", getString(R.string.Book));
                        product_name = getString(R.string.Book);
                        editor.apply();
                        dataModalArrayList.clear();
                        list_item.setAdapter(null);
                        readsql();
                        break;
                    case R.id.Furniture:
                        setTitle("Technique");
                        editor.putString("txt", getString(R.string.Furniture));
                        product_name = getString(R.string.Furniture);
                        editor.apply();
                        dataModalArrayList.clear();
                        list_item.setAdapter(null);
                        readsql();
                        break;
                    case R.id.Clothing:
                        setTitle("Clothing");
                        editor.putString("txt", getString(R.string.Clothing));
                        product_name = getString(R.string.Clothing);
                        editor.apply();
                        dataModalArrayList.clear();
                        list_item.setAdapter(null);
                        readsql();
                        break;
                    case R.id.Food:
                        setTitle("Raw Material");
                        editor.putString("txt", getString(R.string.Food));
                        product_name = getString(R.string.Food);
                        editor.apply();
                        dataModalArrayList.clear();
                        list_item.setAdapter(null);
                        readsql();
                        break;
                }
                return true;
            });
            popupMenu.show();
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(v -> {
            Dialog dialog01 = new Dialog(this);
            dialog01.setContentView(R.layout.popupadd);
            dialog01.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog01.setCancelable(true);
            EditText edipopuptitle = dialog01.findViewById(R.id.edipopuptitle);
            EditText edipopupyouword = dialog01.findViewById(R.id.edipopupyouword);
            Button btnpopupsave = dialog01.findViewById(R.id.btnpopupsave);
            Button btnpopupmenu = dialog01.findViewById(R.id.btnpopupmenu);
            TextView edipopupmeaning = dialog01.findViewById(R.id.edipopupmeaning);
            TextView textView3 = dialog01.findViewById(R.id.textView3);
            CheckBox checkBoxpopup = dialog01.findViewById(R.id.checkBoxpopup);
            DatePicker datePicker = dialog01.findViewById(R.id.datePicker);
            ImageView imageView2 = dialog01.findViewById(R.id.imageView2);
            ConstraintLayout conlayout = dialog01.findViewById(R.id.conlayout);

            conlayout.setOnClickListener(v12 -> {
                edipopuptitle.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                edipopupmeaning.setVisibility(View.VISIBLE);
                edipopupyouword.setVisibility(View.VISIBLE);
                btnpopupsave.setVisibility(View.VISIBLE);
                btnpopupmenu.setVisibility(View.VISIBLE);
                checkBoxpopup.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.VISIBLE);
                datePicker.setVisibility(View.INVISIBLE);
            });
            imageView2.setOnClickListener(v12 -> {
                edipopuptitle.setVisibility(View.INVISIBLE);
                textView3.setVisibility(View.INVISIBLE);
                edipopupmeaning.setVisibility(View.INVISIBLE);
                edipopupyouword.setVisibility(View.INVISIBLE);
                btnpopupsave.setVisibility(View.INVISIBLE);
                btnpopupmenu.setVisibility(View.INVISIBLE);
                checkBoxpopup.setVisibility(View.INVISIBLE);
                imageView2.setVisibility(View.INVISIBLE);
                datePicker.setVisibility(View.VISIBLE);
            });
            datePicker.setOnClickListener(vl -> {
                edipopupmeaning.setText(datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear());
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                datePicker.setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) -> {
                    edipopupmeaning.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                });
            }


            btnpopupmenu.setOnClickListener(v1 -> {
                PopupMenu popupMenu = new PopupMenu(this, btnpopupmenu);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.all:
                            btnpopupmenu.setText(getString(R.string.all));
                            break;
                        case R.id.Book:
                            btnpopupmenu.setText(getString(R.string.Book));
                            break;
                        case R.id.Furniture:
                            btnpopupmenu.setText(getString(R.string.Furniture));
                            break;
                        case R.id.Clothing:
                            btnpopupmenu.setText(getString(R.string.Clothing));
                            break;
                        case R.id.Food:
                            btnpopupmenu.setText(getString(R.string.Food));
                            break;
                    }
                    return true;
                });
                popupMenu.show();
            });

            btnpopupsave.setOnClickListener(v1 -> {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd-HH:mm", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());
                String title = edipopuptitle.getText().toString();
                String youword = edipopupyouword.getText().toString();
                String dattaa = edipopupmeaning.getText().toString();
                String wordprice = "0";
                String check = "0";
                String product = btnpopupmenu.getText().toString();
                String notes = "0";
                if (checkBoxpopup.isChecked()) {
                    check = "1";
                } else {
                    check = "0";
                }
                if (title.equals("") || youword.equals("")) {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                } else {
                    if (dattaa.length() == 0){
                        dattaa = currentDateandTime;
                    }
                    boolean con = sqlData.SqlInsert(title, youword, wordprice, dattaa, notes, check, product);
                    if (con == true) {
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                        setTitle("All Product");
                        editor.putString("txt", getString(R.string.all));
                        editor.apply();
                        product_name = getString(R.string.all);
                        dataModalArrayList.clear();
                        list_item.setAdapter(null);
                        readsql();
                        dialog01.dismiss();
                    } else {
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            dialog01.show();
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            imageView3.setImageURI(uri);
        }
    }

    private void readsql() {
        Cursor cursor = sqlData.ReadSql2();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String title = cursor.getString(1);
                String youword = cursor.getString(2);
                String wordprice = cursor.getString(3);
                String meaning = cursor.getString(4);
                String notes = cursor.getString(5);
                String check = cursor.getString(6);
                String product = cursor.getString(7);
                if (product_name.equals("ALL")) {
                    dataModalArrayList.add(new Constructor(id, title, youword, wordprice, meaning, notes, check, product));
                } else {
                    if (product_name.equals(product)) {
                        dataModalArrayList.add(new Constructor(id, title, youword, wordprice, meaning, notes, check, product));
                    }
                }
            }
            list_item.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void ccheckdata() {
        SharedPreferences sharedPreferences2 = this.getSharedPreferences("studentfile1.text", Context.MODE_PRIVATE);
        tit1 = sharedPreferences2.getString("txt1", "");
        if (tit1.equals("")) {
            tit1 = "0";
        }
        if (tit1.equals("1")) {
            tit1 = "0";
            SharedPreferences sharedPreferences1 = this.getSharedPreferences("studentfile1.text", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences1.edit();
            editor.putString("txt1", "0");
            editor.apply();
            dataModalArrayList.clear();
            list_item.setAdapter(null);
            readsql();
            ccheckdata();
        } else {
            new android.os.Handler(Looper.getMainLooper()).postDelayed(this::ccheckdata, 2000);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setTitle("Warning");
        alertdialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertdialog.setMessage("Do you really want to quit the program?");
        alertdialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = alertdialog.create();
        alertdialog.show();
    }
}