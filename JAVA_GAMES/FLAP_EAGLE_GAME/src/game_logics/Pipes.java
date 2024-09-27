package src.game_logics;

import java.awt.Image;

public class Pipes {
    int x=Flappegle.pipex;
    int y=Flappegle.pipey;
    int width=Flappegle.pipewidth;
    int height=Flappegle.pipeheight;
    Image img;
    boolean passed =false;
    public Pipes(Image img){
        this.img=img;
    }
}
