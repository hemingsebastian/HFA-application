package com.acn.persistance;

import com.acn.model.Stable;
import com.acn.repository.StableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StableDaoImpl implements StableDao
{
    StableRepository stableRepository;

    @Autowired
    public StableDaoImpl(StableRepository stableRepository)
    {
        this.stableRepository = stableRepository;
    }

    @Override
    public List<Stable> readAllStables()
    {
        return (List<Stable>) stableRepository.findAll();
    }

    @Override
    public Stable readStableById(Long id)
    {
        return stableRepository.findById(id).orElse(null);
    }

    @Override
    public void removeStableById(Long id)
    {
        stableRepository.deleteById(id);
    }

    @Override
    public void createStable(Stable stable)
    {
        stableRepository.save(stable);
    }

    @Override
    public void updateStable(Stable stable)
    {
        stableRepository.save(stable);
    }
}
