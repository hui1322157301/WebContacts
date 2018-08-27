package contact_Dao;

import java.util.List;

import org.dom4j.Document;

import contact_Entity.Contact;

public interface ContactDao {
    public void addContact(Contact contact);
    public void deleteContact(String id);
    public void updateContact(Contact contact);
    public List<Contact> showAll(Document doc);
    public Contact findById(String id);
}
