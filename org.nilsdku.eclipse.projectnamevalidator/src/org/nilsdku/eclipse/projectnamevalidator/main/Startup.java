package org.nilsdku.eclipse.projectnamevalidator.main;

import org.eclipse.ui.IStartup;
import org.nilsdku.eclipse.projectnamevalidator.ChangedNameValidator;

/**
 * Класс, содержащий метод, вызываемый после запуска
 * приложения, в состав которого входит данный плагин.
 */
public class Startup implements IStartup
{
	/**
	 * Создаёт объект класса ChangedNameValidator и
	 * вызывает его методы для проверки имён проектов,
	 * существующих в Workspace, и для установки
	 * слушателя изменения имени проекта.
	 */
	@Override
	public void earlyStartup ()
	{
		ChangedNameValidator validator = new ChangedNameValidator();
		validator.validateExistingProjectNames();
		validator.addChangedNameListener();
	}
}
