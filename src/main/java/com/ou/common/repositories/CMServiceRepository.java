package com.ou.common.repositories;


import com.ou.pojos.ServiceEntity;

import java.util.List;

public interface CMServiceRepository {
    List<Object[]> getServices(Integer pageIndex);

    ServiceEntity getService(String servSlug);
    long getServiceAmount();
    ServiceEntity getService(Integer servId);

    boolean createService(ServiceEntity serviceEntity);

    boolean updateService(ServiceEntity serviceEntity);

    boolean deleteService(ServiceEntity serviceEntity);
}
