package RESTService.jsonClasses.Yandex.Response;

import java.util.Date;

public class Segment {
    private Date departure;
    private Date arrival;
    private long duration;
    private Station from;
    private Station to;
    private Thread thread;

    public Date getDeparture() {
        return departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public long getDuration() {
        return duration;
    }

    public Station getFrom() {
        return from;
    }

    public Station getTo() {
        return to;
    }

    public Thread getThread() {
        return thread;
    }
}
