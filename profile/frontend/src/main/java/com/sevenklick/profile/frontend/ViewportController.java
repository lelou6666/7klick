package com.sevenklick.profile.frontend;
import com.sevenklick.common.util.helpers.CirrusDateUtil;
import com.sevenklick.common.util.helpers.ContextHandler;
import com.sevenklick.common.util.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Controller to get tree nodes for showing loads
 */
@Controller
@RequestMapping("/")
public class ViewportController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewportController.class);

    @RequestMapping
    public  String init(ModelMap model, WebRequest request)  {
        return "templates/comming-soon";
    }
    @RequestMapping(value = "/template/login")
    public String loginViewTemplate() throws IOException {
       return "templates/login";
    }
    @RequestMapping(value = "/secured/template/profile")
    public String profileViewTemplate() throws IOException {
        return "templates/profile";
    }
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public
    String uploadFileHandler(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                System.out.println("Server File Location="

                        + serverFile.getAbsolutePath());

                return "templates/profile";
            } catch (Exception e) {
                return "You failed to upload " + "kk" + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + "kk"
                    + " because the file was empty.";
        }
    }

}
