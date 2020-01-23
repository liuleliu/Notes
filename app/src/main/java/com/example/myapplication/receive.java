package com.example.myapplication;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class receive extends AppCompatActivity {

    private EditText name=null;
    private EditText text=null;
    private Button delete=null;
    private Button edit=null;
    private OperateTable mytable =null;
    private SQLiteOpenHelper helper=null;
    private String info=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive);

        this.name=(EditText)super.findViewById(R.id.name);
        this.text=(EditText)super.findViewById(R.id.text);
        this.delete=(Button)super.findViewById(R.id.delete);
        this.edit=(Button)super.findViewById(R.id.edit);

    Intent it=super.getIntent();
    info=it.getStringExtra("info");

        helper=new DataBaseHelp(this);
        helper.getWritableDatabase();
      mytable=new OperateTable(helper.getWritableDatabase());
    tip t=mytable.t(info);
   name.setText(t.getName());
   text.setText(t.getText());
   delete.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           AlertDialog myAlertDialog = new AlertDialog.Builder(receive.this)
                   .setTitle("确认" )
                   .setMessage("确定删除“"+name.getText()+"”吗？" )
                   .setPositiveButton("是" , new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int whichButton) {
                           mytable.delete(info);
                           Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();
                         receive.this.finish();
                       }
                   })
                   .setNegativeButton("否" , null)
                   .show();
       }
   });
   edit.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           mytable.updata(info,name.getText().toString(),text.getText().toString());
           Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
           receive.this.finish();
       }
   });
    }


}
