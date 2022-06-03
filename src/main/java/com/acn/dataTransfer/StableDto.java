package com.acn.dataTransfer;

import java.util.List;

public class StableDto
{
    private Long id;
    private String timezone;
    private List<HorseDto> horses;


    public StableDto(String timezone, List<HorseDto> horses)
    {
        this.timezone = timezone;
        this.horses = horses;
    }

    public StableDto(Long id, String timezone, List<HorseDto> horses)
    {
        this.id = id;
        this.timezone = timezone;
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

    public String getTimezone()
    {
        return timezone;
    }

    public void setTimezone(String timezone)
    {
        this.timezone = timezone;
    }
}
