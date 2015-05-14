package savosh.catmarket.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import savosh.catmarket.dao.OrderDao;
import savosh.catmarket.model.Order;
import savosh.catmarket.model.OrderProduct;
import savosh.catmarket.model.OrderStatus;
import savosh.catmarket.model.Product;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class CardController {

    private static final Logger LOG = Logger.getLogger(CardController.class);

    @Autowired
    private OrderDao orderDao;


    @RequestMapping(value = "card")
    public ModelAndView card(
            @RequestParam(value = "error", required = false) String error,
            HttpSession session){
        ModelAndView modelAndView = new ModelAndView("card");
        Map<Product, Integer> productIntegerMap = (Map<Product, Integer>) session.getAttribute("productIntegerMap");

        boolean hasProducts = true;
        if(productIntegerMap == null){
            hasProducts = false;
            productIntegerMap = new HashMap<>();
        }
        modelAndView.addObject("hasProducts", hasProducts);

        Double totalPrice = 0.0;
        for(Product product : productIntegerMap.keySet()){
            totalPrice += product.getPrice()*productIntegerMap.get(product);
        }
        modelAndView.addObject("totalPrice", totalPrice);
        modelAndView.addObject("productIntegerMap", productIntegerMap);

        System.out.println(productIntegerMap);

        if(StringUtils.hasText(error)){
            String errorMsg = "";
            switch (error){
                case "fio": errorMsg = "Fio is empty."; break;
                case "email": errorMsg = "Email is empty."; break;
                case "phone": errorMsg = "Phone is empty."; break;
                case "deliveryAddress": errorMsg = "Delivery address is empty."; break;
                case "paymentDescription": errorMsg = "Payment description is empty."; break;
            }
            modelAndView.addObject("hasError", true);
            modelAndView.addObject("error", errorMsg);
        } else {
            modelAndView.addObject("hasError", false);
        }

        return modelAndView;
    }

    @RequestMapping(value = "thank_for_buy", method = RequestMethod.POST)
    public ModelAndView thankForBuy(
            @RequestParam(value = "fio", required = false) String fio,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "deliveryAddress", required = false) String deliveryAddress,
            @RequestParam(value = "paymentDescription", required = false) String paymentDescription,
            HttpSession session){

        if(!StringUtils.hasText(fio)){
            return new ModelAndView("redirect:card?error=fio");
        }
        if(!StringUtils.hasText(email)){
            return new ModelAndView("redirect:card?error=email");
        }
        if(!StringUtils.hasText(phone)){
            return new ModelAndView("redirect:card?error=phone");
        }
        if(!StringUtils.hasText(deliveryAddress)){
            return new ModelAndView("redirect:card?error=deliveryAddress");
        }
        if(!StringUtils.hasText(paymentDescription)){
            return new ModelAndView("redirect:card?error=paymentDescription");
        }

        Set<OrderProduct> orderProductSet = new HashSet<>();
        Map<Product, Integer> productIntegerMap = (Map<Product, Integer>) session.getAttribute("productIntegerMap");
        for(Product product : productIntegerMap.keySet()){
            orderProductSet.add(new OrderProduct(product, productIntegerMap.get(product)));
        }

        double totalPrice = CardWebService.countTotalPrice(session);
        Order order = new Order(fio, phone, email, deliveryAddress, paymentDescription, totalPrice,
                OrderStatus.CREATED.getId());

        order.setOrderProducts(orderProductSet);

        orderDao.create(order);

        LOG.debug("saved order: " + order);

        LOG.debug("saved orderProductSet: " + orderProductSet);

        session.removeAttribute("productIntegerMap");

        return new ModelAndView("thank_for_buy");
    }
}
