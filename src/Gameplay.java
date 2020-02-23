import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.MessageFormat;
import java.awt.Graphics2D;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private ImageIcon imagetitle;
    private int[] snakeXlenght=new int[750];
    private int[] snakeYlenght=new int[750];

    private int[] furitsX={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,
                            625,650,675,700,825,850};
    private int[] furitsY={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,
            625};

    private boolean left=false;
    private boolean right=false;
    private boolean up=false;
    private boolean down=false;

    private ImageIcon leftmouth;
    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon snakeimage;
    private ImageIcon fruits;


    //for the fruits location
    private Random random=new Random();
    private int Xpos=random.nextInt(30);
    private  int Ypos=random.nextInt(23);


    private int defultLenghtOfSnake=3;//defult lenght of snake

    private int moves=0;
    private Timer timer;

    private int delay=100;
    Gameplay()
    {
        //defult loction of snake
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();

    }
    public void paint(Graphics g)
    {
        Main m=new Main();
        if(moves==0)
        {
            snakeXlenght[2]=50;
            snakeXlenght[1]=75;
            snakeXlenght[0]=100;

            snakeYlenght[2]=100;
            snakeYlenght[1]=100;
            snakeYlenght[0]=100;
        }
        //draw the border of image
        g.setColor(Color.WHITE);
        g.drawRect(25,11,850,54);

        //draw image title.
        imagetitle =new ImageIcon("snaketitle.jpg");
        imagetitle.paintIcon(this,g,25,11);

        //draw the border for the playing area.
        g.setColor(Color.BLACK);
        g.drawRect(25,75,850,631);

        //for background for the gameplay
        g.setColor(Color.BLACK);
        g.fillRect(25,75,850,631);



        //draw the snake;
        rightmouth=new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this ,g,snakeXlenght[0],snakeYlenght[0]);

        for(int i=0;i<defultLenghtOfSnake;i++){
            if(i==0&& right)
            {
                rightmouth=new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this ,g,snakeXlenght[i],snakeYlenght[i]);
            }
           else if(i==0&& left)
            {
                leftmouth=new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this ,g,snakeXlenght[i],snakeYlenght[i]);
            }
            else if(i==0&& down)
            {
                downmouth=new ImageIcon("downmouth.png");
                rightmouth.paintIcon(this ,g,snakeXlenght[i],snakeYlenght[i]);
            }
            else if(i==0&& up)
            {
                upmouth=new ImageIcon("upmouth.png");
                upmouth.paintIcon(this ,g,snakeXlenght[i],snakeYlenght[i]);
            }
            else if(i!=0)
            {
                snakeimage=new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this,g,snakeXlenght[i],snakeYlenght[i]);
            }
        }
        //draw fruits
        fruits=new ImageIcon("enemy.png");
        if(furitsX[Xpos] ==snakeXlenght[0] &&  furitsY[Ypos]==snakeYlenght[0]){
            defultLenghtOfSnake++;
            Xpos=random.nextInt(30);
            Ypos=random.nextInt(23);

        }
        fruits.paintIcon(this, g,furitsX[Xpos],furitsY[Ypos]);
        for(int b=1;b<defultLenghtOfSnake;b++){
            if(snakeXlenght[b]==snakeXlenght[0]&& snakeYlenght[b]==snakeYlenght[0]){
                right=false;
                left=false;
                up=false;
                down=false;
            }
        }
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D)
        {
            moves++;
            right=true;
            if(!left)
            {
                right=true;//we we press the left button while snake in movine towrds the right direction.
            }
            else
            {
                right=false;
                left=true;
            }
            up=false;
            down=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A)
        {
            moves++;
            left=true;
            if(!right)
            {
                left=true;//we we press the left button while snake in movine towrds the right direction.
            }
            else
            {
                left=false;
                right=true;
            }
            up=false;
            down=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_W)
        {
            moves++;
            up=false;
            if(!down)
            {
                up=true;//we we press the left button while snake in movine towrds the right direction.
            }
            else
            {
                up=false;
                down=true;
            }
            right=false;
            left=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_S)
        {
            moves++;
            down =true;
            if(!up)
            {
                down=true;//we we press the left button while snake in movine towrds the right direction.
            }
            else
            {
                up=true;
                down=false;
            }
            left=false;
            right=false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(right){
            for (int i=defultLenghtOfSnake-1;i>=0;i--)
            {
                snakeYlenght[i+1]=snakeYlenght[i];
            }
            for(int i=defultLenghtOfSnake;i>=0;i--)
            {
                if(i==0)
                {
                    snakeXlenght[i]= snakeXlenght[i]+25;
                }
                else
                {
                    snakeXlenght[i]=snakeXlenght[i-1];
                }
                if (snakeXlenght[i]>850)
                {
                    snakeXlenght[i]=25;
                }
                repaint();
            }
        }
        if(left){
            for (int i=defultLenghtOfSnake-1;i>=0;i--)
            {
                snakeYlenght[i+1]=snakeYlenght[i];
            }
            for(int i=defultLenghtOfSnake;i>=0;i--)
            {
                if(i==0)
                {
                    snakeXlenght[i]= snakeXlenght[i]-25;
                }
                else
                {
                    snakeXlenght[i]=snakeXlenght[i-1];
                }
                if (snakeXlenght[i]<25)
                {
                    snakeXlenght[i]=850;
                }
                repaint();
            }
        }
        if(up){
            for (int i=defultLenghtOfSnake-1;i>=0;i--)
            {
                snakeXlenght[i+1]=snakeXlenght[i];
            }
            for(int i=defultLenghtOfSnake;i>=0;i--)
            {
                if(i==0)
                {
                    snakeYlenght[i]= snakeYlenght[i]-25;
                }
                else
                {
                    snakeYlenght[i]=snakeYlenght[i-1];
                }
                if (snakeYlenght[i]<75)
                {
                    snakeYlenght[i]=630;
                }
                repaint();
            }
        }
        if(down){
            for (int i=defultLenghtOfSnake-1;i>=0;i--)
            {
                snakeXlenght[i+1]=snakeXlenght[i];
            }
            for(int i=defultLenghtOfSnake;i>=0;i--)
            {
                if(i==0)
                {
                    snakeYlenght[i]= snakeYlenght[i]+25;
                }
                else
                {
                    snakeYlenght[i]=snakeYlenght[i-1];
                }
                if (snakeYlenght[i]>630)
                {
                    snakeYlenght[i]=75;
                }
                repaint();
            }
        }
    }

}
