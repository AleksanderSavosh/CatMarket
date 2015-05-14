package savosh.catmarket.dao;


import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import savosh.catmarket.model.Account;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:data-source/data-source.xml", "classpath:spring/dao-context.xml"})
public class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;

    @Test
    public void testCrud(){
        Account expected = new Account("admin2", "12345");
        accountDao.create(expected);
        Account actual = accountDao.read(expected);
        Assert.assertEquals(expected, actual);

        expected.setPassword("54321");
        accountDao.update(expected);
        actual = accountDao.read(expected);
        Assert.assertEquals(expected, actual);

        Set<Account> list = accountDao.readAll();
        Assert.assertTrue(list.contains(expected));

        accountDao.delete(expected);
        Assert.assertNull(accountDao.read(expected));
    }






}
