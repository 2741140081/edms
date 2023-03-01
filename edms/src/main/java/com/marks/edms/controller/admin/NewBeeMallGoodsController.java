package com.marks.edms.controller.admin;

import com.marks.edms.common.NewBeeMallCategoryLevelEnum;
import com.marks.edms.controller.common.ServiceResultEnum;
import com.marks.edms.entity.GoodsCategory;
import com.marks.edms.entity.NewBeeMallGoods;
import com.marks.edms.service.NewBeeMallCategoryService;
import com.marks.edms.service.NewBeeMallGoodsService;
import com.marks.edms.util.NewBeeMallUtils;
import com.marks.edms.util.Result;
import com.marks.edms.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class NewBeeMallGoodsController {

    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;

    @Resource
    private NewBeeMallGoodsService newBeeMallGoodsService;

    @GetMapping("/goods")
    public String goodsPage(HttpServletRequest request) {
        request.setAttribute("path", "newbee_mall_goods");
        return "admin/newbee_mall_goods";
    }

    @GetMapping("/goods/edit")
    public String edit(HttpServletRequest request) {
        request.setAttribute("path","edit");
        //查询所有一级分类
        List<GoodsCategory> firstLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel());
        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
            //查询一级分类列表中第一个实体的所有二级分类
            List<GoodsCategory> secondLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel());
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                //查询二级分类列表中第一个实体的所有三级分类
                List<GoodsCategory> thirdLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel());

                request.setAttribute("firstLevelCategories",firstLevelCategories);
                request.setAttribute("secondLevelCategories",secondLevelCategories);
                request.setAttribute("thirdLevelCategories",thirdLevelCategories);
                request.setAttribute("path", "goods-edit");
                return "admin/newbee_mall_goods_edit";
            }
        }


        return "error/error_5xx";

    }

    @RequestMapping(value = "/goods/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody NewBeeMallGoods goods) {
        if (!StringUtils.hasLength(goods.getGoodsName())
                || !StringUtils.hasLength(goods.getGoodsIntro())
                || !StringUtils.hasLength(goods.getTag())
                || Objects.isNull(goods.getOriginalPrice())
                || Objects.isNull(goods.getSellingPrice())
                || Objects.isNull(goods.getStockNum())
                || Objects.isNull(goods.getGoodsCategoryId())
                || Objects.isNull(goods.getGoodsSellStatus())
                || !StringUtils.hasLength(goods.getGoodsCoverImg())
                || !StringUtils.hasLength(goods.getGoodsDetailContent())) {
            return ResultGenerator.genFailResult("参数异常");
        }

        String result = newBeeMallGoodsService.saveNewBeeMallGoods(goods);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult(result);
        }

    }

    @GetMapping("/goods/edit/{goodsId}")
    public String edit(HttpServletRequest request, @PathVariable("goodsId") Long goodsId) {
        request.setAttribute("path","edit");
        NewBeeMallGoods goods = newBeeMallGoodsService.getNewBeeMallGoodsById(goodsId);
        if (goods == null) {
            return "error/error_400";
        }
        if (goods.getGoodsCategoryId() > 0) {
            /**
             * "goods_id"	"goods_name"	"goods_intro"	"goods_category_id"	"goods_cover_img"	    "goods_carousel"	                                    "goods_detail_content"	"original_price"	"selling_price"	"stock_num"	  "tag"	      "goods_sell_status"	"create_user"	"create_time"	        "update_user"	"update_time"
             * "10907"	   "test 女装外套"	"just for test"	  "76"	            "20230227_17083863.png"	"http://localhost:8081/upload/20230227_17083863.png"	"<p>test ok</p>"	    "100"	             "50"	         "100"	      "夏季衣服"	  "0"	                "0"	             "2023-02-27 17:09:09"	"0"	             "2023-02-27 17:09:09"
             *
             * 假设现在已经获取了当前的goods的属性
             * 通过goods categoryId 判断是否属于是第三分类列表中存在的
             * eg：上面的实例中，goods_category_id = 76
             * 则查询表categories, 得到进一步的详细信息。==> newBeeMallCategoryService.getGoodsCategoryById(goods.getGoodsCategoryId());
             * 得到的数据为：
             * "category_id"	"category_level"	"parent_id"	"category_name"	"category_rank"	"is_deleted"	"create_time"	"create_user"	"update_time"	"update_user"
             * "76"	             "3"	             "67"	     "外套"	         "10"	         "0"	        "2019-09-12 00:21:55"	"0"	    "2019-09-12 00:21:55"	"0"
             * 此时可以得到category_level的数据为3
             * 获取二级分类信息
             * "category_id"	"category_level"	"parent_id"	"category_name"	"category_rank"	"is_deleted"	"create_time"	"create_user"	"update_time"	"update_user"
             * "67"	            "2"	                "16"	    "女装"	        "10"	        "0"	            "2019-09-12 00:15:19"	"0"	"2019-09-12 00:15:19"	"0"
             *
             */
            GoodsCategory currentgoodsCategoryById = newBeeMallCategoryService.getGoodsCategoryById(goods.getGoodsCategoryId());
            if (currentgoodsCategoryById != null && currentgoodsCategoryById.getCategoryLevel() == NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel()) {

                //判断是否是三级分类，并且获取当前二级分类信息和列表，以供修改(包括当前数据的二级分类选项以及所有的二级分类的分类列表)
                GoodsCategory currentSecondCategory = newBeeMallCategoryService.getGoodsCategoryById(currentgoodsCategoryById.getParentId());
                if (currentSecondCategory != null && currentSecondCategory.getCategoryLevel() == NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel()) {
                    //判断是否是二级级分类，并且获取当前一级级分类信息和列表
                    GoodsCategory currentFirstCategory = newBeeMallCategoryService.getGoodsCategoryById(currentSecondCategory.getParentId());
                    //判断是否是一级分类
                    if (currentFirstCategory != null && currentFirstCategory.getCategoryLevel() == NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel()) {

                        List<GoodsCategory> firstLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel());
                        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
                            //查询一级分类列表中第一个实体的所有二级分类
                            List<GoodsCategory> secondLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(currentSecondCategory.getParentId()), NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel());
                            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                                //查询二级分类列表中第一个实体的所有三级分类
                                List<GoodsCategory> thirdLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(currentgoodsCategoryById.getParentId()), NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel());

                                request.setAttribute("firstLevelCategories",firstLevelCategories);
                                request.setAttribute("secondLevelCategories",secondLevelCategories);
                                request.setAttribute("thirdLevelCategories",thirdLevelCategories);

                                request.setAttribute("firstLevelCategoryId",currentFirstCategory.getCategoryId());
                                request.setAttribute("secondLevelCategoryId",currentSecondCategory.getCategoryId());
                                request.setAttribute("thirdLevelCategoryId",currentgoodsCategoryById.getCategoryId());
                            }
                        }

                    }
                }

            }

        }

        if (goods.getGoodsCategoryId() == 0) {
            List<GoodsCategory> firstLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel());
            if (!CollectionUtils.isEmpty(firstLevelCategories)) {
                //查询一级分类列表中第一个实体的所有二级分类
                List<GoodsCategory> secondLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel());
                if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                    //查询二级分类列表中第一个实体的所有三级分类
                    List<GoodsCategory> thirdLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel());

                    request.setAttribute("firstLevelCategories",firstLevelCategories);
                    request.setAttribute("secondLevelCategories",secondLevelCategories);
                    request.setAttribute("thirdLevelCategories",thirdLevelCategories);
                }
            }
        }

        request.setAttribute("path", "goods-edit");
        request.setAttribute("goods", goods);
        return "/admin/newbee_mall_goods_edit";
    }

    @RequestMapping(value = "/goods/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(HttpServletRequest request, @RequestBody NewBeeMallGoods goods) {
        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
        goods.setUpdateUser(loginUserId);
        if (!StringUtils.hasLength(goods.getGoodsName())
                || !StringUtils.hasLength(goods.getGoodsIntro())
                || !StringUtils.hasLength(goods.getTag())
                || Objects.isNull(goods.getOriginalPrice())
                || Objects.isNull(goods.getSellingPrice())
                || Objects.isNull(goods.getStockNum())
                || Objects.isNull(goods.getGoodsCategoryId())
                || Objects.isNull(goods.getGoodsSellStatus())
                || !StringUtils.hasLength(goods.getGoodsCoverImg())
                || !StringUtils.hasLength(goods.getGoodsDetailContent())) {
            return ResultGenerator.genFailResult("参数异常");
        }

        String result = newBeeMallGoodsService.updateNewBeeMallGoods(goods);

        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult(result);
        }

    }


}
