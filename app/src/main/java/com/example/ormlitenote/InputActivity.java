package com.example.ormlitenote;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Date;

public class InputActivity extends AppCompatActivity {
    private EditText etTitle;
    private EditText etContent;
    private TextView tvSave;
    private int noteId;
    private NoteBean noteBean;
    //-1表示创建笔记，否则为修改
    private int CREATE_TYPE = -1;
    private String dateFormate = "YYYY-MM-dd hh:mm:ss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        init();
    }

    private void init(){
        etTitle = (EditText) findViewById(R.id.et_title);
        etContent = (EditText) findViewById(R.id.et_content);
        tvSave = (TextView) findViewById(R.id.tv_save);

        noteId = getIntent().getIntExtra("id",CREATE_TYPE);

        if(noteId != CREATE_TYPE){
            noteBean = new NoteDao(this).queryForId(noteId);
            etTitle.setText(noteBean.getTitle());
            etContent.setText(noteBean.getContent());

            tvSave.setText("修改");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void save(View view){
        String title = etTitle.getText().toString().trim();
        String content = etContent.getText().toString().trim();

        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)){
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
            String date = sdf.format(new Date());
            if(noteId != CREATE_TYPE){  //修改模式
                noteBean.setTitle(title);
                noteBean.setContent(title);
                new NoteDao(this).update(noteBean);
            }else{   //创建模式
                NoteBean bean = new NoteBean((int) System.currentTimeMillis(),title,content,date,content.length());
                new NoteDao(this).insert(bean);
            }


            finish();
        }
    }

}
