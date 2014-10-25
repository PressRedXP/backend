package meetings;

import java.util.ArrayList;
import java.util.List;

// TODO omigod!
public class MeetingsService {
    private static MeetingsService instance;

    private List<Meeting> meetings = new ArrayList<Meeting>();

    public static MeetingsService getInstance() {
        if (instance == null) {
            instance = new MeetingsService();
        }

        return instance;
    }

    private MeetingsService() {
    }

    public Meeting makeMeeting() {
        Meeting meeting = new Meeting(meetings.size() + 1);
        meetings.add(meeting);
        return meeting;
    }

    public Meeting getMeeting(int index) {
        if (index <= meetings.size()) {
            Meeting meeting = meetings.get(index - 1);
            meeting.pretendItIsComplete();
            return meeting;
        }

        throw new IndexOutOfBoundsException();
    }
}
