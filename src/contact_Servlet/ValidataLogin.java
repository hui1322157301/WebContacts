package contact_Servlet;

/*
 * 验证登录
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
		//得到用户名和密码
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		//System.out.println(userName+"---"+passWord);
		//用户文件目录
		File usersfilePath = new File("E:\\ContactListFiles\\UsersFile.xml");
		Document doc = ContactUtils.readXML(usersfilePath);
		//获取根节点
		Element rootElement = doc.getRootElement();
		//获取用户节点
		Iterator<Element> usersElem = rootElement.elementIterator();
		while(usersElem.hasNext()){
			//遍历用户节点
			Element userElem = usersElem.next();
			String username_str = userElem.elementText("username");
			String password_str = userElem.elementText("password");
			//System.out.println(username_str+"---"+password_str);
			if(username_str.equals(userName) && password_str.equals(passWord)){
				//登录成功操作
				//登录成功后，把用户数据保存session对象中
				//System.out.println("登录成功");
				HttpSession session = request.getSession();
				session.setAttribute("loginname", userName);
				response.sendRedirect(request.getContextPath()+"\\ShowAll.html");
				return;
			}
		}
		//登录失败
		response.sendRedirect(request.getContextPath()+"\\LoginFail.html");
	}



	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
