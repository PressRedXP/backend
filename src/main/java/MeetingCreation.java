import java.util.ArrayList;
import java.util.List;

public class MeetingCreation {
    public Organiser organiser;
    public List<People> people;

    public List<String> getPeopleIds() {
        List<String> ids = new ArrayList<String>();
        for (People p: people) {
            ids.add(p.id);
        }

        return ids;
    }

    private class Organiser {
    }

    private class People {
        public String id;
    }
}
