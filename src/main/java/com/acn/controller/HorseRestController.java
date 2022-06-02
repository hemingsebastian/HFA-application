package com.acn.controller;

import com.acn.exceptions.HorseAteTooManyTimesTodayException;
import com.acn.exceptions.HorseAteTooRecentlyException;
import com.acn.model.Horse;
import com.acn.model.Stable;
import com.acn.service.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class HorseRestController
{

    private HorseService horseService;
    @Autowired
    public HorseRestController(HorseService horseService)
    {
        this.horseService = horseService;
    }

    // url: localhost:8080/horses
    @GetMapping("/horses")
    public ResponseEntity<List<Horse>> readAllHorses()
    {
        List<Horse> result = horseService.readAllHorses();

        if(result.size() > 0)
        {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.noContent().build();

    }

    // url: localhost:8080/horses/{id}
    @GetMapping("/horses/{id}")
    public ResponseEntity<Horse> readHorseById(@PathVariable("id") Long id)
    {
        Horse result = horseService.readHorseById(id);

        if(result != null)
        {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.notFound().build();

    }


    //url: localhost:8080/horses/new
    @PostMapping("/horses/new")
    public ResponseEntity createHorse(@RequestBody Horse horse)
    {
        horseService.createHorse(horse);
        if(horse != null)
        {
            return ResponseEntity.created(URI.create("/horses/" + horse.getId())).build();
        }
        return ResponseEntity.badRequest().build();
    }

    //url: localhost:8080/horses/{id}
    @PutMapping("/horses/{id}")
    public ResponseEntity updateHorse(@PathVariable("id") Long id, @RequestBody Horse horse)
    {
        horse.setId(id);

        if(horse != null)
        {
            horseService.updateHorse(horse);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    //url: localhost:8080/horses/{id}/diet?feedPreference=num
    @PutMapping("/horses/{id}/diet")
    public ResponseEntity updateHorseWithFeedingPreference(@PathVariable("id") Long id, @RequestParam("feedPreference") Integer allowedDailyFeedings)
    {
        Horse horse = horseService.readHorseById(id);
        if(horse != null)
        {
            horseService.updateHorseFeedingPreferences(id,allowedDailyFeedings);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    //url: localhost:8080/horses/{id}
    @DeleteMapping("/horses/{id}")
    public ResponseEntity removeHorse(@PathVariable("id") Long id)
    {
        if (horseService.readHorseById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        horseService.removeHorseById(id);
        return ResponseEntity.noContent().build();
    }

    //url: localhost:8080/horses/{id}/feed
    @PutMapping("/horses/{id}/feed")
    public ResponseEntity feedHorse(@PathVariable("id") Long id)
    {
        try
        {
            horseService.feedHorse(id);
            return ResponseEntity.ok().build();
        }
        catch (HorseAteTooRecentlyException e)
        {
            return ResponseEntity.badRequest().build();
        }
        catch (HorseAteTooManyTimesTodayException e)
        {
            return ResponseEntity.badRequest().build();
        }
    }


}
