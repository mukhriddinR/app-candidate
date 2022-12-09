package uz.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.resume.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

}
