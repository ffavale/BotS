package bots.simulation;

import logg.*;
import java.util.Random;

public class Event
{
    public static final EventTemplate[] eventTemplates = {
        new EventTemplate("Universal", -2, -1),
        new EventTemplate("Date", 2, 3),
        new EventTemplate("Gathering", 3, 7),
        new EventTemplate("Party", 7, 71),
        new EventTemplate("Convention", 71, 151)
    };

    private int eventId;
    private String eventTypeName;
    public int participantCount;
    public int minAge;
    public int maxAge;
    private Logg log;

    public Event(int i_iterationNumber, Logg i_log)
    {
        this.eventId = i_iterationNumber;
        this.log = i_log;

        Random rng = new Random();
        EventTemplate thisEvent = Event.eventTemplates[rng.nextInt(Event.eventTemplates.length)];

        this.participantCount = rng.nextInt(thisEvent.maxAttendees - thisEvent.minAttendees) + thisEvent.minAttendees;
        this.minAge = rng.nextInt(thisEvent.maxMinAge - thisEvent.minMinAge) + thisEvent.minMinAge;
        this.maxAge = rng.nextInt(thisEvent.maxMaxAge - thisEvent.minMaxAge) + thisEvent.minMaxAge;
        this.eventTypeName = thisEvent.eventTypeName;

        this.log.logMessage(
                "Created Event-" + String.valueOf(eventId) +
                " of type " + this.eventTypeName +
                " with " + this.participantCount + " participants" +
                " between the ages of " + this.minAge + " and " + this.maxAge); //, "Event-" + String.valueOf(this.eventId));
    }
}

class EventTemplate
{
    public String eventTypeName;
    public int minAttendees;
    public int maxAttendees;
    public int minMinAge;
    public int maxMinAge;
    public int minMaxAge;
    public int maxMaxAge;

    public EventTemplate(String i_eventTypeName, int i_minAttendees, int i_maxAttendees)
    {
        this.eventTypeName = i_eventTypeName;
        this.minAttendees = i_minAttendees;
        this.maxAttendees = i_maxAttendees;
    }
}
