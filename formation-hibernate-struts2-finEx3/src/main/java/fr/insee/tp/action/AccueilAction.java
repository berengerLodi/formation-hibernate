package fr.insee.tp.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AccueilAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		Date date = new Date();
		System.out.println("Date : " + date.toString());
		return SUCCESS;
	}
}
