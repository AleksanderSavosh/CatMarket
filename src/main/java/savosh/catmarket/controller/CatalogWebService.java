package savosh.catmarket.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import savosh.catmarket.dao.ProductDao;
import savosh.catmarket.model.Product;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("service")
public class CatalogWebService {
    private static final Logger LOG = Logger.getLogger(CatalogWebService.class);

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = {"catalog/add_in_card/{bread}"}, method = RequestMethod.POST)
    public String addInCard(@PathVariable("bread") String bread, HttpSession session) {
        LOG.debug("(value = {\"catalog/add_in_card/{bread}\"}, method = RequestMethod.POST)");
        LOG.debug("bread: " + bread);
        LOG.debug("sessionId: " + session.getId());
        Product product = productDao.read(new Product(bread));
        LOG.debug("product: " + product);
        if (product != null) {
            Map<Product, Integer> productIntegerMap = (Map<Product, Integer>) session.getAttribute("productIntegerMap");
            LOG.debug("productIntegerMap: " + productIntegerMap);
            if (productIntegerMap == null) {
                productIntegerMap = new HashMap<>();
            }

            Integer count = productIntegerMap.get(product);
            if (count == null) {
                count = 0;
            }
            productIntegerMap.put(product, ++count);
            session.setAttribute("productIntegerMap", productIntegerMap);
            LOG.debug("productIntegerMap: " + productIntegerMap);
        }

        return getCountProductsInCard(session);
    }

    @RequestMapping(value = {"catalog/count_products_in_card"}, method = RequestMethod.GET)
    public String getCountProductsInCard(HttpSession session) {
        Map<Product, Integer> productIntegerMap = (Map<Product, Integer>) session.getAttribute("productIntegerMap");
        int count = 0;
        if (productIntegerMap != null) {
            for (Product key : productIntegerMap.keySet()) {
                count += productIntegerMap.get(key);
            }
        }
        return "" + count;
    }

    @RequestMapping(value = {"xxx/catalog/delete/{bread}"}, method = RequestMethod.POST)
    public void deleteFromCatalog(@PathVariable("bread") String bread) {
        productDao.delete(new Product(bread));
    }

    @RequestMapping(value = {"xxx/catalog/create"}, method = RequestMethod.POST)
    public void createInCatalog(MultipartHttpServletRequest mRequest) throws IOException {
        String bread = mRequest.getParameter("bread");
        String hairType = mRequest.getParameter("hairType");
        String lifeTime = mRequest.getParameter("lifeTime");
        String maxWeight = mRequest.getParameter("maxWeight");
        String price = mRequest.getParameter("price");

        String fileName = "00000005";//generate

        MultipartFile multipartFile = mRequest.getFile("file");
        multipartFile.transferTo(new File("/media/ssd/apach/Personal/webapps/ROOT/static/" + fileName));

        productDao.create(new Product(bread, Double.valueOf(price), hairType, Integer.valueOf(lifeTime),
                Integer.valueOf(maxWeight), fileName));

    }

}
