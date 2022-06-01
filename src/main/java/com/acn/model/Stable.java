package com.acn.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Stable
{
    @Id
    private UUID id;
    @OneToMany(mappedBy = "stable", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Horse> horses;


    protected Stable()
    {

    }
    public Stable(List<Horse> horses)
    {
        this.horses = horses;
    }

    public Stable(UUID id, List<Horse> horses)
    {
        this.id = id;
        this.horses = horses;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public List<Horse> getHorses()
    {
        return horses;
    }

    public void setHorses(List<Horse> horses)
    {
        this.horses = horses;
    }
}
