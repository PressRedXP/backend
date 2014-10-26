import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import contacts.Contacts;
import contacts.ContactsService;
import contacts.Person;

public class ContactsServiceTest {

    @Test
    public void testThatServiceReturnsFiveContacts() {
        ContactsService service = new ContactsService();

        Contacts contacts = service.getContacts();

        assertTrue(contacts.people.size() == 5);
    }

    @Test
    public void givenFiveContacts_whenRequestContacts_thenReturnsOtherFourContacts() {
        ContactsService service = new ContactsService();

        Contacts contacts = service.getContactsFor("alex");

        assertTrue(contacts.people.size() == 4);
        for (Person person: contacts.people) {
            assertFalse(person.id.equals("alex"));
        }
    }
}

