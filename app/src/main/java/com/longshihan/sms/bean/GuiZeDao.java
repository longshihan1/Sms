package com.longshihan.sms.bean;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Administrator
 * @time 2016/8/11 13:47
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class GuiZeDao {
    private Context mContext;
    private Dao<GuiZe, Integer> userDao;
    private DatabaseHelper mHelper;

    public GuiZeDao(Context context) {
        mContext = context;
        try {
            mHelper = DatabaseHelper.getHelper(context);
            userDao = mHelper.getDao(GuiZe.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add_guize(GuiZe user) {
        try {
            userDao.create(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<GuiZe> queryall_guize(){
        try {
          return  userDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void detele(String name) {
        try {
        List<GuiZe> lists=queryall_guize();
        for (int i = 0; i <lists.size(); i++) {
            if (name.equals(lists.get(i).getName())){
                userDao.delete(lists.get(i));
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
