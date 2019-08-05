package com.njx.mvvmhabit.ui.main.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.ui.main.bean.MenuBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.internal.Utils;


/**
 * Created by Administrator on 2018/7/25.
 */

public class MenuAdapter extends BaseAdapter {
    private List<MenuBean> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    public MenuAdapter(List<MenuBean> datas, Context context) {
        mDatas = datas;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_menu, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        Drawable drawable= Utils.tint(ContextCompat.getDrawable(mContext,mDatas.get(position).getUrl()), ContextCompat.getColor(mContext, R.color.colorPrimary));
//        holder.mIvMenu.setImageDrawable(drawable);//Todo 图片网络加载
        holder.mTvName.setText(mDatas.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.ivMenu)
        ImageView mIvMenu;
        @Bind(R.id.tvName)
        TextView mTvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
