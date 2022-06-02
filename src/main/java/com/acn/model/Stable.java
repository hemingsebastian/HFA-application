package com.acn.model;

import com.acn.dataTransfer.HorseDto;
import com.acn.dataTransfer.StableDto;

import javax.persistence.*;
import java.util.ArrayList;
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

    public StableDto convertToDto()
    {
        List<HorseDto> horsesDto = new ArrayList<>();
        for (Horse horse : this.horses)
        {
            horsesDto.add(horse.convertToDto());
        }
        return new StableDto(this.id, horsesDto);
    }

}
