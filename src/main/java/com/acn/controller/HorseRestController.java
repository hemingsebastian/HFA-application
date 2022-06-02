package com.acn.controller;

import com.acn.dataTransfer.HorseDto;
import com.acn.exceptions.HorseAteTooManyTimesTodayException;
import com.acn.exceptions.HorseAteTooRecentlyException;
import com.acn.model.Horse;
import com.acn.model.Stable;
import com.acn.service.HorseService;
import com.acn.service.StableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HorseRestController
{

    private HorseService horseService;
    private StableService stableService;

    public HorseRestController(HorseService horseService, StableService stableService)
    {
        this.horseService = horseService;
        this.stableService = stableService;
    }

    @Autowired


    // url: localhost:8080/horses
    @GetMapping("/horses")
    public ResponseEntity<List<HorseDto>> readAllHorses()
    {
        List<Horse> horses = horseService.readAllHorses();
        List<HorseDto> result = new ArrayList<>();
        for (Horse horse : horses)
        {
            result.add(horse.convertToDto());
        }

        if(result.size() > 0)
        {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.noContent().build();

    }

    // url: localhost:8080/horses/{id}
    @GetMapping("/horses/{id}")
    public ResponseEntity<HorseDto> readHorseById(@PathVariable("id") Long id)
    {
        HorseDto result = horseService.readHorseById(id).convertToDto();

        if(result != null)
        {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.notFound().build();

    }


    //url: localhost:8080/horses/new
    @PostMapping("/horses/new")
    public ResponseEntity createHorse(@RequestBody HorseDto horseDto)
    {

        Horse horse = new Horse(stableService.readStableById(horseDto.getStableId()), horseDto.getAllowedDailyFeedings(), horseDto.getPreviousFeedings(), horseDto.getName(), horseDto.getAlias(), horseDto.getBreed(), horseDto.getOwnerName());

        horseService.createHorse(horse);
        if(horse != null)
        {
            return ResponseEntity.created(URI.create("/horses/" + horse.getId())).build();
        }
        return ResponseEntity.badRequest().build();
    }

    //url: localhost:8080/horses/{id}
    @PutMapping("/horses/{id}")
    public ResponseEntity updateHorse(@PathVariable("id") Long id, @RequestBody HorseDto horseDto)
    {

        Horse horse = new Horse(stableService.readStableById(horseDto.getStableId()), horseDto.getAllowedDailyFeedings(), horseDto.getPreviousFeedings(), horseDto.getName(), horseDto.getAlias(), horseDto.getBreed(), horseDto.getOwnerName());
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
    public ResponseEntity<Boolean> feedHorse(@PathVariable("id") Long id)
    {
        try
        {
            horseService.feedHorse(id);
            return ResponseEntity.ok(true);
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

    //url: localhost:8080/horses/{id}/name?name=str
    @PutMapping("/horses/{id}/name")
    public ResponseEntity updateHorseWithName(@PathVariable("id") Long id, @RequestParam("name") String name)
    {
        Horse horse = horseService.readHorseById(id);
        if(horse != null)
        {
            horseService.updateHorseName(id, name);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    //url: localhost:8080/horses/{id}/alias?alias=str
    @PutMapping("/horses/{id}/alias")
    public ResponseEntity updateHorseWithAlias(@PathVariable("id") Long id, @RequestParam("alias") String alias)
    {
        Horse horse = horseService.readHorseById(id);
        if(horse != null)
        {
            horseService.updateHorseAlias(id, alias);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    //url: localhost:8080/horses/{id}/owner?owner=str
    @PutMapping("/horses/{id}/owner")
    public ResponseEntity updateHorseWithOwnerName(@PathVariable("id") Long id, @RequestParam("owner") String owner)
    {
        Horse horse = horseService.readHorseById(id);
        if(horse != null)
        {
            horseService.updateHorseOwnerName(id, owner);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    //url: localhost:8080/horses/{id}/stable?stableId=num
    @PutMapping("/horses/{id}/stable")
    public ResponseEntity updateHorseWithStableId(@PathVariable("id") Long id, @RequestParam("stableId")Long stableId)
    {
        Horse horse = horseService.readHorseById(id);
        Stable stable = stableService.readStableById(stableId);
        if(horse != null && stable != null)
        {
            horseService.updateHorseStable(id, stable);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
