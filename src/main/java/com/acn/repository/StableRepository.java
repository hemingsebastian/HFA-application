package com.acn.repository;

import com.acn.model.Stable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StableRepository extends CrudRepository<Stable, Long>
{

}
