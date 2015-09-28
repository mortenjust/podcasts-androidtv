package com.example.mortenjust.deadline;

import java.util.Date;

/**
 * Created by mortenjust on 9/27/15.
 */
public class TvShowFeed {
    String feedUrl;
    String showName;
    Date mostRecentEpisodeDateTime;

    public TvShowFeed(String url, String name){
        this.feedUrl = url;
        this.showName = name;
    }
}
