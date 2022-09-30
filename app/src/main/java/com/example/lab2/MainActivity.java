package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.clear:
                AlertDialog.Builder mess = new AlertDialog.Builder(this);
                mess.setTitle(R.string.message_caption);
                mess.setMessage(R.string.message_content);
                mess.setNeutralButton(R.string.close, new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText et = (EditText)findViewById(R.id.editText1);
                                et.setText(""); // xoá nội dung edittext
                            }
                        }).show();
                break;

            case R.id.setting:
                Intent intent = new Intent(this,OptionActivity.class);
                final int result=1;
                startActivityForResult(intent, result);
                break;

            case R.id.exit:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.exit_caption)
                        .setMessage(R.string.exit_content)
                        .setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                finish();
                            }
                        }).setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        }).show();
                break;
        } //end switch
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
// lấy Bundle dữ liệu
        Bundle bundle = data.getExtras();
        int index1 = bundle.getInt("ForeColor");
        int index2 = bundle.getInt("BackColor");
        int index3 = bundle.getInt("Size");
// lấy mảng màu
        String colorArray[] = getResources().getStringArray(R.array.color_array);
        String sizeArray[] = getResources().getStringArray(R.array.size_array);
// tham chiếu đến editText
        EditText et = (EditText)findViewById(R.id.editText1);
// thiết lập màu
        et.setTextColor(Color.parseColor(colorArray[index1]));
        et.setBackgroundColor(Color.parseColor(colorArray[index2]));
        et.setTextSize(Float.parseFloat(sizeArray[index3]));


    }
}