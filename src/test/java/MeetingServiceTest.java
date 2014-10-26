import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import meetings.Attendee;
import meetings.Meeting;
import meetings.MeetingList;
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
        Attendee organiser = new Attendee("test", MeetingStatus.confirmed);
        List<String> ids = new ArrayList<String>();
        ids.add("test1");

        service.makeMeeting(organiser, ids);
        Meeting meeting = service.getMeeting("1");

        assertNotNull(meeting);
    }

    @Test
    public void givenOneMeetingWithFredAndOneWithout_whenGetMeetingsForFred_thenCorrectMeetingisReturned() {
        MeetingsService service = MeetingsService.getInstance();

        Attendee organiser = new Attendee("fred", MeetingStatus.confirmed);
        service.makeMeeting(organiser, Arrays.asList("wilma", "velma"));

        Attendee organiser2 = new Attendee("harry", MeetingStatus.confirmed);
        service.makeMeeting(organiser2, Arrays.asList("ron", "hermione"));

        MeetingList meetingList = service.getMeetingsForAttendee("fred");

        assertTrue(meetingList.meetings.size() == 1);
    }
}
