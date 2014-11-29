package com.sevenklick.profile.frontend;

import com.sevenklick.common.core.exception.NotAuthenticatedException;
import com.sevenklick.common.core.exception.TechnicalException;
import com.sevenklick.common.core.exception.TicketNotValidException;
import com.sevenklick.common.core.helpers.ContextHandler;
import com.sevenklick.common.core.web.BaseController;
import com.sevenklick.profile.backend.facade.ProfileFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Controller to get tree nodes for showing loads
 */
@Controller
@RequestMapping("/")
public class ViewportController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewportController.class);
    @Autowired
    ProfileFacade profileFacade;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model, WebRequest request) {
        model.addAttribute("template", "templates/register");
        model.addAttribute("fragment","registerFragment");
        return  "templates/index";
        //return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }

    @RequestMapping(value = "/template/student", method = RequestMethod.GET)
    public String loginViewTemplate(@RequestParam("id") Long id) throws IOException {
        return "templates/edit";
    }

    @RequestMapping(value = "/template/login")
    public String loginViewTemplate() throws IOException {
        return "templates/login";
    }

    @RequestMapping(value = "/secured/template/profile")
    public String profileViewTemplate() throws IOException {
        return "templates/profile";
    }

    @RequestMapping(value = "/signon")
    public void signon(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) throws IOException, NotAuthenticatedException {
        modelMap.addAttribute("ticket", profileFacade.authenticateUser(email,password));
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFileHandler(@RequestParam("school") String school, @RequestParam("file") MultipartFile file, @RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) throws NotAuthenticatedException, TechnicalException, TicketNotValidException, InterruptedException {
        //Thread.sleep(3000);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                //TODO Visibility of CV should be provided from UI
                profileFacade.updateProfile(bytes, file.getContentType(), file.getOriginalFilename(), true, email, password);
                modelMap.addAttribute("ticket", ContextHandler.get().getTicket());
                modelMap.addAttribute("location", "secured/template/profile");

            } catch (IOException e) {
                throw new TechnicalException(TechnicalException.TECHNICAL_ERROR, "Could not upload file");
            }

        }

        return "templates/profile";
    }

}
