import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;

public class MySumUp {
    protected static ArrayList<Integer> qualityArrayINT = new ArrayList<>();
    protected static ArrayList<Integer> reportArrayINT = new ArrayList<>();
    protected static ArrayList<Integer> promptnessArrayINT = new ArrayList<>();
    protected static ArrayList<Integer> complaintArrayINT = new ArrayList<>();
    protected static ArrayList<Integer> currentArrayINT = new ArrayList<>();
    protected static ArrayList<Integer> contactArrayINT = new ArrayList<>();
    protected static ArrayList<String> dateArraySTR = new ArrayList<>();
    protected static ArrayList<String> dateArraySTR2 = new ArrayList<>();
    protected static Integer quality1=0, quality2=0, quality3=0, report1=0, report2=0, report3=0, promptness1=0,promptness2=0,promptness3=0;
    protected static Integer complaint1=0, complaint2=0, complaint3=0, current1=0, current2=0, current3=0, contact1=0, contact2=0, contact3=0, cou=0;
    protected static Float qualityMean=0F, reportMean=0F, promptessMean=0F, complaintMean=0F, currentMean=0F, contactMean=0F;

    /**
     * Inicjuje zmienne potrzebne do dalszych działań
     */
    public static void prepareSumUp(){
        String c1 = "SELECT Quality FROM grades";
        String c2 = "SELECT Report FROM grades";
        String c3 = "SELECT Promptness FROM grades";
        String c4 = "SELECT Complaint FROM grades";
        String c5 = "SELECT Current FROM grades";
        String c6 = "SELECT Contact FROM grades";
        String c7 = "SELECT MAX(Data) FROM grades";
        String c8 = "SELECT MIN(Data) FROM grades";

        ArrayList<Object> qualityArray = MyConnection.prepareEditforGrades(c1);
        ArrayList<Object> reportArray = MyConnection.prepareEditforGrades(c2);
        ArrayList<Object> promptnessArray = MyConnection.prepareEditforGrades(c3);
        ArrayList<Object> complaintArray = MyConnection.prepareEditforGrades(c4);
        ArrayList<Object> currentArray = MyConnection.prepareEditforGrades(c5);
        ArrayList<Object> contactArray = MyConnection.prepareEditforGrades(c6);
        ArrayList<Object> dateArray = MyConnection.prepareEditforGrades(c7);
        ArrayList<Object> dateArray2 = MyConnection.prepareEditforGrades(c8);

        for(int i=0;i<qualityArray.size();i++){qualityArrayINT.add(i,(Integer)qualityArray.get(i));}
        for(int i=0;i<reportArray.size();i++){reportArrayINT.add(i,(Integer)reportArray.get(i));}
        for(int i=0;i<promptnessArray.size();i++){promptnessArrayINT.add(i,(Integer)promptnessArray.get(i));}
        for(int i=0;i<complaintArray.size();i++){complaintArrayINT.add(i,(Integer)complaintArray.get(i));}
        for(int i=0;i<currentArray.size();i++){currentArrayINT.add(i,(Integer)currentArray.get(i));}
        for(int i=0;i<contactArray.size();i++){contactArrayINT.add(i,(Integer)contactArray.get(i));}
        for(int i=0;i<dateArray.size();i++){dateArraySTR.add(i,dateArray.get(i).toString());}
        for(int i=0;i<dateArray2.size();i++){dateArraySTR2.add(i,dateArray2.get(i).toString());}

        Integer sum=0;
        for(int i=0;i<qualityArrayINT.size();i++){
            sum+=qualityArrayINT.get(i);
        }
        qualityMean = sum / (float)qualityArrayINT.size();
        sum = 0;
        for(int i=0;i<reportArrayINT.size();i++){
            sum+=reportArrayINT.get(i);
        }
        reportMean = sum / (float)reportArrayINT.size();
        sum =0;
        for(int i=0;i<promptnessArrayINT.size();i++){
            sum+=promptnessArrayINT.get(i);
        }
        promptessMean = sum / (float)promptnessArrayINT.size();
        sum =0;
        for(int i=0;i<complaintArrayINT.size();i++){
            sum+=complaintArrayINT.get(i);
        }
        complaintMean = sum / (float)complaintArrayINT.size();
        sum =0;
        for(int i=0;i<currentArrayINT.size();i++){
            sum+=currentArrayINT.get(i);
        }
        currentMean = sum / (float)currentArrayINT.size();
        sum =0;
        for(int i=0;i<contactArrayINT.size();i++){
            sum+=contactArrayINT.get(i);
        }
        contactMean  = sum / (float)contactArrayINT.size();

        for(int i=0;i<qualityArrayINT.size();i++){
            if(qualityArrayINT.get(i)==1) quality1++;
            if(qualityArrayINT.get(i)==2) quality2++;
            if(qualityArrayINT.get(i)==3) quality3++;
        }
        for(int i=0;i<reportArrayINT.size();i++){
            if(reportArrayINT.get(i)==1) report1++;
            if(reportArrayINT.get(i)==2) report2++;
            if(reportArrayINT.get(i)==3) report3++;
        }
        for(int i=0;i<promptnessArrayINT.size();i++){
            if(promptnessArrayINT.get(i)==1) promptness1++;
            if(promptnessArrayINT.get(i)==2) promptness2++;
            if(promptnessArrayINT.get(i)==3) promptness3++;
        }
        for(int i=0;i<complaintArrayINT.size();i++){
            if(complaintArrayINT.get(i)==1) complaint1++;
            if(complaintArrayINT.get(i)==2) complaint2++;
            if(complaintArrayINT.get(i)==3) complaint3++;
        }
        for(int i=0;i<currentArrayINT.size();i++){
            if(currentArrayINT.get(i)==1) current1++;
            if(currentArrayINT.get(i)==2) current2++;
            if(currentArrayINT.get(i)==3) current3++;
        }
        for(int i=0;i<contactArrayINT.size();i++){
            if(contactArrayINT.get(i)==1) contact1++;
            if(contactArrayINT.get(i)==2) contact2++;
            if(contactArrayINT.get(i)==3) contact3++;
        }
        cou = contactArrayINT.size();
    }

    /**
     * Zapisuje zestawienie obliczonych danych do pliku txt
     */
    public static void saveTxtSumUp(){
        try {
            ZonedDateTime today = ZonedDateTime.now(ZoneId.of("Europe/Warsaw"));
            String dateQ = DateTimeFormatter.ISO_LOCAL_DATE.format(today);
            String timeQ = DateTimeFormatter.ISO_LOCAL_TIME.format(today);
            Vector<String> vecS = MyResultsS.getCol();
            StringBuilder sb = new StringBuilder();
            sb.append("F:/JavaTesty/Podsumowanie_");
            sb.append(dateQ);
            sb.append(".txt");
            String path = sb.toString();
            File file = new File(path);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Podsumowanie, ");
            bw.write(dateQ);
            bw.write("  ");
            bw.write(timeQ);
            bw.newLine();
            bw.newLine();

            bw.write(vecS.get(1));
            bw.newLine();
            bw.write("Średnia ocena: ");
            bw.write(qualityMean.toString());
            bw.newLine();
            bw.write("Ilość ocen '1': ");
            bw.write(quality1.toString());
            bw.newLine();
            bw.write("Ilość ocen '2': ");
            bw.write(quality2.toString());
            bw.newLine();
            bw.write("Ilość ocen '3': ");
            bw.write(quality3.toString());
            bw.newLine();
            bw.newLine();

            bw.write(vecS.get(2));
            bw.newLine();
            bw.write("Średnia ocena: ");
            bw.write(reportMean.toString());
            bw.newLine();
            bw.write("Ilość ocen '1': ");
            bw.write(report1.toString());
            bw.newLine();
            bw.write("Ilość ocen '2': ");
            bw.write(report2.toString());
            bw.newLine();
            bw.write("Ilość ocen '3': ");
            bw.write(report3.toString());
            bw.newLine();
            bw.newLine();

            bw.write(vecS.get(3));
            bw.newLine();
            bw.write("Średnia ocena: ");
            bw.write(promptessMean.toString());
            bw.newLine();
            bw.write("Ilość ocen '1': ");
            bw.write(promptness1.toString());
            bw.newLine();
            bw.write("Ilość ocen '2': ");
            bw.write(promptness2.toString());
            bw.newLine();
            bw.write("Ilość ocen '3': ");
            bw.write(promptness3.toString());
            bw.newLine();
            bw.newLine();

            bw.write(vecS.get(4));
            bw.newLine();
            bw.write("Średnia ocena: ");
            bw.write(complaintMean.toString());
            bw.newLine();
            bw.write("Ilość ocen '1': ");
            bw.write(complaint1.toString());
            bw.newLine();
            bw.write("Ilość ocen '2': ");
            bw.write(complaint2.toString());
            bw.newLine();
            bw.write("Ilość ocen '3': ");
            bw.write(complaint2.toString());
            bw.newLine();
            bw.newLine();

            bw.write(vecS.get(5));
            bw.newLine();
            bw.write("Średnia ocena: ");
            bw.write(currentMean.toString());
            bw.newLine();
            bw.write("Ilość ocen '1': ");
            bw.write(current1.toString());
            bw.newLine();
            bw.write("Ilość ocen '2': ");
            bw.write(current2.toString());
            bw.newLine();
            bw.write("Ilość ocen '3': ");
            bw.write(current3.toString());
            bw.newLine();
            bw.newLine();

            bw.write(vecS.get(6));
            bw.newLine();
            bw.write("Średnia ocena: ");
            bw.write(contactMean.toString());
            bw.newLine();
            bw.write("Ilość ocen '1': ");
            bw.write(contact1.toString());
            bw.newLine();
            bw.write("Ilość ocen '2': ");
            bw.write(contact2.toString());
            bw.newLine();
            bw.write("Ilość ocen '3': ");
            bw.write(contact3.toString());
            bw.newLine();
            bw.newLine();
            bw.write("Pierwszy wpis: ");
            bw.write(dateArraySTR2.get(0));
            bw.newLine();
            bw.write("Ostatni wpis: ");
            bw.write(dateArraySTR.get(0));
            bw.newLine();

            bw.close();
            fw.close();
        }
        catch (IOException ex){ex.printStackTrace();}
    }

    /**
     * Zapisuje obliczone dane do pliku JPG
     */
    public static void saveJpgSumUp(){
        try {
            int width = 500, height = 1250;
            ZonedDateTime today = ZonedDateTime.now(ZoneId.of("Europe/Warsaw"));
            String dateQ = DateTimeFormatter.ISO_LOCAL_DATE.format(today);
            String timeQ = DateTimeFormatter.ISO_LOCAL_TIME.format(today);
            Vector<String> vecS = MyResultsS.getCol();
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            Graphics2D ig2 = bi.createGraphics();
            ig2.setColor(Color.white);
            ig2.setBackground(Color.WHITE);
            ig2.fillRect(0, 0, width, height);
            Font font = new Font("TimesRoman", Font.BOLD, 20);
            ig2.setFont(font);
            ig2.setPaint(Color.BLACK);

            StringBuilder sb = new StringBuilder();
            String path = new String();
            sb.append("F:/JavaTesty/Podsumowanie_");
            sb.append(dateQ);
            sb.append(".JPG");
            path = sb.toString();
            File file = new File(path);

            int x = 10;
            int y = 30;

            ig2.drawString("Podsumowanie, " + dateQ + "  " + timeQ, x, y);
            y = y + 50;
            ig2.drawString(vecS.get(1), x, y);
            y = y + 30;
            ig2.drawString("Średnia ocena:  " + qualityMean, x, y);
            y=y+30;
            ig2.drawString("Ilość ocen '1':  "+ quality1,x,y);
            y=y+30;
            ig2.drawString("Ilość ocen '2':  "+ quality2,x,y);
            y=y+30;
            ig2.drawString("Ilość ocen '3':  "+ quality3,x,y);
            y=y+60;

            ig2.drawString(vecS.get(2), x, y);
            y = y + 30;
            ig2.drawString("Średnia ocena:  " + reportMean, x, y);
            y=y+30;
            ig2.drawString("Ilość ocen '1':  "+ report1,x,y);
            y=y+30;
            ig2.drawString("Ilość ocen '2':  "+ report2,x,y);
            y=y+30;
            ig2.drawString("Ilość ocen '3':  "+ report3,x,y);
            y=y+60;

            ig2.drawString(vecS.get(3), x, y);
            y = y + 30;
            ig2.drawString("Średnia ocena:  " + promptessMean, x, y);
            y=y+30;
            ig2.drawString("Ilość ocen '1':  "+ promptness1,x,y);
            y=y+30;
            ig2.drawString("Ilość ocen '2':  "+ promptness2,x,y);
            y=y+30;
            ig2.drawString("Ilość ocen '3':  "+ promptness3,x,y);
            y=y+60;

            ig2.drawString(vecS.get(4), x, y);
            y = y + 30;
            ig2.drawString("Średnia ocena:  " + complaintMean, x, y);
            y=y+30;
            ig2.drawString("Ilość ocen '1':  "+ complaint1,x,y);
            y=y+30;
            ig2.drawString("Ilość ocen '2':  "+ complaint2,x,y);
            y=y+30;
            ig2.drawString("Ilość ocen '3':  "+ complaint3,x,y);
            y=y+60;

            ig2.drawString(vecS.get(5), x, y);
            y = y + 30;
            ig2.drawString("Średnia ocena:  " + currentMean, x, y);
            y=y+30;
            ig2.drawString("Ilość ocen '1':  "+ current1,x,y);
            y=y+30;
            ig2.drawString("Ilość ocen '2':  "+ current2,x,y);
            y=y+30;
            ig2.drawString("Ilość ocen '3':  "+ current3,x,y);
            y=y+60;

            ig2.drawString(vecS.get(6), x, y);
            y = y + 30;
            ig2.drawString("Średnia ocena:  " + contactMean, x, y);
            y=y+30;
            ig2.drawString("Ilość ocen '1':  "+ contact1,x,y);
            y=y+30;
            ig2.drawString("Ilość ocen '2':  "+ contact2,x,y);
            y=y+30;
            ig2.drawString("Ilość ocen '3':  "+ contact3,x,y);
            y=y+60;

            ig2.drawString("Pierwszy wpis:  "+ dateArraySTR2.get(0),x,y);
            y=y+30;
            ig2.drawString("Ostatni wpis:  "+ dateArraySTR.get(0),x,y);

            ImageIO.write(bi, "JPEG", file);
        }
        catch (IOException ex){ex.printStackTrace();}

    }

    /**
     * Zwraca obliczone wartości
     * @return zwraca ArrayList z obliczonymi wartościami do dalszego ich wykorzystania
     */
    public static ArrayList counts(){
        ArrayList<Float> arf = new ArrayList<>();
        prepareSumUp();
        arf.add(0,(float)quality1);
        arf.add(1,(float)quality2);
        arf.add(2,(float)quality3);

        arf.add(3,(float)report1);
        arf.add(4,(float)report2);
        arf.add(5,(float)report3);

        arf.add(6,(float)promptness1);
        arf.add(7,(float)promptness2);
        arf.add(8,(float)promptness3);

        arf.add(9,(float)complaint1);
        arf.add(10,(float)complaint2);
        arf.add(11,(float)complaint3);

        arf.add(12,(float)current1);
        arf.add(13,(float)current2);
        arf.add(14,(float)current3);

        arf.add(15,(float)contact1);
        arf.add(16,(float)contact2);
        arf.add(17,(float)contact3);

        arf.add(18,qualityMean);
        arf.add(19,reportMean);
        arf.add(20,promptessMean);
        arf.add(21,complaintMean);
        arf.add(22,currentMean);
        arf.add(23,complaintMean);

        arf.add(24,(float)cou);
        return  arf;
    }
}
