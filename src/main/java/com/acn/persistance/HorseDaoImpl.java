package com.acn.persistance;

import com.acn.model.Horse;
import com.acn.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class HorseDaoImpl implements HorseDao
{
    HorseRepository horseRepository;

    @Autowired
    public HorseDaoImpl(HorseRepository horseRepository)
    {
        this.horseRepository = horseRepository;
    }

    @Override
    public List<Horse> readAllHorses()
    {
        return (List<Horse>) horseRepository.findAll();
    }

    @Override
    public Horse readHorseById(Long id)
    {
        return horseRepository.findById(id).orElse(null);
    }

    @Override
    public void createHorse(Horse horse)
    {
     horseRepository.save(horse);
    }

    @Override
    public void updateHorse(Horse horse)
    {
    horseRepository.save(horse);
    }

    @Override
    public void removeHorseById(Long id)
    {
    horseRepository.deleteById(id);
    }

    @Override
    public void updateHorseFeedingPreferences(Long id, Integer allowedDailyFeedings)
    {
        horseRepository.updateFeedingPreferences(id,allowedDailyFeedings);
    }

    @Override
    public void updateHorseName(Long id, String name)
    {
        horseRepository.updateName(id, name);
    }

    @Override
    public void updateHorseAlias(Long id, String alias)
    {
        horseRepository.updateAlias(id, alias);
    }

    @Override
    public void updateHorseOwnerName(Long id, String ownerName)
    {
    horseRepository.updateOwnerName(id, ownerName);
    }


}
