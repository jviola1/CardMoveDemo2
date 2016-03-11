package com.example.jay.cardmovedemo2;

import android.widget.ImageView;

/**
 * Created by jay on 16/3/8.
 */
public class Card {
    Pile pile;
    ImageView img;

    public Card(Pile pile, ImageView img){
        this.img = img;
        pile.addCard(this);
    }

    public void setLocation(float x, float y){
        this.img.setX(x);
        this.img.setY(y);
    }
}
