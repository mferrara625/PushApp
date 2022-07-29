package com.mferrara.PushApp.controllers;

import com.mferrara.PushApp.models.Exercise;
import com.mferrara.PushApp.models.Profile;
import com.mferrara.PushApp.models.StrengthExercise;
import com.mferrara.PushApp.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseRepository repository;

    Exercise ex = new Exercise();
    public void Test(StrengthExercise ex){

        ex.
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise newExercise){
        return new ResponseEntity<>(repository.save(newExercise), HttpStatus.CREATED);
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<Exercise>> getAllExercises() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Exercise> getProfileById(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestBody Exercise update){
        Exercise current = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(update.getName() != null){
            current.setName(update.getName());
        }

        if(update.getWeight() != null){
            current.setWeight(update.getWeight());
        }

        if(update.getSets() != null){
            current.setSets(update.getSets());
        }

        if(update.getExpectedReps() != null){
            current.setExpectedReps(update.getExpectedReps());
        }

        if(update.getRestTime() != null){
            current.setRestTime(update.getRestTime());
        }

        if(update.getActualReps() != null){
            current.setActualReps(update.getActualReps());
        }

        return new ResponseEntity<>(repository.save(current), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteExercise(@PathVariable Long id) {
        repository.deleteById(id);

        return new ResponseEntity<>("Exercise Deleted", HttpStatus.OK);
    }


}
