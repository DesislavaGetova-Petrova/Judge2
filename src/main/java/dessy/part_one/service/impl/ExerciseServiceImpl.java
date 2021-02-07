package dessy.part_one.service.impl;

import dessy.part_one.model.entity.Exercise;
import dessy.part_one.model.service.ExerciseServiceModel;
import dessy.part_one.repositoty.ExerciseRepository;
import dessy.part_one.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
