package amusemeu.companyReportingSystem.dataUnload;

import amusemeu.companyReportingSystem.model.Vvod;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class VvodExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Vvod> listVvod;

    public VvodExcelExporter(List<Vvod> listVvod){
        super();
        this.listVvod = listVvod;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Ввод оборудования");
    }

    private void writeHeaderRow(){
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);

        Cell cell = row.createCell(0);
        cell.setCellValue("НТЭ");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("Код проекта");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("Бизнес код");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("Год поставки");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("Наименование оборудования");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("Кол-во");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("Стоимость оборудования");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("Факт ввода");
        cell.setCellStyle(style);
    }
    private void writeDataRows(){
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);

        for (Vvod vvod: listVvod){
            Row row = sheet.createRow(rowCount++);

            Cell cell = row.createCell(0);
            cell.setCellValue(vvod.getNte());
            sheet.autoSizeColumn(0);
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(vvod.getKodProekta());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(vvod.getBiznesKod());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(vvod.getGodPostavki());
            sheet.autoSizeColumn(3);
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(vvod.getNaimenovanieOborudovania());
            sheet.autoSizeColumn(4);
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue(vvod.getKolichestvo());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(style);

            cell = row.createCell(6);
            cell.setCellValue(vvod.getStoimostOborudoivania());
            sheet.autoSizeColumn(6);
            cell.setCellStyle(style);

            cell = row.createCell(7);
            cell.setCellValue(vvod.getFactVvoda());
            sheet.autoSizeColumn(7);
            cell.setCellStyle(style);
        }

    }
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
