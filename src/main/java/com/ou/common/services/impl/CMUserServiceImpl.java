package com.ou.common.services.impl;

import com.ou.common.repositories.CMAccountRepository;
import com.ou.common.repositories.CMUserRepository;
import com.ou.common.services.CMUserService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class CMUserServiceImpl implements CMUserService {
    @Autowired
    private CMUserRepository cmUserRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Autowired
    private CMAccountRepository cMAccountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String accUsername) throws UsernameNotFoundException, IllegalAccessError {
        AccountEntity account = cmUserRepository.getAccountByUsername(accUsername.trim());
        if (account == null)
            throw new UsernameNotFoundException("Tài khoản không tồn tại");
        account.setAccLastAccess(utilBeanFactory.getApplicationContext().getBean("currentTimeStamp",Timestamp.class));
        cMAccountRepository.updateAccount(account);
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(cmUserRepository.getRoleSlugOfAccount(accUsername.trim())));
        return new org.springframework.security.core.userdetails.User(
                account.getAccUsername(), account.getAccPassword(), true,
                true, true, true, authorities);
    }
}
