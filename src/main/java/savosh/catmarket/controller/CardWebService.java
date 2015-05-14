package savosh.catmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import savosh.catmarket.dao.ProductDao;
import savosh.catmarket.model.Product;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("service")
public class CardWebService {

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "card/remove_product_from_card/{bread}", method = RequestMethod.POST)
    public String removeProductFromCard(@PathVariable("bread") String bread, HttpSession session) {
        Product product = productDao.read(new Product(bread));
        if (product != null) {
            Map<Product, Integer> productIntegerMap = (Map<Product, Integer>) session.getAttribute("productIntegerMap");
            productIntegerMap.remove(product);
            session.setAttribute("productIntegerMap", productIntegerMap);
        }
        return "" + countTotalPrice(session);
    }

    @RequestMapping(value = "card/change_product_count/{bread}/{count}", method = RequestMethod.POST)
    public String changeProductCount(
            @PathVariable("bread") String bread,
            @PathVariable("count") Integer count,
            HttpSession session) {

        Product product = productDao.read(new Product(bread));
        if (product != null) {
            Map<Product, Integer> productIntegerMap = (Map<Product, Integer>) session.getAttribute("productIntegerMap");
            productIntegerMap.put(product, count);
            session.setAttribute("productIntegerMap", productIntegerMap);
        }
        return "" + countTotalPrice(session);
    }

    public static double countTotalPrice(HttpSession session) {
        Map<Product, Integer> productIntegerMap = (Map<Product, Integer>) session.getAttribute("productIntegerMap");
        double price = 0;
        if (productIntegerMap != null) {
            for (Product product : productIntegerMap.keySet()) {
                price += product.getPrice() * productIntegerMap.get(product);
            }
        }
        return price;
    }

}
