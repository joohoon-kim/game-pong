/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joohoon.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joohoonkim
 */
public class Player extends GameObject{
    
    Random r = new Random();
    Handler handler;
    
    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x, y, 16, 128);
    }
    
    public void tick(){
        y += velY;

        y = Game.clamp(y, 0, Game.HEIGHT-150);
        
        collision();
    }
    
    private void collision(){
        for(int i=0; i< handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Ball){
                if(getBounds().intersects(tempObject.getBounds())){
                    tempObject.velX*=-(1+Math.abs(r.nextDouble()));
                    System.out.println(r.nextDouble());
                    tempObject.velY*=-(1+Math.abs(r.nextDouble()));
                }
            }
        }
    }
        
    public void reset(int x, int y){
        this.setX(x);
        this.setY(y);
        this.setVelY(0);
    }
    
    public void render(Graphics g){
        
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBounds());
        
        g.setColor(Color.white);
        g.fillRect(x, y, 16, 128);
    }
    
    public void sleep(int mill){
        try {
            Thread.sleep(mill);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
