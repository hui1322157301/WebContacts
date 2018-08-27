package contact_Utils;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;



/*
 * 通讯录工具类
 */

public class ContactUtils {
	
	/*
	 * 读取xml
	 */

	public static Document readXML(File readPath) {
		SAXReader reader = new SAXReader();
		Document doc = null;
		if(readPath!=null){
			try {
				doc = reader.read(readPath);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return doc;
	}
	
	/*
	 * 写出xml
	 */
	public static void writeXML(Document doc,File Path){
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(Path);
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			XMLWriter writer = new XMLWriter(fos,format);
			writer.write(doc);
			writer.close();
			System.out.println("xml写入成功");
		} catch (Exception e) {
			System.out.println("xml写入失败");
			e.printStackTrace();
		}
	}
}
