package ua.training.model.dao.util;

import ua.training.model.entity.Role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Enumeration that represents data sorting type.
 */
public enum SortingType {
	USER_ID("user_id"),
	NAME("name"),
	USERNAME("username"),
	FIRSTNAME("firstname"),
	LASTNAME("lastname"),
	EMAIL("email"),
	ROLE_ID("roleId"),
	BALANCE("balance"),
	DESCRIPTION("description"),
	CAT_ID("cat_id"),
	ENABLED("enabled"),
	PRICE("price");

	private String value;
	
	private SortingType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	/**
	 * Provides a safe SortingType object.
	 * 
	 * @param value of type that you want to get
	 * @return enumeration item in safe SortingType object
	 */
	public static SortingType safeValueOf(final String value) {
		try {
			return SortingType.valueOf(value.toUpperCase());
		} catch (IllegalArgumentException ex) {
			return NAME;
		}
	}
}