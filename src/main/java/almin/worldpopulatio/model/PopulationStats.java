package almin.worldpopulatio.model;

public class PopulationStats {
	
	private String country;
	private String countryCode;
	private int year;
	private long value;
	
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	
	
	@Override
	public String toString() {
		return "PopulationStats [country=" + country + ", countryCode=" + countryCode + ", year=" + year + ", value="
				+ value + "]";
	}
	
	
	
	

}
