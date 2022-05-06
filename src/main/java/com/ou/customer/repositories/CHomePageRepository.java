package com.ou.customer.repositories;

import com.ou.utils.TourQueryTypeUtil;

import java.util.List;

public interface CHomePageRepository {
    List<Object[]> getTourByCategory(String catSlug);

    List<Object[]> getTours(TourQueryTypeUtil tourQueryTypeUtil, Integer pageIndex, String params);

    Object[] getTour(String tourSlug);

}
