package com.ou.common.services;

import com.ou.pojos.CategoryEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public interface CMCategoryService {
    JSONArray getCategories(Integer pageIndex);

    long getCategoryAmount();
    CategoryEntity getCategoryAsObj(String catSlug);

    CategoryEntity getCategoryAsObj(Integer catId);
    List<CategoryEntity> getCategoriesByStorageSlug(String storSlug);

    JSONObject getCategoryAsJsonObj(String catSlug);

    boolean createCategory(CategoryEntity categoryEntity);

    boolean updateCategory(CategoryEntity categoryEntity);

    boolean deleteCategory(CategoryEntity categoryEntity);
}
