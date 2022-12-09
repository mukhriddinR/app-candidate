package uz.resume.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String email;

    private String city;

    private String country;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Education> educations = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Skill> skills = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    private Date updatedDate;
}
