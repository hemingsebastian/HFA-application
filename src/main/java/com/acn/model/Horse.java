package com.acn.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Horse
{
    @Id
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stable_id", nullable = false)
    private Stable stable;

    protected Horse()
    {

    }

    public Horse(Stable stable)
    {
        this.stable = stable;
    }

    public Horse(UUID id, Stable stable)
    {
        this.id = id;
        this.stable = stable;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public Stable getStable()
    {
        return stable;
    }

    public void setStable(Stable stable)
    {
        this.stable = stable;
    }

    public UUID generateID()
    {
        return UUID.randomUUID();
    }
}
