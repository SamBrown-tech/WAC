package nl.hu.v1wac.firstapp.model;

public class Country {
	private String code;
	private String name;
	private String capital;
	private String continent;
	private String region;
	private double surface;
	private int population;
	private String governmentform;
	private double latitude;
	private double longitude;


	public String getCode() {
		return code;
	}

	public void setCode(String cd) {
		this.code = cd;
	}

	public String getName() {
		return name;
	}
	public void setName(String cd) {
		this.name = cd;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String cd) {
		this.capital = cd;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String cd) {
		this.continent = cd;
	}
	public void setRegion(String cd) {
		this.region = cd;
	}

	public String getRegion() {
		return region;
	}
	public void setSurface(double cd) {
		this.surface = cd;
	}

	public double getSurface() {
		return surface;
	}
	public void setPopulation(int cd) {
		this.population = cd;
	}
	public int getPopulation() {
		return population;
	}
	public void setGovernmentform(String cd) {
		this.governmentform = cd;
	}

	public String getGovernmentform() {
		return governmentform;
	}

	public void setLatitude(double cd) {
		this.latitude = cd;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLongitude(double cd) {
		this.longitude = cd;
	}

	public double getLongitude() {
		return longitude;
	}
}