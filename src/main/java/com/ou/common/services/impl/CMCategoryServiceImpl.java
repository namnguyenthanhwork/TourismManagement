package com.ou.common.services.impl;

import com.ou.common.repositories.CMCategoryRepository;
import com.ou.common.repositories.CMStorageRepository;
import com.ou.common.services.CMCategoryService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.CategoryEntity;
import com.ou.pojos.StorageEntity;
import com.ou.utils.SlugUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMCategoryServiceImpl implements CMCategoryService {

    @Autowired
    private CMCategoryRepository cMCategoryRepository;

    @Autowired
    private CMStorageRepository cMStorageRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getCategories(Integer pageIndex) {
        List<Object[]> categories = cMCategoryRepository.getCategories(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        categories.forEach(category -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("catId", category[0]);
            jsonObject.put("catName", category[1]);
            jsonObject.put("catSlug", category[2]);
            jsonObject.put("feaId", category[3]);
            jsonObject.put("feaName", category[4]);
            jsonObject.put("feaSlug", category[5]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public CategoryEntity getCategoryAsObj(String catSlug) {
        if (catSlug == null || catSlug.trim().length() == 0)
            return null;
        return cMCategoryRepository.getCategory(catSlug);
    }

    @Override
    public CategoryEntity getCategoryAsObj(Integer catId) {
        if (catId == null)
            return null;
        return cMCategoryRepository.getCategory(catId);
    }

    @Override
    public JSONObject getCategoryAsJsonObj(String catSlug) {
        if (catSlug == null || catSlug.trim().length() == 0)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        CategoryEntity category = cMCategoryRepository.getCategory(catSlug.trim());
        if (category == null)
            return null;
        jsonObject.put("catId", category.getCatId());
        jsonObject.put("catName", category.getCatName());
        jsonObject.put("catSlug", category.getCatSlug());
        StorageEntity storage = cMStorageRepository.getStorage(category.getStorId());
        jsonObject.put("storId", storage.getStorId());
        jsonObject.put("storName", storage.getStorName());
        jsonObject.put("storSlug", storage.getStorSlug());
        return jsonObject;
    }

    @Override
    public boolean createCategory(CategoryEntity categoryEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(categoryEntity.getCatName());
        categoryEntity.setCatSlug(slugUtil.getSlug());
        return cMCategoryRepository.createCategory(categoryEntity);
    }

    @Override
    public boolean updateCategory(CategoryEntity categoryEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(categoryEntity.getCatName());
        categoryEntity.setCatSlug(slugUtil.getSlug());
        return cMCategoryRepository.updateCategory(categoryEntity);
    }

    @Override
    public boolean deleteCategory(CategoryEntity categoryEntity) {
        return cMCategoryRepository.deleteCategory(categoryEntity);
    }
}
