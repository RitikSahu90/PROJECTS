package src.game_logics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Flappegle extends JPanel implements ActionListener , KeyListener{
    int brodheight =640;
    static int bordwidth=360;

    //Image
    Image eagle_upImage,eagle_downImage,backgImage,pillarsImage,bottompillar;

    //Eagle
    int eaglex=bordwidth/8;
    int eagley=brodheight/2; 

    class Eagle{
        int x=eaglex;
        int y=eagley;
        int width=34;
        Image img;
        int height=24;
        public Eagle(Image img){
            this.img=img;
        }
    }

    //Pipes
    static int pipex =bordwidth;
    static int pipey=0;
    static int pipewidth=64;
    static int pipeheight=512;


    //Game Logic
    Eagle eagle;
    Timer gameloop;
    Timer pillaTimer;
    int velocityY=0;
    int velocityX=-4;
    int gravity=1;
    double score=0;
    
    boolean game_over=false;

    ArrayList<Pipes> pipe;
    Random random=new Random();

    public Flappegle(){
        setPreferredSize(new Dimension(bordwidth,brodheight));
        setFocusable(true);
        addKeyListener(this);

        //Set image
        eagle_downImage=new ImageIcon("E:\\vscode\\PLANE_FLY\\src\\game_resourse\\Flappy_Bird_down.png").getImage();
        eagle_upImage=new ImageIcon("E:\\vscode\\PLANE_FLY\\src\\game_resourse\\Flappy-Bird-on.png").getImage();
        backgImage=new ImageIcon("E:\\vscode\\PLANE_FLY\\src\\game_resourse\\background.png").getImage();
        pillarsImage=new ImageIcon("E:\\vscode\\PLANE_FLY\\src\\game_resourse\\pillar.png").getImage();
        bottompillar =new ImageIcon("E:\\vscode\\PLANE_FLY\\src\\game_resourse\\pillardown2.png").getImage();

        //eagle
        eagle=new Eagle(eagle_downImage);

        //Pillar
        pipe =new ArrayList<Pipes>();
        pillaTimer=new Timer(1500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                palce_pipe();
            }
            
        });
        //game timer
        gameloop=new Timer(1200/60,this);
        gameloop.start();
        pillaTimer.start();
    }
    
    //Pipe Place using random
    public void palce_pipe(){
        int ranpillar= (int)(pipey-pipeheight/4 -Math.random()*(pipeheight/2)); 
        int openspace = brodheight/4;
        Pipes place=new Pipes(pillarsImage);
        place.y=ranpillar;
        pipe.add(place);
        Pipes bottom=new Pipes(bottompillar);
        bottom.y=place.y+pipeheight+openspace;
        pipe.add(bottom);
    }

    public void paintComponent(Graphics gr){
        super.paintComponent(gr);
        draw(gr);
    }
    public void draw(Graphics g){
        //Background
        g.drawImage(backgImage, 0, 0, bordwidth,brodheight,null);

        //EAgle 
        g.drawImage(eagle.img, eagle.x, eagle.y, eagle.width, eagle.width, null);

        //Pipes
        for (int i = 0; i < pipe.size(); i++) {
            Pipes pipes=pipe.get(i);
            g.drawImage(pipes.img, pipes.x, pipes.y, pipes.width, pipes.height,null);
        }

        //Score 
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (game_over) {
            g.drawString("Gaame Over : "+(int)score, 10, 35);
        }
        else{
            g.drawString("Current Score : "+(int)score, 10, 35);
        }
    }

    public void move(){
        velocityY+=gravity;
        eagle.y+=velocityY;
        eagle.y = Math.max(eagle.y, 0);

        //Pipes
        for (int i = pipe.size() - 1; i >= 0; i--) {
            Pipes pipes =pipe.get(i);
            pipes.x+=velocityX;
            if (!pipes.passed && eagle.x >pipes.x+pipes.width){
                pipes.passed=true;
                score+=0.5;
            }
            if (collied(eagle, pipes)) {
                game_over=true;
            }
        }
        if (eagle.y>=brodheight || eagle.y==0) {
            game_over=true;
        }
    }

    public boolean collied(Eagle e, Pipes P){
        return !(e.x+e.width < P.x || 
        e.x > P.x+P.width ||
        e.y+e.height < P.y || 
        e.y > P.y+P.height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (game_over) {
            pillaTimer.stop();
            gameloop.stop();

            SwingUtilities.invokeLater(() -> {
                new Gameover().createbox();
            });
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
