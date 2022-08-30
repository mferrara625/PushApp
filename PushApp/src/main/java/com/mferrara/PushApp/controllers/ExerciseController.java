package com.mferrara.PushApp.controllers;

import com.mferrara.PushApp.auth.User;
import com.mferrara.PushApp.models.Exercise;
import com.mferrara.PushApp.models.ExerciseSet;
import com.mferrara.PushApp.repositories.ExerciseRepository;
import com.mferrara.PushApp.repositories.ExerciseSetRepository;
import com.mferrara.PushApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseSetRepository exerciseSetRepository;



    @PostMapping("/create/{id}")
    public ResponseEntity<Exercise> createExercise(@PathVariable Long id , @RequestBody Exercise newExercise){
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.addToExercises(newExercise);
        return new ResponseEntity<>(repository.save(newExercise), HttpStatus.CREATED);
    }

    @PostMapping("/completed/{id}")
    public ResponseEntity<Exercise> completeExercise(@PathVariable Long id , @RequestBody Long[] idArray){
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Exercise current = repository.findById(idArray[0]).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<ExerciseSet> sets = new ArrayList<>();
        System.out.println("ARRAY TEST" + idArray);
        for(int i = 1; i < idArray.length; i++){
            System.out.println("LOOP TEST");
            sets.add(exerciseSetRepository.findById(idArray[i]).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        }
        System.out.println("SETCOMPLETIONTEST"+ sets);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        Exercise newExercise = new Exercise(current.getName(), sets, LocalDateTime.now().format(formatter));
        System.out.println("?!?? TEST DATE TIME ##!# > " + LocalDateTime.now());
        user.addToCompletedExercises(newExercise);

        return new ResponseEntity<>(repository.save(newExercise), HttpStatus.CREATED);
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<Exercise>> getAllExercises() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/u/{id}")
    public @ResponseBody ResponseEntity<Set<Exercise>> getAllUserCreatedExercises(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Set<Exercise> exercises = user.getExercises();
        Map<String, Exercise> newSet = new HashMap<>();

        for(Exercise ex : exercises){
            if(newSet.get(ex.getName()) == null){
                newSet.put(ex.getName(), ex);
            }
            if(newSet.get(ex.getName()).getId() > ex.getId()){
                newSet.put(ex.getName(), ex);
            }
        }
        Set<Exercise> result = new HashSet<>(newSet.values());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/completed/{id}")
    public @ResponseBody ResponseEntity<List<Exercise>> getAllUserCompletedExercises(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Exercise> result = new ArrayList<>();
        for(Exercise exercise : user.getCompletedExercises()){
            if(exercise.getSets().size() > 0){
                result.add(exercise);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestBody Exercise update){
        Exercise current = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(update.getName() != null){
            current.setName(update.getName());
        }

        if(update.getSets() != null){
            current.setSets(update.getSets());
        }


        if(update.getId() != null){
            current.setId(update.getId());
        }


        return new ResponseEntity<>(repository.save(current), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteExercise(@PathVariable Long id) {
        repository.deleteById(id);

        return new ResponseEntity<>("Exercise Deleted", HttpStatus.OK);
    }


}
