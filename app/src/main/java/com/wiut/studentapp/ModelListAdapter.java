package com.wiut.studentapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ModelListAdapter extends BaseAdapter implements Filterable {

    private final List<Constructor> itemsModelsl;
    private List<Constructor> itemsModelListFiltered;
    private final Context context;
    private DBHelper sqlData;

    public ModelListAdapter(List<Constructor> itemsModelsl, Context context) {
        this.itemsModelsl = itemsModelsl;
        this.itemsModelListFiltered = itemsModelsl;
        this.context = context;
    }
    @Override
    public int getCount() {
        return itemsModelListFiltered.size();
    }
    @Override
    public Object getItem(int position) {
        return itemsModelListFiltered.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @SuppressLint({"SetTextI18n", "NewApi", "ClickableViewAccessibility"})
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        }

        Constructor dataModal = (Constructor) getItem(position);
        SharedPreferences sharedPreferences = context.getSharedPreferences("studentfile.text",
                Context.MODE_PRIVATE);
        String qwasar = sharedPreferences.getString("txt", "");
        sqlData = new DBHelper(context);
        String product  = dataModal.getProduct();
        TextView txtitmtitle = listitemView.findViewById(R.id.txtitmtitle);
        TextView txtitmyouword = listitemView.findViewById(R.id.txtitmyouword);
        View view6 = listitemView.findViewById(R.id.view6);
        Button btnitemedit = listitemView.findViewById(R.id.btnitemedit);
        Button btnitemdelete = listitemView.findViewById(R.id.btnitemdelete);
        ConstraintLayout item_layout = listitemView.findViewById(R.id.item_layout);
        txtitmyouword.setText(dataModal.getYouword());
        txtitmtitle.setText(dataModal.getTitle());
        /*if (qwasar.equals("ALL")){
            txtitmyouword.setText(dataModal.getYouword());
            txtitmtitle.setText(dataModal.getTitle());
        }else {
            if (qwasar.equals(product)){
                txtitmyouword.setText(dataModal.getYouword());
                txtitmtitle.setText(dataModal.getTitle());
            }else {
                txtitmtitle.setVisibility(View.GONE);
                txtitmyouword.setVisibility(View.GONE);
                view6.setVisibility(View.GONE);
                btnitemedit.setVisibility(View.GONE);
                btnitemdelete.setVisibility(View.GONE);
                item_layout.setVisibility(View.GONE);
            }
        }*/
        btnitemdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlData.DeleteData(itemsModelListFiltered.get(position).getId());
                itemsModelListFiltered.remove(position);
                notifyDataSetChanged();
            }
        });
        btnitemedit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                Dialog dialog01 = new Dialog(context);
                dialog01.setContentView(R.layout.popupadd);
                dialog01.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog01.setCancelable(true);
                EditText edipopuptitle = dialog01.findViewById(R.id.edipopuptitle);
                EditText edipopupyouword = dialog01.findViewById(R.id.edipopupyouword);
                TextView edipopupmeaning = dialog01.findViewById(R.id.edipopupmeaning);
                Button btnpopupsave = dialog01.findViewById(R.id.btnpopupsave);
                Button btnpopupmenu = dialog01.findViewById(R.id.btnpopupmenu);
                CheckBox checkBoxpopup = dialog01.findViewById(R.id.checkBoxpopup);
                DatePicker datePicker = dialog01.findViewById(R.id.datePicker);
                ImageView imageView2 = dialog01.findViewById(R.id.imageView2);
                TextView textView3 = dialog01.findViewById(R.id.textView3);
                ConstraintLayout conlayout = dialog01.findViewById(R.id.conlayout);

                edipopuptitle.setText(dataModal.getTitle());
                edipopupyouword.setText(dataModal.getYouword());
                edipopupmeaning.setText(dataModal.getMeaning());
                btnpopupmenu.setText(qwasar);
                String check1 = dataModal.getCheck();

                SharedPreferences sharedPreferences1 = context.getSharedPreferences("studentfile1.text", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();

                checkBoxpopup.setChecked(check1.equals("1"));
                conlayout.setOnClickListener(v12 -> {
                    textView3.setVisibility(View.VISIBLE);
                    edipopuptitle.setVisibility(View.VISIBLE);
                    edipopupyouword.setVisibility(View.VISIBLE);
                    edipopupmeaning.setVisibility(View.VISIBLE);
                    btnpopupsave.setVisibility(View.VISIBLE);
                    btnpopupmenu.setVisibility(View.VISIBLE);
                    checkBoxpopup.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                    datePicker.setVisibility(View.INVISIBLE);
                });
                imageView2.setOnClickListener(v13 -> {
                    textView3.setVisibility(View.INVISIBLE);
                    edipopuptitle.setVisibility(View.INVISIBLE);
                    edipopupyouword.setVisibility(View.INVISIBLE);
                    edipopupmeaning.setVisibility(View.INVISIBLE);
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
                    PopupMenu popupMenu = new PopupMenu(context, btnpopupmenu);
                    popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(item -> {
                        switch (item.getItemId()) {
                            case R.id.all:
                                btnpopupmenu.setText(context.getString(R.string.all));
                                break;
                            case R.id.Book:
                                btnpopupmenu.setText(context.getString(R.string.Book));
                                break;
                            case R.id.Furniture:
                                btnpopupmenu.setText(context.getString(R.string.Furniture));
                                break;
                            case R.id.Clothing:
                                btnpopupmenu.setText(context.getString(R.string.Clothing));
                                break;
                            case R.id.Food:
                                btnpopupmenu.setText(context.getString(R.string.Food));
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
                    String meaning = edipopupmeaning.getText().toString();
                    String check = "0";
                    String product = btnpopupmenu.getText().toString();
                    String notes = "0";
                    if (checkBoxpopup.isChecked()) {
                        check = "1";
                    }
                    else {
                        check = "0";
                    }
                    if (title.equals("") || youword.equals("")) {
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                    }else {
                        //boolean con = sqlData.SqlInsert(title, youword, wordprice, meaning, notes,check,product);
                        boolean con = sqlData.SqlUpdate(dataModal.getId(), title, youword, currentDateandTime, meaning, notes, check, product);
                        if (con==true) {
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                            editor.putString("txt1", "1");
                            editor.apply();
                            dialog01.dismiss();
                        }else {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog01.show();
            }
        });

        listitemView.setOnClickListener(v -> {
            Dialog dialog01 = new Dialog(context);
            dialog01.setContentView(R.layout.popupread);
            dialog01.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog01.setCancelable(true);
            dialog01.setCanceledOnTouchOutside(true);
            TextView cate = dialog01.findViewById(R.id.textView4);
            TextView cate1 = dialog01.findViewById(R.id.textView5);
            TextView cate2 = dialog01.findViewById(R.id.textView6);
            TextView cate3 = dialog01.findViewById(R.id.textView7);
            cate.setText(dataModal.getProduct());
            cate1.setText(dataModal.getTitle());
            cate2.setText(dataModal.getYouword());
            cate3.setText(dataModal.getMeaning());

            dialog01.show();
        });
        return listitemView;
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = itemsModelsl.size();
                    filterResults.values = itemsModelsl;
                } else {
                    List<Constructor> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();
                    for (Constructor userRequestdata : itemsModelsl) {
                        if (userRequestdata.getTitle().contains(searchStr)) {
                            resultsModel.add(userRequestdata);
                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }
                }
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                itemsModelListFiltered = (List<Constructor>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
