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

<<<<<<< HEAD
=======
    public Meeting makeMeeting() {
        Meeting meeting = new Meeting(meetings.size() + 1);
        meetings.add(meeting);
        return meeting;
    }

>>>>>>> d16fc555e1f72b3b72597573ddee14cffc070b73
    public Meeting makeMeeting(Attendee organiser, List<String> ids) {
        Meeting meeting = new Meeting(meetings.size() + 1, ids, organiser);
        meetings.add(meeting);
        return meeting;
    }

    public Meeting getMeeting(String meetingId) {
        int index = Integer.parseInt(meetingId);
        if (index <= meetings.size()) {
            Meeting meeting = meetings.get(index - 1);
            meeting.pretendItIsComplete();
            return meeting;
        }

        throw new IndexOutOfBoundsException();
    }
}
