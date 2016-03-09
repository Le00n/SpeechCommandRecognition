package de.speechcommandrecognition.client;

public class SpeechRecognitionService {

	public enum Language {
		GERMAN, ENGLISH
	};

	/**
	 * Startet die Spracherkennung
	 */
	public static native void start()/*-{
		if ($wnd.annyang) {
			$wnd.annyang.start();
		}
	}-*/;

	/**
	 * Beendet die Spracherkennung
	 */
	public static native void stop()/*-{
		if ($wnd.annyang) {
			$wnd.annyang.stop();
		}
	}-*/;

	/**
	 * Pausiert die Spracherkennung
	 */
	public static native void pause()/*-{
		if ($wnd.annyang) {
			$wnd.annyang.pause();
		}
	}-*/;

	/**
	 * Beendet die Spracherkennungspause
	 */
	public static native void resume()/*-{
		if ($wnd.annyang) {
			$wnd.annyang.resume();
		}
	}-*/;

	/**
	 * Setzt die Sprache der Spracherkennung
	 * 
	 * @param language
	 *            zuk�nftig zu erkennende Sprache
	 */
	public static void setLanguage(Language language) {
		switch (language) {
		case ENGLISH:
			setLanguage("en-US");
			break;
		case GERMAN:
			setLanguage("de-DE");
			break;
		}
	}

	private static native void setLanguage(String language)/*-{
		if ($wnd.annyang) {
			$wnd.annyang.setLanguage(language);
		}
	}-*/;

	/**
	 * F�gt einen Spracherkennungsbefehl hinzu Ein Spracherkennungsbefehl kann
	 * nach folgenden Mustern aufgebaut sein: - "Text" --> nur der exakte
	 * Wortlaut wird erkannt - "Text (und Schrift)" --> in Klammern = optional
	 * --> sowohl "Text" als auch "Text und Schrift" wird erkannt - "Text *" -->
	 * Callback wird mit dem Parameter * aufgerufen, wobei * f�r 1-n W�rter
	 * steht - "Text :p" --> Callback wird mit dem Parameter p aufgerufen, wobei
	 * p f�r genau 1 Wort steht
	 * 
	 * @param command
	 *            Spracherkennungsbefehl
	 * @param handler
	 *            Callback, falls dieser Befehl erkannt wurde
	 */
	public static native void addCommand(String command, SpeechRecognitionHandler handler)/*-{
		if ($wnd.annyang) {
			var commands = {};
			commands[command] = function(param) {
				handler.@de.speechcommandrecognition.client.SpeechRecognitionHandler::onSpeechRecognitionCommand(Ljava/lang/String;)(param);
			};

			$wnd.annyang.addCommands(commands);
		}
	}-*/;

	/**
	 * Entfernt einen Spracherkennungsbefehl
	 * 
	 * @param command
	 *            Spracherkennungsbefehl, der entfernt werden soll
	 */
	public static native void removeCommand(String command)/*-{
		if ($wnd.annyang) {
			$wnd.annyang.removeCommands(command);
		}
	}-*/;

	/**
	 * Entfernt alle Spracherkennungsbefehle
	 */
	public static native void removeAllCommands()/*-{
		if ($wnd.annyang) {
			$wnd.annyang.removeCommands();
		}
	}-*/;

}
