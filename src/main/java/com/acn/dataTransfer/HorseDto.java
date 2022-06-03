package com.acn.dataTransfer;

import java.util.List;

public class HorseDto
{
    private Long id;
    private Long stableId;
    private Integer allowedDailyFeedings;
    private List<Long> previousFeedings;
    private String name;
    private String alias;
    private String breed;
    private String ownerName;

    public HorseDto()
    {
    }

    public HorseDto(Long id, Long stableId, Integer allowedDailyFeedings, List<Long> previousFeedings, String name, String alias, String breed, String ownerName)
    {
        this.id = id;
        this.stableId = stableId;
        this.allowedDailyFeedings = allowedDailyFeedings;
        this.previousFeedings = previousFeedings;
        this.name = name;
        this.alias = alias;
        this.breed = breed;
        this.ownerName = ownerName;
    }

    public HorseDto(Long stableId, Integer allowedDailyFeedings, List<Long> previousFeedings, String name, String alias, String breed, String ownerName)
    {
        this.stableId = stableId;
        this.allowedDailyFeedings = allowedDailyFeedings;
        this.previousFeedings = previousFeedings;
        this.name = name;
        this.alias = alias;
        this.breed = breed;
        this.ownerName = ownerName;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getStableId()
    {
        return stableId;
    }

    public void setStableId(Long stableId)
    {
        this.stableId = stableId;
    }

    public Integer getAllowedDailyFeedings()
    {
        return allowedDailyFeedings;
    }

    public void setAllowedDailyFeedings(Integer allowedDailyFeedings)
    {
        this.allowedDailyFeedings = allowedDailyFeedings;
    }

    public List<Long> getPreviousFeedings()
    {
        return previousFeedings;
    }

    public void setPreviousFeedings(List<Long> previousFeedings)
    {
        this.previousFeedings = previousFeedings;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public String getBreed()
    {
        return breed;
    }

    public void setBreed(String breed)
    {
        this.breed = breed;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }


}
