package com.ou.common.services.impl;

import com.ou.common.repositories.CMAccountRepository;
import com.ou.common.repositories.CMRoleRepository;
import com.ou.common.services.CMAccountService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.AccountEntity;
import com.ou.pojos.RoleEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@Service
public class CMAccountServiceImpl implements CMAccountService {

    @Autowired
    private CMAccountRepository cMAccountRepository;

    @Autowired
    private CMRoleRepository cMRoleRepository;
    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getAccounts(Integer pageIndex, String ... params) {
        List<Object[]> accounts = cMAccountRepository.getAccounts(pageIndex, params);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        accounts.forEach(account -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("accId", account[0]);
            jsonObject.put("accUsername", account[1]);
            jsonObject.put("accPassword", account[2]);
            jsonObject.put("accFirstName", account[3]);
            jsonObject.put("accLastName", account[4]);
            jsonObject.put("accSex", account[5]);
            jsonObject.put("accIdCard", account[6]);
            jsonObject.put("accPhoneNumber", account[7]);
            jsonObject.put("accDateOfBirth", account[8]);
            jsonObject.put("accJoinedDate", account[9]);
            jsonObject.put("accAvatar", account[10]);
            jsonObject.put("accLastAccess", account[11]);
            jsonObject.put("roleSlug", account[12]);
            jsonObject.put("roleName", account[13]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public AccountEntity getAccountAsObj(String username) {
        if (username == null || username.trim().length() == 0)
            return null;
        return cMAccountRepository.getAccount(username.trim());
    }

    @Override
    public JSONObject getAccountAsJsonObj(String username) {
        if (username == null || username.trim().length() == 0)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        AccountEntity account = cMAccountRepository.getAccount(username.trim());
        if (account == null)
            return null;
        jsonObject.put("accId", account.getAccId());
        jsonObject.put("accUsername", account.getAccUsername());
        jsonObject.put("accPassword", account.getAccPassword());
        jsonObject.put("accFirstName", account.getAccFirstName());
        jsonObject.put("accLastName", account.getAccLastName());
        jsonObject.put("accSex", account.getAccSex());
        jsonObject.put("accIdCard", account.getAccIdCard());
        jsonObject.put("accPhoneNumber", account.getAccPhoneNumber());
        jsonObject.put("accDateOfBirth", account.getAccDateOfBirth());
        jsonObject.put("accJoinedDate", account.getAccJoinedDate());
        jsonObject.put("accAvatar", account.getAccAvatar());
        jsonObject.put("accLastAccess", account.getAccLastAccess());
        RoleEntity role = cMRoleRepository.getRole(account.getRoleId());
        jsonObject.put("roleSlug", role.getRoleSlug());
        jsonObject.put("roleName", role.getRoleName());
        return jsonObject;
    }

    @Override
    public boolean createAccount(AccountEntity accountEntity) {
        accountEntity.setAccJoinedDate(utilBeanFactory.getApplicationContext().getBean(Timestamp.class));
        if (cMAccountRepository.getAccount(accountEntity.getAccUsername()) != null)
            return false;
        boolean addResult;
        try {
            addResult = cMAccountRepository.createAccount(accountEntity);
        } catch (Exception e) {
            addResult = false;
        }
        return addResult;
    }

    @Override
    public boolean updateAccount(AccountEntity accountEntity) {
        if (cMAccountRepository.getAccount(accountEntity.getAccUsername()) != null)
            return false;
        boolean updateResult;
        try {
            updateResult = cMAccountRepository.updateAccount(accountEntity);
        } catch (Exception e) {
            updateResult = false;
        }
        return updateResult;
    }

    @Override
    public boolean deleteAccount(AccountEntity accountEntity) {
        return cMAccountRepository.deleteAccount(accountEntity);
    }
}
