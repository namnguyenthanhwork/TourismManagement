package com.ou.common.services;

import com.ou.pojos.CategoryEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMCategoryService {
    JSONArray getCategories(Integer pageIndex);

    CategoryEntity getCategoryAsObj(String catSlug);

    CategoryEntity getCategoryAsObj(Integer catId);

    JSONObject getCategoryAsJsonObj(String catSlug);

    boolean createCategory(CategoryEntity categoryEntity);

    boolean updateCategory(CategoryEntity categoryEntity);

    boolean deleteCategory(CategoryEntity categoryEntity);
}
