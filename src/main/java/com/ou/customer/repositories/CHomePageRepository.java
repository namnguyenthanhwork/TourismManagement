package com.ou.customer.repositories;

import java.util.List;

public interface CHomePageRepository {
    List<Object[]> getTourByCategory(String catSlug);
}
