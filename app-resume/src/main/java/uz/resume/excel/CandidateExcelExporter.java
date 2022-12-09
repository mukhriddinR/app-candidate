package uz.resume.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uz.resume.dto.CandidateDto;
import uz.resume.model.Candidate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CandidateExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<CandidateDto> candidateList;

    public CandidateExcelExporter(List<CandidateDto> candidateDtos) {
        this.candidateList = candidateDtos;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Candidates");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "First Name", style);
        createCell(row, 1, "Last Name", style);
        createCell(row, 2, "Birth Date", style);
        createCell(row, 3, "Email", style);
        createCell(row, 4, "City", style);
        createCell(row, 5, "Country", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (CandidateDto candidate : candidateList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, candidate.getFirstName(), style);
            createCell(row, columnCount++, candidate.getLastName(), style);
            createCell(row, columnCount++, candidate.getBirthDate(), style);
            createCell(row, columnCount++, candidate.getEmail(), style);
            createCell(row, columnCount++, candidate.getCity(), style);
            createCell(row, columnCount++, candidate.getCountry(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
