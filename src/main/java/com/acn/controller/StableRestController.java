package com.acn.controller;

import com.acn.model.Stable;
import com.acn.service.StableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<List<Stable>> readAllStables()
    {
        List<Stable> result = stableService.readAllStables();

        if(result.size() > 0)
        {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.noContent().build();

    }

    // url: localhost:8080/stables/{id}
    @GetMapping("/stables/{id}")
    public ResponseEntity<Stable> readStableById(@PathVariable("id") Long id)
    {
        Stable result = stableService.readStableById(id);

        if(result != null)
        {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.notFound().build();

    }


    //url: localhost:8080/stables/new
    @PostMapping("/stables/new")
    public ResponseEntity createStable(@RequestBody Stable stable)
    {
        stableService.createStable(stable);
        if(stable != null)
        {
            return ResponseEntity.created(URI.create("/stables/" + stable.getId())).build();
        }
        return ResponseEntity.badRequest().build();
    }

    //url: localhost:8080/stables/{id}
    @PutMapping("/stables/{id}")
    public ResponseEntity updateStable(@PathVariable("id") Long id, @RequestBody Stable stable)
    {
        stable.setId(id);

        if(stable != null)
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
        if (stableService.readStableById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        stableService.removeStableById(id);
        return ResponseEntity.noContent().build();
    }

}
