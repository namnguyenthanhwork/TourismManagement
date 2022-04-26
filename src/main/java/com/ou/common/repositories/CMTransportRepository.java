package com.ou.common.repositories;


import com.ou.pojos.TransportEntity;

import java.util.List;

public interface CMTransportRepository {
    List<Object[]> getTransports(Integer pageIndex);

    TransportEntity getTransport(String tranSlug);

    TransportEntity getTransport(Integer tranId);

    boolean createTransport(TransportEntity transportEntity);

    boolean updateTransport(TransportEntity transportEntity);

    boolean deleteTransport(TransportEntity transportEntity);
}
