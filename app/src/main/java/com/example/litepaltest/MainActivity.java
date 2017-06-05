package com.example.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.litepaltest.entity.Book;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createBtn = (Button)findViewById(R.id.create_database);
        createBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });

        Button addBtn = (Button)findViewById(R.id.add_data);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("First Line Code");
                book.setAuthor("Guo Ling");
                book.setPages(500);
                book.setPrice(50.25);
                book.setPress("TuLing");
                book.save();
            }
        });

        Button updateBtn = (Button)findViewById(R.id.update_data);
        updateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setPrice(45.50);
                book.setPress("TuLing Company");

                //更新为默认值
                //book.setToDefault("pages");
                book.updateAll("name=? and author = ?","First Line Code","Guo Ling");
            }
        });

        Button deleteBtn = (Button)findViewById(R.id.delete_data);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class,"pages < ?","600");
            }
        });

        Button queryBtn = (Button)findViewById(R.id.query_data);
        queryBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                List<Book> bookList = DataSupport.findAll(Book.class);
                for(Book book :bookList){
                    Log.d(TAG, "name:"+book.getName());
                    Log.d(TAG, "author:"+book.getAuthor());
                    Log.d(TAG, "price:"+book.getPrice());
                    Log.d(TAG, "pages:"+book.getPages());
                    Log.d(TAG, "press:"+book.getPress());
                }
            }
        });
    }
}
