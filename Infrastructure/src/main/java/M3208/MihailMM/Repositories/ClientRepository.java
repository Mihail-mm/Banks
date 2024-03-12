package M3208.MihailMM.Repositories;

import M3208.MihailMM.Clients.Client;
import M3208.MihailMM.Models.Address;
import M3208.MihailMM.Models.Passport;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/banks_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static Connection connection;
    private JdbcTemplate jdbcTemplate;

    /**
     * Constructor
     */
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM clients";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Client.ClientBuilder clientBuilder = new Client.ClientBuilder();
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                clientBuilder.AddName(name);
                String surname = resultSet.getString("surname");
                clientBuilder.AddSurname(surname);
                String passportNumber = resultSet.getString("passport_number");
                String passportSeries = resultSet.getString("passport_series");
                if (passportNumber != null && passportSeries != null) {
                    clientBuilder.AddPassport(new Passport(Integer.parseInt(passportNumber), Integer.parseInt(passportSeries)));
                }
                String street = resultSet.getString("address_street");
                String house = resultSet.getString("address_building_number");
                if (street != null && house != null) {
                    clientBuilder.AddAddress(new Address(street, Integer.parseInt(house)));
                }
                clients.add(clientBuilder.Build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }


    public Client FindClientByNameAndSurname(String name, String surname) {
        return jdbcTemplate.query("SELECT * FROM clients WHERE name = ? AND surname = ?", new Object[]{name, surname}, (rs, rowNum) -> {
            Client.ClientBuilder clientBuilder = new Client.ClientBuilder();
            clientBuilder.AddName(rs.getString("name"));
            clientBuilder.AddSurname(rs.getString("surname"));
            clientBuilder.AddPassport(new Passport(rs.getInt("passport_number"), rs.getInt("passport_series")));
            clientBuilder.AddAddress(new Address(rs.getString("street"), rs.getInt("address_building_number")));
            return clientBuilder.Build();
        }).stream().findFirst().get();
    }
}
