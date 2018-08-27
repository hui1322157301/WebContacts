package contact_Servlet;

/*
 * 显示所有联系人
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;

import contact_DaoImpl.ContactDaoImpl;
import contact_Entity.Contact;
import contact_Utils.ContactUtils;

public class ShowAll extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//创建session并且获取用户名称
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("name");
		//读取用户对应的xml文档
		File userContactsFile = new File("E:\\ContactListFiles\\UserContact\\"+userName+".xml");
		Document userDocument = ContactUtils.readXML(userContactsFile);
		//获取用户联系人list集合
		List<Contact> contactsList = new ContactDaoImpl().showAll(userDocument);
		//写出到浏览器窗口
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		String html = new String();
		html = "<!DOCTYPE html>"
			+"<html lang='en'>"
			+"<head>"
			+"    <style type='text/css'>"
			+"        table{"
			+"            text-align: center;"
			+"            border-collapse: collapse;"
			+"        }"
			+""
			+"    </style>"
			+""
			+"    <meta charset='UTF-8'>"
			+"    <title>ShowAll</title>"
			+"</head>"
			+"<body>"
			+"    <h3 align='center'>admin的所有联系人</h3>"
			+"    <table style='width:800px' align='center' border='2'>"
			+"        <tr>"
			+"            <th>姓名</th>"
			+"            <th>性别</th>"
			+"            <th>电话</th>"
			+"            <th>邮箱</th>"
			+"            <th>QQ</th>"
			+"            <th>操作</th>"
			+"        </tr>"
			+"        <tr>"
			+"            <td>张三</td>"
			+"            <td>男</td>"
			+"            <td>15133654847</td>"
			+"            <td>zahud@163.com</td>"
			+"            <td>364587385</td>"
			+"            <td><a href='Revise.html'>修改</a>&nbsp;&nbsp;&nbsp;<a href=''>删除</a></td>"
			+"        </tr>"
			+"        <tr>"
			+"            <td colspan='6'>"
			+"                <a href='Add.html'>添加联系人</a>"
			+"            </td>"
			+"        </tr>"
			+"    </table>"
			+"</body>"
			+"</html>";
					
		writer.write(html);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
