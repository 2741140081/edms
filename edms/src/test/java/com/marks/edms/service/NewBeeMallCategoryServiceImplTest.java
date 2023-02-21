package com.marks.edms.service;

import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NewBeeMallCategoryServiceImplTest {
    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;
    @Test
    public void conflictTime() {
        Map<String, Object> params = new HashMap<>();
        params.put("page","1");
        params.put("limit","10");
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        PageResult categorisePage = newBeeMallCategoryService.getCategorisePage(pageQueryUtil);
        System.out.println(categorisePage.getCurrPage());
    }

}
