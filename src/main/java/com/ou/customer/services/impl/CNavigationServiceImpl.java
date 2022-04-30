package com.ou.customer.services.impl;

import com.ou.common.repositories.CMCategoryRepository;
import com.ou.common.repositories.CMStorageRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.customer.services.CNavigationService;
import com.ou.pojos.CategoryEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CNavigationServiceImpl implements CNavigationService {

    @Autowired
    private CMStorageRepository cMStorageRepository;

    @Autowired
    private CMCategoryRepository cmCategoryRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getNavigation() {
        List<Object[]> storageEntities = cMStorageRepository.getStorages(null);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        storageEntities.forEach(objects -> {
            JSONObject jsonObject= utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("storId", objects[0]);
            jsonObject.put("storName", objects[1]);
            jsonObject.put("storSlug", objects[2]);
            List<CategoryEntity> categoryEntities =
                    cmCategoryRepository.getCategoriesByStorageSlug((String) objects[2]);
            JSONArray jsonArray1 = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
            categoryEntities.forEach(categoryEntity -> {
                JSONObject jsonObject1= utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
                jsonObject1.put("catId", categoryEntity.getCatId());
                jsonObject1.put("catName", categoryEntity.getCatName());
                jsonObject1.put("catSlug", categoryEntity.getCatSlug());
                jsonArray1.add(jsonObject1);
            });
            jsonObject.put("categories", jsonArray1);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }
}
