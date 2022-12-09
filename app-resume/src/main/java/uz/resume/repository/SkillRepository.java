package uz.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.resume.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
