package com.example.spacecom.intothevoid;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by zhang on 5/16/2015.
 */
public class MainThread extends Thread{
    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;

    public static Canvas canvas;

    //constructor
    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run(){
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000/FPS;

        while(running){
            startTime = System.nanoTime();
            canvas = null;

            //try locking canvas for pixel editing
            //tries to update the screen and draw at 1/30th of a second
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }catch (Exception e){
            }
            finally{
                if(canvas!=null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMillis;

            //time to wait before the next loop
            try{
                this.sleep(waitTime);
            }catch(Exception e){
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == FPS){
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount =0;
                totalTime =0;
                //check the average frame rate of the game
                System.out.println(averageFPS);
            }

        }
    }

    public void setRunning(boolean state){
        running = state;
    }
}
