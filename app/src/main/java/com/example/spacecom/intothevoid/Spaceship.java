package com.example.spacecom.intothevoid;

import android.graphics.Bitmap;
import android.graphics.Canvas;


/**
 * Created by zhang on 5/16/2015.
 */
public class Spaceship extends GameObject {
    private Bitmap spritesheet;
    private int score;
    private double deltaAcc;
    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;


    public Spaceship (Bitmap res, int w, int h, int numFrames){
        x = 100;
        y = GamePanel.HEIGHT/2;
        dy = 0;
        score = 0;
        height = h;
        width = w;

        Bitmap[] playerImage = new Bitmap[numFrames];
        spritesheet = res;

        for (int i=0; i<playerImage.length;i++){
            playerImage[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        animation.setFrames(playerImage);
        animation.setDelay(10);
        startTime = System.nanoTime();
    }

    //when player press down on the screen, the ship goes up
    public void setUp(boolean state) {
        up = state;
    }

    //update the spaceship status
    public void update(){
        long elapsed = (System.nanoTime() - startTime)/1000000;
        //score increases every 100ms
        if (elapsed>100){
            score++;
            startTime = System.nanoTime();
        }
        animation.update();

        if(up){
            dy = (int) (deltaAcc-=1.1);
        }else{
            dy = (int) (deltaAcc+=1.1);
        }

        if(dy>14)
            dy = 14;

        if(dy<-14)
            dy = -14;

        y += dy*2;
        dy = 0;

    }


    public void draw (Canvas canvas){
        canvas.drawBitmap(animation.getPlayerImage(), x, y, null);
    }

    public int getScore(){
        return score;
    }

    public boolean getPlaying(){
        return playing;
    }

    public void setPlaying (boolean status){
        playing = status;
    }

    public void resetAcc(){
        deltaAcc = 0;
    }

    public void resetScore(){
        score = 0;
    }

}
