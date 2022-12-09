package uz.resume.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import uz.resume.dto.CandidateDto;
import uz.resume.model.Candidate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Date;
import java.util.Map;

public class CandidateDataPdfExport extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.addHeader("Content-Disposition", "attachment;filename=candidate.pdf");

        //read data from controller
        java.util.List<CandidateDto> list = (java.util.List<CandidateDto>) map.get("list");

        //create element
        Font titleFont = new Font(Font.TIMES_ROMAN, 24, Font.BOLD, Color.blue);
        Paragraph title = new Paragraph("ALL CANDIDATES DATA", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingBefore(20.0f);
        title.setSpacingAfter(25.0f);

        //add to document
        document.add(title);

        Font tableHead = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, Color.blue);
        PdfPTable table = new PdfPTable(6);// no.of columns
        table.setWidthPercentage(100.0f);
        table.addCell(new Phrase("FIRSTNAME",tableHead));
        table.addCell(new Phrase("LASTNAME",tableHead));
        table.addCell(new Phrase("BIRTHDAY",tableHead));
        table.addCell(new Phrase("EMAIL",tableHead));
        table.addCell(new Phrase("CITY",tableHead));
        table.addCell(new Phrase("COUNTRY",tableHead));

        for(CandidateDto candidateDto : list ) {
            table.addCell(candidateDto.getFirstName());
            table.addCell(candidateDto.getLastName());
            table.addCell(candidateDto.getBirthDate().toString());
            table.addCell(candidateDto.getEmail());
            table.addCell(candidateDto.getCity());
            table.addCell(candidateDto.getCountry());

        }
        //add table data to document
        document.add(table);
    }

    @Override
    protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
        Font headerFont = new Font(Font.TIMES_ROMAN, 20, Font.BOLD, Color.magenta);
        HeaderFooter header = new HeaderFooter(new Phrase("All Candidates PDF View", headerFont), false);
        header.setAlignment(Element.ALIGN_CENTER);
        document.setHeader(header);

        Font dateFont = new Font(Font.HELVETICA, 12, Font.NORMAL, Color.BLUE);

        HeaderFooter footer = new HeaderFooter(new Phrase("PDF Export Executed On : " + new Date(), dateFont), true);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.setFooter(footer);
    }
}
