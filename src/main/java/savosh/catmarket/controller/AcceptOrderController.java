package savosh.catmarket.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import savosh.catmarket.dao.OrderDao;
import savosh.catmarket.model.Order;

import java.util.Set;

@Controller
public class AcceptOrderController {
    private static final Logger LOG = Logger.getLogger(AcceptOrderController.class);

    @Autowired
    private OrderDao orderDao;

    @RequestMapping(value = "/xxx/accept_order")
    public ModelAndView acceptOrder() {
        ModelAndView modelAndView = new ModelAndView("accept_order");
        Set<Order> orders = orderDao.readAll();
        LOG.debug("orders: " + orders);
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

}
