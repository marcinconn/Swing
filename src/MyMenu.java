import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MyMenu extends JFrame {

    /**
     * TowrzyJMenuBar
     * @return zwraca JMenuBar
     */
    public JMenuBar createMB() {
        JMenu modify = new JMenu("Modyfikuj");
        modify.setMnemonic('M');
        JMenuItem editMenu = new JMenuItem("Edytuj");
        editMenu.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
        JMenuItem del = new JMenuItem("Usuń");
        del.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
        JMenuItem delAll = new JMenuItem("Usuń wszystkie dane");
        delAll.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
        modify.add(editMenu);
        modify.add(del);
        modify.add(delAll);

        JMenu lookFor = new JMenu("Szukaj");
        lookFor.setMnemonic('S');

        JMenu exportData = new JMenu("Eksportuj jako txt");
        exportData.setMnemonic('E');

        JMenu raport = new JMenu("Raport");
        raport.setMnemonic('R');
        JMenuItem toTxt = new JMenuItem("TXT");
        toTxt.setAccelerator(KeyStroke.getKeyStroke("ctrl W"));
        JMenuItem toJpg = new JMenuItem("JPG");
        toJpg.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
        raport.add(toTxt);
        raport.add(toJpg);

        JMenu charts = new JMenu("Wykresy");
        charts.setMnemonic('W');
        JMenuItem it1 = new JMenuItem("Jakość wykonanych badań");
        JMenuItem it2 = new JMenuItem("Jakość sprawozdania z badań");
        JMenuItem it3 = new JMenuItem("Terminowość usług");
        JMenuItem it4 = new JMenuItem("Reklamacje");
        JMenuItem it5 = new JMenuItem("Bieżąca współpraca");
        JMenuItem it6 = new JMenuItem("Latwosc kontaktu");
        JMenuItem it7 = new JMenuItem("Porównanie średnich ocen");
        charts.add(it1);
        charts.add(it2);
        charts.add(it3);
        charts.add(it4);
        charts.add(it5);
        charts.add(it6);
        charts.add(it7);

        JMenuBar menuBar = new JMenuBar();

        menuBar.add(modify);
        menuBar.add(lookFor);
        menuBar.add(exportData);
        menuBar.add(raport);
        menuBar.add(charts);

        editMenu.addActionListener(ev -> {
            MyForEdit.createAndShowGUI();});
        lookFor.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                MySearch.createSearchWindow();
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        exportData.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {MyExportToTxt ex = new MyExportToTxt();}

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        del.addActionListener(ev -> {MyDelete.createAndShowGUI();});
        delAll.addActionListener(ev -> {MyDeleteAll.deletingAll();});
        toTxt.addActionListener(ev -> {MySumUp.prepareSumUp(); MySumUp.saveTxtSumUp();});
        toJpg.addActionListener(ev -> {MySumUp.prepareSumUp();MySumUp.saveJpgSumUp();});
        it1.addActionListener(ev -> {MyCharts.createCh(0,1,2,"Jakość wykonanych badań");});
        it2.addActionListener(ev -> {MyCharts.createCh(3,4,5,"Jakość sprawozdania z badań");});
        it3.addActionListener(ev -> {MyCharts.createCh(6,7,8,"Terminowość usług");});
        it4.addActionListener(ev -> {MyCharts.createCh(9,10,11,"Reklamacje");});
        it5.addActionListener(ev -> {MyCharts.createCh(12,13,14,"Bieżąca współpraca");});
        it6.addActionListener(ev -> {MyCharts.createCh(15,16,17,"Jakość wykonanych badań");});
        it7.addActionListener(ev -> {MyCharts.meanComparison(18,19,20,21,22,23);});
        return menuBar;
    }
}
