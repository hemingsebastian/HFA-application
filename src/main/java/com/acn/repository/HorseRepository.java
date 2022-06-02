package com.acn.repository;

import com.acn.model.Horse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HorseRepository extends CrudRepository<Horse, Long>
{
    @Query("update Horse set allowed_daily_feedings = :allowedDailyFeedings where id= :id") @Modifying
    void updateFeedingPreferences(@Param("id")Long id,@Param("allowedDailyFeedings") Integer allowedDailyFeedings);
}
