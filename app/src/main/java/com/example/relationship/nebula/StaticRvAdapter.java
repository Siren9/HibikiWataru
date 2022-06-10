package com.example.relationship.nebula;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relationship.R;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRVViewHolder>{
    private ArrayList<StaticRvModel> items;
    int row_index = -1;
    private OnItemClickListener onItemClickListener;

    public StaticRvAdapter(ArrayList<StaticRvModel> items) {
        this.items = items;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent,false);
        View view = View.inflate(parent.getContext(), R.layout.static_rv_item, null);
        StaticRVViewHolder staticRVViewHolder = new StaticRVViewHolder(view);
        view.setOnClickListener((View.OnClickListener) this);
        return staticRVViewHolder;
    }

    public void onClick(View v){
        if (onItemClickListener != null){
            onItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRVViewHolder holder, @SuppressLint("RecyclerView") int position) {
        StaticRvModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        StaticRVViewHolder viewholder = (StaticRVViewHolder) holder;
//        viewholder.textView.setText(items.get(position));
        viewholder.itemView.setTag(position);


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if (row_index == position){
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
        }
        else {
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
        }
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;

        public StaticRVViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_rv);
            textView = itemView.findViewById(R.id.text_rv);
            linearLayout = itemView.findViewById(R.id.linearLayour);
        }
    }
}
