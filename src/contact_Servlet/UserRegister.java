package contact_Servlet;

/*
 * 注册操作
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
		//获取用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//获取用户文件
		File userfilePath = new File("E:\\ContactListFiles\\UsersFile.xml");
		Document doc = ContactUtils.readXML(userfilePath);	
		//判断用户文件中有没有相同的用户
		//获取根节点
		Element userFileRootElement = doc.getRootElement();
		//获取用户节点的迭代器
		Iterator<Element> usersElementIterator = userFileRootElement.elementIterator();
		while(usersElementIterator.hasNext()){
			Element userElement = usersElementIterator.next();
			String usernameFile = userElement.elementText("username");
			String passwordFile = userElement.elementText("password");
			if(usernameFile.equals(username) && passwordFile.equals(password)){
				//用户存在，转到注册失败页面
				System.out.println("用户存在");
				response.sendRedirect(request.getContextPath()+"\\RegisterFail.html");
				return;
			}
		}
		//增加用户节点
		Element newUserElement = userFileRootElement.addElement("user");
		Element usernameElement = newUserElement.addElement("username");
		usernameElement.addText(username);
		Element passwordElement = newUserElement.addElement("password");
		passwordElement.addText(password);
		//写出到用户文件
		ContactUtils.writeXML(doc,userfilePath);
		
		//增加用户通讯录
		File userContactPath = new File("E:\\ContactListFiles\\UserContact\\"+username+".xml");
		//创建xml
		Document userContactFile = DocumentHelper.createDocument();
		//创建根节点
		Element contactRootElement = userContactFile.addElement("contacts");
		//添加默认联系人
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
		//创建完成，写出文件
		ContactUtils.writeXML(userContactFile, userContactPath);
		response.sendRedirect(request.getContextPath()+"\\RegisterSuccess.html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	

}
