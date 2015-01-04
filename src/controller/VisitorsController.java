package controller;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import pdf.PDFCreator;
import dao.XmlDAO;
import dto.PerformanceDTO;
import dto.VisitorDTO;

@ManagedBean
@RequestScoped
/*
 * Ich glaub das müsste mer ändere, wil die PerformanceID wird verlore gah wenn das Bean
 * zerstört wird (nach request), das heisst ab visitors.xhtml wird es glaub ich zerstört
 * und das performance Objekt vo vorher isch ja nirgends gspeichert
 * Daher vilicht SessionScoped, so dass das länger lebt
 *
 * das isch nur e these; am beste du probiersch das us oder ändersch wie du es für richtig
 * haltisch.
 */
public class VisitorsController {
	@ManagedProperty(value="#{xmlDAO}")
	private XmlDAO xmlDAO;
	@ManagedProperty(value="#{pDFCreator}") //Stimmt das so?
	private PDFCreator pdfcreator;
	private int performanceID;

	public XmlDAO getXmlDAO() {
		return xmlDAO;
	}

	public void setXmlDAO(XmlDAO xmlDAO) {
		this.xmlDAO = xmlDAO;
	}

	public int getPerformanceID() {
		return performanceID;
	}

	public void setPerformanceID(int performanceID) {
		this.performanceID = performanceID;
	}

	public List<VisitorDTO> showVisitors() {
		return xmlDAO.getDataVisitors(performanceID);
	}

	public String visitorsAction(PerformanceDTO performance) {
		performanceID = performance.getId();
		return "visitors.xhtml";
	}

	public void generateVisitorsPDF() {
		pdfcreator.generateVisitorsPDF(xmlDAO.getDataVisitors(performanceID));
	}

}