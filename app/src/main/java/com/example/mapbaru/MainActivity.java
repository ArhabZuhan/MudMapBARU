package com.example.mapbaru;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Tempat,Lokasi;
    TextView textView;
    DBController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tempat = (EditText)findViewById(R.id.TEMPAT);
        Lokasi = (EditText)findViewById(R.id.LOKASI);
        textView = (TextView)findViewById(R.id.textView);

        controller = new DBController(this,"",null,1);
    }

    public void btn_click(View view){
        switch(view.getId()){
            case R.id.btn_add:
                try {
                    controller.insert_datamap(Tempat.getText().toString(),Lokasi.getText().toString());
                }catch (SQLiteException e){
                    Toast.makeText(MainActivity.this, "ALREADY EXIST", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_delete:
                controller.delete_datamap(Lokasi.getText().toString());
                break;
            case R.id.btn_update:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Masukkan Tempat");

                final EditText new_firstname = new EditText(this);
                dialog.setView(new_firstname);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        controller.update_datamap(Tempat.getText().toString(),new_firstname.getText().toString());
                    }
                });
                dialog.show();
                break;
            case R.id.btn_list:
                controller.list_all_datamap(textView);
                break;
        }

    }
}