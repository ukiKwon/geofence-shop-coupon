package com.android.raywenderlich.remindmethere;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class CouponAdapter extends RecyclerView.Adapter < CouponAdapter.MyViewHolder> {

    private ArrayList<Coupon> mDataset;
    //click-listener
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }
    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mimage;
        //String mCategory;
        public TextView mStoreName;
        public TextView mDiscountRate;
        public TextView mLocation;
        public TextView mLeftTime;
        //constructor
        public MyViewHolder(View itemView) {
            super(itemView);
            mimage = itemView.findViewById(R.id.list_item_coupon_icon);
            mStoreName = itemView.findViewById(R.id.list_item_coupon_store);
            mDiscountRate = itemView.findViewById(R.id.list_item_coupon_discount);
            mLeftTime = itemView.findViewById(R.id.list_item_coupon_leftTime);
            //click-listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {//anything click on Recycler item
                        if(mListener != null) {
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public CouponAdapter(ArrayList<Coupon> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CouponAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_coupon, parent, false);
        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

         Coupon mc = mDataset.get(position);

        holder.mimage.setImageDrawable(mc.mimage);
        holder.mStoreName.setText(mc.mStoreName);
        holder.mDiscountRate.setText(mc.mDiscountRate);
        holder.mLeftTime.setText(String.valueOf(mc.mLeftTime));
        //todo : 위치, 카테고리도 향후 추가
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
