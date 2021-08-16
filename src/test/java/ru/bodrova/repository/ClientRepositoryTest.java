package ru.bodrova.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bodrova.model.Client;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository repository;

    @Test
    public void getClient() throws SQLException {
        Client client = repository.getClient(1L);
        Assert.assertEquals(Long.valueOf(1), client.getId());
        Assert.assertEquals(Long.valueOf(7), client.getAccount());
        Assert.assertEquals(new BigDecimal("9999"), client.getBalance());
        Assert.assertEquals("Иванов", client.getFio());
    }

    @Test(expected = NoSuchElementException.class)
    public void getClientNotFound() throws SQLException {
        Client client = repository.getClient(10000L);
    }

    @Test
    public void getClientByFio() throws SQLException {
        Client client = repository.getClient("Иванов");
        Assert.assertEquals(Long.valueOf(1), client.getId());
        Assert.assertEquals(Long.valueOf(7), client.getAccount());
        Assert.assertEquals(new BigDecimal("9999"), client.getBalance());
        Assert.assertEquals("Иванов", client.getFio());
    }

}
