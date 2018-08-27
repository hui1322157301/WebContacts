package contact_Servlet;

/*
 * ��֤��¼
 */

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.Element;

import contact_Utils.ContactUtils;

public class ValidataLogin extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//�õ��û���������
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		//System.out.println(userName+"---"+passWord);
		//�û��ļ�Ŀ¼
		File usersfilePath = new File("E:\\ContactListFiles\\UsersFile.xml");
		Document doc = ContactUtils.readXML(usersfilePath);
		//��ȡ���ڵ�
		Element rootElement = doc.getRootElement();
		//��ȡ�û��ڵ�
		Iterator<Element> usersElem = rootElement.elementIterator();
		while(usersElem.hasNext()){
			//�����û��ڵ�
			Element userElem = usersElem.next();
			String username_str = userElem.elementText("username");
			String password_str = userElem.elementText("password");
			//System.out.println(username_str+"---"+password_str);
			if(username_str.equals(userName) && password_str.equals(passWord)){
				//��¼�ɹ�����
				//��¼�ɹ��󣬰��û����ݱ���session������
				//System.out.println("��¼�ɹ�");
				HttpSession session = request.getSession();
				session.setAttribute("loginname", userName);
				response.sendRedirect(request.getContextPath()+"\\ShowAll.html");
				return;
			}
		}
		//��¼ʧ��
		response.sendRedirect(request.getContextPath()+"\\LoginFail.html");
	}



	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
