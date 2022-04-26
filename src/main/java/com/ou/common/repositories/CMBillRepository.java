package com.ou.common.repositories;


import java.util.List;

public interface CMBillRepository {
    List<Object[]> getBills(Integer pageIndex);
}
