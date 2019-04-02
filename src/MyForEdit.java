import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class MyForEdit implements ItemListener {
    JPanel cards;
    final static String gradesPanel = "Edycja ocen";
    final static String usersPanel = "Edycja nicku";
    protected boolean ready = true;

    /**
     * Dodaje komponenty do kontenera
     * @param pane kontener, do którego dodawane są elementy
     */
    public void addComponentToPane(Container pane) {
        GridBagLayout gblE = new GridBagLayout();
        JPanel comboBoxPane = new JPanel();
        String comboBoxItems[] = {gradesPanel , usersPanel };
        JComboBox cb = new JComboBox(comboBoxItems);
        comboBoxPane.setLayout(gblE);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb, new GBC(0,0).setInsets(15,5,2,5));

        JPanel card1 = new JPanel();
        JTextField idForGrades = new JTextField(10);
        JLabel warningG = new JLabel(" ");
        JButton editGrades = new JButton("Edytuj wpis");

        card1.setLayout(gblE);
        card1.add(new JLabel("Podaj lp wpisu do edycji"), new GBC(0,0).setAnchor(GBC.PAGE_START).setInsets(40,0,0,0));
        card1.add(idForGrades, new GBC(0,1).setInsets(0,100,0,100));
        card1.add(warningG, new GBC(0,2).setAnchor(GBC.CENTER));
        card1.add(editGrades, new GBC(0,3).setAnchor(GBC.PAGE_END).setInsets(2,0,15,0));

        JPanel card2 = new JPanel();
        JTextField idForUsers = new JTextField(10);
        JLabel warningU = new JLabel(" ");
        JButton editUsers = new JButton("Edytuj nick");
        card2.setLayout(gblE);
        card2.add(new JLabel("Podaj nick do edycji"), new GBC(0,0).setAnchor(GBC.PAGE_START).setInsets(40,0,0,0));
        card2.add(idForUsers, new GBC(0,1).setInsets(0,100,2,100));
        card2.add(warningU, new GBC(0,2).setAnchor(GBC.CENTER));
        card2.add(editUsers, new GBC(0,3).setAnchor(GBC.PAGE_END).setInsets(2,0,15,0));

        editGrades.addActionListener(ev -> {
            String eG = idForGrades.getText();
            try{
                Integer.parseInt(eG);
            }
            catch(NumberFormatException ex){
                warningG.setForeground(Color.RED);
                warningG.setText("PODAJ LICZBE");
                ready = false;
            }
            if(ready){
            int eGlen = eG.length();
            if(eGlen==0){warningG.setForeground(Color.RED); warningG.setText("POLE PUSTE");} else{
                StringBuilder chechGIf = new StringBuilder();
                chechGIf.append("SELECT ID_G FROM grades WHERE ID_G = ");
                chechGIf.append(eG);
                chechGIf.append(";");
                String gradesQuery = chechGIf.toString();
                boolean isHereG = MyConnection.isHere(gradesQuery);
                if(!isHereG){warningG.setForeground(Color.RED); warningG.setText("NIE MA TAKIEGO WPISU");} else {
                    StringBuilder gradesMaking = new StringBuilder();
                    gradesMaking.append("SELECT grades.ID_G, grades.Quality, grades.Report, grades.Promptness, grades.Complaint, grades.Current, ");
                    gradesMaking.append("grades.Contact, grades.Warnings, users.User FROM grades LEFT JOIN users ON grades.Filler = users.ID_U WHERE grades.ID_G = ");
                    gradesMaking.append(eG);
                    gradesMaking.append(";");
                    String gradesQuery2 = gradesMaking.toString();
                    ArrayList<Object> vG = MyConnection.prepareEditforGrades(gradesQuery2);
                    MyGradesEdit.createGradesEdit(vG);
                    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(editGrades);
                    topFrame.setVisible(false);
                }
                }
            }
        });

        editUsers.addActionListener(ev -> {
            String eU = idForUsers.getText();
            int eUlen = eU.length();
            if(eUlen==0){warningU.setForeground(Color.RED); warningU.setText("POLE PUSTE");} else{
                StringBuilder gradesMaking = new StringBuilder();
                gradesMaking.append("SELECT * FROM users WHERE User = '");
                gradesMaking.append(eU);
                gradesMaking.append("';");
                String usersQuery = gradesMaking.toString();
                boolean isHereU = MyConnection.isHere(usersQuery);
                if(!isHereU){warningU.setForeground(Color.RED); warningU.setText("NIE MA TAKIEGO NICKU");} else {
                    StringBuilder strU = new StringBuilder();
                    strU.append("SELECT * FROM users WHERE User = '");
                    strU.append(eU);
                    strU.append("';");
                    String userQuery = strU.toString();
                    ArrayList<String> vU = MyConnection.prepareEdit(userQuery);
                    editUsers.setText(userQuery);
                    int idU = Integer.parseInt(vU.get(0));
                    JPanel pnl = new JPanel();
                    pnl.setLayout(gblE);
                    JLabel oldNick = new JLabel("Stary nick: ");
                    JLabel newNick = new JLabel("Nowy nick: ");
                    JLabel nickAchtung = new JLabel(" ");
                    JButton oldNickPlace = new JButton(vU.get(1));
                    oldNickPlace.setEnabled(false);
                    JTextField newNickPlace = new JTextField(10);
                    JButton confirmChanges = new JButton("Zatwierdź zmiany");
                    confirmChanges.addActionListener(eve -> {
                        String newN = newNickPlace.getText();
                        int newNlen = newN.length();
                        if(newNlen==0){nickAchtung.setForeground(Color.RED); nickAchtung.setText("WPISZ NOWY NICK");} else {
                            StringBuilder uQuery = new StringBuilder();
                            uQuery.append("UPDATE users SET User = '");
                            uQuery.append(newN);
                            uQuery.append("' WHERE ID_U = ");
                            uQuery.append(vU.get(0));
                            uQuery.append(";");
                            String finalUserQuery = uQuery.toString();
                            MyConnection.updateDB(finalUserQuery);
                            JFrame topFrame = (JFrame)SwingUtilities.getWindowAncestor(confirmChanges);
                            topFrame.setVisible(false);

                        }
                    });
                    pnl.add(oldNick, new GBC(0,0).setAnchor(GBC.LINE_START).setInsets(20,10,0,10));
                    pnl.add(oldNickPlace, new GBC(0,1).setAnchor(GBC.CENTER));
                    pnl.add(new JPanel(), new GBC(0,2));
                    pnl.add(newNick, new GBC(0,3).setAnchor(GBC.LINE_START));
                    pnl.add(newNickPlace, new GBC(0,4).setAnchor(GBC.CENTER));
                    pnl.add(new JPanel(), new GBC(0,5));
                    pnl.add(nickAchtung, new GBC(0,6).setAnchor(GBC.CENTER));
                    pnl.add(confirmChanges, new GBC(0,7).setInsets(0,10,25,10));
                    JScrollPane scrU = new JScrollPane(pnl);
                    JFrame topFrame = (JFrame)SwingUtilities.getWindowAncestor(comboBoxPane);
                    topFrame.setContentPane(scrU);
                }
            }
        });

        cards = new JPanel(new CardLayout());
        cards.add(card1, gradesPanel);
        cards.add(card2, usersPanel);

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
        //Create and set up the window.
        JFrame frame = new JFrame("Edycja");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 700);
        ImageIcon img = new ImageIcon("C:\\Users\\Marcin\\Desktop\\uczelnia\\semestr_3\\programowanie_obiektowe\\FormSign.png");
        frame.setIconImage(img.getImage());

        MyForEdit demo = new MyForEdit();
        demo.addComponentToPane(frame.getContentPane());

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.pack();
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }
}
