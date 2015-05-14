package savosh.catmarket.dao.jdbc;

import org.apache.log4j.Logger;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import savosh.catmarket.dao.AccountDao;
import savosh.catmarket.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AccountDaoImpl implements AccountDao {
    private static final Logger LOG = Logger.getLogger(AccountDaoImpl.class);

    private JdbcOperations jdbcOperations;

    public AccountDaoImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private RowMapper<Account> rowMapper = new RowMapper<Account>() {
        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Utils.accountFromResultSet(rs);
        }
    };

    @Override
    public Account create(Account object) {
        jdbcOperations.update("INSERT INTO ACCOUNT (LOGIN, PASSWORD) SELECT ?, ?",
                object.getLogin(), object.getPassword());
        return object;
    }

    @Override
    public Set<Account> readAll() {
        return new HashSet<>(jdbcOperations.query("SELECT * FROM ACCOUNT", rowMapper));
    }

    @Override
    public Account read(Account emptyObjectWithPk) {
        try {
            return jdbcOperations.queryForObject("SELECT * FROM ACCOUNT WHERE LOGIN = ?", rowMapper,
                    emptyObjectWithPk.getLogin());
        } catch (IncorrectResultSizeDataAccessException e) {
            Utils.toLog(LOG, e, "Exception in read account block code");
            return null;
        }
    }

    @Override
    public void update(Account object) {
        jdbcOperations.update("UPDATE ACCOUNT SET PASSWORD = ? WHERE LOGIN = ?", object.getPassword(),
                object.getLogin());
    }

    @Override
    public void delete(Account objectWithPk) {
        jdbcOperations.update("DELETE FROM ACCOUNT WHERE LOGIN = ?", objectWithPk.getLogin());
    }
}
