import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MySearch {
    /**
     * Tworzy ramkę z komponentami do wyszukiwania
     */
    public static void createSearchWindow() {
        Integer[] grades = {0, 1, 2, 3};
        JComboBox<Integer> qualityS = new JComboBox<>(grades);
        JComboBox<Integer> reportS = new JComboBox<>(grades);
        JComboBox<Integer> promptnessS = new JComboBox<>(grades);
        JComboBox<Integer> complaintS = new JComboBox<>(grades);
        JComboBox<Integer> currentCooperationS = new JComboBox<>(grades);
        JComboBox<Integer> contactS = new JComboBox<>(grades);
        JTextField nickS = new JTextField(15);
        JTextField teF = new JTextField(15);
        JButton dateButton = new JButton("...");
        dateButton.setPreferredSize(new Dimension(20,20));
        dateButton.addActionListener(ev -> {
            final JFrame f = new JFrame();
            teF.setText(new MyDatePicker(f).setPickedDate());
        });

        JButton searchNow = new JButton("Szukaj");
        searchNow.addActionListener(ev -> {
            String nickCheck = nickS.getText();
            int nickCheck2 = nickCheck.length();
            String dateCheck = teF.getText();
            int dateCheck2 = dateCheck.length();

            if(qualityS.getItemAt(qualityS.getSelectedIndex())==0 && reportS.getItemAt(reportS.getSelectedIndex())==0 && promptnessS.getItemAt(promptnessS.getSelectedIndex())==0
                    && complaintS.getItemAt(complaintS.getSelectedIndex())==0 && currentCooperationS.getItemAt(currentCooperationS.getSelectedIndex())==0
                    && contactS.getItemAt(contactS.getSelectedIndex())==0 && nickCheck2==0 && dateCheck2==0)
            {
                StringBuilder sQ0 = new StringBuilder();
                sQ0.append("SELECT grades.ID_G, grades.Quality, grades.Report, grades.Promptness, ");
                sQ0.append("grades.Complaint, grades.Current, grades.Contact, grades.Warnings, grades.Data, ");
                sQ0.append("users.User FROM grades INNER JOIN users ON grades.Filler = users.ID_U;");
                String searchQuery0 = sQ0.toString();


                MyResultsS res1 = new MyResultsS(searchQuery0);

                JFrame topFrame = (JFrame)SwingUtilities.getWindowAncestor(searchNow);
                topFrame.setVisible(false);

            }
            else {
            String fieldNick = nickS.getText();
            String dateField = teF.getText();
            StringBuilder sQ = new StringBuilder();
            boolean isIt = false;
            sQ.append("SELECT grades.ID_G, grades.Quality, grades.Report, grades.Promptness, ");
            sQ.append("grades.Complaint, grades.Current, grades.Contact, grades.Warnings, grades.Data, ");
            sQ.append("users.User FROM grades LEFT JOIN users ON grades.Filler = users.ID_U ");

            if(qualityS.getItemAt(qualityS.getSelectedIndex())!=0)
            {
                sQ.append(" WHERE grades.Quality = ");
                sQ.append(qualityS.getItemAt(qualityS.getSelectedIndex()).toString());
                isIt = true;
            }

            if(reportS.getItemAt(reportS.getSelectedIndex())!=0 && isIt)
            {
                sQ.append(" AND grades.Report = ");
                sQ.append(reportS.getItemAt(reportS.getSelectedIndex()).toString());
            }

            if(reportS.getItemAt(reportS.getSelectedIndex())!=0 && !isIt)
            {
                sQ.append(" WHERE grades.Report = ");
                sQ.append(reportS.getItemAt(reportS.getSelectedIndex()).toString());
                isIt = true;
            }

            if(promptnessS.getItemAt(promptnessS.getSelectedIndex())!=0 && isIt)
            {
                sQ.append(" AND grades.Promptness = ");
                sQ.append(promptnessS.getItemAt(promptnessS.getSelectedIndex()).toString());
            }

            if(promptnessS.getItemAt(promptnessS.getSelectedIndex())!=0 && !isIt)
            {
                sQ.append(" WHERE grades.Promptness = ");
                sQ.append(promptnessS.getItemAt(promptnessS.getSelectedIndex()).toString());
                isIt = true;
            }

            if(complaintS.getItemAt(complaintS.getSelectedIndex())!=0 && isIt)
            {
                sQ.append(" AND grades.Complaint = ");
                sQ.append(complaintS.getItemAt(complaintS.getSelectedIndex()).toString());
            }

            if(complaintS.getItemAt(complaintS.getSelectedIndex())!=0 && !isIt)
            {
                sQ.append(" WHERE grades.Complaint = ");
                sQ.append(complaintS.getItemAt(complaintS.getSelectedIndex()).toString());
                isIt = true;
            }

            if(currentCooperationS.getItemAt(currentCooperationS.getSelectedIndex())!=0 && isIt)
            {
                sQ.append(" AND grades.Current = ");
                sQ.append(currentCooperationS.getItemAt(currentCooperationS.getSelectedIndex()).toString());
            }

            if(currentCooperationS.getItemAt(currentCooperationS.getSelectedIndex())!=0 && !isIt)
            {
                sQ.append(" WHERE grades.Current = ");
                sQ.append(currentCooperationS.getItemAt(currentCooperationS.getSelectedIndex()).toString());
                isIt = true;
            }

            if(contactS.getItemAt(contactS.getSelectedIndex())!=0 && isIt)
            {
                sQ.append(" AND grades.Contact = ");
                sQ.append(contactS.getItemAt(contactS.getSelectedIndex()).toString());
            }

            if(contactS.getItemAt(contactS.getSelectedIndex())!=0 && !isIt)
            {
                sQ.append(" WHERE grades.Contact = ");
                sQ.append(contactS.getItemAt(contactS.getSelectedIndex()).toString());
                isIt = true;
            }

            if (dateCheck2 != 0 && isIt)
            {
                sQ.append(" AND DATEDIFF('");
                sQ.append(dateField);
                sQ.append("',grades.Data) = 0");
            }

            if (dateCheck2 != 0 && !isIt)
            {
                sQ.append(" WHERE DATEDIFF('");
                sQ.append(dateField);
                sQ.append("',grades.Data) = 0");
                isIt = true;
            }

            if (nickCheck2 != 0 && isIt)
            {
                sQ.append(" AND users.User = '");
                sQ.append(fieldNick);
                sQ.append("'");
            }

            if (nickCheck2 != 0 && !isIt)
            {
                sQ.append(" WHERE users.User = '");
                sQ.append(fieldNick);
                sQ.append("'");
            }
            String searchQuery = sQ.toString();
            MyResultsS res2 = new MyResultsS(searchQuery);

            JFrame topFrame = (JFrame)SwingUtilities.getWindowAncestor(searchNow);
            topFrame.setVisible(false);
        }
        });

        JLabel topicL = new JLabel("Wpisz szukane wartości, wybierz 0, gdy wartość dowolna");

        JLabel qualityLS = new JLabel("Jakość wykonanych badań");
        JLabel reportLS = new JLabel("Jakoć sprawozdania z badań");
        JLabel promptnessLS = new JLabel("Terminowość usług");
        JLabel complaintLS = new JLabel("Reklamacje");
        JLabel currentCooperationLS = new JLabel("Bieżąca współpraca");
        JLabel contactLS = new JLabel("Latwość kontaktu");
        JLabel nickLS = new JLabel("Nick");
        JLabel dataLS = new JLabel("Data");

        JFrame searchF = new JFrame("Szukaj");
        searchF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchF.setSize(400, 350);
        ImageIcon imgS = new ImageIcon("C:\\Users\\Marcin\\Desktop\\uczelnia\\semestr_3\\programowanie_obiektowe\\FormSign.png");
        searchF.setIconImage(imgS.getImage());

        GridBagLayout layoutS = new GridBagLayout();

        JPanel labelsS = new JPanel();
        labelsS.setLayout(layoutS);
        labelsS.add(qualityLS, new GBC(0,0).setAnchor(GBC.LINE_END));
        labelsS.add(new JPanel(), new GBC(0,1).setAnchor(GBC.LINE_END));
        labelsS.add(reportLS, new GBC(0,2).setAnchor(GBC.LINE_END));
        labelsS.add(new JPanel(), new GBC(0,3).setAnchor(GBC.LINE_END));
        labelsS.add(promptnessLS, new GBC(0,4).setAnchor(GBC.LINE_END));
        labelsS.add(new JPanel(), new GBC(0,5).setAnchor(GBC.LINE_END));
        labelsS.add(complaintLS, new GBC(0,6).setAnchor(GBC.LINE_END));
        labelsS.add(new JPanel(), new GBC(0,7).setAnchor(GBC.LINE_END));
        labelsS.add(currentCooperationLS, new GBC(0,8).setAnchor(GBC.LINE_END));
        labelsS.add(new JPanel(), new GBC(0,9).setAnchor(GBC.LINE_END));
        labelsS.add(contactLS, new GBC(0,10).setAnchor(GBC.LINE_END));
        labelsS.add(new JPanel(), new GBC(0,11).setAnchor(GBC.LINE_END));
        labelsS.add(nickLS, new GBC(0,12).setAnchor(GBC.LINE_END));
        labelsS.add(new JPanel(), new GBC(0,13).setAnchor(GBC.LINE_END));
        labelsS.add(dataLS, new GBC(0,14).setAnchor(GBC.LINE_END));

        JPanel optionsS = new JPanel();
        optionsS.setLayout(layoutS);
        optionsS.add(qualityS, new GBC(0,0).setAnchor(GBC.LINE_START));
        optionsS.add(reportS, new GBC(0,2).setAnchor(GBC.LINE_START));
        optionsS.add(promptnessS, new GBC(0,4).setAnchor(GBC.LINE_START));
        optionsS.add(complaintS, new GBC(0,6).setAnchor(GBC.LINE_START));
        optionsS.add(currentCooperationS, new GBC(0,8).setAnchor(GBC.LINE_START));
        optionsS.add(contactS, new GBC(0,10).setAnchor(GBC.LINE_START));
        optionsS.add(new JPanel(), new GBC(0,11).setAnchor(GBC.LINE_START));
        optionsS.add(nickS, new GBC(0,12).setAnchor(GBC.LINE_START));
        optionsS.add(new JPanel(), new GBC(0,13).setAnchor(GBC.LINE_START));
        optionsS.add(teF, new GBC(0,14).setAnchor(GBC.LINE_START));
        optionsS.add(dateButton, new GBC(1,14).setAnchor(GBC.LINE_START));

        JPanel searchView = new JPanel();
        searchView.setLayout(layoutS);
        searchView.add(labelsS, new GBC(0,0));
        searchView.add(new JPanel(), new GBC(1,0));
        searchView.add(optionsS, new GBC(2,0));

        JPanel button = new JPanel();
        button.setLayout(layoutS);
        button.add(searchNow,new GBC(0,0));
        searchView.add(new JPanel(), new GBC(0,1));


        JPanel searchWindow = new JPanel();
        searchWindow.setLayout(layoutS);
        searchWindow.add(searchView, new GBC(0,1));
        searchWindow.add(new JPanel(), new GBC(0,2));
        searchWindow.add(button, new GBC(0,3).setAnchor(GBC.CENTER));

        JScrollPane scrollS = new JScrollPane(searchWindow);
        searchF.setContentPane(scrollS);
        Border bor = BorderFactory.createEtchedBorder();
        scrollS.setViewportBorder(bor);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        searchF.setLocation(dim.width/2-searchF.getSize().width/2, dim.height/2-searchF.getSize().height/2);

        searchF.setVisible(true);
        searchF.setAlwaysOnTop(true);
    }
}
