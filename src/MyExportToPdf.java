import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MyExportToPdf {
    public static final String FONT = "C:/Users/Marcin/Desktop/uczelnia/semestr_3/programowanie_obiektowe/FreeSans.ttf";

    /**
     * Zapisuje dane z JTable do pliku PDF
     * @param t tabela, która jest źródłem danych
     */
    public static void savePdf(JTable t) {
        try {
            Document doc = new Document(PageSize.A4.rotate());
            ZonedDateTime today = ZonedDateTime.now(ZoneId.of("Europe/Warsaw"));
            String dateQ = DateTimeFormatter.ISO_LOCAL_DATE.format(today);
            String timeQ1 = DateTimeFormatter.ISO_LOCAL_TIME.format(today);
            String timeQ = timeQ1.replaceAll(":", "-");
            StringBuilder sb = new StringBuilder();
            sb.append("F:/JavaTesty/PDFExport_");
            sb.append(dateQ);
            sb.append("_");
            sb.append(timeQ);
            sb.append(".pdf");
            String path = sb.toString();
            PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.open();
            PdfPTable pdfTable = new PdfPTable(t.getColumnCount());
            pdfTable.setWidthPercentage(100);
            pdfTable.setWidths(new float[]{1,3,3,2.5F,2,2,2,2,2,2});
            for (int i = 0; i < t.getColumnCount(); i++) {
                pdfTable.addCell(getNormalCell(t.getColumnName(i)));
            }
            for (int rows = 0; rows < t.getRowCount(); rows++) {
                for (int cols = 0; cols < t.getColumnCount(); cols++) {
                    pdfTable.addCell(getNormalCell(t.getModel().getValueAt(rows, cols).toString()));
                }
            }
            doc.add(pdfTable);
            doc.close();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Tworzy komórkę tabeli
     * @param string tekst w komórce
     * @return zwraca utworzoną komórkę tabeli
     * @throws DocumentException
     * @throws IOException
     */
    public static PdfPCell getNormalCell(String string) throws DocumentException, IOException {
        Font f  = getFont();
        f.setSize(12);
        PdfPCell cell = new PdfPCell(new Phrase(string, f));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    /**
     * Tworzy czcionkę
     * @return zwraca utworzoną czcionkę
     */
    public static Font getFont() {
        Font f = new Font();
        try {
            BaseFont bf = BaseFont.createFont(FONT, BaseFont.CP1250, BaseFont.EMBEDDED);
            f = new Font(bf, 12);
        } catch (IOException ex) {ex.printStackTrace();}
        catch (DocumentException dex){dex.printStackTrace();}
        return  f;
    }
}
