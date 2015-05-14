package savosh.catmarket.dao.jdbc;

import org.apache.log4j.Logger;
import savosh.catmarket.model.Account;
import savosh.catmarket.model.Order;
import savosh.catmarket.model.OrderProduct;
import savosh.catmarket.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {


    public static String trim(String string) {
        return string != null ? string.trim() : null;
    }

    public static Double stringToDouble(String string) {
        return string != null ? Double.valueOf(trim(string)) : null;
    }

    public static Integer stringToInteger(String string) {
        return string != null ? Integer.valueOf(trim(string)) : null;
    }

    public static Product productFromResultSet(ResultSet rs) throws SQLException {
        return new Product(
                trim(rs.getString("BREAD")),
                stringToDouble(rs.getString("PRICE")),
                trim(rs.getString("HAIR_TYPE")),
                stringToInteger(rs.getString("LIFE_TIME")),
                stringToInteger(rs.getString("MAX_WEIGHT")),
                trim(rs.getString("IMG_NAME")));
    }

    public static Order orderFromResultSet(ResultSet rs) throws SQLException {
        return new Order(
                stringToInteger(rs.getString("ID")),
                trim(rs.getString("FIO")),
                trim(rs.getString("PHONE")),
                trim(rs.getString("EMAIL")),
                trim(rs.getString("DELIVERY_ADDRESS")),
                trim(rs.getString("PAYMENT_DESCRIPTION")),
                stringToDouble(rs.getString("TOTAL_PRICE")),
                trim(rs.getString("STATUS")));
    }

    public static Account accountFromResultSet(ResultSet rs) throws SQLException {
        return new Account(
                trim(rs.getString("LOGIN")),
                trim(rs.getString("PASSWORD")));
    }

    public static OrderProduct orderProductFromResultSet(ResultSet rs) throws SQLException {
        OrderProduct orderProduct = new OrderProduct(stringToInteger(rs.getString("COUNTS")));
        orderProduct.setProduct(productFromResultSet(rs));
        return orderProduct;
    }


    public static void toLog(Logger log, Exception e, String alternativeMessage) {
        log.debug(e.getMessage() != null ? e.getMessage() : alternativeMessage, e);
        log.error(e.getMessage() != null ? e.getMessage() : alternativeMessage);
    }
}
