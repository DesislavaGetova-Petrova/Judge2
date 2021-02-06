package dessy.part_one.web;

import dessy.part_one.model.binding.UserAddBindingModel;
import dessy.part_one.model.binding.UserLoginBindingModel;
import dessy.part_one.model.service.UserServiceModel;
import dessy.part_one.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController<httpSession> {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(Model model){
        if(!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("notFound",false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",bindingResult);
            return "redirect:login";
        }
        UserServiceModel user=userService.findUserByUsernameAndPassword(userLoginBindingModel.getUsername(),
                userLoginBindingModel.getPassword());
        if(user==null){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notFound",true);
            return "redirect:login";
        }
        userService.login(user);
            return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model){
        if(!model.containsAttribute("userAddBindingModel")){
            model.addAttribute("userAddBindingModel", new UserAddBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm
            (@Valid @ModelAttribute UserAddBindingModel userAddBindingModel,
             BindingResult bindingResult,
             RedirectAttributes redirectAttributes){


        if(bindingResult.hasErrors()||!userAddBindingModel.getPassword().equals(userAddBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAddBindingModel",bindingResult);
            return "redirect:register";


        }else{
            UserServiceModel userServiceModel=this.userService
                    .registerUser(this.modelMapper
                    .map(userAddBindingModel,UserServiceModel.class));
        }
        return"redirect:login";
    }
    @GetMapping("/logout")
    public String logout(){
        userService.lodout();
        return "redirect:/";
    }

}
