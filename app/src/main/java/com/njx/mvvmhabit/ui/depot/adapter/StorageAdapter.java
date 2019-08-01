package com.njx.mvvmhabit.ui.depot.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.entity.StorageEntity;

import java.util.List;

public class StorageAdapter extends RecyclerView.Adapter<StorageAdapter.StorageViewHodler> {
    private Context context;
    private List<StorageEntity> storageEntityList;
    private OnItemClickListener onItemClickListener;

    public StorageAdapter(Context context, List<StorageEntity> storageEntityList) {
        this.context = context;
        this.storageEntityList = storageEntityList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public StorageViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_storage_operate, viewGroup, false);
        StorageViewHodler storageViewHodler = new StorageViewHodler(view);
        return storageViewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull StorageViewHodler storageViewHodler, final int position) {
        StorageEntity storageEntity = storageEntityList.get(position);
        storageViewHodler.materTxt.setText(storageEntity.getMaterialsNo());
        storageViewHodler.numTxt.setText("");
        String str;
        if (position == 0) {
            str = "本次入库" + storageEntity.getCurentStor();
            SpannableString curSpanStr = new SpannableString(str);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.GREEN);
            curSpanStr.setSpan(colorSpan, 0, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            storageViewHodler.numTxt.append(curSpanStr);
            storageViewHodler.numTxt.append("\n");
        }

        str = "已入库" + storageEntity.getAlreadyStor();

        int alNum = 0;
        int NeeNum =0;
        if(!TextUtils.isEmpty(storageEntity.getAlreadyStor())){
            alNum = Integer.parseInt(storageEntity.getAlreadyStor());
        }
        if(!TextUtils.isEmpty(storageEntity.getNeedStor())){
            NeeNum = Integer.parseInt(storageEntity.getNeedStor());
        }
        if (alNum < NeeNum) {
            SpannableString curSpanStr = new SpannableString(str);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.BLUE);
            curSpanStr.setSpan(colorSpan, 0, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            storageViewHodler.numTxt.append(curSpanStr);
        } else {
            storageViewHodler.numTxt.append(str);
        }
        storageViewHodler.numTxt.append("\n");

        storageViewHodler.numTxt.append("需入库" + storageEntity.getNeedStor());

        storageViewHodler.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onItemClickListener) {
                    onItemClickListener.onItemClick(position, v);
                }
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    @Override
    public int getItemCount() {
        return storageEntityList == null ? 0 : storageEntityList.size();
    }

    public class StorageViewHodler extends RecyclerView.ViewHolder {
        private TextView materTxt;
        private TextView numTxt;
        private View view;

        public StorageViewHodler(@NonNull View itemView) {
            super(itemView);
            this.materTxt = itemView.findViewById(R.id.mater_txt);
            this.numTxt = itemView.findViewById(R.id.num_txt);
            view = itemView;
        }
    }
}
