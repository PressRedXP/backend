package meetings;

import java.util.Optional;

import contacts.ContactsService;
import contacts.Person;


public class Attendee {
    final public String id;
    public Optional<Position> position;
    public MeetingStatus status;
    public String name;

    public Attendee(String id, Position position, MeetingStatus status) {
        this.id = id;
        this.position = Optional.of(position);
        this.status = status;
        getNameOfAttendee(id);
    }

    public Attendee(String id, MeetingStatus status) {
        this.id = id;
        this.status = status;
        this.position = Optional.empty();
        getNameOfAttendee(id);
    }

    private void getNameOfAttendee(String id) {
        ContactsService contactsService = new ContactsService();
        Optional<Person> person = contactsService.getContactById(id);
        if (person.isPresent()) {
            name = person.get().name;
        }
    }
}
