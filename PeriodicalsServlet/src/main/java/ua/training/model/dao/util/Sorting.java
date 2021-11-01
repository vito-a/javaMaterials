package ua.training.model.dao.util;

/**
 * Enumeration that represents data sorting way.
 */
public enum Sorting {
	DESC("DESC"),
	ASC("ASC"),
	DEFAULT("");
	
	private String type;
	
	private Sorting(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	/**
	 * Reverse sort direction, only two way of sorting.
	 */
	public static Sorting reverse(Sorting sort) {
		return ASC.equals(sort) ? DESC : ASC;
	}
}