package cn.jeff.demosecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author swzhang
 * @date 2018/8/21
 * @description
 */
@Controller
public class RegistrationController {

    @Autowired
    private IUserService service;

    @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping(value = "/user/registration")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto accountDto,
            BindingResult result, WebRequest request, Errors errors
    ) {
        User registered = null;
        if (!result.hasErrors()) {
            registered = createAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("null", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        } else {
            return new ModelAndView("successRegister", "user", accountDto);
        }
    }

    private User createAccount(UserDto userDto, BindingResult result) {
        User registered = null;
        try {
            registered = service.registerNewUserAccount(userDto);
        } catch (Exception e) {
            return null;
        }
        return registered;
    }


}
