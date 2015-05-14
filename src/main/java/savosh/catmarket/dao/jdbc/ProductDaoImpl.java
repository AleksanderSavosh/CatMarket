package savosh.catmarket.dao.jdbc;

import org.apache.log4j.Logger;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import savosh.catmarket.dao.ProductDao;
import savosh.catmarket.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ProductDaoImpl implements ProductDao {
    private static final Logger LOG = Logger.getLogger(ProductDaoImpl.class);

    private JdbcOperations jdbcOperations;

    public ProductDaoImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private RowMapper<Product> rowMapper = new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Utils.productFromResultSet(rs);
        }
    };

    @Override
    public Product create(Product object) {
        jdbcOperations.update("INSERT INTO PRODUCT (BREAD, PRICE, HAIR_TYPE, LIFE_TIME, MAX_WEIGHT, IMG_NAME) "
                        + "SELECT ?, ?, ?, ?, ?, ?",
                object.getBread(), object.getPrice(), object.getHairType(),
                object.getLifeTime(), object.getMaxWeight(), object.getImgName());
        return object;
    }

    @Override
    public Set<Product> readAll() {
        return new HashSet<>(jdbcOperations.query("SELECT * FROM PRODUCT", rowMapper));
    }

    @Override
    public Product read(Product emptyObjectWithPk) {
        try {
            return jdbcOperations.queryForObject("SELECT * FROM PRODUCT WHERE BREAD = ?", rowMapper,
                    emptyObjectWithPk.getBread());
        } catch (IncorrectResultSizeDataAccessException e) {
            Utils.toLog(LOG, e, "Exception in read product block code");
            return null;
        }
    }

    @Override
    public void update(Product object) {
        jdbcOperations.update("UPDATE PRODUCT "
                        + "SET PRICE = ?, HAIR_TYPE = ?, LIFE_TIME = ?, MAX_WEIGHT = ?, IMG_NAME = ? WHERE BREAD = ?",
                object.getPrice(), object.getHairType(),
                object.getLifeTime(), object.getMaxWeight(), object.getImgName(), object.getBread());
    }

    @Override
    public void delete(Product objectWithPk) {
        jdbcOperations.update("DELETE FROM PRODUCT WHERE BREAD = ?", objectWithPk.getBread());
    }
}
