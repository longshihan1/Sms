package com.longshihan.sms.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Administrator
 * @time 2016/8/11 13:29
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 *
 * 首先在User类上添加@DatabaseTable(tableName = "tb_user")，
 * 标明这是数据库中的一张表，标明为tb_user
 * 然后分别在属性上添加@DatabaseField(columnName = "name") ，
 * columnName的值为该字段在数据中的列名
 * @DatabaseField(generatedId = true) ，generatedId 表示id为主键且自动生成
 */

@DatabaseTable(tableName = "msg")
public class GuiZe {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "name")
    private String name;

    public GuiZe(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GuiZe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
