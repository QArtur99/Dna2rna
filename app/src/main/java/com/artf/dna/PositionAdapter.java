package com.artf.dna;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;


public class PositionAdapter extends RecyclerView.Adapter<PositionAdapter.MyViewHolder> {

    private static final String DOT = "\u2022";

    private List<Integer> data;
    final private OnListItemClickListener mOnClickListener;

    public PositionAdapter(List<Integer> myDataset, OnListItemClickListener listener) {
        data = myDataset;
        mOnClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(position);


    }

    public Object getDataAtPosition(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<Integer> getData() {
        return data;
    }


    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.description) TextView description;
        @BindView(R.id.commentFrame) LinearLayout frame;
        @BindColor(R.color.bc) int color;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            Integer rnaObject = (Integer) getDataAtPosition(position);
            description.setText(String.valueOf(rnaObject));
            if(position % 2 != 0){
                frame.setBackgroundColor(color);
            }
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onListItemClick(getAdapterPosition());
        }
    }
}