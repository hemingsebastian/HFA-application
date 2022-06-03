package com.acn.persistance;

import com.acn.model.Stable;

import java.util.List;

public interface StableDao
{
    List<Stable> readAllStables();

    Stable readStableById(Long id);

    void removeStableById(Long id);

    void createStable(Stable stable);

    void updateStable(Stable stable);
}
