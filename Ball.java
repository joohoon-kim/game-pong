/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joohoon.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Circle;

/**
 *
 * @author joohoonkim
 */
public class Ball extends GameObject{
    Random r = new Random();
    private Handler handler;
    public Ball(int x, int y, ID id, Handler handler){
        super(x,y,id);
        
        velX = 3;
        velY = 3;
        
        this.handler = handler;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x, y, 16, 16);
    }
    
    public void reset(int x, int y){
        this.setX(x);
        this.setY(y);
    }
    
    public void tick(){
        x += velX;
        y += velY;
        
        if(y <= 0 || y >= Game.HEIGHT - 32)
            velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 16){
            this.reset(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32);
            velX=3;
            velY=3;
            for(int i=0; i<handler.object.size();i++){
                GameObject tempObject = handler.object.get(i);
                if(tempObject.getId() == ID.Player){
                    handler.object.get(i).reset(45, Game.HEIGHT/2 - 84);
                    //handler.object.get(i).sleep(5000);
                }
                if(tempObject.getId() == ID.Player2){
                    handler.object.get(i).reset(Game.WIDTH-64, Game.HEIGHT/2 - 84);
                    //handler.object.get(i).sleep(5000);
                    
                }
            }
        }
        /*if(x <= 0)
            this.setX(Game.WIDTH/2 - 32);
            this.setY(Game.HEIGHT/2 - 32);*/

    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.drawOval(x, y, 16, 16);
        g.fillOval(x, y, 16, 16);
    }
    
}