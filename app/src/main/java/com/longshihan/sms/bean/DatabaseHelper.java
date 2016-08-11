package com.longshihan.sms.bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * @author Administrator
 * @time 2016/8/11 13:37
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TABLE_NAME = "sqlite-test.db";
    /**
     * userDao ，每张表对于一个
     */
    private Dao<GuiZe, Integer> userDao;

    private DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, GuiZe.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, GuiZe.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    /**
     * 获得userDao
     *
     * @return
     * @throws SQLException
     */
    public Dao<GuiZe, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(GuiZe.class);
        }
        return userDao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        userDao = null;
    }

}
