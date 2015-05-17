package com.example.spacecom.intothevoid;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by zhang on 5/16/2015.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    //define the game background dimension
    public static final int WIDTH = 423;
    public static final int HEIGHT = 402;
    public static final int MOVE_SPEED = -5;
    private MainThread mainThread;
    private Background background;
    private Spaceship player;
    //constructor
    public GamePanel(Context context) {
        super(context);
        //add callback to the surface holder so as to intercept event
        getHolder().addCallback(this);

        mainThread = new MainThread(getHolder(), this);

        //enable focus to handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        while(retry) {
            try {
                mainThread.setRunning(false);
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //load the background image to background object
        background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.spacebg));
        player = new Spaceship(BitmapFactory.decodeResource(getResources(),R.drawable.helicopter),65,25,3);
        //start the game loop here
        mainThread.setRunning(true);
        mainThread.start();
    }


    //user interaction with the device screen
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(!player.getPlaying())
            {
                player.setPlaying(true);
            }
            else
            {
                player.setUp(true);
            }
            return true;
        }
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            player.setUp(false);
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void update(){
        //only update if player is playing
        if(player.getPlaying()){
            background.update();
            player.update();
        }

    }
    @Override
    public void draw(Canvas canvas){
        //scale the background to a right dimension
        final float scaleFactorX = (float)getWidth()/WIDTH;
        final float scaleFactorY = (float)getHeight()/HEIGHT;

        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            background.draw(canvas);
            player.draw(canvas);
            //after scaling, return to save state otherwise it will keep scaling
            canvas.restoreToCount(savedState);
        }
    }

}


