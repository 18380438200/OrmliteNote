package com.example.ormlitenote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CommonAdapter<NoteBean> adapter;
    private ArrayList<NoteBean> datas = new ArrayList<>();
    private NoteDao noteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();
    }

    private void init() {
        noteDao = new NoteDao(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new CommonAdapter<NoteBean>(getApplicationContext(),R.layout.item,datas) {
            @Override
            protected void convert(ViewHolder holder, NoteBean noteBean, int position) {
                holder.setText(R.id.tv_title,noteBean.getTitle());
                holder.setText(R.id.tv_content,noteBean.getContent());
                holder.setText(R.id.tv_date,noteBean.getDate());
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(MainActivity.this,InputActivity.class);
                intent.putExtra("id",datas.get(position).getNoteId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                noteDao.del(datas.get(position));
                loadData();
                return false;
            }
        });
    }

    private void loadData(){
        datas.clear();
        List list = noteDao.queryAll();
        datas.addAll(list);
        adapter.notifyDataSetChanged();
    }

    public void addData(View view){
        Intent intent = new Intent(this,InputActivity.class);
        startActivity(intent);
    }
}
