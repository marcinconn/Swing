import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MyDeleteAll {
    /**
     * Tworzy ramkę z komponentami do usuwania wszystkich danych
     */
    public static void deletingAll(){
        JLabel delAll = new JLabel("Autoryzacja usunięcia");
        JButton delAllB = new JButton("USUN WSZYSTKO");
        JLabel warningDellAll = new JLabel(" ");
        GridBagLayout gbl = new GridBagLayout();
        JFrame fr = new JFrame();
        fr.setSize(600,700);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel pnl = new JPanel();
        pnl.setLayout(gbl);
        JButton intToConfirm = new JButton();
        intToConfirm.setEnabled(false);

        Random generator = new Random();
        final Integer k = generator.nextInt(900)+100;
        intToConfirm.setText(k.toString());

        MyCustomTextField fieldForInt = new MyCustomTextField(20);
        fieldForInt.setPlaceholder("WPISZ POWYZSZA LICZBE");

        pnl.add(delAll, new GBC(0,0).setAnchor(GBC.PAGE_START).setInsets(50,50,0,50));
        pnl.add(intToConfirm, new GBC(0,1).setAnchor(GBC.CENTER));
        pnl.add(new JPanel(), new GBC(0,2).setAnchor(GBC.CENTER));
        pnl.add(fieldForInt, new GBC(0,3).setAnchor(GBC.CENTER));
        pnl.add(new JPanel(), new GBC(0,4).setAnchor(GBC.CENTER));
        pnl.add(warningDellAll, new GBC(0,5).setAnchor(GBC.CENTER));
        pnl.add(new JPanel(), new GBC(0,6).setAnchor(GBC.CENTER));
        pnl.add(delAllB, new GBC(0,7).setAnchor(GBC.PAGE_END).setInsets(0,50,50,50));

        delAllB.addActionListener(ev -> {
            String test = fieldForInt.getText();
            Integer t = Integer.parseInt(test);

            if(t.intValue()!=k.intValue()){warningDellAll.setForeground(Color.RED);warningDellAll.setText("AUTORYZACJA NIEUDANA");} else {
                String dA1 = "DELETE FROM grades;";
                String dA2 = "DELETE FROM users;";
                MyConnection.updateDB(dA2);
                MyConnection.updateDB(dA1);
                fr.setVisible(false);
            }
        });

        JScrollPane scrlP1 = new JScrollPane(pnl);
        fr.setContentPane(scrlP1);
        fr.pack();
        ImageIcon img = new ImageIcon("C:\\Users\\Marcin\\Desktop\\uczelnia\\semestr_3\\programowanie_obiektowe\\FormSign.png");
        fr.setIconImage(img.getImage());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fr.setLocation(dim.width/2-fr.getSize().width/2, dim.height/2-fr.getSize().height/2);

        fr.setAlwaysOnTop(true);
        fr.setVisible(true);
    }
}
