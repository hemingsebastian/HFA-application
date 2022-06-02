package com.acn.service;

import com.acn.model.Stable;
import com.acn.persistance.StableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StableServiceImpl implements StableService
{
    private StableDao stableDao;

    @Autowired
    public StableServiceImpl(StableDao stableDao)
    {
        this.stableDao = stableDao;
    }

    @Override
    public List<Stable> readAllStables()
    {
        return stableDao.readAllStables();
    }

    @Override
    public Stable readStableById(Long id)
    {
        return stableDao.readStableById(id);
    }

    @Override
    public void removeStableById(Long id)
    {
        stableDao.removeStableById(id);
    }

    @Override@Transactional
    public void createStable(Stable stable)
    {
        stableDao.createStable(stable);
    }

    @Override@Transactional
    public void updateStable(Stable stable)
    {
    stableDao.updateStable(stable);
    }
}
