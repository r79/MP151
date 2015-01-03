package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class CalendarController {
	private Date startDate;
	private Date endDate;

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected",
						format.format(event.getObject())));

	}

	/*
	 * <h:inputText value="#{contactData.name}" required="true" id="name"
	 * requiredMessage="Enddatum vor Startdatum, Error 404"
	 * validator="#{contactController.checkName}"/>
	 */

	// FacesContext context, UIComponent component, Object value
	public void click() {
		RequestContext requestContext = RequestContext.getCurrentInstance();

		requestContext.update("form:display");
		requestContext.execute("PF('dlg').show()");
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void checkDate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		UIInput confirmComponent = (UIInput) component.getAttributes().get(
				"second");

		String startDate = ((UIInput) component).getSubmittedValue().toString();
		String endDate = confirmComponent.getSubmittedValue().toString();

		SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MMM-dd");
		Date startDateDate = null;
		Date endDateDate = null;
		
		try {
			startDateDate = parseFormat.parse(startDate);
			endDateDate = parseFormat.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (startDateDate.after(endDateDate)) {
			throw new ValidatorException(new FacesMessage("Startdatum nach Enddatum"));
		}

	}

}