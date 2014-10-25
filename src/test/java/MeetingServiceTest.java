import static org.junit.Assert.assertNotNull;

import meetings.Attendee;
import org.junit.Test;

import meetings.Meeting;
import meetings.MeetingsService;

import java.util.ArrayList;
import java.util.List;

public class MeetingServiceTest {

    @Test
    public void testCreateMeeting() {
        MeetingsService service = MeetingsService.getInstance();
        Attendee attendee = new Attendee("test", "confirmed");
        List<String> ids = new ArrayList<String>();
        ids.add("test1");
        Meeting meeting = service.makeMeeting(attendee, ids);

        assertNotNull(meeting);
    }

    @Test
    public void testGetMeeting() {
        MeetingsService service = MeetingsService.getInstance();
        Attendee attendee = new Attendee("test", "confirmed");
        List<String> ids = new ArrayList<String>();
        ids.add("test1");

        service.makeMeeting(attendee, ids);
        Meeting meeting = service.getMeeting("1");

        assertNotNull(meeting);
    }
}
