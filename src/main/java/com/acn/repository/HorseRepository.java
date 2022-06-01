package com.acn.repository;

import com.acn.model.Horse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HorseRepository extends CrudRepository<Horse, UUID>
{

}
