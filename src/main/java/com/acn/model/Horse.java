package com.acn.model;

import com.acn.dataTransfer.HorseDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "horse")
public class Horse
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "stable_id", nullable = false)
    private Stable stable;
    @Column(nullable=false)
    private Integer allowedDailyFeedings;
    @ElementCollection
    @Column(nullable=false)
    @CollectionTable(name = "previous_feedings")
    private List<Long> previousFeedings;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String alias;
    @Column(nullable=false)
    private String breed;
    @Column(nullable=false)
    private String ownerName;

    protected Horse()
    {
    }

    public Horse(Stable stable, Integer allowedDailyFeedings, List<Long> previousFeedings, String name, String alias, String breed, String ownerName)
    {
        this.stable = stable;
        this.allowedDailyFeedings = allowedDailyFeedings;
        this.previousFeedings = previousFeedings;
        this.name = name;
        this.alias = alias;
        this.breed = breed;
        this.ownerName = ownerName;
    }

    public Horse(Long id, Stable stable, Integer allowedDailyFeedings, List<Long> previousFeedings, String name, String alias, String breed, String ownerName)
    {
        this.id = id;
        this.stable = stable;
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

    public Stable getStable()
    {
        return stable;
    }

    public void setStable(Stable stable)
    {
        this.stable = stable;
    }

    public HorseDto convertToDto()
    {
        return new HorseDto(this.id, this.stable.getId(), this.allowedDailyFeedings, this.previousFeedings, this.name, this.alias, this.breed, this.ownerName);
    }

}
