import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;




public class GameFeld extends JPanel implements ActionListener {
    private final int SIZE = 320; //поле
    private final int DOT_SIZE = 16; // размер змейки
    private final int ALL_DOTS = 400; // сколько единиц может поместится на поле
    private Image dot;
    private Image apple; //
    private int appleX; //сохраняем положение яблок по оси Х
    private int appleY; //Сохраняем положение яблок по оси Y
    private int[] x = new int[ALL_DOTS]; //сохраняем положение змейки
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;


    public GameFeld(){ //создаем методы игры
        setBackground(Color.BLACK);
        loadImage(); // подкружаем иконки в игру
        initGame(); //вызывваем игру

    }

    public void initGame(){ //создаем метод
        dots = 3;
        for (int i = 0; i< dots; i++){
            x[i] = 48 - i*DOT_SIZE;//48-i потому чтол кратнгое 16 размеру
            y[i] = 48;

        }
        timer = new Timer(250, this);
        timer.start();
        creatrApple();
    }
    public void creatrApple(){
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }
    public void loadImage(){ //определяем иконки для объектов
       ImageIcon iia = new ImageIcon("apple.png");
       apple = iia.getImage();
       ImageIcon iid = new ImageIcon("dot.png");
       dot = iid.getImage();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame){
            g.drawImage(apple,appleX,appleX,this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        }
    }

    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];

        }
        if (left) {
            x[0] -= DOT_SIZE;
        }
        if (right) {
            x[0] += DOT_SIZE;
        }
        if (up) {
            y[0] -= DOT_SIZE;
        }
        if (down) {
            y[0] += DOT_SIZE;
        }
    }

    public void checkApple(){
            if (x[0] == appleX && y[0] == appleY){
                dots++;
                creatrApple();
            }
        }

    public void chekCollisions(){
        for (int i = dots; i>0; i--){
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }
        }
        if (x[0]>SIZE){
            inGame = false;
        }
        if (x[0]<0){
            inGame = false;
        }
        if (y[0]>SIZE){
            inGame = false;
        }
        if (y[0]<0){
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame){
            checkApple();
            chekCollisions();
            move();
        }
        repaint();
    }
}
