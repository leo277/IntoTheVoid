package com.example.spacecom.intothevoid;

import android.graphics.Rect;

/**
 * Created by zhang on 5/16/2015.
 */
public abstract class GameObject {
    protected int x, y, dx, dy, width, height;

    public void setX (int x){
        this.x = x;
    }

    public void setY (int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    //for checking object collision
    public Rect getRectangle(){
        return new Rect(x, y, x+width, y+height);
    }


}
