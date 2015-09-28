package com.example.mortenjust.deadline;

import android.util.Log;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Commit;

import java.awt.font.TextAttribute;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by mortenjust on 9/26/15.
 */


@Root(strict = false)
public class TvShow {
    String TAG = "mj.tvshow.class";

    @Element(name = "channel")
    Channel channel;

    @Root(strict = false)
    static public class Channel {
        @Element(name = "version", required = false)
        private String version;

        @Element(name = "link", required = false)
        private String link;

        @Element(name = "description", required = false)
        private String description;

        @Element(name = "language", required = false)
        private String language;

        @Element(name = "copyright", required = false)
        private String copyright;

        @Element(name = "managingEditor", required = false)
        private String managingeditor;

        @Element(name = "title", required = false)
        private String showTitle;

        @ElementList(name = "image", type = ShowImage.class, inline = true, required = false)
        private List<ShowImage> showImages;

        @ElementList(name = "item", type = Episode.class, inline = true, required = false)
        private List<Episode> episodes;
//
//        @ElementList(name = "image", required = false, inline = true)
//        private List<Image> images;

        //    @ElementList(inline = true)
        //    private List<Item> items;

    }

    @Root(name = "image", strict = false)
    static public class ShowImage {
        @Element(name = "url", required = false)
        String url;
        @Element(name = "title", required = false)
        String title;
        @Element(name = "link", required = false)
        String link;
    }


    @Root(name = "item", strict = false)
    static public class Episode {
        @Element(name = "title", required = false)
        String title;
        @Element(name = "description", required = false)
        String description;
        @Element(name = "pubDate", required = false)
        String pubDate;
        @Element(name = "enclosure", required = false)
        Enclosure enclosure;

        String getEpisodeTitle(){
            return title;
        }

        String getDescription(){
            return description;
        }

        String getPubDate(){
            return pubDate;
        }

        String getVideoLink(){
            return enclosure.url;
        }
    }

    @Root(name = "Enclosure", strict = false)
    static public class Enclosure {
        @Attribute
        String url;
    }

    public String getShowTitle(){
        return channel.showTitle;
    }

    public String getShowImage(){
        if(channel.showImages != null){
            return channel.showImages.get(0).url;
        }
        else {
            return "http://mortenjust.com/podcast-androidtv/noImage.png";
        }
    }

    public String getCopyright(){
        return channel.copyright;
    }

    public Episode getLatestEpisode(){
        return channel.episodes.get(0);
    }

    public Date getNewestDateTime(){
        String stringDate = getLatestEpisode().getPubDate();

        Log.d(TAG, "stringdate is "+stringDate);


        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        try{
            Date date = format.parse(stringDate);
            Log.d(TAG, "parsed date: "+date);
            return date;

        } catch(ParseException e) {
            Log.d(TAG, "parse exception for date");
            e.printStackTrace();
        }
    return null;
    }


    public List<Episode> getAllEpisodes(){
        return channel.episodes;
    }



}
