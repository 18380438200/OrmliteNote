package com.example.ormlitenote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by libo on 2017/12/20.
 */

public class OrmliteOpenHelper extends OrmLiteSqliteOpenHelper{
    private static final String TABLE_NAME = "sqlite.db";

    private Map<String, Dao> daos = new HashMap();

    private OrmliteOpenHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    private static OrmliteOpenHelper instance;

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized OrmliteOpenHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (OrmliteOpenHelper.class) {
                if (instance == null)
                    instance = new OrmliteOpenHelper(context);
            }
        }
        return instance;
    }

    public OrmliteOpenHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            //根据实体类创建对应表
            TableUtils.createTable(connectionSource,NoteBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            //根据实体类删除对应表
            TableUtils.dropTable(connectionSource,NoteBean.class,true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取类对应操作Dao类
     * @param clazz
     * @return
     * @throws SQLException
     */
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }


    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();

        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
