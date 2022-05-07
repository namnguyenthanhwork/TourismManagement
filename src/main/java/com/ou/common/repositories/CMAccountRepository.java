package com.ou.common.repositories;

import com.ou.pojos.AccountEntity;

import java.util.List;

public interface CMAccountRepository {
    List<Object[]> getAccounts(Integer pageIndex, String ... params);

    AccountEntity getAccount(String username);

    AccountEntity getAccount(Integer accId);
    long getAccountAmount(String...params);

    boolean createAccount(AccountEntity accountEntity);

    boolean updateAccount(AccountEntity accountEntity);

    boolean deleteAccount(AccountEntity accountEntity);
}
