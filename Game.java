/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joohoon.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 *
 * @author joohoonkim
 */
public class Game extends Canvas implements Runnable{
    
    static final int WIDTH = 840, HEIGHT = WIDTH / 12 * 9;
    
    private Thread thread;
    private boolean running = false;
    
    //private Random r;
    private Handler handler;
    private Counter counter;
    
    public Game(){
        handler = new Handler();        
        
        this.addKeyListener(new KeyInput(handler));
        
        new Window(WIDTH,HEIGHT,"Pong",this);
        counter = new Counter(0, 0, handler);
        
        //r = new Random();

        handler.addObject(new Player(45, HEIGHT/2 - 84, ID.Player, handler));
        handler.addObject(new Player(WIDTH-64, HEIGHT/2 - 84, ID.Player2, handler));
        handler.addObject(new Ball(WIDTH/2 - 32,HEIGHT/2 - 32, ID.Ball, handler));
        
    }
    
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void run(){
        this.requestFocus();    //Look up what this does. 
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS : " + frames);
                frames = 0;
            }
        }
        stop();
    }
    
    private void tick(){
        handler.tick();
        //counter.tick();
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
        
        handler.render(g);
        
        //counter.render(g);
        
        g.dispose();
        bs.show();
    }
    
    public static int clamp(int var, int min, int max ){
        if(var >= max)  
            return max;
        else if(var <= min)
            return min;
        else
            return var;
    }
    
    public static void main(String[] args) {
        new Game();
    }
    
}
