package dessy.part_one.repositoty;

import dessy.part_one.model.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,String> {
    @Query("select e.name from Exercise as e order by e.name")
    List<String> findAllExNames();

    Optional<Exercise> findByName(String name);
}
