package com.example.recyclememoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.recyclememoapp.adapter.MemoListRecycleViewAdapter;
import com.example.recyclememoapp.model.RowData;
import com.example.recyclememoapp.openhelper.MemoListOpenHelper;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MemoListRecycleViewAdapter adapter;
    private List<RowData> dataset = new ArrayList<>();
    private MemoListOpenHelper helper = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ヘッダ用のレイアウトファイルと紐づける
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //各ボタンを押した時の処理
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rv = findViewById(R.id.memoListRecyclerView);
        // メモリストに値を設定
        //        MemoListRecycleViewAdapter adapter = new MemoListRecycleViewAdapter(this.createDataSet());


        adapter = new MemoListRecycleViewAdapter(this.createDataSet());

        LinearLayoutManager llm = new LinearLayoutManager(this);

        // RecycleViewに枠線を追加
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(itemDecoration);

        rv.setHasFixedSize(true);

        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);

        // ドラッグ・スワイプ時の処理

        ItemTouchHelper ith = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN ,
                ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {

                final int fromPos = viewHolder.getAdapterPosition();
                final int toPos = target.getAdapterPosition();
                adapter.notifyItemMoved(fromPos, toPos);
                return true;// true if moved, false otherwise
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int fromPos = viewHolder.getAdapterPosition();
                dataset.remove(fromPos);
                adapter.notifyItemRemoved(fromPos);
            }
        });

        ith.attachToRecyclerView(rv);

    }

    private List<RowData> createDataSet(){
        dataset = new ArrayList<>();

        if(helper == null){
            helper = new MemoListOpenHelper(MainActivity.this);
        }

        // DB取得
        SQLiteDatabase db = helper.getWritableDatabase();

        try{
            Cursor cusor = db.rawQuery("select id, detail, title, priority, created_datetime, updated_datetime from MEMO_TABLE order by id", null);
            boolean next = cusor.moveToFirst();

            while(next){
                RowData rowData = new RowData();
                rowData.setId(cusor.getInt(0));
                rowData.setDetail(cusor.getString(1));
                rowData.setTitle(cusor.getString(2));
                rowData.setPriority(cusor.getInt(3));
                rowData.setCreated_datetime(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse(cusor.getString(4)));
                rowData.setUpdated_datetime(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse(cusor.getString(5)));

                dataset.add(rowData);
                next = cusor.moveToNext();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }


//
//
//        for (int i = 1; i < 20; i++) {
//            RowData data = new RowData();
//            data.setTitle("タイトル" + i);
//            data.setDetail("詳細" + i );
//
//            dataset.add(data);
//        }
        return dataset;
    }
}
