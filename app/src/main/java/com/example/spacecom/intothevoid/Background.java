package com.example.spacecom.intothevoid;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by zhang on 5/16/2015.
 */
public class Background {

    private Bitmap image;
    private int x, y, dy;

    public Background (Bitmap res){
        image = res;
        dy = GamePanel.MOVE_SPEED;
    }

    /*
     * updates background
     * by setting x value to dx, this will constantly increment the x-pos,
     * and loop if the image goes off screen
     * thus creating a scrolling effect horizontally
     */
    public void update(){
        y += dy;
        //loop the background image when it goes offscreen
        if(y<-GamePanel.HEIGHT){
            y=0;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image,x,y,null);
        //fill in the empty space when the background goes off screen
        //so that it looks like a continuous image
        if(y<0){
            canvas.drawBitmap(image,x, y+GamePanel.HEIGHT, null);
        }
    }


}
