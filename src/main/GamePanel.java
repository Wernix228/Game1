package main;

import javax.swing.*;

import entity.Player;

import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int FPS = 120;

    KeyHandler KeyH = new KeyHandler();
Thread gameThread;
Player player = new Player(this,KeyH);

    int PlayerX = 100;
    int PlayerY = 100;
    int PlayerSpeed = 2;

    public GamePanel() {


        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
//    public void run() {
//
//        double drawInterval = 1000000000/FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while (gameThread != null) {
//
//
//        long currentTime = System.nanoTime();
//            System.out.println("Местное время"+currentTime);
//            update();
//
//            repaint();
//
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1000000;
//            if(remainingTime < 0)
//              remainingTime = 0;                    {
//            }
//                Thread.sleep((long)remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer+= (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
         if(timer >=1000000000){
             System.out.println("FPS:" + drawCount);
             drawCount = 0;
             timer = 0;
         }
        }
    }
    public void update(){

        if(KeyH.upPressed == true){
        PlayerY -= PlayerSpeed;
        }
        else if(KeyH.downPressed == true){
            PlayerY += PlayerSpeed;
        }
        else if(KeyH.leftPressed == true){
            PlayerX -= PlayerSpeed;
        }
        else if(KeyH.rightPressed == true){
            PlayerX += PlayerSpeed;
        }



    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(PlayerX,PlayerY,tileSize,tileSize);

        g2.dispose();
    }
}