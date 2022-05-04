package com.ou.common.repositories;

import com.ou.pojos.DepartureDateEntity;

import java.util.List;

public interface CMDepartureDateRepository {
    List<Object[]> getDepartureDates(Integer pageIndex);

    long getDepartureDateAmount();
    DepartureDateEntity getDepartureDate(Integer dptId);


    boolean createDepartureDate(DepartureDateEntity departureDateEntity);

    boolean updateDepartureDate(DepartureDateEntity departureDateEntity);

    boolean deleteDepartureDate(DepartureDateEntity departureDateEntity);
}
