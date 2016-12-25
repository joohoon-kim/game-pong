/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joohoon.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author joohoonkim
 */
public class Counter{
    
    private int playerOnePoints = 0;
    private int playerTwoPoints = 0;
    public Handler handler;
    
    public Counter(int p1Points, int p2Points, Handler handler){
        
        playerOnePoints = p1Points;
        playerTwoPoints = p2Points;
        this.handler = handler;
        
    }
    
    public void tick(){
        
        for(int i=0; i<=handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Ball){
                GameObject tempObject = handler.object.get(i);
                if(tempObject.getX() <= 0)
                    playerOnePoints += 1;
                if(tempObject.getX() >= Game.WIDTH - 16)
                    playerTwoPoints += 2;
            }
        }
        
    }
    
    public void render(Graphics g){
        
        g.setColor(Color.white);
        g.drawString(Integer.toString(playerOnePoints), 15, 15);
        
        //g.setColor(Color.white);
        //g.drawString(Integer.toString(playerTwoPoints), 15, 100);
        
        
    }

}
