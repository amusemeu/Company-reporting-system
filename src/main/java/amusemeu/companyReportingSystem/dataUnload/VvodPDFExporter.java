package amusemeu.companyReportingSystem.dataUnload;

import amusemeu.companyReportingSystem.model.Vvod;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VvodPDFExporter {

    private List<Vvod> vvodList;

    public VvodPDFExporter(List<Vvod> vvodList){
        this.vvodList = vvodList;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new Color(173,208,134));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        Font font = new Font();
        font.setSize(10);
        font.setColor(Color.BLACK);
        cell.setPhrase(new Phrase("НТЭ", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Код проекта", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Бизнес Код", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Год поставки", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Наименование оборудования", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Кол-во", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Стоимость оборудования", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Факт ввода", font));
        table.addCell(cell);
    }
    private void writeTableData(PdfPTable table){
        Font font = new Font();
        font.setSize(10);


        for (Vvod vvod: vvodList){
            table.addCell(vvod.getNte());
            table.addCell(vvod.getKodProekta());
            table.addCell(vvod.getBiznesKod());
            table.addCell(vvod.getGodPostavki());
            table.addCell(vvod.getNaimenovanieOborudovania());
            table.addCell(vvod.getKolichestvo());
            table.addCell(vvod.getStoimostOborudoivania());
            table.addCell(vvod.getFactVvoda());
        }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());
        Font font = new Font();
        font.setSize(16);
        Paragraph title = new Paragraph("Ввод оборудования в экспулатацию " +
                                               "(не входящее в сметы строек) на "
                                               + currentDateTime + " г.", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[]{1.5f, 3.0f, 3.0f, 2.0f, 7.0f, 1.5f, 3.0f, 3.0f});
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);

        document.close();
    }
}
