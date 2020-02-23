import javax.swing.*;
import java.awt.*;

public class Main {
    public  static void main(String [] arg){
        JFrame frame=new JFrame();
        Gameplay gp=new Gameplay();
        frame.add(gp);
        frame.setBounds(10,10,900,750);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /*JTextArea textaa;

        textaa =new JTextArea();
        frame.add(textaa);
        textaa.setBounds(900,11,500,700);
        textaa.setBackground(Color.white);*/

    }
}
