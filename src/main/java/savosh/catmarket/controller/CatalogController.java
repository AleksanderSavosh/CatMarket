package savosh.catmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import savosh.catmarket.dao.ProductDao;


@Controller
public class CatalogController {

    @Autowired
    private ProductDao productDao;

    public CatalogController() {
    }

    @RequestMapping({"catalog"})
    public ModelAndView userCatalog() {
        ModelAndView modelAndView = this.configModelAndView(false);
        return modelAndView;
    }

    @RequestMapping({"xxx/catalog"})
    public ModelAndView adminCatalog() {
        return this.configModelAndView(true);
    }

    private ModelAndView configModelAndView(boolean isAdmin) {
        ModelAndView modelAndView = new ModelAndView("catalog");
        modelAndView.addObject("isAdmin", Boolean.valueOf(isAdmin));
        modelAndView.addObject("products", this.productDao.readAll());
        return modelAndView;
    }

}