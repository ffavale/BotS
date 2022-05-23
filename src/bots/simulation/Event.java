package bots.simulation;

import logg.*;
import bots.simulation.individual.*;
import java.util.*;

public class Event
{
    // define the templates used to create events
    public static final EventTemplate[] eventTemplates = {
        new EventTemplate("Date", 2, 3, 100, 5000, 6000, 10000),
        new EventTemplate("Gathering", 3, 7, 100, 2500, 3000, 6000),
        new EventTemplate("Party", 7, 70, 100, 2500, 3000, 7000),
        new EventTemplate("Convention", 70, 151, 500, 6000, 7000, 12000)
    };

    private long eventId;
    private String eventTypeName;
    public int participantCount;
    public int minAge;
    public int maxAge;

    private Individual[] participants;
    private Individual[] malePart;
    private Individual[] femalePart;

    private ArrayList<Couple> couplesList= new ArrayList<Couple>();

    private Logg log;
    private Random rng = new Random();

    public Event(long i_iterationNumber, ArrayList<Individual> i_candidates, Logg i_log)
    {
        // set eventId and log
        this.eventId = i_iterationNumber;
        this.log = i_log;

        // define the type of event this is
        EventTemplate thisEvent = Event.eventTemplates[this.rng.nextInt(Event.eventTemplates.length)];

        // decide what its specific charachteristics are
        this.participantCount = this.rng.nextInt(thisEvent.maxAttendees - thisEvent.minAttendees) + thisEvent.minAttendees;
        this.minAge = this.rng.nextInt(thisEvent.maxMinAge - thisEvent.minMinAge) + thisEvent.minMinAge;
        this.maxAge = this.rng.nextInt(thisEvent.maxMaxAge - thisEvent.minMaxAge) + thisEvent.minMaxAge;
        this.eventTypeName = thisEvent.eventTypeName;

        this.log.logQuietMessage(
                "Created Event-" + String.valueOf(eventId) +
                " of type \"" + this.eventTypeName + "\"" +
                " with " + this.participantCount + " participants" +
                " between the ages of " + this.minAge + " and " + this.maxAge); //, "Event-" + String.valueOf(this.eventId));

        // populate the event
        this.participants = this.selectAttendees(createInvitationList(i_candidates), this.rng);

        splitPartByGender();
    }

    public Event(long i_iterationNumber, ArrayList<Individual> i_candidates, Logg i_log, boolean i_isUniversal)
    {
        // set eventId and log
        this.eventId = i_iterationNumber;
        this.log = i_log;

        // decide what its specific charachteristics are
        this.participantCount = i_candidates.size();
        this.minAge = 0;
        this.maxAge = (int) i_iterationNumber;
        this.eventTypeName = "Universal";

        this.log.logQuietMessage(
                "Created Event-" + String.valueOf(eventId) +
                " of type \"" + this.eventTypeName + "\"" +
                " with " + this.participantCount + " participants"); //, "Event-" + String.valueOf(this.eventId));

        // populate the event
        this.participants = new Individual[this.participantCount];
        for (int i = 0; i < i_candidates.size(); i++)
        {
            this.participants[i] = i_candidates.get(i);
            // this.log.logQuietMessage("Individual-" + String.valueOf(participants[i].getId()) + " is participating in Event-" + String.valueOf(this.eventId), "Event-" + String.valueOf(this.eventId));
        }

        splitPartByGender();
    }

    public ArrayList<Couple> createCouples()
    {
        for (int h = 0; h < this.participants.length; h++)
        {
            Individual individual = participants[this.rng.nextInt(this.participants.length)];

            if (!individual.isAvailable)
            {
                continue;
            }
            switch (individual.sex)
            {
                case MALE:
                {
                    for(int i = 0; i < this.femalePart.length; i++)
                    {
                        if (femalePart[i].isAvailable)
                        {
                            this.couplesList.add(new Couple(individual, femalePart[i], this.log));
                        }
                    }
                    break;
                }
                case FEMALE:
                {
                    for(int i = 0; i < this.malePart.length; i++)
                    {
                        if (malePart[i].isAvailable)
                        {
                            this.couplesList.add(new Couple(malePart[i], individual, this.log));
                        }
                    }
                    break;
                }
            }
        }
        return this.couplesList;
    }

    private void splitPartByGender()
    {
        ArrayList<Individual> tempMale = new ArrayList<Individual>();
        ArrayList<Individual> tempFemale = new ArrayList<Individual>();

        for (int i = 0; i < participants.length; i++)
        {
            if (participants[i].sex == Individual.Gender.MALE)
            {
                tempMale.add(participants[i]);
            } else
            {
                tempFemale.add(participants[i]);
            }
        }

        this.malePart = new Individual[tempMale.size()];
        this.femalePart = new Individual[tempFemale.size()];
        this.malePart = tempMale.toArray(malePart);
        this.femalePart = tempFemale.toArray(femalePart);

        this.log.logQuietMessage("Male/Female/Total : " +
                String.valueOf(this.malePart.length) + "/" +
                String.valueOf(this.femalePart.length) + "/" +
                String.valueOf(this.participants.length)
                , "Event-" + String.valueOf(this.eventId));
    }

    private ArrayList<Individual> createInvitationList(ArrayList<Individual> i_candidates)
    {
        int satisfactoryCandidateCount = 0;
        ArrayList<Individual> invitationList = new ArrayList<Individual>();

        // find all suitable candidates and add them to the invitation list
        for (Individual ind : i_candidates)
        {
            if (this.minAge <= ind.getAge() && ind.getAge() <= this.maxAge)
            {
                satisfactoryCandidateCount++;
                invitationList.add(ind);
            }
        }

        // resize event if not enough suitable candidates are found
        if (satisfactoryCandidateCount < this.participantCount)
        {
            this.participantCount = satisfactoryCandidateCount;
            this.log.logQuietMessage("Event capacity resized to " + String.valueOf(this.participantCount) + " due to lack of suitable candidates for the event", "Event-" + String.valueOf(this.eventId));
        }

        return invitationList ;
    }

    private Individual[] selectAttendees(ArrayList<Individual> i_candidates, Random i_rng)
    {
        Individual[] attendeeArray = new Individual[this.participantCount];

        // pick attendees at random from the invitation array
        for (int i = 0; i < this.participantCount; i++)
        {
            int consideredIdx = i_rng.nextInt(i_candidates.size());

            attendeeArray[i] = i_candidates.get(consideredIdx);
            i_candidates.remove(attendeeArray[i]);
            // this.log.logQuietMessage("Individual-" + String.valueOf(attendeeArray[i].getId()) + " is participating in Event-" + String.valueOf(this.eventId), "Event-" + String.valueOf(this.eventId));
        }

        return attendeeArray;
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
