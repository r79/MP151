package controller;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import pdf.PDFCreator;
import dao.XmlDAO;
import dto.PerformanceDTO;

@ManagedBean
@RequestScoped
//Da bin ich nöd ganz sicher, ich glaub RequestScoped söt gah.
//ViewScoped würd vilicht ja au gah: Also de controller existiert
//nur so lang die view no offe isch.
//Wie gseit da bin ich nöd ganz sicher
public class MainController {
	@ManagedProperty(value="#{calendarController}")
	private CalendarController calendarView;
	@ManagedProperty(value="#{xmlDAO}")
	private XmlDAO xmlDAO;
	@ManagedProperty(value="#{pDFCreator}") //Stimmt das so?
	private PDFCreator pdfcreator;

	public CalendarController getCalendarView() {
		return calendarView;
	}

	public void setCalendarView(CalendarController calendarView) {
		this.calendarView = calendarView;
	}

	public XmlDAO getXmlDAO() {
		return xmlDAO;
	}

	public void setXmlDAO(XmlDAO xmlDAO) {
		this.xmlDAO = xmlDAO;
	}

	public List<PerformanceDTO> getPerformancesAction() {
		return xmlDAO.getDataPerformances(calendarView.getStartDate().getTime(), calendarView.getEndDate().getTime());
	}

	public void generatePerformancesPDF() {
		pdfcreator.generatePerformancesPDF(xmlDAO.getDataPerformances(calendarView.getStartDate().getTime(), calendarView.getEndDate().getTime()));
	}
}
