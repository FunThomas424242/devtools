package com.github.funthomas424242.eclipseconfigurator.handlers;

import java.io.File;
import java.io.FileOutputStream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.github.funthomas424242.eclipseconfigurator.Activator;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ExportHandler extends AbstractHandler {

	/**
	 * The constructor.
	 */
	public ExportHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		//
		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(window.getShell(), "Eclipseconfigurator",
				"Export Eclipse Configuration");

		//
		IPreferencesService service = Platform.getPreferencesService();
		IEclipsePreferences rootNode = service.getRootNode();
		final File file = new File("c:/tmp/Preferences.epf");
		if (file.exists()) {
			file.delete();
		}

		try {
			file.createNewFile();
			final FileOutputStream fileOutputStream = new FileOutputStream(file);
			service.exportPreferences(rootNode, fileOutputStream, null);
		} catch (Exception ex) {
			Activator.logError(ex, ex.getLocalizedMessage());
		}
		return null;
	}
}