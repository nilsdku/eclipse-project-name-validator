package org.nilsdku.eclipse.projectnamevalidator.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PropertyPage;
import org.nilsdku.eclipse.projectnamevalidator.ChangedNameValidator;
import org.nilsdku.eclipse.projectnamevalidator.Messages;
import org.nilsdku.eclipse.projectnamevalidator.ProjectValidator;
import org.nilsdku.eclipse.projectnamevalidator.log.ErrorStatusHandler;
import org.nilsdku.eclipse.projectnamevalidator.property.RenameIgnoringProperty;

/**
 * Страница свойства RenameIgnoringProperty для проекта
 * в рабочем пространстве.
 */
public class RenameIgnoringPropertyPage extends PropertyPage implements IWorkbenchPropertyPage
{	
	private Button m_renameIgnoringButton;
	
	private RenameIgnoringProperty m_property;
	
	@Override
	protected Control createContents(Composite a_parent)
	{
		m_property = new RenameIgnoringProperty((IProject)getElement());
		
		m_renameIgnoringButton = new Button(a_parent, SWT.CHECK | SWT.WRAP);
		GridData buttonData = new GridData(SWT.LEFT, SWT.UP, false, false);
		buttonData.heightHint = 42;
		m_renameIgnoringButton.setLayoutData(buttonData);
		m_renameIgnoringButton.setText(Messages.Property_Page_Text_Ru.replaceAll("\n", System.lineSeparator()));
		
		try
		{
			m_renameIgnoringButton.setSelection(m_property.getValue());
		}
		catch (CoreException e)
		{
			ErrorStatusHandler.log(e, e.getMessage());
		}	
		return a_parent;
	}
	
	@Override
	public void performDefaults ()
	{
		m_renameIgnoringButton.setSelection(false);
		try
		{
			m_property.setValue(false);
			validateProjectName();
		}
		catch (CoreException e)
		{
			ErrorStatusHandler.log(e, e.getMessage());
		}
	}
	
	@Override
	public boolean performOk ()
	{
		try
		{
			m_property.setValue(m_renameIgnoringButton.getSelection());
			validateProjectName();
		}
		catch (CoreException e)
		{
			ErrorStatusHandler.log(e, e.getMessage());
		}
		return true;
	}
	
	/**
	 * Проверяет имя проекта на идентичность имени
	 * его папки в файловой системе.
	 */
	private void validateProjectName ()
	{
		IProject project = (IProject)getElement();
		if (!new ProjectValidator(project).isProjectOpen()) return;
		ChangedNameValidator validator = new ChangedNameValidator();
		validator.validateProjectName(project, false);
	}
}
