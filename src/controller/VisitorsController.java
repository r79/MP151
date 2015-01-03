package controller;
import java.util.List;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import dao.XmlDAO;
import dto.PerformanceDTO;
import dto.VisitorDTO;

@ManagedBean
@RequestScoped
public class VisitorsController {
	@ManagedProperty(value="#{xmlDAO}")
	private XmlDAO xmlDAO;
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
	
}