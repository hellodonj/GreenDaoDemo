package com.winning.health.rims.greendaodemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.winning.health.rims.greendaodemo.R;
import com.winning.health.rims.greendaodemo.entity.User;

import java.util.List;

/**
 * desc: 显示GreenDao增删改查的结果的adapter
 * author：djj on 2017/7/14 14:39
 * 简书：http://www.jianshu.com/u/dfbde65a03fc
 */
public class UserAdapter extends BaseAdapter {

    private List<User> mUserList;
    private Context mContext;

    public UserAdapter(List<User> mUserList, Context mContext) {
        this.mUserList = mUserList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if (mUserList != null) {
            return mUserList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return mUserList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_user, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_id = (TextView) view.findViewById(R.id.tv_id);
            viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        User user = mUserList.get(i);
        viewHolder.tv_id.setText(String.valueOf(user.getId()));
        viewHolder.tv_name.setText(user.getName());

        return view;
    }

    class ViewHolder {
        TextView tv_id;
        TextView tv_name;
    }
}
