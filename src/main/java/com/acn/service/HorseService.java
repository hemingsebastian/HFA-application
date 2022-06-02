package com.acn.service;

import com.acn.exceptions.HorseAteTooManyTimesTodayException;
import com.acn.exceptions.HorseAteTooRecentlyException;
import com.acn.model.Horse;
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
}
