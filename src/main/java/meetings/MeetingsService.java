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

    public MeetingsService() {
        numberOfMeetings = 0;
    }

    public void registerMeeting() {
        numberOfMeetings++;
    }
}
