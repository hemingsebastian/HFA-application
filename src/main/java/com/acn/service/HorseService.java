package com.acn.service;

import com.acn.exceptions.HorseAteTooManyTimesTodayException;
import com.acn.exceptions.HorseAteTooRecentlyException;
import com.acn.model.Horse;
import com.acn.model.Stable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HorseService
{
    List<Horse> readAllHorses();
    Horse readHorseById(Long id);
    void createHorse(Horse horse);
    void updateHorse(Horse horse);
    void removeHorseById(Long id);
    void updateHorseFeedingPreferences(Long id, Integer allowedDailyFeedings);
    void feedHorse(Long id) throws HorseAteTooRecentlyException, HorseAteTooManyTimesTodayException;
    void updateHorseName(Long id, String name);
    void updateHorseAlias(Long id, String alias);
    void updateHorseOwnerName(Long id, String ownerName);
    void updateHorseStable(Long id, Stable stable);
}
