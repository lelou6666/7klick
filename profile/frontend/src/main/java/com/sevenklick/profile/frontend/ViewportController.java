package com.sevenklick.profile.frontend;

import com.sevenklick.common.util.helpers.CirrusDateUtil;
import com.sevenklick.common.util.helpers.ContextHandler;
import com.sevenklick.common.util.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

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
        model.addAttribute("now", CirrusDateUtil.getLocalizedDateFormatForContext().format(CirrusDateUtil.getFirstDateInPreviousMonth()));
        model.addAttribute("endDate",CirrusDateUtil.getLocalizedDateFormatForContext().format(CirrusDateUtil.getLastDateInPreviousMonth()));
        model.addAttribute("currentUserCountryCode", ContextHandler.get().getCountryCode());
        return "templates/comming-soon";
    }
    @RequestMapping(value = "/template/login")
    public String loginViewTemplate() throws IOException {
       return "templates/login";
    }

}
