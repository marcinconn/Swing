import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class MyExportToTxt {
    protected JTextField placeForId = new JTextField(5);
    protected JRadioButton btnS = new JRadioButton("Eksportuj rekord numer: ");
    protected JRadioButton btnA = new JRadioButton("Eksportuj wszystko ");
    protected JButton confirm = new JButton("OK");
    protected ButtonGroup btnGr = new ButtonGroup();
    protected JFrame txtExport = new JFrame();
    protected JLabel warning = new JLabel(" ");
    protected boolean ready = true;

    /**
     * Tworzy ramkę z komponentami, z wyborem eksportu do txt
     */
    public MyExportToTxt(){
        btnGr.add(btnA);
        btnGr.add(btnS);
        btnS.setActionCommand("1");
        btnA.setActionCommand("2");
        ImageIcon img = new ImageIcon("C:\\Users\\Marcin\\Desktop\\uczelnia\\semestr_3\\programowanie_obiektowe\\FormSign.png");
        txtExport.setIconImage(img.getImage());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        txtExport.setLocation(dim.width/4-txtExport.getSize().width, dim.height/3-txtExport.getSize().height);
        JPanel pnl = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridLayout gl = new GridLayout(3,1,10,10);
        pnl.setLayout(gl);
        txtExport.setSize(540,200);
        txtExport.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel singlePanel = new JPanel();
        singlePanel.setLayout(gbl);
        singlePanel.setPreferredSize(new Dimension(500,45));
        singlePanel.add(btnS, new GBC(0,0).setAnchor(GBC.LINE_START));
        singlePanel.add(new JPanel(), new GBC(1,0).setAnchor(GBC.CENTER));
        singlePanel.add(btnA, new GBC(2,0).setAnchor(GBC.LINE_END));

        JPanel confirmPanel = new JPanel();
        GridLayout gl2 = new GridLayout(1,4,20,10);
        confirmPanel.setLayout(gl2);
        confirmPanel.setPreferredSize(new Dimension(500,45));
        confirmPanel.add(new JPanel());
        confirmPanel.add(placeForId);
        confirmPanel.add(new JPanel());
        confirmPanel.add(new JPanel());

        JPanel allPanel = new JPanel();
        allPanel.setLayout(gbl);
        allPanel.setPreferredSize(new Dimension(500,45));
        allPanel.add(warning, new GBC(0,0).setAnchor(GBC.PAGE_START));
        allPanel.add(confirm, new GBC(1,0).setAnchor(GBC.CENTER));
        allPanel.add(new JPanel(), new GBC(2,0).setAnchor(GBC.LINE_END));

        pnl.add(singlePanel);
        pnl.add(confirmPanel);
        pnl.add(allPanel);
        JScrollPane scrl = new JScrollPane(pnl);
        txtExport.setContentPane(scrl);

        confirm.addActionListener(ev -> {
            String str = btnGr.getSelection().getActionCommand();
            if(str=="2"){
                StringBuilder sQ = new StringBuilder();
                sQ.append("SELECT grades.ID_G, grades.Quality, grades.Report, grades.Promptness, ");
                sQ.append("grades.Complaint, grades.Current, grades.Contact, grades.Warnings, grades.Data, ");
                sQ.append("users.User FROM grades INNER JOIN users ON grades.Filler = users.ID_U;");
                String searchQuery = sQ.toString();
                saveText(searchQuery,2, null);
                txtExport.setVisible(false);
            }
            if(str=="1"){
                String s1 = placeForId.getText();
                try{
                    Integer.parseInt(s1);
                }catch(NumberFormatException ex){
                    warning.setForeground(Color.RED);
                    warning.setText("PODAJ LICZBE");
                    ready = false;
                }
                if(ready) {
                    StringBuilder sQ2 = new StringBuilder();
                    sQ2.append("SELECT grades.ID_G, grades.Quality, grades.Report, grades.Promptness, ");
                    sQ2.append("grades.Complaint, grades.Current, grades.Contact, grades.Warnings, grades.Data, ");
                    sQ2.append("users.User FROM grades LEFT JOIN users ON grades.Filler = users.ID_U WHERE grades.ID_G = ");
                    sQ2.append(s1);
                    sQ2.append(";");
                    String searchQuery2 = sQ2.toString();
                    saveText(searchQuery2, 1, s1);
                    txtExport.setVisible(false);
                }
            }
        });

        txtExport.setAlwaysOnTop(true);
        txtExport.setVisible(true);
    }

    /**
     * Zapisuje do pliku txt
     * @param com zapytanie SQL do wykonania
     * @param n opcja zapisu(wszystko lub jeden rekord)
     * @param name numer rekordu zapisywanego
     */
    public static void saveText(String com, Integer n, String name)
    {
        try{
            StringBuilder sb = new StringBuilder();
            String path = new String();
            if(n==2) {
                sb.append("F:/JavaTesty/Wszystkie_Rekordy.txt");
                path=sb.toString();
            }
            if(n==1){
                sb.append("F:/JavaTesty/Rekord_nr_");
                sb.append(name);
                sb.append(".txt");
                path = sb.toString();
            }
            File file = new File(path);
            if(!file.exists()){file.createNewFile();}

            JTable t =  new JTable(MyConnection.showResults(com),MyResultsS.getCol());

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i=0; i<t.getRowCount();i++)
            {
                for(int j=0;j<t.getColumnCount();j++)
                {
                    bw.write(t.getColumnName(j)+": ");
                    bw.write(t.getModel().getValueAt(i, j).toString());
                    bw.newLine();
                }
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Zapisuje dane z tabeli do pliku txt
     * @param t tabela, która jest źródłem danych
     */
    public static void saveTableText(JTable t)
    {
        try{
            File file = new File("F:/JavaTesty/Niestandardowy_eksport.txt");
            if(!file.exists()){file.createNewFile();}

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i=0; i<t.getRowCount();i++)
            {
                for(int j=0;j<t.getColumnCount();j++)
                {
                    bw.write(t.getColumnName(j)+": ");
                    bw.write(t.getModel().getValueAt(i, j).toString());
                    bw.newLine();
                }
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
