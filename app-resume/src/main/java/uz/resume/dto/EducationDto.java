package uz.resume.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.resume.model.Candidate;
import uz.resume.model.Education;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class EducationDto {

    private Integer id;
    private String institution;
    private String city;
    private String qualification;
    private String fieldOfStudy;
    private String graduationYear;
    private Integer candidateid;

    public EducationDto(Education education) {
        BeanUtils.copyProperties(education,this, "candidateid");

        Candidate candidate = education.getCandidate();
        if (candidate != null) {
            candidateid = candidate.getId();
        }
    }
}
