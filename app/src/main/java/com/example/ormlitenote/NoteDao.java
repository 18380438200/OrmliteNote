package com.example.ormlitenote;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by libo on 2017/12/20.
 */
public class NoteDao{
    private Dao<NoteBean,Integer> noteDao;

    public NoteDao(Context context){
        try {
            noteDao = OrmliteOpenHelper.getHelper(context).getDao(NoteBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(NoteBean noteBean){
        try {
            noteDao.create(noteBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void del(NoteBean noteBean){
        try {
            noteDao.delete(noteBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(NoteBean noteBean){
        try {
            noteDao.update(noteBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public NoteBean queryForId(int id){
        try {
            return noteDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List queryAll(){
        try {
            return noteDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}



