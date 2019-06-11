package com.iiseinstein.autout;

public class CardListItem {
    private int mImageResource;
    private String  mText;

    public CardListItem(int ImageResource, String Text){
        mImageResource = ImageResource;
        mText=Text;

    }


    public int getImageResource(){
        return mImageResource;
    }

    public String getText(){
        return mText;
    }


}
