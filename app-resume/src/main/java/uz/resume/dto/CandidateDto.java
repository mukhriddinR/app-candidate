package uz.resume.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.resume.model.Candidate;
import uz.resume.model.Education;
import uz.resume.model.Experience;
import uz.resume.model.Skill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CandidateDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String city;
    private String country;
    private List<EducationDto> educations = new ArrayList<>();
    private List<ExperienceDto> experiences = new ArrayList<>();
    private List<SkillDto> skills = new ArrayList<>();

    public CandidateDto(Candidate candidate) {
        BeanUtils.copyProperties(candidate, this, "educations", "experiences", "skills");

        List<Education> educations = candidate.getEducations();
        List<Experience> experiences = candidate.getExperiences();
        List<Skill> skills = candidate.getSkills();

        if (educations != null && educations.size() > 0) {
            educations.forEach(education -> {
                EducationDto shelfDto = new EducationDto(education);
                this.educations.add(shelfDto);
            });
        }

        if (experiences != null && experiences.size() > 0) {
            experiences.forEach(experience -> {
                ExperienceDto shelfDto = new ExperienceDto(experience);
                this.experiences.add(shelfDto);
            });
        }

        if (skills != null && skills.size() > 0) {
            skills.forEach(skill -> {
                SkillDto shelfDto = new SkillDto(skill);
                this.skills.add(shelfDto);
            });
        }
    }
}
