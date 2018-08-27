package contact_Servlet;

/*
 * ע�����
 */

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import contact_Utils.ContactUtils;

public class UserRegister extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//��ȡ�û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//��ȡ�û��ļ�
		File userfilePath = new File("E:\\ContactListFiles\\UsersFile.xml");
		Document doc = ContactUtils.readXML(userfilePath);	
		//�ж��û��ļ�����û����ͬ���û�
		//��ȡ���ڵ�
		Element userFileRootElement = doc.getRootElement();
		//��ȡ�û��ڵ�ĵ�����
		Iterator<Element> usersElementIterator = userFileRootElement.elementIterator();
		while(usersElementIterator.hasNext()){
			Element userElement = usersElementIterator.next();
			String usernameFile = userElement.elementText("username");
			String passwordFile = userElement.elementText("password");
			if(usernameFile.equals(username) && passwordFile.equals(password)){
				//�û����ڣ�ת��ע��ʧ��ҳ��
				System.out.println("�û�����");
				response.sendRedirect(request.getContextPath()+"\\RegisterFail.html");
				return;
			}
		}
		//�����û��ڵ�
		Element newUserElement = userFileRootElement.addElement("user");
		Element usernameElement = newUserElement.addElement("username");
		usernameElement.addText(username);
		Element passwordElement = newUserElement.addElement("password");
		passwordElement.addText(password);
		//д�����û��ļ�
		ContactUtils.writeXML(doc,userfilePath);
		
		//�����û�ͨѶ¼
		File userContactPath = new File("E:\\ContactListFiles\\UserContact\\"+username+".xml");
		//����xml
		Document userContactFile = DocumentHelper.createDocument();
		//�������ڵ�
		Element contactRootElement = userContactFile.addElement("contacts");
		//���Ĭ����ϵ��
		Element contactElement = contactRootElement.addElement("contact");
		Element contactIdElement = contactElement.addElement("id");
		contactIdElement.addText("xxx");
		Element contactNameElement = contactElement.addElement("name");
		contactNameElement.addText("xxx");
		Element contactSexElement = contactElement.addElement("sex");
		contactSexElement.addText("xxx");
		Element contactPhoneElement = contactElement.addElement("phone");
		contactPhoneElement.addText("xxx");
		Element contactEmailElement = contactElement.addElement("email");
		contactEmailElement.addText("xxx");
		Element contactQqElement = contactElement.addElement("qq");
		contactQqElement.addText("xxx");
		//������ɣ�д���ļ�
		ContactUtils.writeXML(userContactFile, userContactPath);
		response.sendRedirect(request.getContextPath()+"\\RegisterSuccess.html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	

}
