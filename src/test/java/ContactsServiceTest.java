import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

import contacts.Contacts;
import contacts.ContactsService;
import contacts.Person;
import org.junit.Test;

public class ContactsServiceTest {

    @Test
    public void testThatServiceReturnsFourContacts() {
        ContactsService service = new ContactsService();

        Contacts contacts = service.getContacts();

        assertTrue(contacts.people.size() == 4);
    }

    @Test
    public void givenFourContacts_whenRequestContacts_thenReturnsOtherThreeContacts() {
        ContactsService service = new ContactsService();

        Contacts contacts = service.getContactsFor("alex");

        assertTrue(contacts.people.size() == 3);
        for (Person person: contacts.people) {
            assertFalse(person.id.equals("alex"));
        }
    }
}

