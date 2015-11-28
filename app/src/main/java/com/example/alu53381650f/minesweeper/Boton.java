package com.example.alu53381650f.minesweeper;

import android.content.Context;
import android.widget.ImageButton;

/**
 * Created by alu53381650f on 27/11/15.
 */
public class Boton extends ImageButton {
    public int posX, posY;
    public Estados state;

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Estados getState() {
        return state;
    }

    public void setState(Estados state) {
        this.state = state;
    }

    public Boton(Context context, int x, int y) {
        super(context);
        this.posX = x;
        this.posY = y;
        state = Estados.NUEVO;
        setImageDrawable(getResources().getDrawable(R.drawable.free));
    }
}
