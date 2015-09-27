package com.example.mortenjust.deadline;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mortenjust on 9/27/15.
 */
public class TvShowFeedStore {
    static private TvShowFeed[] feeds = {
            new TvShowFeed("http://vpodcast.dr.dk/feeds/deadline2230rss.xml", "Deadline 22:30"),
            new TvShowFeed("http://vpodcast.dr.dk/feeds/debattenrss.xml", "Debatten"),
            new TvShowFeed("http://vpodcast.dr.dk/feeds/kontantrss.xml", "Kontant"),
            new TvShowFeed("http://vpodcast.dr.dk/feeds/TVavisen2130rss.xml", "TV-Avisen"),
            new TvShowFeed("http://www.dr.dk/mu/Feed/vi-ses-hos-clement.xml?format=podcast&limit=500", "Vi ses hos Clement"),
            new TvShowFeed("http://www.dr.dk/mu/Feed/den-store-bagedyst.xml?format=podcast&limit=500", "Den store bagedyst"),
            new TvShowFeed("http://www.dr.dk/mu/Feed/jersild-minus-spin.xml?format=podcast&limit=500", "Jersild minus spin"),
            new TvShowFeed("http://www.dr.dk/mu/Feed/autograf.xml?format=podcast&limit=500", "Autograf"),
            new TvShowFeed("http://www.dr.dk/mu/Feed/kunstrazzia.xml?format=podcast&limit=500", "Kunstrazzia")
    };

    static List<TvShowFeed> getAllFeeds(){
        List<TvShowFeed> tvShowFeedList = new ArrayList<>();

        for(TvShowFeed f : feeds){
            tvShowFeedList.add(f);
        }

        return tvShowFeedList;
    }

}
