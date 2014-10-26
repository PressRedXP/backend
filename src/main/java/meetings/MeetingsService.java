package meetings;

import java.util.ArrayList;
import java.util.List;

// TODO omigod!
public class MeetingsService {
    private static MeetingsService instance;

    private List<Meeting> meetings = new ArrayList<>();

    public static MeetingsService getInstance() {
        if (instance == null) {
            instance = new MeetingsService();
        }

        return instance;
    }

    public Meeting makeMeeting(Attendee organiser, List<String> ids) {
        Meeting meeting = new Meeting(meetings.size() + 1, ids, organiser);
        meetings.add(meeting);
        return meeting;
    }

    public Meeting getMeeting(String meetingId) {
        int index = Integer.parseInt(meetingId);
        if (index <= meetings.size()) {
            Meeting meeting = meetings.get(index - 1);
            return meeting;
        }

        throw new IndexOutOfBoundsException();
    }

    public MeetingList getMeetingsForAttendee(String attendeeId) {
        List<Meeting> filteredMeetings = new ArrayList<>();

        meetings.stream()
                .filter(meeting -> doesMeetingContainAttendee(meeting, attendeeId))
                .forEach(p -> filteredMeetings.add(p));

        MeetingList meetingList = new MeetingList(filteredMeetings);

        return meetingList;
    }

    private boolean doesMeetingContainAttendee(Meeting meeting, String attendeeId) {
        for(Attendee attendee : meeting.people){
            if(attendee.id.equals(attendeeId))
            {
                return true;
            }
        }
        return false;
    }
}
