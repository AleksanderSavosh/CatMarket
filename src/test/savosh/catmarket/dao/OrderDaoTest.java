package savosh.catmarket.dao;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import savosh.catmarket.model.Order;
import savosh.catmarket.model.OrderProduct;
import savosh.catmarket.model.OrderStatus;
import savosh.catmarket.model.Product;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:data-source/data-source.xml", "classpath:spring/dao-context.xml"})
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Test
    public void testCrud(){
        Order expected = new Order("Aleksandr", "1254552", "Email", "some address",
                "some kind of payment", 750.25, OrderStatus.CREATED.getId());
        expected = orderDao.create(expected);
        Order actual = orderDao.read(expected);
        Assert.assertEquals(expected, actual);

        expected.setEmail("new Email");
        orderDao.update(expected);
        actual = orderDao.read(expected);
        Assert.assertEquals(expected, actual);

        Set<Order> setActual = orderDao.readAll();
        Assert.assertTrue(setActual.contains(expected));

        orderDao.delete(expected);
        Assert.assertNull(orderDao.read(expected));

    }

    @Test
    public void testCrWithOrderProducts(){
        Order expected = new Order("Aleksandr", "1254552", "Email", "some address",
                "some kind of payment", 750.25, OrderStatus.CREATED.getId());

        Set<Product> products = productDao.readAll();
        Set<OrderProduct> orderProducts = new HashSet<>();
        for(Product product : products){
            orderProducts.add(new OrderProduct(product, 5));
        }
        expected.setOrderProducts(orderProducts);
        expected = orderDao.createWithOrderProducts(expected);
        Set<Order> setActual = orderDao.readAllWithOrderProducts();
        Assert.assertTrue(setActual.contains(expected));

    }

}
