package com.ou.common.repositories;

import com.ou.pojos.AccountEntity;

public interface CMUserRepository {
    AccountEntity getAccountByUsername(String accUsername);

    String getRoleSlugOfAccount(String accUsername);
}
