package com.ou.common.services;

import com.ou.pojos.AccountEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMAccountService {
    JSONArray getAccounts(Integer pageIndex,String ... params);

    AccountEntity getAccountAsObj(String username);
    long getAccountAmount(String... params);

    JSONObject getAccountAsJsonObj(String username);

    boolean createAccount(AccountEntity accountEntity);

    boolean updateAccount(AccountEntity accountEntity);

    boolean deleteAccount(AccountEntity accountEntity);
}
