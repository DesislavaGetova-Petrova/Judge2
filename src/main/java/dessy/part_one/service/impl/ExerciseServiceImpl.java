package dessy.part_one.service.impl;

import dessy.part_one.model.entity.Exercise;
import dessy.part_one.model.service.ExerciseServiceModel;
import dessy.part_one.repositoty.ExerciseRepository;
import dessy.part_one.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addExercise(ExerciseServiceModel exerciseServiceModel) {
        exerciseRepository.save(modelMapper.map(exerciseServiceModel, Exercise.class));

    }

    @Override
    public List<String> findAllNames() {
        return exerciseRepository.findAllExNames();
    }

    @Override
    public boolean check(String exercise) {
        Exercise exerciseEntiry=exerciseRepository.findByName(exercise).orElse(null);
            return exerciseEntiry.getDueDate().isBefore(LocalDateTime.now());
    }

    @Override
    public Exercise findByName(String name) {
        return exerciseRepository.findByName(name).orElse(null);
    }
}
