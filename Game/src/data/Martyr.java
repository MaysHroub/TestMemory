package data;

public class Martyr {
	
	private String name;
	private String dateOfMartyrdom;
	
	/**
	 * Constructs a Martyr object with the specified information.
	 * @param name
	 * @param dateOfMartyrdom
	 */
	public Martyr(String name, String dateOfMartyrdom) {
		setName(name);
		setDateOfMartyrdom(dateOfMartyrdom);
	}

	/**
	 * Constructs an empty Martyr object.
	 */
	public Martyr() {
	}
	
	/**
	 * Retrieves the martyr's date of martyrdom.
	 * 
	 * @return The date of martyrdom.
	 */
	public String getDateOfMartyrdom() {
		return dateOfMartyrdom;
	}
	
	/**
	 * Sets the date of when the martyr has been killed.
	 * 
	 * @param dateOfMartyrdom The date of martyrdom.
	 */
	public void setDateOfMartyrdom(String dateOfMartyrdom)
			throws IllegalArgumentException {
		
		if (!isValid(dateOfMartyrdom)) 
			throw new IllegalArgumentException("Invalid date format");
		
		this.dateOfMartyrdom = dateOfMartyrdom;
	}
	
	/**
	 * Checks whether the date format is valid or not.
	 * @param date The date to check its format validity.
	 * @return {@code true} if the date format is valid, {@code false} otherwise.
	 */
	private boolean isValid(String date) {
		String[] tokens = date.split("-");
		if (tokens.length != 3) return false;
		int d = 0, m = 0, y = 0;
		try {
			d = Integer.parseInt(tokens[0]);
			m = Integer.parseInt(tokens[1]);
			y = Integer.parseInt(tokens[2]);
		} catch (NumberFormatException e) {
			return false;
		}
		return !(d > 31 || d <= 0 || m > 12 || m <= 0 || y < 1900);
	}
	
	/**
	 * Retrieves the marytr's name
	 * 
	 * @return The marytr's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the martyr's name if the given name has more than two characters, otherwise throws an exception.
	 * 
	 * @param name The name of the martyr.
	 * @throws IllegalArgumentException
	 */
	public void setName(String name) throws IllegalArgumentException {
		if (name.length() > 2)
			this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
		else 
			throw new IllegalArgumentException("The name must be at least 3 characters long");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Martyr)) return false;
		return name.equalsIgnoreCase( ((Martyr) obj).getName() );
	}
	
}
