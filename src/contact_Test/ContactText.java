package contact_Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Test;

import contact_DaoImpl.ContactDaoImpl;
import contact_Entity.Contact;
import contact_Utils.ContactUtils;

public class ContactText {
	
	//���Թ�����writeXML����
	//@Test
	public void testWriteXML(){
		
		//��ȡ�û���������
		String username = "zhangsan";
		String password = "123456";
		//��ȡ�û��ļ�
		File userfilePath = new File("E:\\ContactListFiles\\UsersFile.xml");
		Document doc = ContactUtils.readXML(userfilePath);	
		//��ȡ���ڵ�
		Element rootElement = doc.getRootElement();
		//�����û��ڵ�
		Element userElement = rootElement.addElement("user");
		Element usernameElement = userElement.addElement("username");
		Element usernameText = usernameElement.addText(username);
		System.out.println(usernameText.getText());
		Element passwordElement = userElement.addElement("password");
		passwordElement.addText(password);
		//д�����û��ļ�
		ContactUtils.writeXML(doc,userfilePath);
		
	}
	
	//����showAll()����
	//@Test
	public void showAllTest(){
		File userfilePath = new File("E:\\ContactListFiles\\UserContact\\admin.xml");
		Document doc = ContactUtils.readXML(userfilePath);
		List<Contact> ContactsList = new ArrayList<Contact>();
		ContactsList = new ContactDaoImpl().showAll(doc);
		for(Contact contact : ContactsList){
			System.out.println(contact);
		}
	}
}
