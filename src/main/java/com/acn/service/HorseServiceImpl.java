package com.acn.service;

import com.acn.exceptions.HorseAteTooManyTimesTodayException;
import com.acn.exceptions.HorseAteTooRecentlyException;
import com.acn.model.Horse;
import com.acn.persistance.HorseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
@Service
public class HorseServiceImpl implements HorseService
{
    private HorseDao horseDao;
    public static final boolean CHECK_FEEDING_INTERVAL = false;
    @Autowired
    public HorseServiceImpl(HorseDao horseDao)
    {
        this.horseDao = horseDao;
    }

    @Override
    public List<Horse> readAllHorses()
    {
        return horseDao.readAllHorses();
    }

    @Override
    public Horse readHorseById(Long id)
    {
        return horseDao.readHorseById(id);
    }

    @Override@Transactional
    public void createHorse(Horse horse)
    {
        horseDao.createHorse(horse);
    }

    @Override@Transactional
    public void updateHorse(Horse horse)
    {
    horseDao.updateHorse(horse);
    }

    @Override
    public void removeHorseById(Long id)
    {
    horseDao.removeHorseById(id);
    }

    @Override@Transactional
    public void updateHorseFeedingPreferences(Long id, Integer allowedDailyFeedings)
    {
    horseDao.updateHorseFeedingPreferences(id,allowedDailyFeedings);
    }

    @Override@Transactional
    public void feedHorse(Long id) throws HorseAteTooRecentlyException, HorseAteTooManyTimesTodayException
    {
        Horse horse = horseDao.readHorseById(id);
        List<Long> feedingsToday= new ArrayList<>();
        Long now = Instant.now().getEpochSecond();
        LocalDate today = convertUnixTimeStampToLocalDate(now);
        if(horse.getPreviousFeedings().size() > 0)
        {
            Long lastFeeding = horse.getPreviousFeedings().get(horse.getPreviousFeedings().size()-1);
            if(CHECK_FEEDING_INTERVAL && ((now - lastFeeding) < 2 * 60 * 60))
            {
                throw new HorseAteTooRecentlyException("Horse ate only less than two hours ago.");
            }
        }
        for (Long previousFeeding : horse.getPreviousFeedings())
        {
         if(convertUnixTimeStampToLocalDate(previousFeeding).equals(today))
         {
             feedingsToday.add(previousFeeding);
         }
        }

        if(horse.getAllowedDailyFeedings() <= feedingsToday.size())
        {
        throw new HorseAteTooManyTimesTodayException("Limit of daily feedings reached");
        }

        horse.getPreviousFeedings().add(now);
        horseDao.updateHorse(horse);

    }


    private LocalDate convertUnixTimeStampToLocalDate(Long timestamp)
    {
        LocalDateTime localDateTime = Instant.ofEpochSecond(timestamp).atZone(ZoneId.of("Europe/Berlin")).toLocalDateTime();
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;

    }

}
