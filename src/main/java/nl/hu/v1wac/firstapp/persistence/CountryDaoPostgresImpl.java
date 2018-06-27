package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.firstapp.model.Country;

public class CountryDaoPostgresImpl extends PostgresBaseDao implements CountryDao {

	@Override
	public boolean save(String code, String name, String capital, String continent, String region, double surfacearea, int population, String governmentform) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();

        if(code != null) {
            String insert = "INSERT INTO country (code, name, capital, continent, region, surfacearea, population, governmentform)"
            		+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stat = conn.prepareStatement(insert);
            stat.setString(1, code);
            stat.setString(2, name);
            stat.setString(3, capital);
            stat.setString(4, continent);
            stat.setString(5, region);
            stat.setDouble(6, surfacearea);
            stat.setInt(7, population);
            stat.setString(8, governmentform);
            stat.executeUpdate();
            System.out.println(name + " is toegevoegd");
            return true;
        }
        return false;
    }
    
    public List<Country> findAll() throws SQLException, ClassNotFoundException {
        ArrayList<Country> lijst_landen = new ArrayList<Country>();
        Connection conn = getConnection();


        String query = "SELECT * FROM country";
        PreparedStatement stat = conn.prepareStatement(query);
        ResultSet rs = stat.executeQuery();

        while (rs.next()) {
            Country country = new Country();
            country.setCode(rs.getString("CODE"));
            country.setName(rs.getString("NAME"));
            country.setContinent(rs.getString("CONTINENT"));
            country.setRegion(rs.getString("REGION"));
            country.setSurface(rs.getDouble("SURFACEAREA"));
            country.setPopulation(rs.getInt("POPULATION"));
            country.setGovernmentform(rs.getString("GOVERNMENTFORM"));
            country.setLatitude(rs.getInt("LATITUDE"));
            country.setLongitude(rs.getInt("LONGITUDE"));
            country.setCapital(rs.getString	("CAPITAL"));
            lijst_landen.add(country);
        }

        return lijst_landen;
    }


    public Country findByCode(String code) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();

        String query = "SELECT * FROM country WHERE code='" + code + "'";

        PreparedStatement stat = conn.prepareStatement(query);
        ResultSet rs = stat.executeQuery();
        Country country = new Country();
        while (rs.next()) {
            country.setCode(rs.getString("CODE"));
            country.setName(rs.getString("NAME"));
            country.setContinent(rs.getString("CONTINENT"));
            country.setRegion(rs.getString("REGION"));
            country.setSurface(rs.getDouble("SURFACEAREA"));
            country.setPopulation(rs.getInt("POPULATION"));
            country.setGovernmentform(rs.getString("GOVERNMENTFORM"));
            country.setLatitude(rs.getInt("LATITUDE"));
            country.setLongitude(rs.getInt("LONGITUDE"));
            country.setCapital(rs.getString	("CAPITAL"));
        }
        System.out.println("De naam van het land is "+country.getName());
        return country;
    }
    

    public List<Country> find10LargestPop() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        ArrayList<Country> lijst_landen = new ArrayList<Country>();

        String query = "SELECT * FROM country ORDER BY population DESC LIMIT 10";

        PreparedStatement stat = conn.prepareStatement(query);
        ResultSet rs = stat.executeQuery();

        while (rs.next()) {
            Country country = new Country();
            country.setCode(rs.getString("CODE"));
            country.setName(rs.getString("NAME"));
            country.setContinent(rs.getString("CONTINENT"));
            country.setRegion(rs.getString("REGION"));
            country.setSurface(rs.getDouble("SURFACEAREA"));
            country.setPopulation(rs.getInt("POPULATION"));
            country.setGovernmentform(rs.getString("GOVERNMENTFORM"));
            country.setLatitude(rs.getInt("LATITUDE"));
            country.setLongitude(rs.getInt("LONGITUDE"));
            country.setCapital(rs.getString	("CAPITAL"));
            lijst_landen.add(country);
        }

        return lijst_landen;
    }


    public List<Country> find10LargestSurf() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        ArrayList<Country> lijst_landen = new ArrayList<Country>();

        String query = "SELECT * FROM country ORDER BY surfacearea DESC LIMIT 10";
        PreparedStatement stat = conn.prepareStatement(query);
        ResultSet rs = stat.executeQuery();


        while (rs.next()) {
            Country country = new Country();
            country.setCode(rs.getString("CODE"));
            country.setName(rs.getString("NAME"));
            country.setContinent(rs.getString("CONTINENT"));
            country.setRegion(rs.getString("REGION"));
            country.setSurface(rs.getDouble("SURFACEAREA"));
            country.setPopulation(rs.getInt("POPULATION"));
            country.setGovernmentform(rs.getString("GOVERNMENTFORM"));
            country.setLatitude(rs.getInt("LATITUDE"));
            country.setLongitude(rs.getInt("LONGITUDE"));
            country.setCapital(rs.getString	("CAPITAL"));
            lijst_landen.add(country);
        }

        return lijst_landen;
    }


    public boolean update(String name, String capital, String region, double surface, int population, String code) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        if (code != null) {
            String query = "UPDATE COUNTRY SET NAME = ?, CAPITAL = ?, REGION = ?, SURFACEAREA = ?, POPULATION = ? WHERE CODE = ?";
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, name);
            stat.setString(2, capital);
            stat.setString(3, region);
            stat.setDouble(4, surface);
            stat.setInt(5, population);
            stat.setString(6, code);
            stat.executeUpdate();
            System.out.println(name+" is geupdate");
            return true;
        }
        return false;
    }
    
    public boolean delete(Country country) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();

        if(country != null) {
            String delete =  "DELETE FROM country WHERE code = ?";
            PreparedStatement stat = conn.prepareStatement(delete);
            stat.setString(1, country.getCode());
            System.out.println("Het land " + country.getName() + " is verwijderd\n");
            return stat.executeUpdate() == 1;
        }
        return false;
    }

}