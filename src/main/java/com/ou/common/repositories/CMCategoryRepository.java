package com.ou.common.repositories;

import com.ou.pojos.CategoryEntity;

import java.util.List;

public interface CMCategoryRepository {
    List<Object[]> getCategories(Integer pageIndex);
    long getCategoryAmount();

    CategoryEntity getCategory(String catSlug);

    CategoryEntity getCategory(Integer catId);

    List<CategoryEntity> getCategoriesByStorageSlug(String storSlug);

    boolean createCategory(CategoryEntity categoryEntity);

    boolean updateCategory(CategoryEntity categoryEntity);

    boolean deleteCategory(CategoryEntity categoryEntity);
}
