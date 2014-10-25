package contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactsService {
    private List<Person> people = new ArrayList<Person>();

    public ContactsService() {
        people.add(new Person("Alex Sneddon", "alex"));
        people.add(new Person("Will Crossland", "will"));
        people.add(new Person("Krishnan Sambasivan", "krishnan"));
        people.add(new Person("Roberto Nerici", "roberto"));
    }
    public Contacts getContacts() {
        return new Contacts(people);
    }

    public Contacts getContactsFor(String id) {

        List<Person> filteredPeople = people;
        filteredPeople.removeIf(p-> p.id.equals(id));

        return new Contacts(filteredPeople);
    }
}
