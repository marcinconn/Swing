import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MyForm {
    /**
     * Tworzy panel z komponentami do utworzenia nowego wpisu
     * @param fr JFrame, w której znajdą się kompnenty
     */
    public static void createForm(JFrame fr){
        JLabel rulesL = new JLabel("Skala ocen: 1-niska, 2-średnia, 3-wysoka");
        JLabel achtung = new JLabel();

        JLabel qualityL = new JLabel("Jakość wykonanych badań");
        ButtonGroup qualityBG = new ButtonGroup();
        JRadioButton qualityB1 = new JRadioButton("1");
        JRadioButton qualityB2 = new JRadioButton("2");
        JRadioButton qualityB3 = new JRadioButton("3");
        qualityB1.setActionCommand("1");
        qualityB2.setActionCommand("2");
        qualityB3.setActionCommand("3");
        qualityBG.add(qualityB1);
        qualityBG.add(qualityB2);
        qualityBG.add(qualityB3);


        JLabel reportL = new JLabel("Jakość sprawozdania z badań");
        ButtonGroup reportBG = new ButtonGroup();
        JRadioButton reportB1 = new JRadioButton("1");
        JRadioButton reportB2 = new JRadioButton("2");
        JRadioButton reportB3 = new JRadioButton("3");
        reportB1.setActionCommand("1");
        reportB2.setActionCommand("2");
        reportB3.setActionCommand("3");
        reportBG.add(reportB1);
        reportBG.add(reportB2);
        reportBG.add(reportB3);


        JLabel promptnessL = new JLabel("Terminowość usług");
        ButtonGroup promptnessBG = new ButtonGroup();
        JRadioButton promptnessB1 = new JRadioButton("1");
        JRadioButton promptnessB2 = new JRadioButton("2");
        JRadioButton promptnessB3 = new JRadioButton("3");
        promptnessB1.setActionCommand("1");
        promptnessB2.setActionCommand("2");
        promptnessB3.setActionCommand("3");
        promptnessBG.add(promptnessB1);
        promptnessBG.add(promptnessB2);
        promptnessBG.add(promptnessB3);


        JLabel complaintL = new JLabel("Reklamacje");
        ButtonGroup complaintBG = new ButtonGroup();
        JRadioButton complaintB1 = new JRadioButton("1");
        JRadioButton complaintB2 = new JRadioButton("2");
        JRadioButton complaintB3 = new JRadioButton("3");
        complaintB1.setActionCommand("1");
        complaintB2.setActionCommand("2");
        complaintB3.setActionCommand("3");
        complaintBG.add(complaintB1);
        complaintBG.add(complaintB2);
        complaintBG.add(complaintB3);


        JLabel currentCooperationL = new JLabel("Bieżąca współpraca");
        ButtonGroup currentCooperationBG = new ButtonGroup();
        JRadioButton currentCooperationB1 = new JRadioButton("1");
        JRadioButton currentCooperationB2 = new JRadioButton("2");
        JRadioButton currentCooperationB3 = new JRadioButton("3");
        currentCooperationB1.setActionCommand("1");
        currentCooperationB2.setActionCommand("2");
        currentCooperationB3.setActionCommand("3");
        currentCooperationBG.add(currentCooperationB1);
        currentCooperationBG.add(currentCooperationB2);
        currentCooperationBG.add(currentCooperationB3);


        JLabel contactL = new JLabel("Latwość kontaktu");
        ButtonGroup contactBG = new ButtonGroup();
        JRadioButton contactB1 = new JRadioButton("1");
        JRadioButton contactB2 = new JRadioButton("2");
        JRadioButton contactB3 = new JRadioButton("3");
        contactB1.setActionCommand("1");
        contactB2.setActionCommand("2");
        contactB3.setActionCommand("3");
        contactBG.add(contactB1);
        contactBG.add(contactB2);
        contactBG.add(contactB3);

        JLabel nickL = new JLabel("Nick");
        JTextField nickF = new JTextField(15);


        JLabel warningL = new JLabel("Uwagi");
        JTextArea warningTA = new JTextArea(12,40);
        warningTA.setWrapStyleWord(true);
        JScrollPane warningS = new JScrollPane(warningTA);

        GridBagLayout layout = new GridBagLayout();
        JPanel view = new JPanel();
        view.setLayout(layout);
        view.add(rulesL, new GBC(0,0).setAnchor(GBC.PAGE_START).setInsets(2,0,5,0));

        JPanel qualityPanel = new JPanel();
        view.setLayout(layout);
        view.add(qualityL,new GBC(0,1).setAnchor(GBC.CENTER));
        qualityPanel.add(qualityB1,new GBC(0,0).setAnchor(GBC.LINE_END));
        qualityPanel.add(qualityB2,new GBC(4,0));
        qualityPanel.add(qualityB3,new GBC(8,0).setAnchor(GBC.LINE_START));
        view.add(qualityPanel, new GBC(0,2));

        JPanel reportPanel = new JPanel();
        reportPanel.setLayout(layout);
        view.add(reportL,new GBC(0,3).setAnchor(GBC.CENTER));
        reportPanel.add(reportB1,new GBC(0,0).setAnchor(GBC.LINE_END));
        reportPanel.add(reportB2,new GBC(4,0));
        reportPanel.add(reportB3,new GBC(8,0).setAnchor(GBC.LINE_START));
        view.add(reportPanel, new GBC(0,4));

        JPanel promptnessPanel = new JPanel();
        promptnessPanel.setLayout(layout);
        view.add(promptnessL,new GBC(0,5).setAnchor(GBC.CENTER));
        promptnessPanel.add(promptnessB1,new GBC(0,0).setAnchor(GBC.LINE_END));
        promptnessPanel.add(promptnessB2,new GBC(4,0));
        promptnessPanel.add(promptnessB3,new GBC(8,0).setAnchor(GBC.LINE_START));
        view.add(promptnessPanel, new GBC(0,6));

        JPanel complaintPanel = new JPanel();
        complaintPanel.setLayout(layout);
        view.add(complaintL,new GBC(0,7).setAnchor(GBC.CENTER));
        complaintPanel.add(complaintB1,new GBC(0,0).setAnchor(GBC.LINE_END));
        complaintPanel.add(complaintB2,new GBC(4,0));
        complaintPanel.add(complaintB3,new GBC(8,0).setAnchor(GBC.LINE_START));
        view.add(complaintPanel, new GBC(0,8));

        JPanel currentCooperationPanel = new JPanel();
        currentCooperationPanel.setLayout(layout);
        view.add(currentCooperationL,new GBC(0,9).setAnchor(GBC.CENTER));
        currentCooperationPanel.add(currentCooperationB1,new GBC(0,0).setAnchor(GBC.LINE_END));
        currentCooperationPanel.add(currentCooperationB2,new GBC(4,0));
        currentCooperationPanel.add(currentCooperationB3,new GBC(8,0).setAnchor(GBC.LINE_START));
        view.add(currentCooperationPanel, new GBC(0,10));

        JPanel contactPanel = new JPanel();
        contactPanel.setLayout(layout);
        view.add(contactL,new GBC(0,11).setAnchor(GBC.CENTER));
        contactPanel.add(contactB1,new GBC(0,0).setAnchor(GBC.LINE_END));
        contactPanel.add(contactB2,new GBC(4,0));
        contactPanel.add(contactB3,new GBC(8,0).setAnchor(GBC.LINE_START));
        view.add(contactPanel, new GBC(0,12));

        view.add(nickL, new GBC(0,14).setAnchor(GBC.CENTER));
        view.add(nickF, new GBC(0,15));

        view.add(warningL, new GBC(0,17).setAnchor(GBC.CENTER));
        view.add(warningS, new GBC(0,18).setFill(GBC.BOTH));

        JButton addNew = new JButton("Dodaj");
        addNew.addActionListener(ev ->{
            boolean ready=false;
            if(nickF.getText()!="" && qualityBG.getSelection()!=null && reportBG.getSelection()!=null && promptnessBG.getSelection()!=null &&
                    complaintBG.getSelection()!=null && currentCooperationBG.getSelection()!=null &&  contactBG.getSelection()!=null)ready=true;
            else {achtung.setForeground(Color.RED); achtung.setText("WYMAGANE WSZYSTKO POZA UWAGAMI!!!");}
            if(ready ==  true)
            {String field = nickF.getText();
            StringBuilder checking = new StringBuilder();
            checking.append("SELECT ID_U FROM users WHERE EXISTS (SELECT User FROM users WHERE User = '");
            checking.append(field);
            checking.append("')");
            String com1 = checking.toString();
            boolean isem = MyConnection.isInDB(com1);
            if(isem) {
                StringBuilder addingUser = new StringBuilder();
                addingUser.append("INSERT INTO users(User) VALUE('");
                addingUser.append(field);
                addingUser.append("')");
                String com2 = addingUser.toString();
                MyConnection.MakeConnection(com2);
            }
            StringBuilder findRelationSB = new StringBuilder();
            findRelationSB.append("SELECT ID_U FROM users WHERE User = '");
            findRelationSB.append(field);
            findRelationSB.append("'");
            String findRelation = findRelationSB.toString();
            Integer fin = MyConnection.findIndex(findRelation);

            StringBuilder addingGrades = new StringBuilder();
            addingGrades.append("INSERT INTO grades(Quality,Report,Promptness,Complaint,Current,Contact,Warnings,Data,Filler) VALUES(");
            String qualityQ = qualityBG.getSelection().getActionCommand();
            String reportQ = reportBG.getSelection().getActionCommand();
            String promptnessQ = promptnessBG.getSelection().getActionCommand();
            String complaintQ = complaintBG.getSelection().getActionCommand();
            String currentCooperationQ = currentCooperationBG.getSelection().getActionCommand();
            String contactQ = contactBG.getSelection().getActionCommand();
            String warningsQ = warningTA.getText();
            String iduserQ = fin.toString();

            ZonedDateTime today = ZonedDateTime.now(ZoneId.of("Europe/Warsaw"));
            String dateQ = DateTimeFormatter.ISO_LOCAL_DATE.format(today);

            addingGrades.append(qualityQ);
            addingGrades.append(" , ");
            addingGrades.append(reportQ);
            addingGrades.append(" , ");
            addingGrades.append(promptnessQ);
            addingGrades.append(" , ");
            addingGrades.append(complaintQ);
            addingGrades.append(" , ");
            addingGrades.append(currentCooperationQ);
            addingGrades.append(" , ");
            addingGrades.append(contactQ);
            addingGrades.append(" , '");
            addingGrades.append(warningsQ);
            addingGrades.append("' , '");
            addingGrades.append(dateQ);
            addingGrades.append("' , ");
            addingGrades.append(iduserQ);
            addingGrades.append(")");
            String grades = addingGrades.toString();

            MyConnection.MakeConnection(grades);
            JFrame topFrame = (JFrame)SwingUtilities.getWindowAncestor(rulesL);
            MyForm.createForm(topFrame);}
        });

        JPanel buttons = new JPanel();
        buttons.setLayout(layout);
        buttons.add(addNew, new GBC(0,0).setAnchor(GBC.CENTER));
        view.add(buttons, new GBC(0,19));
        view.add(achtung, new GBC(0,20).setAnchor(GBC.PAGE_END).setInsets(2,0,5,0));


        JScrollPane scrl = new JScrollPane(view);
        Border bor = BorderFactory.createEtchedBorder();
        scrl.setViewportBorder(bor);
        fr.setContentPane(scrl);

        fr.setVisible(true);
    }
}
