package com.example.mortenjust.deadline;

import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ObjectAdapter;

import java.util.Date;

/**
 * Created by mortenjust on 9/27/15.
 */
public class SortableListRow extends ListRow implements Comparable<SortableListRow> {
// this is like a listrow but it also contains a date so we can sort by it

    public Date mostRecentEpisodeDateTime;


    // Do I really have to add all these constructors?

    public SortableListRow(HeaderItem header, ObjectAdapter adapter, Date mostRecent){
        super(header, adapter);
        mostRecentEpisodeDateTime = mostRecent;
    }

    public SortableListRow(HeaderItem header, ObjectAdapter adapter) {
        super(header, adapter);
    }

    public SortableListRow(long id, HeaderItem header, ObjectAdapter adapter){
        super(id, header, adapter);
    }

    public SortableListRow(ObjectAdapter adapter) {
        super(adapter);
    }

    @Override
    public int compareTo(SortableListRow another) {
        return another.getMostRecentEpisodeDateTime().compareTo(getMostRecentEpisodeDateTime());
        //return getMostRecentEpisodeDateTime().compareTo(another.getMostRecentEpisodeDateTime());
    }

    public Date getMostRecentEpisodeDateTime() {
        return mostRecentEpisodeDateTime;
    }
}
