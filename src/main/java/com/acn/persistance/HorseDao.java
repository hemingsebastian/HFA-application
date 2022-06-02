package com.acn.persistance;

import com.acn.model.Horse;

import java.util.List;

public interface HorseDao
{

    List<Horse> readAllHorses();
    Horse readHorseById(Long id);
    void createHorse(Horse horse);
    void updateHorse(Horse horse);
    void removeHorseById(Long id);
    void updateHorseFeedingPreferences(Long id, Integer allowedDailyFeedings);
    void updateHorseName(Long id, String name);
    void updateHorseAlias(Long id, String alias);
    void updateHorseOwnerName(Long id, String ownerName);
}
