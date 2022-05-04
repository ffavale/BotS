package bots.simulation;

import logg.*;
import bots.simulation.individual.*;
import java.util.*;

public class Event
{
    public static final EventTemplate[] eventTemplates = {
        new EventTemplate("Date", 2, 3, 100, 5000, 6000, 10000),
        new EventTemplate("Gathering", 3, 7, 100, 2500, 3000, 6000),
        new EventTemplate("Party", 7, 71, 100, 2500, 3000, 7000),
        new EventTemplate("Convention", 71, 151, 500, 6000, 7000, 12000)
    };

    private int eventId;
    private String eventTypeName;
    public int participantCount;
    public int minAge;
    public int maxAge;

    private Individual[] participants;
    private Logg log;

    public Event(int i_iterationNumber, ArrayList<Individual> i_candidates, Logg i_log)
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
                " of type \"" + this.eventTypeName + "\"" +
                " with " + this.participantCount + " participants" +
                " between the ages of " + this.minAge + " and " + this.maxAge); //, "Event-" + String.valueOf(this.eventId));

        this.participants = this.selectAttendees(resizeEvent(i_candidates), rng);
    }

    public Event(int i_iterationNumber, ArrayList<Individual> i_candidates, Logg i_log, boolean i_isUniversal)
    {
        this.eventId = i_iterationNumber;
        this.log = i_log;

        this.participantCount = i_candidates.size();
        this.minAge = 0;
        this.maxAge = i_iterationNumber;
        this.eventTypeName = "Universal";

        this.log.logMessage(
                "Created Event-" + String.valueOf(eventId) +
                " of type \"" + this.eventTypeName + "\"" +
                " with " + this.participantCount + " participants"); //, "Event-" + String.valueOf(this.eventId));

        this.participants = new Individual[this.participantCount];
        for (int i = 0; i < i_candidates.size(); i++)
        {
            this.participants[i] = i_candidates.get(i);
            this.log.logMessage("Individual-" + String.valueOf(participants[i].getId()) + " is participating in Event-" + String.valueOf(this.eventId), "Event-" + String.valueOf(this.eventId));
        }
    }

    private ArrayList<Individual> resizeEvent(ArrayList<Individual> i_candidates)
    {
        int satisfactoryCandidateCount = 0;
        ArrayList<Individual> satisfactoryCandidates = new ArrayList<Individual>();

        for (Individual ind : i_candidates)
        {
            if (this.minAge <= ind.getAge() && ind.getAge() <= this.maxAge)
            {
                satisfactoryCandidateCount++;
                satisfactoryCandidates.add(ind);
            }
        }

        if (satisfactoryCandidateCount < this.participantCount)
        {
            this.participantCount = satisfactoryCandidateCount;
            this.log.logMessage("Event capacity resized to " + String.valueOf(this.participantCount) + " due to lack of suitable candidates for the event", "Event-" + String.valueOf(this.eventId));
        }

        return satisfactoryCandidates;
    }

    private Individual[] selectAttendees(ArrayList<Individual> i_candidates, Random i_rng)
    {
        Individual[] returnIndividualArray = new Individual[this.participantCount];

        for (int i = 0; i < this.participantCount; i++)
        {
            int consideredIdx = i_rng.nextInt(i_candidates.size());

            returnIndividualArray[i] = i_candidates.get(consideredIdx);
            i_candidates.remove(returnIndividualArray[i]);
            this.log.logMessage("Individual-" + String.valueOf(returnIndividualArray[i].getId()) + " is participating in Event-" + String.valueOf(this.eventId), "Event-" + String.valueOf(this.eventId));
        }

        return returnIndividualArray;
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

    public EventTemplate(String i_eventTypeName, int i_minAttendees, int i_maxAttendees, int i_minMinAge, int i_maxMinAge, int i_minMaxAge, int i_maxMaxAge)
    {
        this.eventTypeName = i_eventTypeName;
        this.minAttendees = i_minAttendees;
        this.maxAttendees = i_maxAttendees;
        this.minMinAge = i_minMinAge;
        this.maxMinAge = i_maxMinAge;
        this.minMaxAge = i_minMaxAge;
        this.maxMaxAge = i_maxMaxAge;
    }
}
