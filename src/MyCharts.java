import org.jfree.chart.*;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.io.File;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MyCharts {
    /**
     * Tworzy wykres w oparciu o konkretne wartości
     * @param i indeks w ArrayList z danymi do wykresu, ilość ocen '1'
     * @param j indeks w ArrayList z danymi do wykresu, ilość ocen '2'
     * @param k indeks w ArrayList z danymi do wykresu, ilość ocen '3'
     * @param name nazwa kryterium oceny
     */
    public static void createCh(int i, int j, int k, String name){
        try {
            ArrayList<Float> ar = MySumUp.counts();
            DefaultPieDataset def = new DefaultPieDataset();
            def.setValue("Ocena '1'", ar.get(i));
            def.setValue("Ocena '2'", ar.get(j));
            def.setValue("Ocena '3'", ar.get(k));
            StringBuilder sb = new StringBuilder();
            sb.append("Strunktura odpowiedzi wg kryterium '");
            sb.append(name);
            sb.append("'");
            String title = sb.toString();
            JFreeChart jfc = ChartFactory.createPieChart(title, def, false, true, true);
            PiePlot P = (PiePlot) jfc.getPlot();
            P.setExplodePercent("K",0.10);
            P.setSimpleLabels(true);
            PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                    "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
            P.setLabelGenerator(gen);
            ChartPanel chP = new ChartPanel(jfc);
            JFrame chF = new JFrame();
            chF.setContentPane(chP);

            JMenu saving = new JMenu("Zapisz");
            saving.addMenuListener(new MenuListener() {
                @Override
                public void menuSelected(MenuEvent e) {
                    try {
                        String s = jfc.getTitle().toString();
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("F:/JavaTesty/");
                        sb2.append(title);
                        sb2.append(".JPG");
                        String path = sb2.toString();
                        File pieChart = new File(path);
                        ChartUtilities.saveChartAsJPEG(pieChart, jfc, 600, 600);
                    }
                    catch (Exception ex){ex.printStackTrace();}
                }

                @Override
                public void menuDeselected(MenuEvent e) {}

                @Override
                public void menuCanceled(MenuEvent e) {}
            });
            JMenuBar menuB = new JMenuBar();
            menuB.add(saving);
            chF.setJMenuBar(menuB);
            ImageIcon img = new ImageIcon("C:\\Users\\Marcin\\Desktop\\uczelnia\\semestr_3\\programowanie_obiektowe\\FormSign.png");
            chF.setIconImage(img.getImage());
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            chF.setLocation(dim.width/3, dim.height/8);
            chF.pack();
            chF.setAlwaysOnTop(true);
            chF.setVisible(true);
            chF.setSize(600, 600);
        }
        catch(Exception  ex){ex.printStackTrace();}
    }

    /**
     * Tworzy wykres porównania średnich ocen według kryteriów
     * @param k1 indeks w ArrayList z danymi do wykresu, kryterium "Jakość wykonanych badań"
     * @param k2 indeks w ArrayList z danymi do wykresu, kryterium "Jakość sprawozdania z badań"
     * @param k3 indeks w ArrayList z danymi do wykresu, kryterium "Terminowość usług"
     * @param k4 indeks w ArrayList z danymi do wykresu, kryterium "Reklamacje"
     * @param k5 indeks w ArrayList z danymi do wykresu, kryterium "Bieżąca współpraca"
     * @param k6 indeks w ArrayList z danymi do wykresu, kryterium "Latwosc kontaktu"
     */
    public static void meanComparison(int k1, int k2, int k3, int k4, int k5, int k6){
        try {
            ArrayList<Float> ar = MySumUp.counts();
            DefaultCategoryDataset def = new DefaultCategoryDataset();
            def.setValue(ar.get(k1),"Średnia ocena", "Jakość wykonanych badań");
            def.setValue(ar.get(k2), "Średnia ocena","Jakość sprawozdania z badań");
            def.setValue(ar.get(k3),"Średnia ocena","Terminowość usług");
            def.setValue(ar.get(k4),"Średnia ocena","Reklamacje");
            def.setValue(ar.get(k5),"Średnia ocena","Bieżąca współpraca");
            def.setValue(ar.get(k6),"Średnia ocena","Latwosc kontaktu");
            String title = "Porównanie średnich ocen";
            JFreeChart jfc = ChartFactory.createBarChart(title, "Kategorie ocen","Średnia ocena",def, PlotOrientation.HORIZONTAL, false, true, false);
            CategoryPlot P = jfc.getCategoryPlot();
            P.setRangeGridlinePaint(Color.BLACK);

            ChartPanel chP = new ChartPanel(jfc);
            JFrame chF = new JFrame();
            chF.setContentPane(chP);

            JMenu saving = new JMenu("Zapisz");
            saving.addMenuListener(new MenuListener() {
                @Override
                public void menuSelected(MenuEvent e) {
                    try {
                        String s = jfc.getTitle().toString();
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("F:/JavaTesty/");
                        sb2.append(title);
                        sb2.append(".JPG");
                        String path = sb2.toString();
                        File pieChart = new File(path);
                        ChartUtilities.saveChartAsJPEG(pieChart, jfc, 600, 600);
                    }
                    catch (Exception ex){ex.printStackTrace();}
                }

                @Override
                public void menuDeselected(MenuEvent e) {}

                @Override
                public void menuCanceled(MenuEvent e) {}
            });
            JMenuBar menuB = new JMenuBar();
            menuB.add(saving);
            chF.setJMenuBar(menuB);
            ImageIcon img = new ImageIcon("C:\\Users\\Marcin\\Desktop\\uczelnia\\semestr_3\\programowanie_obiektowe\\FormSign.png");
            chF.setIconImage(img.getImage());
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            chF.setLocation(dim.width/4, dim.height/20);
            chF.pack();
            chF.setAlwaysOnTop(true);
            chF.setVisible(true);
            chF.setSize(800, 800);
        }
        catch(Exception  ex){ex.printStackTrace();}
    }
}
