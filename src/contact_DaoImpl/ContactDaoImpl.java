package contact_DaoImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import contact_Dao.ContactDao;
import contact_Entity.Contact;

public class ContactDaoImpl implements ContactDao {

	@Override
	public void addContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteContact(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Contact> showAll(Document doc) {
		//创建联系人集合
		List<Contact> ContactList = new ArrayList<Contact>();
		//读取xml文件
		Element rootElement = doc.getRootElement();
		//遍历联系人
		Iterator<Element> ContactsElement = rootElement.elementIterator();
		while(ContactsElement.hasNext()){
			Contact contact = new Contact();
			//获取联系人信息
			Element ContactElement = ContactsElement.next();
			contact.setId(ContactElement.elementText("id"));
			contact.setName(ContactElement.elementText("name"));
			contact.setSex(ContactElement.elementText("sex"));
			contact.setPhone(ContactElement.elementText("phone"));
			contact.setEmail(ContactElement.elementText("email"));
			contact.setQq(ContactElement.elementText("qq"));
			//存入list集合中
			ContactList.add(contact);
		}
		return ContactList;
	}

	@Override
	public Contact findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
