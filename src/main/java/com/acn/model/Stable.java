package com.acn.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "stable")
public class Stable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "stable", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Horse> horses;

    protected Stable()
    {

    }
    public Stable(List<Horse> horses)
    {
        this.horses = horses;
    }

    public Stable(Long id, List<Horse> horses)
    {
        this.id = id;
        this.horses = horses;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
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
