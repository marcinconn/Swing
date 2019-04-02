import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.Vector;

public class MyResultsS extends JFrame {
    protected JPanel contentPane;
    protected JButton printButton;
    protected JScrollPane scrolTab;
    protected JButton exportTxtButton;
    protected JButton exportPdfButton;
    protected JButton exportExcelButton;

    /**
     * Tworzy ramkę w wynikami wyszukiwania i przyciskami
     * @param com zapytanie SQL do wykonania
     */
    public MyResultsS(String com) {
        JFrame frameR = new JFrame();
        frameR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameR.setSize(1300,600);
        ImageIcon imgR = new ImageIcon("C:\\Users\\Marcin\\Desktop\\uczelnia\\semestr_3\\programowanie_obiektowe\\FormSign.png");
        frameR.setIconImage(imgR.getImage());
        JTable t2 =  new JTable(MyConnection.showResults(com),getCol());
        t2.setAutoCreateRowSorter(true);

        scrolTab = new JScrollPane(t2);
        printButton = new JButton("Drukuj");
        exportTxtButton = new JButton("Eksportuj jako txt");
        exportPdfButton = new JButton("Eksportuj jako pdf");
        exportExcelButton = new JButton("Eksportuj jako xls");
        printButton.addActionListener(ev -> {printTable(t2);});
        exportTxtButton.addActionListener(ev -> {MyExportToTxt.saveTableText(t2);});
        exportPdfButton.addActionListener(ev -> {MyExportToPdf.savePdf(t2);});
        exportExcelButton.addActionListener(ev ->{MyExportToExcel.saveToExcel(t2);});

        contentPane = new JPanel();
        addComponents();
        frameR.setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frameR.setLocation(dim.width/2-frameR.getSize().width/2, dim.height/2-frameR.getSize().height/2);
        frameR.setVisible(true);
        frameR.setAlwaysOnTop(true);
    }

    /**
     * Tworzy wektor z nazwami kolumn tabeli
     * @return zwaraca Vector z danymi
     */
    public static Vector getCol(){
        Vector<String> columnNamesV = new Vector<>(4);
        columnNamesV.add("Lp");
        columnNamesV.add("Jakość wykonanych badań");
        columnNamesV.add("Jakość sprawozdania z badań");
        columnNamesV.add("Terminowość usług");
        columnNamesV.add("Reklamacje");
        columnNamesV.add("Bieżąca współpraca");
        columnNamesV.add("Latwosc kontaktu");
        columnNamesV.add("Uwagi");
        columnNamesV.add("Data");
        columnNamesV.add("User");
        return columnNamesV;
    }

    /**
     * Dodaje komponenty do contentPane
     */
    public void addComponents(){
        JPanel bottomPanel = new JPanel();
        GroupLayout groupLay = new GroupLayout(bottomPanel);
        bottomPanel.setLayout(groupLay);
        groupLay.setAutoCreateGaps(true);
        groupLay.setAutoCreateContainerGaps(true);

        groupLay.setHorizontalGroup(
                groupLay.createSequentialGroup()
                .addComponent(printButton)
                .addComponent(exportTxtButton)
                .addComponent(exportPdfButton)
                .addComponent(exportExcelButton)
        );

        groupLay.setVerticalGroup(
                groupLay.createSequentialGroup()
                        .addGroup(groupLay.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(printButton)
                                .addComponent(exportTxtButton)
                        .addComponent(exportPdfButton)
                        .addComponent(exportExcelButton))
        );

        GroupLayout layout = new GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(scrolTab, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                        .addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrolTab, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
    }

    /**
     * Drukuje tabelę
     * @param t tabela, której dane będą źródłem informacji
     */
    public void printTable(JTable t) {
        MessageFormat header = new MessageFormat("Wyniki wyszukiwania");
        MessageFormat footer = new MessageFormat("{0}");
        boolean showPrintDialog = true;
        JTable.PrintMode mode = JTable.PrintMode.FIT_WIDTH;

        try {
            boolean complete = t.print(mode, header, footer, showPrintDialog, null, false, null);

            if (complete) {
                JOptionPane.showMessageDialog(this,
                        "Drukowanie zakończone",
                        "Wynik drukowania",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Drukowanie anulowane",
                        "Wynik drukowania",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(this,
                    "Drukowanie nieudane: " + pe.getMessage(),
                    "Wynik drukowania",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
