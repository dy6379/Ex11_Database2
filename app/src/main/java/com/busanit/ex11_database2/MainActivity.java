package com.busanit.ex11_database2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editText1, editText2;
    private TextView textView;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);

        Button createDB = findViewById(R.id.createDB);
        createDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    String database = editText1.getText().toString();
                createDatabase();
            }
        });

        Button createTable = findViewById(R.id.createTable);
        createTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tableName = editText2.getText().toString();
                createTable();
                insertRecord();
            }
        });
        Button btnSelect = findViewById(R.id.btnSelect);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeQuery();
            }
        });
    }



    private void executeQuery() {
        println("executeQuery 호출됨.");
        Cursor cursor = database.rawQuery("select _id, name, age,mobile from emp", null);
        int recordCount = cursor.getCount();
        println("레코드 개수 : " + recordCount);
        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String mobile = cursor.getString(3);
            println("레코드 " + i + " : " + id + ", " + name + ", " + age + ", " + mobile);
        }
        cursor.close();
    }

    private void createTable() {
        println("createTable 호출됨.");
//            if (database==null){
//                println("데이터베이스를 먼저 생성하세요");
//                return;
//            }
//            database.execSQL("create table if not exists "+tableName+"("
//                    +"_id integer PRIMARY KEY autoincrement, "
//                    +"name text, "
//                    +"age integer,"
//                    +"moblie text)");
        println("테이블 생성함 : " + tableName);
    }

    private void insertRecord() {
        println("insertRecord 호출됨.");
//            if(database==null){
//                println("데이터베이스를 먼저 생성하세요.");
//                return;
//            }
//            if(tableName==null){
//                println("table을 먼저 생성하세요.");
//                return;
//            }
        database.execSQL("insert into emp"
                + " (name, age, mobile)"
                + " values "
                + "('John',20,'010-1000-1000')");
        println("레코드 추가함.");
    }

    private void createDatabase() {
        println("createDatabase 호출됨.");
        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
//            database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
        println("데이터베이스 생성함 : ");
    }

    public void println(String data) {
        textView.append(data + "\n");
    }
}