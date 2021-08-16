package ru.bodrova.repository;

import org.springframework.stereotype.Repository;
import ru.bodrova.model.Client;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@Repository
public class ClientRepository {
    private static final String GET_CLIENT_QUERY = "SELECT * FROM CLIENT WHERE ID = ?";
    private static final String INSERT_CLIENT_QUERY =
            "INSERT INTO CLIENT(ID, ACCOUNT, FIO, BALANCE)\n" +
            "VALUES (seq_client.nextval, ?, ?, ?)";
    private static final String GET_CLIENT_BY_FIO_QUERY = "SELECT * FROM CLIENT WHERE FIO = ?";

    private final DataSource dataSource;

    public ClientRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public int createClient(Client client) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_QUERY );
            preparedStatement.setLong(1, client.getAccount());
            preparedStatement.setString(2, client.getFio());
            preparedStatement.setBigDecimal(3, client.getBalance());
            int result = preparedStatement.executeUpdate();
            if (result==0) {
                throw new NoSuchElementException(String.format("Client did not create", client.getFio()));
            }
            return result;
        }
    }

    public Client getClient(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Client client = new Client();
            if (resultSet.next()) {
                client.setId(resultSet.getLong("ID"));
                client.setAccount(resultSet.getLong("ACCOUNT"));
                client.setBalance(resultSet.getBigDecimal("BALANCE"));
                client.setFio(resultSet.getString("FIO"));
            } else {
                throw new NoSuchElementException(String.format("Client with id %s not found in DB", id));
            }
            return client;
        }

    }

    public Client getClient(String fio) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_BY_FIO_QUERY);
            preparedStatement.setString(1, fio);
            ResultSet resultSet = preparedStatement.executeQuery();
            Client client = new Client();
            if (resultSet.next()) {
                client.setId(resultSet.getLong("ID"));
                client.setAccount(resultSet.getLong("ACCOUNT"));
                client.setBalance(resultSet.getBigDecimal("BALANCE"));
                client.setFio(resultSet.getString("FIO"));
            } else {
                throw new NoSuchElementException(String.format("Client with fio %s not found in DB", fio));
            }
            return client;
        }
    }

}
