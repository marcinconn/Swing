import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class MyDelete implements ItemListener {
    JPanel cards;
    final static String deleteGrades = "Usuń wpis z ocenami";
    final static String deleteUser = "Usuń nick";
    protected boolean ready = true;

    /**
     * Dodaje komponenty do kontenera
     * @param pane kontener, do którego dodawane są elementy
     */
    public void addComponentToPane(Container pane) {
        GridBagLayout gblD = new GridBagLayout();
        JPanel comboBoxPane = new JPanel();
        String comboBoxItems[] = { deleteGrades, deleteUser };
        JComboBox cb = new JComboBox(comboBoxItems);
        comboBoxPane.setLayout(gblD);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb,new GBC(0,0).setInsets(15,5,2,5));

        JPanel card1 = new JPanel();
        JTextField idForGrades = new JTextField(10);
        JLabel warningDG = new JLabel(" ");
        JButton delGrades = new JButton("Usuń rekord ocen");
        card1.setLayout(gblD);
        card1.add(new JLabel("Podaj lp wpisu do usunięcia"), new GBC(0,0).setAnchor(GBC.PAGE_START).setInsets(40,0,0,0));
        card1.add(idForGrades, new GBC(0,1).setInsets(0,100,0,100));
        card1.add(warningDG, new GBC(0,2).setAnchor(GBC.CENTER));
        card1.add(delGrades, new GBC(0,3).setAnchor(GBC.PAGE_END).setInsets(2,0,15,0));

        JPanel card2 = new JPanel();
        JTextField idForUsers = new JTextField(10);
        JLabel warningDU = new JLabel(" ");
        JButton delUsers = new JButton("Usuń nick");
        card2.setLayout(gblD);
        card2.add(new JLabel("Podaj nick do usunięcia"), new GBC(0,0).setAnchor(GBC.PAGE_START).setInsets(40,0,0,0));
        card2.add(idForUsers, new GBC(0,1).setInsets(0,100,2,100));
        card2.add(warningDU, new GBC(0,2).setAnchor(GBC.CENTER));
        card2.add(delUsers, new GBC(0,3).setAnchor(GBC.PAGE_END).setInsets(2,0,15,0));

        delGrades.addActionListener(ev -> {
            String dG = idForGrades.getText();
            try{
                Integer.parseInt(dG);
            }
            catch (NumberFormatException ex){
                warningDG.setForeground(Color.RED);
                warningDG.setText("PODAJ LICZBE");
                ready = false;
            }
            if(ready){
            int dGlen = dG.length();
            if(dGlen==0){warningDG.setForeground(Color.RED); warningDG.setText("POLE PUSTE");} else{
                StringBuilder chechGIf = new StringBuilder();
                chechGIf.append("SELECT ID_G FROM grades WHERE ID_G = ");
                chechGIf.append(dG);
                chechGIf.append(";");
                String gradesQuery = chechGIf.toString();
                boolean isHereG = MyConnection.isHere(gradesQuery);
                if(!isHereG){warningDG.setForeground(Color.RED); warningDG.setText("NIE MA TAKIEGO WPISU");} else {
                    JFrame fdG = new JFrame();
                    fdG.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    fdG.setSize(400, 300);

                    JLabel deG = new JLabel("Autoryzacja usunięcia, przepisz podaną liczbę");
                    JButton delG = new JButton("USUN WPIS");
                    JLabel warningDelG = new JLabel(" ");
                    JButton intToConfirm = new JButton();
                    intToConfirm.setEnabled(false);
                    JTextField fieldForInt = new JTextField(20);

                    GridBagLayout gbl = new GridBagLayout();
                    JPanel pnl = new JPanel();
                    pnl.setLayout(gbl);

                    Random generator = new Random();
                    final Integer k = generator.nextInt(900) + 100;
                    intToConfirm.setText(k.toString());

                    pnl.add(deG, new GBC(0, 0).setAnchor(GBC.PAGE_START).setInsets(50, 50, 0, 50));
                    pnl.add(intToConfirm, new GBC(0, 1).setAnchor(GBC.CENTER));
                    pnl.add(new JPanel(), new GBC(0, 2).setAnchor(GBC.CENTER));
                    pnl.add(fieldForInt, new GBC(0, 3).setAnchor(GBC.CENTER));
                    pnl.add(new JPanel(), new GBC(0, 4).setAnchor(GBC.CENTER));
                    pnl.add(warningDelG, new GBC(0, 5).setAnchor(GBC.CENTER));
                    pnl.add(new JPanel(), new GBC(0, 6).setAnchor(GBC.CENTER));
                    pnl.add(delG, new GBC(0, 7).setAnchor(GBC.PAGE_END).setInsets(0, 50, 50, 50));
                    JScrollPane scrlG = new JScrollPane(pnl);

                    delG.addActionListener(e1 -> {
                        String test = fieldForInt.getText();
                        String delIDG = idForGrades.getText();
                        Integer t = Integer.parseInt(test);

                        if (t.intValue() != k.intValue()) {
                            warningDelG.setForeground(Color.RED);
                            warningDelG.setText("AUTORYZACJA NIEUDANA");
                        } else {
                            StringBuilder dA1 = new StringBuilder();
                            dA1.append("DELETE FROM grades WHERE ID_G = ");
                            dA1.append(delIDG);
                            dA1.append(";");
                            String dA = dA1.toString();
                            MyConnection.updateDB(dA);
                            fdG.setVisible(false);
                        }
                    });
                    ImageIcon img = new ImageIcon("C:\\Users\\Marcin\\Desktop\\uczelnia\\semestr_3\\programowanie_obiektowe\\FormSign.png");
                    fdG.setIconImage(img.getImage());

                    fdG.setContentPane(scrlG);
                    fdG.pack();

                    fdG.setAlwaysOnTop(true);
                    fdG.setVisible(true);
                    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(comboBoxPane);
                    topFrame.setVisible(false);
                }
                }
            }
        });

        delUsers.addActionListener(ev -> {
            String dU = idForUsers.getText();
            int dUlen = dU.length();
            if(dUlen==0){warningDU.setForeground(Color.RED); warningDU.setText("POLE PUSTE");} else{
                StringBuilder gradesMaking = new StringBuilder();
                gradesMaking.append("SELECT * FROM users WHERE User = '");
                gradesMaking.append(dU);
                gradesMaking.append("';");
                String usersQuery = gradesMaking.toString();
                boolean isHereU = MyConnection.isHere(usersQuery);
                if(!isHereU){warningDU.setForeground(Color.RED); warningDU.setText("NIE MA TAKIEGO NICKU");} else {
                    JFrame fdU = new JFrame();
                    fdU.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    fdU.setSize(400,300);

                    JLabel deU = new JLabel("Autoryzacja usunięcia, przepisz podaną liczbę");
                    JButton delU = new JButton("USUN NICK");
                    JLabel warningDelU = new JLabel(" ");
                    JButton intToConfirm = new JButton();
                    intToConfirm.setEnabled(false);
                    JTextField fieldForInt = new JTextField(20);

                    GridBagLayout gbl = new GridBagLayout();
                    JPanel pnl = new JPanel();
                    pnl.setLayout(gbl);

                    Random generator = new Random();
                    final Integer k=generator.nextInt(900)+100;
                    intToConfirm.setText(k.toString());

                    pnl.add(deU, new GBC(0,0).setAnchor(GBC.PAGE_START).setInsets(50,50,0,50));
                    pnl.add(intToConfirm, new GBC(0,1).setAnchor(GBC.CENTER));
                    pnl.add(new JPanel(), new GBC(0,2).setAnchor(GBC.CENTER));
                    pnl.add(fieldForInt, new GBC(0,3).setAnchor(GBC.CENTER));
                    pnl.add(new JPanel(), new GBC(0,4).setAnchor(GBC.CENTER));
                    pnl.add(warningDelU, new GBC(0,5).setAnchor(GBC.CENTER));
                    pnl.add(new JPanel(), new GBC(0,6).setAnchor(GBC.CENTER));
                    pnl.add(delU, new GBC(0,7).setAnchor(GBC.PAGE_END).setInsets(0,50,50,50));
                    JScrollPane scrlG = new JScrollPane(pnl);

                    delU.addActionListener(e2 -> {
                        String test = fieldForInt.getText();
                        Integer t = Integer.parseInt(test);

                        if(t.intValue()!=k.intValue()){warningDelU.setForeground(Color.RED);warningDelU.setText("AUTORYZACJA NIEUDANA");} else {
                            StringBuilder dA1 = new StringBuilder();
                            dA1.append("DELETE FROM users WHERE User = '");
                            dA1.append(dU);
                            dA1.append("';");
                            String dA = dA1.toString();
                            MyConnection.updateDB(dA);
                            fdU.setVisible(false);
                        }
                    });

                    ImageIcon img = new ImageIcon("C:\\Users\\Marcin\\Desktop\\uczelnia\\semestr_3\\programowanie_obiektowe\\FormSign.png");
                    fdU.setIconImage(img.getImage());
                    fdU.setContentPane(scrlG);
                    fdU.pack();

                    fdU.setAlwaysOnTop(true);
                    fdU.setVisible(true);
                    JFrame topFrame = (JFrame)SwingUtilities.getWindowAncestor(comboBoxPane);
                    topFrame.setVisible(false);
                }
            }
        });

        cards = new JPanel(new CardLayout());
        cards.add(card1, deleteGrades);
        cards.add(card2, deleteUser);

        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }

    /**
     * Tworzy ramkę z komponentami
     */
    public static void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700,700);
        ImageIcon img = new ImageIcon("C:\\Users\\Marcin\\Desktop\\uczelnia\\semestr_3\\programowanie_obiektowe\\FormSign.png");
        frame.setIconImage(img.getImage());

        MyDelete demo = new MyDelete();
        demo.addComponentToPane(frame.getContentPane());

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.pack();
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }
}
