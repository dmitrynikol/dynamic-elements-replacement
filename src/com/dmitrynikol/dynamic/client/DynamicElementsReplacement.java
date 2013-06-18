package com.dmitrynikol.dynamic.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Example how to extract all emails from string,
 * replace them and change click handler for elements.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public class DynamicElementsReplacement implements EntryPoint {
	
	public static final String text = 
			"&nbsp;&nbsp;<b>Main requirements:</b><br/>"  +
			"Expertise in Java, Spring Framework, Hibernate, JMS, Enterprise Integration Platforms.<br/>" +
			"Experience with GWT, JavaScript, JQuery.<br/>" +
			"Strong object oriented programming concepts: Ability to develop flexible and scalable concept.<br/>" + 
			"Strong understanding of GoF Design Patterns, J2EE Patterns and anti-patterns.<br/>" +
			"&nbsp;&nbsp;<b>Nice to Have:</b><br/>" +
			"Experience working with coherent, terracotta technology.<br/>" +
			"Experience in Subversion, Maven.<br/>" +
			"Outstanding analytical and communication skills.<br/><br/>" +
			"Unix/Linux Experience.<br/>" +
			"Client contact details: first@gmail.com<br/>" +
			"Email your resume in Word to: second@gmail.com";
	
	private HTMLPanel html;
	
	public void onModuleLoad() {
		
		html = new HTMLPanel(text);
		RootPanel.get().add(html);
		
		RootPanel.get().add(new Button("Search and replace emails") {{
			addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					html.getElement().setInnerHTML(text);
					String result = StringUtils.replaceEmail(html.getElement().getInnerHTML());
					html.getElement().setInnerHTML(result);
				}
			});
		}});
		
		RootPanel.get().add(new Button("Change click handler for emails") {{
			addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					NodeList<Element> anchors = html.getElement().getElementsByTagName("a");
					for (int i = 0; i < anchors.getLength(); i++) {
						Element anchor = anchors.getItem(i);
						final Anchor email = new Anchor(anchor.getInnerHTML());
						email.addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								Window.alert("Send an email message");
							}
						});
						html.addAndReplaceElement(email, anchor);
					}
				}
			});
		}});
	}
}