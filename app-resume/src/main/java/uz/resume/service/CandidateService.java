package uz.resume.service;

import uz.resume.dto.CandidateDto;
import uz.resume.model.Candidate;

import java.util.List;

public interface CandidateService {

    CandidateDto saveCandidate(CandidateDto candidateDto);

    List<CandidateDto> getAllCandidates();

    CandidateDto getCandidateById(Integer id);

    void deleteCandidateById(Integer id);

}
