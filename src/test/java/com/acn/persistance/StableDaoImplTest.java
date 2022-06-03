package com.acn.persistance;

import com.acn.model.Horse;
import com.acn.model.Stable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StableDaoImplTest
{

    StableDao stableDao;
    Stable testStable;
    @Autowired
    public StableDaoImplTest(StableDao stableDao)
    {
        this.stableDao = stableDao;
    }

    @BeforeEach
    void setup()
    {
      assertNotNull(stableDao);

    }

    @AfterEach
    void teardown()
    {

    }


    @Test
    void readAllStables()
    {
        Stable testStable1 =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        Stable testStable2 =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        Stable testStable3 =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        stableDao.createStable(testStable1);
        stableDao.createStable(testStable2);
        stableDao.createStable(testStable3);
        List<Stable> testStables = stableDao.readAllStables();
        assertNotNull(testStables);
        assertTrue( testStables.size() >= 3);
        assertEquals("Europe/Berlin", testStables.get(testStables.size()-1).getTimezone());
        stableDao.removeStableById(testStable1.getId());
        stableDao.removeStableById(testStable2.getId());
        stableDao.removeStableById(testStable3.getId());
    }

    @Test
    void readStableById()
    {
        Stable testStable =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        stableDao.createStable(testStable);

        Stable result = stableDao.readStableById(testStable.getId());
        assertNotNull(result);
        assertEquals("Europe/Berlin", result.getTimezone());
        stableDao.removeStableById(testStable.getId());
    }

    @Test
    void removeStableById()
    {
        Stable testStable =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        stableDao.createStable(testStable);
        stableDao.removeStableById(testStable.getId());
        assertNull(stableDao.readStableById(testStable.getId()));

    }

    @Test
    void createStable()
    {
        Stable testStable =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        stableDao.createStable(testStable);
        assertNotNull(testStable.getId());
        Stable checkStable = stableDao.readStableById(testStable.getId());
        assertEquals(testStable.getTimezone(), checkStable.getTimezone());
        stableDao.removeStableById(testStable.getId());
    }

    @Test
    void updateStable()
    {
        testStable =  new Stable("Europe/Berlin",new ArrayList<Horse>());
        stableDao.createStable(testStable);
        assertNotNull(testStable.getId());
        Stable checkStable = stableDao.readStableById(testStable.getId());
        assertEquals(testStable.getTimezone(), checkStable.getTimezone());
        stableDao.removeStableById(testStable.getId());
    }
}