package br.com.wpattern.annotation.injection;

import java.lang.reflect.Field;
import java.util.Date;

import br.com.wpattern.annotation.WPatternField;
import br.com.wpattern.annotation.WPatternValue;
import br.com.wpattern.annotation.exception.FormatFieldException;
import br.com.wpattern.annotation.exception.InjectionException;
import br.com.wpattern.annotation.exception.MapFieldException;
import br.com.wpattern.annotation.util.ErrorMessages;
import br.com.wpattern.annotation.util.MapFields;

public class InjectorManager {

	//=====================================================================================
	// PUBLIC METHODS
	//=====================================================================================

	/**
	 * Inject just public fields.
	 * 
	 * @param objectInstance
	 */
	public static void injectValues(Object objectInstance, MapFields mapFields) throws InjectionException {
		ValidatorManager.validateFields(objectInstance);

		Class<?> objClass = objectInstance.getClass();

		Field[] fields = objClass.getFields();

		for (Field field : fields) {
			WPatternField annotationField = field.getAnnotation(WPatternField.class);

			if (annotationField != null) {
				injectValue(field, annotationField, objectInstance, mapFields);
			}
		}
	}

	/**
	 * Inject all types (public, protected, private, final) of fields.
	 * 
	 * @param objectInstance
	 */
	public static void injectAllValues(Object objectInstance, MapFields mapFields) throws InjectionException {
		ValidatorManager.validateFields(objectInstance);

		Class<?> objClass = objectInstance.getClass();

		Field[] fields = objClass.getDeclaredFields();

		for (Field field : fields) {
			WPatternField annotationField = field.getAnnotation(WPatternField.class);

			if (annotationField != null) {
				field.setAccessible(true);

				injectValue(field, annotationField, objectInstance, mapFields);
			}
		}
	}

	//=====================================================================================
	// PRIVATE METHODS
	//=====================================================================================

	private static void injectValue(Field field, WPatternField fieldAnnotation, Object objectInstance, MapFields mapFields) throws InjectionException {
		Object value = null;

		try {
			validateValue(fieldAnnotation, mapFields);

			if ((field.getType() == int.class) || (field.getType() == Integer.class)) {            // INTEGER
				value = mapFields.getIntValue(fieldAnnotation.name());
			} else if ((field.getType() == long.class) || (field.getType() == Long.class)) {       // LONG
				value = mapFields.getLongValue(fieldAnnotation.name());
			} else if ((field.getType() == boolean.class) || (field.getType() == Boolean.class)) { // BOOLEAN
				value = mapFields.getBooleanValue(fieldAnnotation.name());
			} else if ((field.getType() == char.class) || (field.getType() == Character.class)) {  // CHARACTER
				value = mapFields.getCharacterValue(fieldAnnotation.name());
			} else if ((field.getType() == double.class) || (field.getType() == Double.class)) {   // DOUBLE
				value = mapFields.getDoubleValue(fieldAnnotation.name());
			} else if ((field.getType() == float.class) || (field.getType() == Float.class)) {     // FLOAT
				value = mapFields.getFloatValue(fieldAnnotation.name());
			} else if (field.getType() == Date.class) {                                            // DATE
				value = mapFields.getDateValue(fieldAnnotation.name());
			}
		} catch (MapFieldException e) {
			if (fieldAnnotation.required()) {
				throw new InjectionException(e.getMessage(), e);
			}
		} catch (FormatFieldException e) {
			throw new InjectionException(e.getMessage(), e);
		}

		try {
			field.set(objectInstance, value);
		} catch (IllegalArgumentException e) {
			throw new InjectionException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new InjectionException(e.getMessage(), e);
		}
	}

	private static void validateValue(WPatternField fieldAnnotation, MapFields mapFields) throws MapFieldException, InjectionException {
		if (fieldAnnotation.values().length <= 0) {
			return;
		}

		String value = mapFields.getValue(fieldAnnotation.name());

		for (WPatternValue wPatternValue : fieldAnnotation.values()) {
			if (wPatternValue.value().equalsIgnoreCase(value)) {
				return;
			}
		}

		throw new InjectionException(String.format(ErrorMessages.FIELD_WITH_INVALID_VALUE, fieldAnnotation.name(), value));
	}

}
