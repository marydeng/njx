package com.njx.mvvmhabit.ui.produce.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.entity.SteelEntity;

import java.util.List;

public class SteelAdapter extends RecyclerView.Adapter<SteelAdapter.SMTViewHodler> {
    private Context context;
    private List<SteelEntity> steelEntityList;
    private OnItemClickListener onItemClickListener;

    public SteelAdapter(Context context, List<SteelEntity> steelEntities) {
        this.context = context;
        this.steelEntityList = steelEntities;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SMTViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_steel_operate, viewGroup, false);
        SMTViewHodler SMTViewHodler = new SMTViewHodler(view);
        return SMTViewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull SMTViewHodler SMTViewHodler, final int position) {
        SteelEntity steelEntity = steelEntityList.get(position);
        SMTViewHodler.type.setText(steelEntity.getCategory());
        SMTViewHodler.code.setText(steelEntity.getSteelPlateType());
        SMTViewHodler.line.setText(steelEntity.getLineClass());
        SMTViewHodler.view.setOnClickListener(new View.OnClickListener() {
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
        return steelEntityList == null ? 0 : steelEntityList.size();
    }

    public class SMTViewHodler extends RecyclerView.ViewHolder {
        private TextView type;
        private TextView code;
        private TextView line;
        private View view;

        public SMTViewHodler(@NonNull View itemView) {
            super(itemView);
            this.type = itemView.findViewById(R.id.type);
            this.code = itemView.findViewById(R.id.code);
            this.line = itemView.findViewById(R.id.line);
            view = itemView;
        }
    }
}
