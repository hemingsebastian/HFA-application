package com.acn.persistance;

import com.acn.model.Stable;

import java.util.List;
import java.util.UUID;

public interface StableDao
{
    List<Stable> readAllStables();
    Stable readStableById(Long id);
    void removeStableById(Long id);
    void createStable(Stable stable);
    void updateStable(Stable stable);
}
