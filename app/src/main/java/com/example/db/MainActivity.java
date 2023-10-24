package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private DBHelper helper;
    private SQLiteDatabase database;
    private Button food_btn,clothes_btn,game_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView1);
        food_btn=findViewById(R.id.button2);
        clothes_btn=findViewById(R.id.button);
        game_btn=findViewById(R.id.button3);
        helper=new DBHelper(getApplicationContext());
        try{
            database=helper.getWritableDatabase();
        }catch (Exception e){
            e.printStackTrace();
        }

        clothes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<HashMap<String,String>> clothes = new ArrayList<>();
                HashMap<String,String> animal;
                Cursor cursor = database.rawQuery("SELECT id,name,quantity FROM clothes",null);
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    animal=new HashMap<>();
                    animal.put("name", cursor.getString(1));
                    animal.put("quantity", cursor.getString(2));
                    clothes.add(animal);
                    cursor.moveToNext();
                }
                cursor.close();

                SimpleAdapter adapter = new SimpleAdapter(
                        getApplicationContext(),clothes, android.R.layout.simple_list_item_2,
                        new String[]{"name","quantity"}, new int[]{android.R.id.text1,android.R.id.text2}

                );
                listView.setAdapter(adapter);
            }
        });

        game_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<HashMap<String,String>> games = new ArrayList<>();
                HashMap<String,String> game;
                Cursor cursor = database.rawQuery("SELECT id,name,quantity FROM games",null);
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    game=new HashMap<>();
                    game.put("name", cursor.getString(1));
                    game.put("quantity", cursor.getString(2));
                    games.add(game);
                    cursor.moveToNext();
                }
                cursor.close();

                SimpleAdapter adapter = new SimpleAdapter(
                        getApplicationContext(),games, android.R.layout.simple_list_item_2,
                        new String[]{"name","quantity"}, new int[]{android.R.id.text1,android.R.id.text2}

                );
                listView.setAdapter(adapter);
            }
        });

        food_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<HashMap<String,String>> foods = new ArrayList<>();
                HashMap<String,String> food;
                Cursor cursor = database.rawQuery("SELECT id,name,quantity FROM foods",null);
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    food=new HashMap<>();
                    food.put("name", cursor.getString(1));
                    food.put("quantity", cursor.getString(2));
                    foods.add(food);
                    cursor.moveToNext();
                }
                cursor.close();

                SimpleAdapter adapter = new SimpleAdapter(
                        getApplicationContext(),foods, android.R.layout.simple_list_item_2,
                        new String[]{"name","quantity"}, new int[]{android.R.id.text1,android.R.id.text2}

                );
                listView.setAdapter(adapter);
            }
        });
    }
}