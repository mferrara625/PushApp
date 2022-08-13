package com.mferrara.PushApp.controllers;

import com.mferrara.PushApp.auth.User;
import com.mferrara.PushApp.models.Exercise;
import com.mferrara.PushApp.models.ExerciseSet;
import com.mferrara.PushApp.repositories.ExerciseSetRepository;
import com.mferrara.PushApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/sets")
public class ExerciseSetController {

    @Autowired
    private ExerciseSetRepository repository;

    @Autowired
    private UserRepository userRepository;



    @PostMapping()
    public ResponseEntity<ExerciseSet> createExerciseSet(@RequestBody ExerciseSet newSet){

        return new ResponseEntity<>(repository.save(newSet), HttpStatus.CREATED);
    }



    @GetMapping
    public @ResponseBody ResponseEntity<List<ExerciseSet>> getAllExerciseSets() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<ExerciseSet> getExerciseSetById(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ExerciseSet> updateExercise(@PathVariable Long id, @RequestBody ExerciseSet update){
        ExerciseSet current = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(update.getReps() != null){
            current.setReps(update.getReps());
        }

        if(update.getWeight() != null){
            current.setWeight(update.getWeight());
        }

        if(update.getId() != null){
            current.setId(update.getId());
        }


        return new ResponseEntity<>(repository.save(current), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteExerciseSet(@PathVariable Long id) {
        repository.deleteById(id);

        return new ResponseEntity<>("Set Deleted", HttpStatus.OK);
    }


}
