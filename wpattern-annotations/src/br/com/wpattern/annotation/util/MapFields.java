package br.com.wpattern.annotation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.wpattern.annotation.exception.FormatFieldException;
import br.com.wpattern.annotation.exception.MapFieldException;

public class MapFields {

	private final Map<String, String> mapOfValues = new HashMap<String, String>();

	public void AddField(String name, String value) throws MapFieldException {
		if ((name == null) || (value == null)) {
			throw new MapFieldException(String.format(ErrorMessages.FIELD_CAN_NOT_BE_NULL, name, value));
		}

		this.mapOfValues.put(name.toUpperCase(), value.toUpperCase());
	}

	public String getValue(String name) throws MapFieldException {
		String value = this.mapOfValues.get(name);

		if (value == null) {
			throw new MapFieldException(String.format(ErrorMessages.FIELD_NOT_FOUNDED, name));
		}

		return value;
	}

	public Integer getIntValue(String name) throws MapFieldException, FormatFieldException {
		String value = getValue(name);

		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new FormatFieldException(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name), e);
		}
	}

	public Long getLongValue(String name) throws MapFieldException, FormatFieldException {
		String value = getValue(name);

		try {
			return Long.parseLong(value);
		} catch(NumberFormatException e) {
			throw new FormatFieldException(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name), e);
		}
	}

	public Double getDoubleValue(String name) throws MapFieldException, FormatFieldException {
		String value = getValue(name);

		try {
			return Double.parseDouble(value);
		} catch(NumberFormatException e) {
			throw new FormatFieldException(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name), e);
		}
	}

	public Float getFloatValue(String name) throws MapFieldException, FormatFieldException {
		String value = getValue(name);

		try {
			return Float.parseFloat(value);
		} catch(NumberFormatException e) {
			throw new FormatFieldException(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name), e);
		}
	}

	public Boolean getBooleanValue(String name) throws MapFieldException, FormatFieldException {
		String value = getValue(name);

		try {
			return Boolean.parseBoolean(value);
		} catch(NumberFormatException e) {
			throw new FormatFieldException(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name), e);
		}
	}

	public Character getCharacterValue(String name) throws MapFieldException, FormatFieldException {
		String value = getValue(name);

		if (value.length() != 1) {
			throw new FormatFieldException(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name));
		}

		try {
			return value.charAt(0);
		} catch(NumberFormatException e) {
			throw new FormatFieldException(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name), e);
		}
	}

	public Date getDateValue(String name) throws MapFieldException, FormatFieldException {
		String value = getValue(name);

		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

		try {
			return formater.parse(value);
		} catch(ParseException e) {
			throw new FormatFieldException(String.format(ErrorMessages.FIELD_INVALID_FORMAT, value, name), e);
		}
	}

}
