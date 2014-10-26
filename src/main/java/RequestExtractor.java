import java.util.List;

import com.google.gson.Gson;

public class RequestExtractor {
    private Gson gson = new Gson();

    public List<String> getIdsOfPeopleFrom(String body) {
        MeetingCreation meetingCreation = gson.fromJson(body, MeetingCreation.class);
        List<String> ids = meetingCreation.getPeopleIds();
        return ids;
    }
}
