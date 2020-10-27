package org.nilsdku.eclipse.projectnamevalidator;

import org.eclipse.osgi.util.NLS;

/**
 * Сообщения, используемые плагином.
 */
public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.nilsdku.eclipse.projectnamevalidator.messages";
	
	static
	{
		reloadMessages();
	}
	
	/**
	 * Инициализирует сообщения, объявленные в данном классе.
	 * Сообщения хранятся в файле messages.properties.
	 */
	public static void reloadMessages ()
	{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	public static String Exception_Marker_Creation;
	
	public static String Exception_Marker_Deletion;
}

