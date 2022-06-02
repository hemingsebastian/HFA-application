package com.acn.dataTransfer;

import java.util.List;

public class StableDto
{
    private Long id;
    private List<HorseDto> horses;

    public StableDto()
    {
    }

    public StableDto(Long id, List<HorseDto> horses)
    {
        this.id = id;
        this.horses = horses;
    }

    public StableDto(List<HorseDto> horses)
    {
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

    public List<HorseDto> getHorses()
    {
        return horses;
    }

    public void setHorses(List<HorseDto> horses)
    {
        this.horses = horses;
    }
}
