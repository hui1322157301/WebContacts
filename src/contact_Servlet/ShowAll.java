package contact_Servlet;

/*
 * ��ʾ������ϵ��
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
		//����session���һ�ȡ�û�����
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("name");
		//��ȡ�û���Ӧ��xml�ĵ�
		File userContactsFile = new File("E:\\ContactListFiles\\UserContact\\"+userName+".xml");
		Document userDocument = ContactUtils.readXML(userContactsFile);
		//��ȡ�û���ϵ��list����
		List<Contact> contactsList = new ContactDaoImpl().showAll(userDocument);
		//д�������������
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
			+"    <h3 align='center'>admin��������ϵ��</h3>"
			+"    <table style='width:800px' align='center' border='2'>"
			+"        <tr>"
			+"            <th>����</th>"
			+"            <th>�Ա�</th>"
			+"            <th>�绰</th>"
			+"            <th>����</th>"
			+"            <th>QQ</th>"
			+"            <th>����</th>"
			+"        </tr>"
			+"        <tr>"
			+"            <td>����</td>"
			+"            <td>��</td>"
			+"            <td>15133654847</td>"
			+"            <td>zahud@163.com</td>"
			+"            <td>364587385</td>"
			+"            <td><a href='Revise.html'>�޸�</a>&nbsp;&nbsp;&nbsp;<a href=''>ɾ��</a></td>"
			+"        </tr>"
			+"        <tr>"
			+"            <td colspan='6'>"
			+"                <a href='Add.html'>�����ϵ��</a>"
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
