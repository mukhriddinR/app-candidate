package uz.resume.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.resume.model.Candidate;
import uz.resume.model.Experience;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExperienceDto {

    private Integer id;
    private String jobTitle;
    private String employer;
    private String city;
    private String country;
    private String startYear;
    private String startMonth;
    private String endYear;
    private String endMonth;
    private String description;
    private Integer candidateid;

    public ExperienceDto(Experience experience) {
        BeanUtils.copyProperties(experience, this, "candidateid");

        Candidate candidate = experience.getCandidate();
        if (candidate != null) {
            this.candidateid = candidate.getId();
        }
    }
}
