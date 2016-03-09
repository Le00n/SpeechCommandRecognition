package de.speechcommandrecognition.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SpeechCommandRecognition implements EntryPoint {

	private TextBox nameField;

	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		nameField = new TextBox();
		nameField.setText("GWT");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		SpeechRecognitionService.addCommand("show (me a map)", new SpeechRecognitionHandler() {

			@Override
			public void onSpeechRecognitionCommand(String param) {
				nameField.setText("show");
			}
		});

		SpeechRecognitionService.addCommand("show *param", new SpeechRecognitionHandler() {

			@Override
			public void onSpeechRecognitionCommand(String param) {
				nameField.setText(param);
			}
		});

		SpeechRecognitionService.addCommand("open :appname", new SpeechRecognitionHandler() {

			@Override
			public void onSpeechRecognitionCommand(String param) {
				nameField.setText(param);
			}
		});

		SpeechRecognitionService.addCommand("remove show", new SpeechRecognitionHandler() {

			@Override
			public void onSpeechRecognitionCommand(String param) {
				SpeechRecognitionService.removeCommand("show (me a map)");
				nameField.setText("removed");
			}
		});

		SpeechRecognitionService.start();
	}
}
