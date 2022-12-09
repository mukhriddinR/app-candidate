package uz.resume.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.resume.enums.SkillLevel;
import uz.resume.model.Candidate;
import uz.resume.model.Skill;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class SkillDto {

    private Long id;
    private String name;
    private SkillLevel level;
    private Integer candidateid;

    public SkillDto(Skill skill) {
        BeanUtils.copyProperties(skill, this, "candidateid");

        Candidate candidate = skill.getCandidate();
        if (candidate != null) {
            this.candidateid = candidate.getId();
        }
    }
}
