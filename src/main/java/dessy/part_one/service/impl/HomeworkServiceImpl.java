package dessy.part_one.service.impl;

import dessy.part_one.model.entity.Homework;
import dessy.part_one.repositoty.HomeworkRepository;
import dessy.part_one.security.CurrentUser;
import dessy.part_one.service.ExerciseService;
import dessy.part_one.service.HomeworkService;
import dessy.part_one.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    private  final HomeworkRepository homeworkRepository;
    private final ExerciseService exerciseService;
    private final CurrentUser currentUser;
    private final UserService userService;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, ExerciseService exerciseService, CurrentUser currentUser, UserService userService) {
        this.homeworkRepository = homeworkRepository;
        this.exerciseService = exerciseService;
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @Override
    public void addHomework(String exercise, String gitAddress) {
        Homework homework=new Homework();
        homework.setGitAddress(gitAddress);
        homework.setAddedOn(LocalDateTime.now());
        homework.setExercise(exerciseService.findByName(exercise));
        homework.setAuthor(userService.findByUserId(currentUser.getId()));


        homeworkRepository.save(homework);

    }
}
