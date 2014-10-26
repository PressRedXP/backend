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

    public Meeting getMeeting(String meetingIdAsString) {
        return findMeeting(meetingIdAsString);
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
        for (Attendee attendee : meeting.people) {
            if (attendee.id.equals(attendeeId)) {
                return true;
            }
        }
        return false;
    }

    public void setAttendence(String meetingId, String attendeeId, Position position) {

        Meeting meeting = findMeeting(meetingId);
        meeting.updateAttendence(attendeeId, MeetingStatus.confirmed, position);
    }


    private Meeting findMeeting(String meetingIdAsString) {
        Integer index = Integer.parseInt(meetingIdAsString) - 1;
        if (index < meetings.size() && index >= 0) {
            return meetings.get(index);
        }

        throw new IndexOutOfBoundsException();
    }
}
