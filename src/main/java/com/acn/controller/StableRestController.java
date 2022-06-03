package com.acn.controller;

import com.acn.dataTransfer.HorseDto;
import com.acn.dataTransfer.StableDto;
import com.acn.exceptions.StableStillHasHorsesException;
import com.acn.model.Horse;
import com.acn.model.Stable;
import com.acn.service.StableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StableRestController
{

    private StableService stableService;

    @Autowired
    public StableRestController(StableService stableService)
    {
        this.stableService = stableService;
    }

    // url: localhost:8080/stables
    @GetMapping("/stables")
    public ResponseEntity<List<StableDto>> readAllStables()
    {
        List<Stable> stables = stableService.readAllStables();
        List<StableDto> result = new ArrayList<>();
        for (Stable stable : stables)
        {
            result.add(stable.convertToDto());
        }

        if (result.size() > 0)
        {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.noContent().build();

    }

    // url: localhost:8080/stables/{id}
    @GetMapping("/stables/{id}")
    public ResponseEntity<StableDto> readStableById(@PathVariable("id") Long id)
    {
        StableDto result = stableService.readStableById(id).convertToDto();

        if (result != null)
        {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.notFound().build();

    }

    //url: localhost:8080/stables/new
    @PostMapping("/stables/new")
    public ResponseEntity createStable(@RequestBody StableDto stableDto)
    {

        List<Horse> horses = new ArrayList<>();
        if (stableDto.getHorses() != null)
        {
            for (HorseDto horseDto : stableDto.getHorses())
            {
                horses.add(new Horse(stableService.readStableById(horseDto.getStableId()), horseDto.getAllowedDailyFeedings(), horseDto.getPreviousFeedings(), horseDto.getName(), horseDto.getAlias(), horseDto.getBreed(), horseDto.getOwnerName()));
            }
        }

        Stable stable = new Stable(stableDto.getTimezone(), horses);
        stableService.createStable(stable);
        if (stable != null)
        {
            return ResponseEntity.created(URI.create("/stables/" + stable.getId())).build();
        }
        return ResponseEntity.badRequest().build();
    }

    //url: localhost:8080/stables/{id}
    @PutMapping("/stables/{id}")
    public ResponseEntity updateStable(@PathVariable("id") Long id, @RequestBody StableDto stableDto)
    {
        List<Horse> horses = new ArrayList<>();
        if (stableDto.getHorses() != null)
        {
            for (HorseDto horseDto : stableDto.getHorses())
            {
                horses.add(new Horse(stableService.readStableById(horseDto.getStableId()), horseDto.getAllowedDailyFeedings(), horseDto.getPreviousFeedings(), horseDto.getName(), horseDto.getAlias(), horseDto.getBreed(), horseDto.getOwnerName()));
            }
        }
        Stable stable = new Stable(stableDto.getTimezone(), horses);
        stable.setId(id);

        if (stable != null)
        {
            stableService.updateStable(stable);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    //url: localhost:8080/stables/{id}
    @DeleteMapping("/stables/{id}")
    public ResponseEntity removeStable(@PathVariable("id") Long id)
    {
        if (stableService.readStableById(id) == null)
        {
            return ResponseEntity.notFound().build();
        }
        try
        {
            stableService.removeStableById(id);
        }
        catch (StableStillHasHorsesException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

}
