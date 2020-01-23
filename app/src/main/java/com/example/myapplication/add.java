package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class add extends AppCompatActivity {
private  OperateTable mytable =null;
    private  SQLiteOpenHelper helper=null;

    private EditText name=null;
    private EditText text=null;
    private  Button save=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        this.name=(EditText)super.findViewById(R.id.aname);
        this.text=(EditText)super.findViewById(R.id.atext);
        Button save=( Button) super.findViewById(R.id.save);

        helper=new DataBaseHelp(this);
        helper.getWritableDatabase();
        mytable=new OperateTable(helper.getWritableDatabase());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("")){Toast.makeText(getApplicationContext(),"请输入标题",Toast.LENGTH_SHORT).show();}
               else{ mytable.insert(name.getText().toString(),text.getText().toString());
                Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
                add.this.finish();}

            }
        });

    }


}