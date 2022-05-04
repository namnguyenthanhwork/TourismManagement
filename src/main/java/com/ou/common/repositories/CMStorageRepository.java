package com.ou.common.repositories;


import com.ou.pojos.StorageEntity;

import java.util.List;

public interface CMStorageRepository {
    List<Object[]> getStorages(Integer pageIndex);
    long getStorageAmount();
    StorageEntity getStorage(String storSlug);

    StorageEntity getStorage(Integer storId);

    boolean createStorage(StorageEntity storageEntity);

    boolean updateStorage(StorageEntity storageEntity);

    boolean deleteStorage(StorageEntity storageEntity);
}
