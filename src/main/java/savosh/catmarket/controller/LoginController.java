package savosh.catmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    public LoginController() {
    }

    @RequestMapping(
            value = {"xxx/login"},
            method = {RequestMethod.GET}
    )
    public ModelAndView login(@RequestParam(
            value = "error",
            required = false
    ) boolean error) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }
}