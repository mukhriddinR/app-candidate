package uz.resume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import uz.resume.dto.CandidateDto;
import uz.resume.excel.CandidateExcelExporter;
import uz.resume.pdf.CandidateDataPdfExport;
import uz.resume.service.CandidateService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService service;

    @GetMapping
    private List<CandidateDto> getAllCandidates() {
        return service.getAllCandidates();
    }

    @GetMapping("/{objectID}")
    private CandidateDto getCandidate(@PathVariable("objectID") Integer id) {
        return service.getCandidateById(id);
    }

    @PostMapping
    private ResponseEntity<CandidateDto> saveCandidate(@RequestBody CandidateDto candidateDto) {
        CandidateDto newCandidate = service.saveCandidate(candidateDto);
        return new ResponseEntity<>(newCandidate, HttpStatus.CREATED);
    }

    @PutMapping
    public CandidateDto updateCandidate(@RequestBody CandidateDto candidateDto) {
        return service.saveCandidate(candidateDto);
    }

    @DeleteMapping("/{objectID}")
    public void deleteCandidate(@PathVariable("objectID") Integer id) {
        service.deleteCandidateById(id);
    }

    @GetMapping("/pdf")
    public ModelAndView exportToPdf() {
        ModelAndView mav = new ModelAndView();
        mav.setView(new CandidateDataPdfExport());
        List<CandidateDto> list = service.getAllCandidates();
        mav.addObject("list", list);
        return mav;
    }

    @GetMapping("/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=candidate_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            List<CandidateDto> candidates = service.getAllCandidates();

            CandidateExcelExporter excelExporter = new CandidateExcelExporter(candidates);

            excelExporter.export(response);
    }
}
