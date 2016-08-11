package com.longshihan.sms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.longshihan.sms.bean.GuiZe;

import java.util.List;

import static java.util.Collections.addAll;

/**
 * @author Administrator
 * @time 2016/8/11 14:21
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class GuiListAdapter extends BaseAdapter {
    private Context mContext;
    private List<GuiZe> mList;
    private LayoutInflater mInflater;

    public GuiListAdapter(Context context, List<GuiZe> list) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        mList = list;
    }
    public void setList(List<GuiZe> list){
        mList.clear();
        addAll(list);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=mInflater.inflate(R.layout.switch_item,null);
            holder.name= (TextView) view.findViewById(R.id.item_txt);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        GuiZe guiZe=mList.get(i);
        holder.name.setText(guiZe.getName());
        return view;
    }

    class ViewHolder{
        public TextView name;
    }
}
