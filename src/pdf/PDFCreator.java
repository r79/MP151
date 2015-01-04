package pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dto.PerformanceDTO;
import dto.VisitorDTO;

@ManagedBean
@ApplicationScoped
/*1 Scope für ganzi Applikation
 * Vilicht müsste mer da umprogrammiere
 * dass für jede User sozusege en eigene PDF name da isch
 * bispiel: also ich mit de ip 1.1.1.1 hätti den die pdf file
 * wo heisst: 1_1_1_1_vorstellungen.pdf
 * oder so öppis damit en user nöd die pdf überchunt wo grad
 * öppert anders glichzitig erstellt het
 *
 * oder wir lönds so.
 */
public class PDFCreator {

    public void generatePerformancesPDF(List<PerformanceDTO> list) {
        try {
            OutputStream file = new FileOutputStream(new File("Vorstellungen.pdf"));

            Document document = new Document();
            PdfWriter.getInstance(document, file);

            document.open();
            document.add(new Paragraph("Alle Vorstellungen"));
            document.add(new Paragraph(new Date().toString()));
            document.addAuthor("R79 & 23dev");
            document.addCreationDate();
            document.addCreator("JSF Projekt");
            document.addTitle("Vorstellungen PDF");

            //Erstelle Paragraph
            Paragraph paragraph = new Paragraph("Alle Vorstellungen",new Font(Font.FontFamily.TIMES_ROMAN, 18,
                    Font.BOLD));

            //Neue line
            paragraph.add(new Paragraph(" "));
            paragraph.add("Überblick");
            paragraph.add(new Paragraph(" "));
            document.add(paragraph);

            //Tabelle in PDF generieren
            PdfPTable pdfTable = new PdfPTable(4);
            PdfPCell cell1 = new PdfPCell(new Phrase("Datum"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Zimmer"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Titel"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Link"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);
            pdfTable.setHeaderRows(1);

            for(PerformanceDTO performance: list) {
                pdfTable.addCell(performance.getDate().toString());
                pdfTable.addCell(performance.getRoom());
                pdfTable.addCell(performance.getTitle());
                pdfTable.addCell(performance.getTitleLink());
            }

            document.add(pdfTable);

            document.close();
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateVisitorsPDF(List<VisitorDTO> list) {
        try {
            OutputStream file = new FileOutputStream(new File("Besucher.pdf"));

            Document document = new Document();
            PdfWriter.getInstance(document, file);

            document.open();
            document.add(new Paragraph("Alle Besucher der Vorstellung"));
            document.add(new Paragraph(new Date().toString()));

            document.addAuthor("R79 & 23dev");
            document.addCreationDate();
            document.addCreator("JSF Projekt");
            document.addTitle("Besucher PDF");

            //Erstelle Paragraph
            Paragraph paragraph = new Paragraph("Alle Besucher",new Font(Font.FontFamily.TIMES_ROMAN, 18,
                    Font.BOLD));

            //Neue line
            paragraph.add(new Paragraph(" "));
            paragraph.add("Überblick");
            paragraph.add(new Paragraph(" "));
            document.add(paragraph);

            //Tabelle in PDF generieren
            PdfPTable pdfTable = new PdfPTable(3);
            PdfPCell cell1 = new PdfPCell(new Phrase("Name"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Vorname"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Telefon"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);
            pdfTable.setHeaderRows(1);

            for(VisitorDTO visitor: list) {
                pdfTable.addCell(visitor.getName());
                pdfTable.addCell(visitor.getPrename());
                pdfTable.addCell(visitor.getPhone());
            }

            document.add(pdfTable);

            document.close();
            file.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
