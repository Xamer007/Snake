import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow (){
        setTitle("Змейка"); //название окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //крестик на окне закрывает
        setSize(320,345); //размер окна
        setLocation(400,400);
        add(new GameFeld());
        setVisible(true);

    }
    public static void main(String[]args){
        MainWindow MW = new MainWindow(); //зайпуск программы
    }
}
