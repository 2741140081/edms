package com.marks.edms.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ErrorPageController implements ErrorViewResolver {

    private static ErrorPageController errorPageController;

    @Autowired
    private ErrorAttributes errorAttributes;

    public ErrorPageController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    public ErrorPageController() {
        if (errorPageController == null) {
            errorPageController = new ErrorPageController(errorAttributes);
        }
    }

    /**
     * Resolve an error view for the specified details.
     *
     * @param request the source request
     * @param status  the http status of the error
     * @param model   the suggested model to be used with the view
     * @return a resolved {@link ModelAndView} or {@code null}
     */
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        if (HttpStatus.BAD_REQUEST == status) {
            return new ModelAndView("error/error_400");
        } else if (HttpStatus.NOT_FOUND == status) {
            return new ModelAndView("error/error_404");
        } else {
            return new ModelAndView("error/error_5xx");
        }
    }
}
