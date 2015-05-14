package savosh.catmarket.dao.jdbc;

import org.apache.log4j.Logger;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import savosh.catmarket.dao.OrderDao;
import savosh.catmarket.model.Order;
import savosh.catmarket.model.OrderProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class OrderDaoImpl implements OrderDao {
    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);

    private JdbcOperations jdbcOperations;

    public OrderDaoImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private RowMapper<Order> rowMapper = new RowMapper<Order>() {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Utils.orderFromResultSet(rs);
        }
    };

    private RowMapper<OrderProduct> orderProductRowMapper = new RowMapper<OrderProduct>() {
        @Override
        public OrderProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Utils.orderProductFromResultSet(rs);
        }
    };

    @Override
    public Order create(Order object) {
        jdbcOperations.update(
                "INSERT INTO ORDERS (FIO, PHONE, EMAIL, DELIVERY_ADDRESS, PAYMENT_DESCRIPTION, TOTAL_PRICE, STATUS) "
                        + "SELECT ?, ?, ?, ?, ?, ?, ?",
                object.getFio(), object.getPhone(), object.getEmail(), object.getDeliveryAddress(),
                object.getPaymentDescription(), object.getTotalPrice(), object.getStatus());

        return jdbcOperations.queryForObject("SELECT * FROM ORDERS WHERE FIO = ? AND PHONE = ? AND EMAIL = ? "
                        + "AND DELIVERY_ADDRESS = ? AND PAYMENT_DESCRIPTION = ? AND TOTAL_PRICE = ? AND STATUS = ?",
                rowMapper,
                object.getFio(), object.getPhone(), object.getEmail(), object.getDeliveryAddress(),
                object.getPaymentDescription(), object.getTotalPrice(), object.getStatus());
    }

    @Override
    public Set<Order> readAll() {
        return new HashSet<>(jdbcOperations.query("SELECT * FROM ORDERS", rowMapper));
    }

    @Override
    public Order read(Order emptyObjectWithPk) {
        try {
            return jdbcOperations.queryForObject("SELECT * FROM ORDERS WHERE ID = ?", rowMapper,
                    emptyObjectWithPk.getId());
        } catch (IncorrectResultSizeDataAccessException e) {
            Utils.toLog(LOG, e, "Exception in read order block code");
            return null;
        }
    }

    @Override
    public void update(Order object) {
        jdbcOperations.update("UPDATE ORDERS "
                        + "SET FIO = ?, PHONE = ?, EMAIL = ?, DELIVERY_ADDRESS = ?, PAYMENT_DESCRIPTION = ?, "
                        + "TOTAL_PRICE = ?, STATUS = ? WHERE ID = ?",
                object.getFio(), object.getPhone(), object.getEmail(), object.getDeliveryAddress(),
                object.getPaymentDescription(), object.getTotalPrice(), object.getStatus(), object.getId());
    }

    @Override
    public void delete(Order objectWithPk) {
        jdbcOperations.update("DELETE FROM ORDERS WHERE ID = ?", objectWithPk.getId());
    }

    @Override
    public Order createWithOrderProducts(Order order) {
        try {
            order = create(order);
            Set<OrderProduct> orderProducts = order.getOrderProducts();
            if (orderProducts != null && !orderProducts.isEmpty()) {
                for (OrderProduct orderProduct : orderProducts) {
                    jdbcOperations.update("INSERT INTO ORDER_PRODUCT(ORDER_ID, BREAD, COUNTS) "
                                    + "SELECT ?, ?, ?", order.getId(), orderProduct.getProduct().getBread(),
                            orderProduct.getCount());
                }
            }
        } catch (Exception e) {
            Utils.toLog(LOG, e, "Exception in create method order with order products block code");
            throw new RuntimeException(e);
        }
        return order;
    }

    @Override
    public Set<Order> readAllWithOrderProducts() {
        Set<Order> orders = readAll();
        for (Order order : orders) {
            order.setOrderProducts(new HashSet<>(jdbcOperations.query(
                    "SELECT * FROM ORDER_PRODUCT OP INNER JOIN PRODUCT P ON P.BREAD = OP.BREAD WHERE OP.ORDER_ID = ?",
                    orderProductRowMapper, order.getId()
            )));
        }
        return orders;
    }
}








