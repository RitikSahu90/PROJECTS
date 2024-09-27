package src;
import javax.swing.*;

import src.game_logics.Flappegle;
public class app {
    public static void main(String[] args) throws Exception{
        int brodheight =640;
        int bordwidth=360;

        JFrame frame=new JFrame("EAGELE FLAP");
        frame.setLocationRelativeTo(null);
        frame.setSize(bordwidth, brodheight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Flappegle flapegale=new Flappegle();
        frame.add(flapegale);
        frame.pack();
        frame.setLocation(0, 0);
        flapegale.requestFocus();                                
        frame.setVisible(true);
    }
}
