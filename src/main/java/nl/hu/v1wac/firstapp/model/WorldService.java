package nl.hu.v1wac.firstapp.model;

import java.sql.SQLException;
import java.util.List;

import nl.hu.v1wac.firstapp.persistence.*;

public class WorldService {
	
	public boolean saveCountry(String code, String name, String capital, String continent, String region, double surfacearea, int population, String governmentform) throws ClassNotFoundException, SQLException {
		CountryDaoPostgresImpl c1 = new CountryDaoPostgresImpl();
        c1.save(code, name, capital, continent, region, surfacearea, population, governmentform);
		return true;
	}
	
	public List<Country> getAllCountries() throws SQLException, ClassNotFoundException {
		CountryDaoPostgresImpl c1 = new CountryDaoPostgresImpl();
		return c1.findAll();
	}
	
	public Country getCountryByCode(String code) throws SQLException, ClassNotFoundException {
		CountryDaoPostgresImpl c1 = new CountryDaoPostgresImpl();
		c1.findByCode(code);

		return c1.findByCode(code);
	}	

	public List<Country> get10LargestPopulations() throws SQLException, ClassNotFoundException {
		CountryDaoPostgresImpl c1 = new CountryDaoPostgresImpl();
		c1.find10LargestPop();

		return c1.find10LargestPop();
	}

	public List<Country> get10LargestSurfaces() throws SQLException, ClassNotFoundException {
		CountryDaoPostgresImpl c1 = new CountryDaoPostgresImpl();
		c1.find10LargestSurf();

		return c1.find10LargestSurf();
	}
	
	public boolean updateCountry(String name, String capital, String region, double surface, int population, String code) throws ClassNotFoundException, SQLException {
		CountryDaoPostgresImpl c1 = new CountryDaoPostgresImpl();
	        c1.update(name, capital, region, surface, population, code);

	        return true;
	}
	
	public boolean delete(Country country) throws SQLException, ClassNotFoundException{
		CountryDaoPostgresImpl c1 = new CountryDaoPostgresImpl();
		c1.delete(country);

		return c1.delete(country);
	}
}