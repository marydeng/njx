package com.njx.mvvmhabit.ui.produce.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.entity.FeedingEntity;

import java.util.List;

public class MaterChangeAdapter extends RecyclerView.Adapter<MaterChangeAdapter.SMTViewHodler> {
    private Context context;
    private List<FeedingEntity> feedingEntityList;
    private OnItemClickListener onItemClickListener;

    public MaterChangeAdapter(Context context, List<FeedingEntity> feedingEntities) {
        this.context = context;
        this.feedingEntityList = feedingEntities;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SMTViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mate_change_operate, viewGroup, false);
        SMTViewHodler SMTViewHodler = new SMTViewHodler(view);
        return SMTViewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull SMTViewHodler SMTViewHodler, final int position) {
        FeedingEntity feedingEntity = feedingEntityList.get(position);
        SMTViewHodler.materGun.setText(feedingEntity.getMaterialsGunID());
        SMTViewHodler.newMaterId.setText(feedingEntity.getREELID());
        SMTViewHodler.oldMaterId.setText(feedingEntity.getREELID());
        SMTViewHodler.materStation.setText(feedingEntity.getMaterialsStaID());
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
        private TextView materGun;
        private TextView newMaterId;
        private TextView oldMaterId;
        private TextView materStation;
        private View view;

        public SMTViewHodler(@NonNull View itemView) {
            super(itemView);
            this.materGun = itemView.findViewById(R.id.mater_gun);
            this.newMaterId = itemView.findViewById(R.id.new_mater_roll);
            this.oldMaterId=itemView.findViewById(R.id.old_mater_roll);
            this.materStation = itemView.findViewById(R.id.mater_station);
            view = itemView;
        }
    }
}
