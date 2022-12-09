package uz.resume.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.resume.dto.CandidateDto;
import uz.resume.dto.EducationDto;
import uz.resume.dto.ExperienceDto;
import uz.resume.dto.SkillDto;
import uz.resume.exception.CandidateNotFoundException;
import uz.resume.model.Candidate;
import uz.resume.model.Education;
import uz.resume.model.Experience;
import uz.resume.model.Skill;
import uz.resume.repository.CandidateRepository;
import uz.resume.repository.EducationRepository;
import uz.resume.repository.ExperienceRepository;
import uz.resume.repository.SkillRepository;
import uz.resume.service.CandidateService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    CandidateRepository candicateRepository;
    @Autowired
    ExperienceRepository experienceRepository;
    @Autowired
    EducationRepository educationRepository;
    @Autowired
    SkillRepository skillRepository;

    @Override
    public CandidateDto saveCandidate(CandidateDto candidateDto) {
        Candidate candidate = dtoToEntity(candidateDto);
        Candidate savedCandidate = candicateRepository.save(candidate);
        return new CandidateDto(savedCandidate);
    }

    private Candidate dtoToEntity(CandidateDto candidateDto) {
        Candidate candidate = new Candidate();
        List<Education> educations = new ArrayList<>();
        List<Experience> experiences = new ArrayList<>();
        List<Skill> skills = new ArrayList<>();

        BeanUtils.copyProperties(candidateDto, candidate, "educations", "experiences", "skills", "createdDate", "updatedDate");

        List<EducationDto> educationDtos = candidateDto.getEducations();
        List<ExperienceDto> experienceDtos = candidateDto.getExperiences();
        List<SkillDto> skillDtos = candidateDto.getSkills();

        if (educationDtos != null && educationDtos.size() > 0) {
            educationDtos.forEach(educationDto -> {
                Education education = new Education();
                BeanUtils.copyProperties(educationDto, education);
                education.setCandidate(candidate);
                educations.add(education);
            });
        }
        if (experienceDtos != null && experienceDtos.size() > 0) {
            experienceDtos.forEach(experienceDto -> {
                Experience experience = new Experience();
                BeanUtils.copyProperties(experienceDto, experience);
                experience.setCandidate(candidate);
                experiences.add(experience);
            });
        }
        if (skillDtos != null && skillDtos.size() > 0) {
            skillDtos.forEach(skillDto -> {
                Skill skill = new Skill();
                BeanUtils.copyProperties(skillDto, skill);
                skill.setCandidate(candidate);
                skills.add(skill);
            });
        }
        candidate.setEducations(educations);
        candidate.setExperiences(experiences);
        candidate.setSkills(skills);

        return candidate;
    }

    @Override
    public List<CandidateDto> getAllCandidates() {
        return this.candicateRepository
                .findAll()
                .stream()
                .map(candidate -> new CandidateDto(candidate))
                .collect(Collectors.toList());
    }

    @Override
    public CandidateDto getCandidateById(Integer id) {
        Candidate candidate = this.candicateRepository
                .findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate with Id : " + id + " Not Found"));

        return new CandidateDto(candidate);
    }

    @Override
    public void deleteCandidateById(Integer id) {
        candicateRepository.deleteById(id);
    }
}
