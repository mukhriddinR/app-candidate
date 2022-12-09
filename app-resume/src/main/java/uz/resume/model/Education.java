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
public class Education {

    @Id
    @GeneratedValue
    private Integer id;

    private String institution;

    private String city;

    private String qualification;

    private String fieldOfStudy;

    private String graduationYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidateid")
    private Candidate candidate;

}
