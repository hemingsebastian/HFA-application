package com.acn.dataTransfer;

public class FeedingDto
{
    private Boolean feedingSuccessful;
    private String feedingInformation;

    public FeedingDto(Boolean feedingSuccessful, String feedingInformation)
    {
        this.feedingSuccessful = feedingSuccessful;
        this.feedingInformation = feedingInformation;
    }

    public Boolean getFeedingSuccessful()
    {
        return feedingSuccessful;
    }

    public void setFeedingSuccessful(Boolean feedingSuccessful)
    {
        this.feedingSuccessful = feedingSuccessful;
    }

    public String getFeedingInformation()
    {
        return feedingInformation;
    }

    public void setFeedingInformation(String feedingInformation)
    {
        this.feedingInformation = feedingInformation;
    }
}
