package com.mrdelicious.minitankswot.cards.upgrades;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;

import java.util.List;

public class UpgradeOnListAdapter extends RecyclerView.Adapter<UpgradeOnListAdapter.UpgradeOnListViewHolder> {
    List<UpgradeOnList> upgradeList;
    EverythingDatabase db;
    Context context;

    public UpgradeOnListAdapter(List<UpgradeOnList> upgradeList, Context context) {
        this.upgradeList = upgradeList;
        this.context = context;
    }

    @NonNull
    @Override
    public UpgradeOnListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_upgrade_in_list, parent, false);
        return new UpgradeOnListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpgradeOnListViewHolder holder, int position) {
        holder.upgradeName.setText(upgradeList.get(position).getName());
        holder.upgradePts.setText(String.valueOf(upgradeList.get(position).getPts()) + "pkt");
        holder.upgradeType.setText(upgradeList.get(position).getType());
        holder.upgradeFlag.setImageResource(upgradeList.get(position).getNation());
        holder.parentLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, SelectedUpgradeActivity.class);
        });
    }

    @Override
    public int getItemCount() {
        return upgradeList.size();
    }

    public static class UpgradeOnListViewHolder extends RecyclerView.ViewHolder {
        ImageView upgradeFlag;
        TextView upgradeName, upgradePts, upgradeType;
        ConstraintLayout parentLayout;

        public UpgradeOnListViewHolder(@NonNull View itemView) {
            super(itemView);

            upgradeFlag = itemView.findViewById(R.id.layoutUpgList_flag);
            upgradeName = itemView.findViewById(R.id.layoutUpgList_name);
            upgradePts = itemView.findViewById(R.id.layoutUpgList_pkt);
            upgradeType = itemView.findViewById(R.id.layoutUpgList_type);
            parentLayout = itemView.findViewById(R.id.layoutUpgList_lay);
        }
    }
}
