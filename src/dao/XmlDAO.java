package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import dto.PerformanceDTO;
import dto.VisitorDTO;

@ManagedBean
@ApplicationScoped
public class XmlDAO {
	private ArrayList<PerformanceDTO> arrayListDTO;
	private ArrayList<VisitorDTO> arrayListDTO2;
	
	public ArrayList<PerformanceDTO> getArrayListDTO() {
		return arrayListDTO;
	}

	public void setArrayListDTO(ArrayList<PerformanceDTO> arrayListDTO) {
		this.arrayListDTO = arrayListDTO;
	}
	
	public ArrayList<VisitorDTO> getArrayListDTO2() {
		return arrayListDTO2;
	}

	public void setArrayListDTO2(ArrayList<VisitorDTO> arrayListDTO2) {
		this.arrayListDTO2 = arrayListDTO2;
	}

	public List<PerformanceDTO> getDataPerformances(long startdate, long enddate) {
		arrayListDTO = new ArrayList<PerformanceDTO>();
		
		URL XMLURL = null;
		InputStream rawInStream = null;
		BufferedReader rdr = null;
		String XML = "";
		try {
			XMLURL = new URL("localhost:8080/performances/" + startdate + "/" + enddate);
			HttpURLConnection conn = (HttpURLConnection) XMLURL.openConnection();
			conn.setRequestMethod("GET");
			rawInStream = conn.getInputStream();
			
			rdr = new BufferedReader(new InputStreamReader(rawInStream, "UTF-8"));
			
			String line = rdr.readLine();
			
			while (line != null) {
				XML += line;
				line = rdr.readLine();
			}
			
			rdr.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Document document = null;
		try {
			document = loadXMLFromString(XML);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		document.normalize();
		
		NodeList nList = document.getElementsByTagName("performance");
		for (int i = 0; i < nList.getLength(); i++) {
			Element elem = (Element) nList.item(i);
			int id = Integer.parseInt(getValueOfSingleChild(elem, "id"));
			
			Long dateLong = Long.parseLong(getValueOfSingleChild(elem, "date"));
			Date date = new Date(dateLong);
			
			String room = getValueOfSingleChild(elem, "room");
			String title = getValueOfSingleChild(elem, "title");
			String titleLink = getValueOfSingleChild(elem, "titleLink");

			arrayListDTO.add(new PerformanceDTO(id, date, room, title, titleLink));
		}
		
		return arrayListDTO;
	}
	
	public List<VisitorDTO> getDataVisitors(int performanceID) {
		arrayListDTO2 = new ArrayList<VisitorDTO>();
		
		URL XMLURL = null;
		InputStream rawInStream = null;
		BufferedReader rdr = null;
		String XML = "";
		try {
			XMLURL = new URL("localhost:8080/visitors/" + performanceID);
			HttpURLConnection conn = (HttpURLConnection) XMLURL.openConnection();
			conn.setRequestMethod("GET");
			rawInStream = conn.getInputStream();
			
			rdr = new BufferedReader(new InputStreamReader(rawInStream, "UTF-8"));
			
			String line = rdr.readLine();
			
			while (line != null) {
				XML += line;
				line = rdr.readLine();
			}
			
			rdr.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Document document = null;
		try {
			document = loadXMLFromString(XML);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		document.normalize();
		
		NodeList nList = document.getElementsByTagName("performance");
		for (int i = 0; i < nList.getLength(); i++) {
			Element elem = (Element) nList.item(i);
			int id = Integer.parseInt(getValueOfSingleChild(elem, "id"));
			String name = getValueOfSingleChild(elem, "name");
			String prename = getValueOfSingleChild(elem, "prename");
			String phone = getValueOfSingleChild(elem, "phone");
			arrayListDTO2.add(new VisitorDTO(id, name, prename, phone));
		}
		
		return arrayListDTO2;
	}
	
	public static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    return builder.parse(is);
	}
	
	public String getValueOfSingleChild(Element elem, String tagName) {
		NodeList nList = elem.getElementsByTagName(tagName);
		if(nList.getLength() < 1) {
			return "";
		}
		Node textNode = nList.item(0);
		return textNode.getFirstChild().getNodeValue();
	}
	
}
