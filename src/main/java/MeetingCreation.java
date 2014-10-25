import java.util.List;
import java.util.stream.Collectors;

import meetings.Attendee;
import meetings.MeetingStatus;
import meetings.Position;

public class MeetingCreation {
    public MeetingOrganiser organiser;
    public List<People> people;

    public List<String> getPeopleIds() {
        return people.stream().map(p -> p.id).collect(Collectors.toList());
    }

    public Attendee getOrganiser() {
        return new Attendee(organiser.id, new Position(1, 1), MeetingStatus.confirmed);
    }

    private class MeetingOrganiser {
        public String id;
    }

    private class People {
        public String id;
    }
}
