package com.njx.mvvmhabit.ui.produce.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.entity.SMTRecordEntity;

import java.util.List;

//更换料枪记录adapter
public class GunChangeAdapter extends RecyclerView.Adapter<GunChangeAdapter.SMTViewHodler> {
    private Context context;
    private List<SMTRecordEntity> feedingEntityList;
    private OnItemClickListener onItemClickListener;

    public GunChangeAdapter(Context context, List<SMTRecordEntity> feedingEntities) {
        this.context = context;
        this.feedingEntityList = feedingEntities;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SMTViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_smt_gun, viewGroup, false);
        SMTViewHodler SMTViewHodler = new SMTViewHodler(view);
        return SMTViewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull SMTViewHodler SMTViewHodler, final int position) {
        SMTRecordEntity feedingEntity = feedingEntityList.get(position);
        SMTViewHodler.newMaterGun.setText(feedingEntity.getMaterialRack());
        SMTViewHodler.oldMaterGun.setText(feedingEntity.getMaterialRoll());
        SMTViewHodler.materStation.setText(feedingEntity.getMaterialStation());
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
        return feedingEntityList == null ? 0 : feedingEntityList.size();
    }

    public class SMTViewHodler extends RecyclerView.ViewHolder {
        private TextView newMaterGun;
        private TextView oldMaterGun;
        private TextView materStation;
        private View view;

        public SMTViewHodler(@NonNull View itemView) {
            super(itemView);
            this.newMaterGun = itemView.findViewById(R.id.new_mater_gun);
            this.oldMaterGun = itemView.findViewById(R.id.old_mater_gun);
            this.materStation = itemView.findViewById(R.id.mater_station);
            view = itemView;
        }
    }
}
