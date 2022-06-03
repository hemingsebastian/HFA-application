package com.acn.service;

import com.acn.exceptions.StableStillHasHorsesException;
import com.acn.model.Stable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface StableService
{
    List<Stable> readAllStables();
    Stable readStableById(Long id);
    void removeStableById(Long id) throws StableStillHasHorsesException;
    void createStable(Stable stable);
    void updateStable(Stable stable);
}
