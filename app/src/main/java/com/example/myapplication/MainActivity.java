package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
private OperateTable mytable =null;
private SQLiteOpenHelper helper=null;
private FloatingActionButton add=null;
private String info=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        helper=new DataBaseHelp(this);
        helper.getWritableDatabase();
        MainActivity.this.mytable=new OperateTable(MainActivity.this.helper.getWritableDatabase());


        SimpleAdapter adapter = new SimpleAdapter(this,this.mytable.getdata(), R.layout.activity_main
                , new String[]{"id","tt"},
                new int[]{R.id.id,R.id.tt});
        ListView listView=(ListView)findViewById(R.id.vi);
        add=(FloatingActionButton)findViewById(R.id.add);
listView.setAdapter(adapter);
listView.setOnItemClickListener(new OnItemClick());
listView.setOnItemLongClickListener(new OnItemLongClick());
add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent it=new Intent(MainActivity.this,add.class);
        MainActivity.this.startActivity(it);
    }
});
    }
    private List<Map<String,Object>> getdata()
    {List<Map<String,Object>>list=new ArrayList<Map<String,Object>>();
    Map<String,Object> map=new HashMap<String,Object>();
        map.put("tt","111");
        map.put("id","222");
    list.add(map);
        map=new HashMap<String,Object>();
        map.put("id","222");
        map.put("tt","222");
        list.add(map);
        map=new HashMap<String,Object>();
        map.put("tt","333");
        map.put("id","222");
        list.add(map);

return  list;}
    private class OnItemClick implements AdapterView.OnItemClickListener
    { public void onItemClick(AdapterView<?>arg0, View arg1, int arg2, long arg3){
        ListView list = (ListView) findViewById(R.id.vi);
        HashMap<String,Object> map=(HashMap<String,Object>)list.getItemAtPosition(arg2);

        info=map.get("id").toString();
        Intent it=new Intent(MainActivity.this,receive.class);
        it.putExtra("info",info);
        MainActivity.this.startActivity(it);



    }



    }
    private class OnItemLongClick implements AdapterView.OnItemLongClickListener
    {
        public boolean onItemLongClick(AdapterView<?>arg0, View arg1, int arg2, long arg3){

            ListView list = (ListView) findViewById(R.id.vi);
            HashMap<String,Object> map=(HashMap<String,Object>)list.getItemAtPosition(arg2);
            String name;
            info=map.get("id").toString();
            name=map.get("tt").toString();
            AlertDialog myAlertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("确认" )
                    .setMessage("确定删除“"+name+"”吗？" )
                    .setPositiveButton("是" , new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            mytable.delete(info);
                            Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();
                            onResume();
                        }
                    })
                    .setNegativeButton("否" , null)
                    .show();

            return true;


        }

    }


    public void onResume() {
        super.onResume();  // Always call the superclass method first

        SimpleAdapter adapter = new SimpleAdapter(this,MainActivity.this.mytable.getdata(), R.layout.activity_main
                , new String[]{"id","tt"},
                new int[]{R.id.id,R.id.tt});
        ListView listView=(ListView)findViewById(R.id.vi);
        add=(FloatingActionButton)findViewById(R.id.add);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClick());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,add.class);
                MainActivity.this.startActivity(it);
            }
        });
    }

}








