package com.ou.common.services.impl;

import com.ou.common.repositories.CMStorageRepository;
import com.ou.common.services.CMStorageService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.StorageEntity;
import com.ou.utils.SlugUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMStorageServiceImpl implements CMStorageService {

    @Autowired
    private CMStorageRepository cMStorageRepository;
    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getStorages(Integer pageIndex) {
        List<Object[]> storages = cMStorageRepository.getStorages(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        storages.forEach(storage -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("storId", storage[0]);
            jsonObject.put("storName", storage[1]);
            jsonObject.put("storSlug", storage[2]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public long getStorageAmount() {
        return cMStorageRepository.getStorageAmount();
    }

    @Override
    public StorageEntity getStorageAsObj(String storSlug) {
        if (storSlug == null || storSlug.trim().length() == 0)
            return null;
        return cMStorageRepository.getStorage(storSlug);
    }

    @Override
    public JSONObject getStorageAsJsonObj(String storSlug) {
        if (storSlug == null || storSlug.trim().length() == 0)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        StorageEntity storage = cMStorageRepository.getStorage(storSlug.trim());
        if (storage == null)
            return null;
        jsonObject.put("storId", storage.getStorId());
        jsonObject.put("storName", storage.getStorName());
        jsonObject.put("storSlug", storage.getStorSlug());
        return jsonObject;
    }

    @Override
    public boolean createStorage(StorageEntity storageEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(storageEntity.getStorName());
        storageEntity.setStorSlug(slugUtil.getSlug());
        if (cMStorageRepository.getStorage(storageEntity.getStorSlug()) != null)
            return false;
        boolean addResult;
        try {
            addResult = cMStorageRepository.createStorage(storageEntity);
        } catch (Exception e) {
            addResult = false;
        }
        return addResult;
    }

    @Override
    public boolean updateStorage(StorageEntity storageEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(storageEntity.getStorName());
        storageEntity.setStorSlug(slugUtil.getSlug());
        StorageEntity updateStorage = cMStorageRepository.getStorage(storageEntity.getStorSlug());
        if (updateStorage!= null && updateStorage.getStorId()!=storageEntity.getStorId())
            return false;
        boolean updateResult;
        try {
            updateResult = cMStorageRepository.updateStorage(storageEntity);
        } catch (Exception e) {
            updateResult = false;
        }
        return updateResult;
    }

    @Override
    public boolean deleteStorage(StorageEntity storageEntity) {
        return cMStorageRepository.deleteStorage(storageEntity);
    }
}
