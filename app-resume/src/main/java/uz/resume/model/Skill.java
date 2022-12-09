package uz.resume.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.resume.enums.SkillLevel;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private SkillLevel level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidateid")
    private Candidate candidate;
}
