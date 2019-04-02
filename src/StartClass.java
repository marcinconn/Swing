import javax.swing.*;
import java.awt.*;

public class StartClass {
    public static void main(String...args){
        EventQueue.invokeLater(()->
        {
            JFrame frame = new JFrame("Program wspomagający badanie zadowolenia klienta");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(550, 700);
            MyMenu demo = new MyMenu();
            frame.setJMenuBar(demo.createMB());
            MyForm.createForm(frame);
            frame.setVisible(true);
            ImageIcon img = new ImageIcon("C:\\Users\\Marcin\\Desktop\\uczelnia\\semestr_3\\programowanie_obiektowe\\FormSign.png");
            frame.setIconImage(img.getImage());
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        });
    }
}
