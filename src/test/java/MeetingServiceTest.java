import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import meetings.Attendee;
import meetings.Meeting;
import meetings.MeetingStatus;
import meetings.MeetingsService;

public class MeetingServiceTest {

    @Test
    public void testCreateMeeting() {
        MeetingsService service = MeetingsService.getInstance();
        Attendee attendee = new Attendee("test", MeetingStatus.confirmed);
        List<String> ids = new ArrayList<String>();
        ids.add("test1");
        Meeting meeting = service.makeMeeting(attendee, ids);

        assertNotNull(meeting);
    }

    @Test
    public void testGetMeeting() {
        MeetingsService service = MeetingsService.getInstance();
        Attendee attendee = new Attendee("test", MeetingStatus.confirmed);
        List<String> ids = new ArrayList<String>();
        ids.add("test1");

        service.makeMeeting(attendee, ids);
        Meeting meeting = service.getMeeting("1");

        assertNotNull(meeting);
    }
}
