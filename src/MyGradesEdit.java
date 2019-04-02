import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class MyGradesEdit {

    /**
     * Tworzy ramkę z odpowiedziami do edycji
     * @param al dane do edycji
     */
    public static void createGradesEdit(ArrayList<Object> al){
        GridBagLayout gblGE = new GridBagLayout();
        JTextField qualityGE = new JTextField(10);
        JTextField reportGE = new JTextField(10);
        JTextField promptnessGE = new JTextField(10);
        JTextField complaintGE = new JTextField(10);
        JTextField currentCooperationGE = new JTextField(10);
        JTextField contactGE = new JTextField(10);
        JTextField nickGE = new JTextField(10);
        JTextArea warningsGE = new JTextArea(12,40);
        JScrollPane warningScrl = new JScrollPane(warningsGE);
        warningsGE.setWrapStyleWord(true);
        JLabel qualityLE = new JLabel("Jakość wykonanych badań");
        JLabel reportLE = new JLabel("Jakość sprawozdania z badań");
        JLabel promptnessLE = new JLabel("Terminowość usług");
        JLabel complaintLE = new JLabel("Reklamacje");
        JLabel currentCooperationLE = new JLabel("Bieżąca współpraca");
        JLabel contactLE = new JLabel("Latwość kontaktu");
        JLabel nickLE = new JLabel("Nick");
        JLabel warningsLE = new JLabel("Uwagi");
        JLabel gradesAchtung = new JLabel(" ");
        JButton confirmChange = new JButton("Zatwierdź zmiany");

        qualityGE.setText(al.get(1).toString());
        reportGE.setText(al.get(2).toString());
        promptnessGE.setText(al.get(3).toString());
        complaintGE.setText(al.get(4).toString());
        currentCooperationGE.setText(al.get(5).toString());
        contactGE.setText(al.get(6).toString());
        warningsGE.setText(al.get(7).toString());
        nickGE.setText(al.get(8).toString());
        Integer idG = Integer.parseInt(al.get(0).toString());

        JPanel qualityPanel = new JPanel();
        qualityPanel.setLayout(gblGE);
        qualityPanel.add(qualityLE, new GBC(0,0).setAnchor(GBC.LINE_START));
        qualityPanel.add(new JPanel(), new GBC(1,0).setAnchor(GBC.CENTER));
        qualityPanel.add(qualityGE, new GBC(2,0).setAnchor(GBC.LINE_END));

        JPanel reportPanel = new JPanel();
        reportPanel.setLayout(gblGE);
        reportPanel.add(reportLE, new GBC(0,0).setAnchor(GBC.LINE_START));
        reportPanel.add(new JPanel(), new GBC(1,0).setAnchor(GBC.CENTER));
        reportPanel.add(reportGE, new GBC(2,0).setAnchor(GBC.LINE_END));

        JPanel promptnessPanel = new JPanel();
        promptnessPanel.setLayout(gblGE);
        promptnessPanel.add(promptnessLE, new GBC(0,0).setAnchor(GBC.LINE_START));
        promptnessPanel.add(new JPanel(), new GBC(1,0).setAnchor(GBC.CENTER));
        promptnessPanel.add(promptnessGE, new GBC(2,0).setAnchor(GBC.LINE_END));

        JPanel complaintPanel = new JPanel();
        complaintPanel.setLayout(gblGE);
        complaintPanel.add(complaintLE, new GBC(0,0).setAnchor(GBC.LINE_START));
        complaintPanel.add(new JPanel(), new GBC(1,0).setAnchor(GBC.CENTER));
        complaintPanel.add(complaintGE, new GBC(2,0).setAnchor(GBC.LINE_END));

        JPanel currentPanel = new JPanel();
        currentPanel.setLayout(gblGE);
        currentPanel.add(currentCooperationLE, new GBC(0,0).setAnchor(GBC.LINE_START));
        currentPanel.add(new JPanel(), new GBC(1,0).setAnchor(GBC.CENTER));
        currentPanel.add(currentCooperationGE, new GBC(2,0).setAnchor(GBC.LINE_END));

        JPanel contactPanel = new JPanel();
        contactPanel.setLayout(gblGE);
        contactPanel.add(contactLE, new GBC(0,0).setAnchor(GBC.LINE_START));
        contactPanel.add(new JPanel(), new GBC(1,0).setAnchor(GBC.CENTER));
        contactPanel.add(contactGE, new GBC(2,0).setAnchor(GBC.LINE_END));

        JPanel nickPanel = new JPanel();
        nickPanel.setLayout(gblGE);
        nickPanel.add(nickLE, new GBC(0,0).setAnchor(GBC.LINE_START));
        nickPanel.add(new JPanel(), new GBC(1,0).setAnchor(GBC.CENTER));
        nickPanel.add(nickGE, new GBC(2,0).setAnchor(GBC.LINE_END));

        JPanel warningsPanel = new JPanel();
        warningsPanel.setLayout(gblGE);
        warningsPanel.add(warningsLE, new GBC(0,0).setAnchor(GBC.CENTER));
        warningsPanel.add(warningScrl, new GBC(0,1).setAnchor(GBC.CENTER));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(gblGE);
        buttonPanel.add(confirmChange, new GBC(2,0).setAnchor(GBC.CENTER));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(gblGE);
        mainPanel.add(qualityPanel, new GBC(0,0).setAnchor(GBC.LINE_START));
        mainPanel.add(new JPanel(), new GBC(0,1).setAnchor(GBC.LINE_START));
        mainPanel.add(reportPanel, new GBC(0,2).setAnchor(GBC.LINE_START));
        mainPanel.add(new JPanel(), new GBC(0,3).setAnchor(GBC.LINE_START));
        mainPanel.add(promptnessPanel, new GBC(0,4).setAnchor(GBC.LINE_START));
        mainPanel.add(new JPanel(), new GBC(0,5).setAnchor(GBC.LINE_START));
        mainPanel.add(complaintPanel, new GBC(0,6).setAnchor(GBC.LINE_START));
        mainPanel.add(new JPanel(), new GBC(0,7).setAnchor(GBC.LINE_START));
        mainPanel.add(currentPanel, new GBC(0,8).setAnchor(GBC.LINE_START));
        mainPanel.add(new JPanel(), new GBC(0,9).setAnchor(GBC.LINE_START));
        mainPanel.add(contactPanel, new GBC(0,10).setAnchor(GBC.LINE_START));
        mainPanel.add(new JPanel(), new GBC(0,11).setAnchor(GBC.LINE_START));
        mainPanel.add(warningsPanel, new GBC(0,12).setAnchor(GBC.LINE_START));
        mainPanel.add(new JPanel(), new GBC(0,13).setAnchor(GBC.LINE_START));
        mainPanel.add(nickPanel, new GBC(0,14).setAnchor(GBC.LINE_START));
        mainPanel.add(new JPanel(), new GBC(0,15).setAnchor(GBC.LINE_START));
        mainPanel.add(gradesAchtung, new GBC(0,16).setAnchor(GBC.CENTER));
        mainPanel.add(new JPanel(), new GBC(0,17).setAnchor(GBC.CENTER));
        mainPanel.add(buttonPanel, new GBC(0,18).setAnchor(GBC.CENTER));

        confirmChange.addActionListener(ev -> {
            String qualityStr = qualityGE.getText();
            String reportStr = reportGE.getText();
            String promptnessStr = promptnessGE.getText();
            String complaintStr = complaintGE.getText();
            String currentStr = currentCooperationGE.getText();
            String contactStr = contactGE.getText();
            String warningsStr = warningsGE.getText();
            String nickStr = nickGE.getText();
            boolean isReady = true;
            int val1=0, val2=0, val3=0, val4=0, val5=0, val6=0;
            try {
                val1 = Integer.parseInt(qualityStr);
                val2 = Integer.parseInt(reportStr);
                val3 = Integer.parseInt(promptnessStr);
                val4 = Integer.parseInt(complaintStr);
                val5 = Integer.parseInt(currentStr);
                val6 = Integer.parseInt(contactStr);
            }
            catch (Exception ex){isReady=false;gradesAchtung.setForeground(Color.RED); gradesAchtung.setText("WPISZ LICZBE");}
            if(!isReady){} else{
            StringBuilder checking = new StringBuilder();
            checking.append("SELECT ID_U FROM users WHERE EXISTS (SELECT User FROM users WHERE User = '");
            checking.append(nickStr);
            checking.append("')");
            String com1 = checking.toString();
            boolean isem = MyConnection.isInDB(com1);
            if(isem) {gradesAchtung.setForeground(Color.RED); gradesAchtung.setText("BRAK NICKU W BAZIE");} else {
                if ((val1 != 1 && val1 != 2 && val1 != 3) || (val2 != 1 && val2 != 2 && val2 != 3) || (val3 != 1 && val3 != 2 && val3 != 3) ||
                        (val4 != 1 && val4 != 2 && val4 != 3) || (val5 != 1 && val5 != 2 && val5 != 3) || (val6 != 1 && val6 != 2 && val6 != 3)) {
                    gradesAchtung.setForeground(Color.RED);
                    gradesAchtung.setText("PODAJ OCENE W SKALI 1-3");
                } else {
                    StringBuilder findNewUID = new StringBuilder();
                    findNewUID.append("SELECT ID_U FROM users WHERE User = '");
                    findNewUID.append(nickStr);
                    findNewUID.append("';");
                    String findNewUserID = findNewUID.toString();
                    Integer indU = MyConnection.findIndex(findNewUserID);

                    StringBuilder gradesUpdate = new StringBuilder();
                    gradesUpdate.append("UPDATE grades SET Quality = ");
                    gradesUpdate.append(qualityStr);
                    gradesUpdate.append(", Report = ");
                    gradesUpdate.append(reportStr);
                    gradesUpdate.append(", Promptness = ");
                    gradesUpdate.append(promptnessStr);
                    gradesUpdate.append(", Complaint = ");
                    gradesUpdate.append(complaintStr);
                    gradesUpdate.append(", Current = ");
                    gradesUpdate.append(currentStr);
                    gradesUpdate.append(", Contact = ");
                    gradesUpdate.append(contactStr);
                    gradesUpdate.append(", Warnings = '");
                    gradesUpdate.append(warningsStr);
                    gradesUpdate.append("', Filler = ");
                    gradesUpdate.append(indU.toString());
                    gradesUpdate.append(" WHERE ID_G = ");
                    gradesUpdate.append(idG.toString());
                    gradesUpdate.append(";");
                    String finalGradesUpdate = gradesUpdate.toString();
                    MyConnection.updateDB(finalGradesUpdate);
                    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(qualityGE);
                    topFrame.setVisible(false);
                }
            }
            }
        });

        JScrollPane scrlGE = new JScrollPane(mainPanel);
        Border bor = BorderFactory.createEtchedBorder();
        scrlGE.setViewportBorder(bor);

        JFrame editF = new JFrame();
        editF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editF.setSize(550, 600);
        ImageIcon img = new ImageIcon("C:\\Users\\Marcin\\Desktop\\uczelnia\\semestr_3\\programowanie_obiektowe\\FormSign.png");
        editF.setIconImage(img.getImage());
        editF.setContentPane(scrlGE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        editF.setLocation(dim.width/2-editF.getSize().width/2, dim.height/2-editF.getSize().height/2);

        editF.setAlwaysOnTop(true);
        editF.setVisible(true);

    }
}
