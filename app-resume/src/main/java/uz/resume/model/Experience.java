package uz.resume.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Experience {

    @Id
    @GeneratedValue
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidateid")
    private Candidate candidate;

}
