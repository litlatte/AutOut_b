package com.iiseinstein.autout;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardListViewHolder>{
    private ArrayList<CardListItem> mCardList;

    @NonNull
    @Override
    public CardListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carditem, viewGroup, false);
        CardListViewHolder clvh = new CardListViewHolder(v);
        return clvh;
    }

    public CardListAdapter(ArrayList<CardListItem> CardList){
        mCardList=CardList;

    }

    @Override
    public void onBindViewHolder(@NonNull CardListViewHolder cardListViewHolder, int i) {
        CardListItem currentitem = mCardList.get(i);
        cardListViewHolder.mImageView.setImageResource(currentitem.getImageResource());
        cardListViewHolder.mTextView.setText(currentitem.getText());
    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }

    public static class CardListViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView;
        public CardListViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.textView);

        }
    }
}
