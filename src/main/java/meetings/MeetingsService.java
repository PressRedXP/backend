package meetings;

// TODO omigod!
public class MeetingsService {
    private static MeetingsService instance;

    public int numberOfMeetings; // TODO: should be private

    public static MeetingsService getInstance() {
        if (instance == null) {
            instance = new MeetingsService();
        }

        return instance;
    }

    private MeetingsService() {
        numberOfMeetings = 0;
    }

    public Meeting makeMeeting() {
        return new Meeting();
    }

    public Meeting getMeeting(int i) {
        return new Meeting();
    }
}
