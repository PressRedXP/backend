import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import meetings.Meeting;
import meetings.MeetingsService;

public class MeetingServiceTest {

    @Test
    public void testCreateMeeting() {
        MeetingsService service = MeetingsService.getInstance();

        Meeting meeting = service.makeMeeting();

        assertNotNull(meeting);
    }

    @Test
    public void testGetMeeting() {
        MeetingsService service = MeetingsService.getInstance();

        service.makeMeeting();
        Meeting meeting = service.getMeeting(1);

        assertNotNull(meeting);
    }
}
