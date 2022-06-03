package com.acn.persistance;

import com.acn.model.Horse;
import com.acn.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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

    @Override@Transactional
    public void updateHorseFeedingPreferences(Long id, Integer allowedDailyFeedings)
    {
        horseRepository.updateFeedingPreferences(id,allowedDailyFeedings);
    }

    @Override@Transactional
    public void updateHorseName(Long id, String name)
    {
        horseRepository.updateName(id, name);
    }

    @Override@Transactional
    public void updateHorseAlias(Long id, String alias)
    {
        horseRepository.updateAlias(id, alias);
    }

    @Override@Transactional
    public void updateHorseOwnerName(Long id, String ownerName)
    {
    horseRepository.updateOwnerName(id, ownerName);
    }


}
