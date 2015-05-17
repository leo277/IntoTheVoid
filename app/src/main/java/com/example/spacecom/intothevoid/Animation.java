package com.example.spacecom.intothevoid;

import android.graphics.Bitmap;

/**
 * Created by zhang on 5/16/2015.
 */
public class Animation {
    private Bitmap[] frames;
    private int currentFrame;
    private long startTime, delay;
    private boolean playedOnce;

    public void setFrames(Bitmap[] frames){
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }

    public void setDelay(long delayTime){
        delay = delayTime;
    }

    //for manually setting frame if needed
    public void setFrame (int framePosition){
        currentFrame = framePosition;
    }

    //determines which frame of the playerImage
    public void update(){
        long elapsed = (System.nanoTime() - startTime)/1000000;
        if(elapsed>delay){
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length){
            currentFrame = 0;
            playedOnce = true;
        }
    }

    //return current image frame of the player
    public Bitmap getPlayerImage(){
        return frames[currentFrame];
    }

    public int getFrame(){
        return currentFrame;
    }

    public boolean playedOnce(){
        return playedOnce;
    }

}
