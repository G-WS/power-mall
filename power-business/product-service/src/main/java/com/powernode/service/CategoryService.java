package com.powernode.service;

import com.powernode.domain.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CategoryService extends IService<Category>{

    /**
     * 查询系统所有商品类目
     * @return
     */
    List<Category> queryAllCategoryList();

}
