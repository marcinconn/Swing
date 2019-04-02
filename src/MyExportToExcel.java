import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.swing.*;
import java.io.FileOutputStream;

public class MyExportToExcel {
    public static void saveToExcel(JTable t){
        try{
            Workbook wb = new HSSFWorkbook();
            Sheet sh = wb.createSheet("Exksport do Excela");

            Row rowCol = sh.createRow(0);
            for (int i = 0; i < t.getColumnCount(); i++) {
                Cell c = rowCol.createCell(i);
                c.setCellValue(t.getColumnName(i));
            }
            for (int rows = 0; rows < t.getRowCount(); rows++) {
                Row row = sh.createRow(rows+1);
                for (int cols = 0; cols < t.getColumnCount(); cols++) {
                    Cell  c = row.createCell(cols);
                    c.setCellValue(t.getModel().getValueAt(rows, cols).toString());
                }
            }
            FileOutputStream fos = new FileOutputStream("F:/JavaTesty/PDFExport_Excel.xls");
            wb.write(fos);
            fos.close();
        } catch (Exception ex) {ex.printStackTrace();}
    }
}
