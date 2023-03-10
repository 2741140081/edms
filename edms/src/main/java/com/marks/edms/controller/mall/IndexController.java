package com.marks.edms.controller.mall;

import com.marks.edms.common.Constants;
import com.marks.edms.controller.vo.NewBeeMallIndexCarouselVO;
import com.marks.edms.service.NewBeeMallCarouselService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private NewBeeMallCarouselService newBeeMallCarouselService;

    @GetMapping({"/index", "/", "/index.html"})
    public String indexPage(HttpServletRequest request) {

        List<NewBeeMallIndexCarouselVO> carousels = newBeeMallCarouselService.getCarouselForIndex(Constants.INDEX_CAROUSEL_NUMBER);
        request.setAttribute("carousels", carousels);
        return "mall/index";
    }
}
