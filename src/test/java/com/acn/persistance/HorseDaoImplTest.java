package com.acn.persistance;

import com.acn.model.Horse;
import com.acn.model.Stable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HorseDaoImplTest
{
    HorseDao horseDao;
    StableDao stableDao;

    @Autowired
    public HorseDaoImplTest(HorseDao horseDao, StableDao stableDao)
    {
        this.horseDao = horseDao;
        this.stableDao = stableDao;
    }
    @Test
    void readAllHorses()
    {
        Stable testStable =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        stableDao.createStable(testStable);
        Horse testHorse1 = new Horse(testStable,3,new ArrayList<Long>(), "Robin Hoof", "Friday", "Durch Race Horse", "Sebastian Heming");
        Horse testHorse2 = new Horse(testStable,3,new ArrayList<Long>(), "Robin Hoof", "Friday", "Durch Race Horse", "Sebastian Heming");
        Horse testHorse3 = new Horse(testStable,3,new ArrayList<Long>(), "Robin Hoof", "Friday", "Durch Race Horse", "Sebastian Heming");
        horseDao.createHorse(testHorse1);
        horseDao.createHorse(testHorse2);
        horseDao.createHorse(testHorse3);
        List<Horse> testHorses = horseDao.readAllHorses();
        assertNotNull(testHorses);
        assertTrue( testHorses.size() >= 3);
        assertEquals("Friday", testHorses.get(testHorses.size()-1).getAlias());
        horseDao.removeHorseById(testHorse1.getId());
        horseDao.removeHorseById(testHorse2.getId());
        horseDao.removeHorseById(testHorse3.getId());
        stableDao.removeStableById(testStable.getId());
    }

    @Test
    void readHorseById()
    {
        Stable testStable =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        stableDao.createStable(testStable);
        Horse testHorse = new Horse(testStable,3,new ArrayList<Long>(), "Robin Hoof", "Friday", "Durch Race Horse", "Sebastian Heming");
        horseDao.createHorse(testHorse);
        Horse result = horseDao.readHorseById(testHorse.getId());
        assertEquals(testHorse.getId(), result.getId());
        assertEquals(testHorse.getAlias(), result.getAlias());
        horseDao.removeHorseById(testHorse.getId());
        stableDao.removeStableById(testStable.getId());

    }

    @Test
    void createHorse()
    {
        Stable testStable =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        stableDao.createStable(testStable);
        Horse testHorse = new Horse(testStable,3,new ArrayList<Long>(), "Robin Hoof", "Friday", "Durch Race Horse", "Sebastian Heming");
        horseDao.createHorse(testHorse);
        assertNotNull(testHorse.getId());
        Horse checkHorse = horseDao.readHorseById(testHorse.getId());
        assertEquals(testHorse.getAlias(), checkHorse.getAlias());
        horseDao.removeHorseById(testHorse.getId());
        stableDao.removeStableById(testStable.getId());
    }

    @Test
    void updateHorse()
    {
        Stable testStable =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        stableDao.createStable(testStable);
        Horse testHorse = new Horse(testStable,3,new ArrayList<Long>(), "Robin Hoof", "Friday", "Durch Race Horse", "Sebastian Heming");
        horseDao.createHorse(testHorse);
        horseDao.updateHorse(new Horse(testHorse.getId(),testStable,3,new ArrayList<Long>(), "Robin Hoof", "Monday", "Durch Race Horse", "Sebastian Heming"));
        assertEquals("Monday", horseDao.readHorseById(testHorse.getId()).getAlias());
        horseDao.removeHorseById(testHorse.getId());
        stableDao.removeStableById(testStable.getId());
    }

    @Test
    void removeHorseById()
    {        Stable testStable =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        stableDao.createStable(testStable);
        Horse testHorse = new Horse(testStable,3,new ArrayList<Long>(), "Robin Hoof", "Friday", "Durch Race Horse", "Sebastian Heming");
        horseDao.createHorse(testHorse);
        horseDao.removeHorseById(testHorse.getId());
        assertNull(horseDao.readHorseById(testHorse.getId()));
        stableDao.removeStableById(testStable.getId());
    }

    @Test
    void updateHorseFeedingPreferences()
    {
        Stable testStable =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        stableDao.createStable(testStable);
        Horse testHorse = new Horse(testStable,3,new ArrayList<Long>(), "Robin Hoof", "Friday", "Durch Race Horse", "Sebastian Heming");
        horseDao.createHorse(testHorse);
        horseDao.updateHorseFeedingPreferences(testHorse.getId(),5);
        assertEquals(5, horseDao.readHorseById(testHorse.getId()).getAllowedDailyFeedings());
        horseDao.removeHorseById(testHorse.getId());
        stableDao.removeStableById(testStable.getId());
    }

    @Test
    void updateHorseName()
    {
        Stable testStable =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        stableDao.createStable(testStable);
        Horse testHorse = new Horse(testStable,3,new ArrayList<Long>(), "Robin Hoof", "Friday", "Durch Race Horse", "Sebastian Heming");
        horseDao.createHorse(testHorse);
        horseDao.updateHorseName(testHorse.getId(),"Jimmy Hoof");
        assertEquals("Jimmy Hoof", horseDao.readHorseById(testHorse.getId()).getName());
        horseDao.removeHorseById(testHorse.getId());
        stableDao.removeStableById(testStable.getId());
    }

    @Test
    void updateHorseAlias()
    {
        Stable testStable =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        stableDao.createStable(testStable);
        Horse testHorse = new Horse(testStable,3,new ArrayList<Long>(), "Robin Hoof", "Friday", "Durch Race Horse", "Sebastian Heming");
        horseDao.createHorse(testHorse);
        horseDao.updateHorseAlias(testHorse.getId(),"Monday");
        assertEquals("Monday", horseDao.readHorseById(testHorse.getId()).getAlias());
        horseDao.removeHorseById(testHorse.getId());
        stableDao.removeStableById(testStable.getId());
    }

    @Test
    void updateHorseOwnerName()
    {
        Stable testStable =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        stableDao.createStable(testStable);
        Horse testHorse = new Horse(testStable,3,new ArrayList<Long>(), "Robin Hoof", "Friday", "Durch Race Horse", "Sebastian Heming");
        horseDao.createHorse(testHorse);
        horseDao.updateHorseOwnerName(testHorse.getId(),"Sebastian Doe");
        assertEquals("Sebastian Doe", horseDao.readHorseById(testHorse.getId()).getOwnerName());
        horseDao.removeHorseById(testHorse.getId());
        stableDao.removeStableById(testStable.getId());
    }
}