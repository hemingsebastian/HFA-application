package com.acn.repository;

import com.acn.model.Stable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StableRepository extends CrudRepository<Stable, Long>
{

}
