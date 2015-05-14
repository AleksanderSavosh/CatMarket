package savosh.catmarket.dao;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import savosh.catmarket.model.Product;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:data-source/data-source.xml", "classpath:spring/dao-context.xml"})
public class ProductDaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void testCrud(){
        Product expected = new Product("Cat", 500.21, "Long", 3, 15, "some name");
        productDao.create(expected);
        Product actual = productDao.read(expected);
        Assert.assertEquals(expected, actual);

        expected.setImgName("new name");
        productDao.update(expected);
        actual = productDao.read(expected);
        Assert.assertEquals(expected, actual);

        Set<Product> list = productDao.readAll();
        Assert.assertTrue(list.contains(expected));

        productDao.delete(expected);
        Assert.assertNull(productDao.read(expected));
    }


}
