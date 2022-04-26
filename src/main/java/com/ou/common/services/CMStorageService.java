package com.ou.common.services;


import com.ou.pojos.StorageEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMStorageService {
    JSONArray getStorages(Integer pageIndex);

    StorageEntity getStorageAsObj(String storSlug);

    JSONObject getStorageAsJsonObj(String storSlug);

    boolean createStorage(StorageEntity storageEntity);

    boolean updateStorage(StorageEntity storageEntity);

    boolean deleteStorage(StorageEntity storageEntity);
}
