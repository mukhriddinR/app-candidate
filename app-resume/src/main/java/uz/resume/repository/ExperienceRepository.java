package uz.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.resume.model.Experience;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer> {
}
