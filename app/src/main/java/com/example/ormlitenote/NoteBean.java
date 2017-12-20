package com.example.ormlitenote;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by libo on 2017/12/20.
 */

@DatabaseTable(tableName = "tb_note")
public class NoteBean {
    @DatabaseField(id = true,columnName = "noteid")
    private int noteId;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "content")
    private String content;
    @DatabaseField(columnName = "date")
    private String date;
    @DatabaseField(columnName = "textnum")
    private int textNum;

    public NoteBean(){}

    public NoteBean(int noteId,String title, String content, String date, int textNum) {
        this.noteId = noteId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.textNum = textNum;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTextNum() {
        return textNum;
    }

    public void setTextNum(int textNum) {
        this.textNum = textNum;
    }
}
