package day2;

import java.util.List;

/**
 * Created by andreicontan on 28/02/2017.
 */
public class Conference {

    public String name;
    public String theme;
    public Location location;
    public String startDate;
    public String endDate;
    public String c4pStartDate;
    public String c4pEndDate;
    public List<Speaker> speakers;
    public List<String> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getC4pStartDate() {
        return c4pStartDate;
    }

    public void setC4pStartDate(String c4pStartDate) {
        this.c4pStartDate = c4pStartDate;
    }

    public String getC4pEndDate() {
        return c4pEndDate;
    }

    public void setC4pEndDate(String c4pEndDate) {
        this.c4pEndDate = c4pEndDate;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
