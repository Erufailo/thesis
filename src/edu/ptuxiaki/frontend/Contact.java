package edu.ptuxiaki.frontend;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Contact extends Composite {

	
	// instance of the class
	static private Contact _instance = null;
	FlowPanel fp = new FlowPanel();

	public Contact() {
		initPage();
		initWidget(fp);

	}
	// Singleton for creating one instance of a page
	public static Contact getInstance() {
		if (_instance == null) {
			_instance = new Contact();
		}
		return _instance;
	}

	public void initPage() {
		HTML contact = new HTML("<h1>Contact Details</h1>");
		Label label = new Label("Name: John Rinakakis");
		Label label2 = new Label("Tel: 6982250484");
		Label label3 = new Label("Address: TEI of Crete");
		Label label4 = new Label("Email: jrin2113@gmail.com");
		
		HorizontalPanel hPanel = new HorizontalPanel();
		VerticalPanel vPanel = new VerticalPanel();
		
		HTML maps = new HTML("<iframe src='https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1991.140026368387!2d25.101717525906967!3d35.3186183259448!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xeaf88a87a5eda840!2zzqTOvM6uzrzOsSDOoM67zrfPgc6_z4bOv8-BzrnOus6uz4I!5e1!3m2!1sel!2sgr!4v1477862426332' 'width='400' height='300' style='border:0' allowfullscreen></iframe>");
		
		label.addStyleName("contactLabel");
		label2.addStyleName("contactLabel");
		label3.addStyleName("contactLabel");
		label4.addStyleName("contactLabel");
	
		vPanel.add(label);
		vPanel.add(label2);
		vPanel.add(label3);
		vPanel.add(label4);
		
		
	
		hPanel.setSpacing(140);
		hPanel.add(vPanel);
		hPanel.add(maps);
		
		hPanel.addStyleName("contactPanel");
	
		fp.add(contact);
		fp.add(hPanel);
		
		fp.addStyleName("mainContent");
	}
}
