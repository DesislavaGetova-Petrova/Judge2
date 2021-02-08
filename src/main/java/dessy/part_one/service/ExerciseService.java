package dessy.part_one.service;

import dessy.part_one.model.entity.Exercise;
import dessy.part_one.model.service.ExerciseServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ExerciseService {
   void addExercise(ExerciseServiceModel exerciseServiceModel);

    List<String> findAllNames();

    boolean check(String exercise);

    Exercise findByName(String exercise);
}
