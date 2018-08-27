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
	
	//测试工具类writeXML方法
	//@Test
	public void testWriteXML(){
		
		//获取用户名和密码
		String username = "zhangsan";
		String password = "123456";
		//获取用户文件
		File userfilePath = new File("E:\\ContactListFiles\\UsersFile.xml");
		Document doc = ContactUtils.readXML(userfilePath);	
		//获取根节点
		Element rootElement = doc.getRootElement();
		//增加用户节点
		Element userElement = rootElement.addElement("user");
		Element usernameElement = userElement.addElement("username");
		Element usernameText = usernameElement.addText(username);
		System.out.println(usernameText.getText());
		Element passwordElement = userElement.addElement("password");
		passwordElement.addText(password);
		//写出到用户文件
		ContactUtils.writeXML(doc,userfilePath);
		
	}
	
	//测试showAll()方法
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
