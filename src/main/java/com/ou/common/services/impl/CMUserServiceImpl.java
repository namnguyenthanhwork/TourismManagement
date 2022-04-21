package com.ou.common.services.impl;

import com.ou.common.repositories.CMUserRepository;
import com.ou.common.services.CMUserService;
import com.ou.pojos.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class CMUserServiceImpl implements CMUserService {
    @Autowired
    private CMUserRepository cmUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String accUsername) throws UsernameNotFoundException, IllegalAccessError{
        AccountEntity account = cmUserRepository.getAccountByUsername(accUsername.trim());
        if (account == null)
            throw new UsernameNotFoundException("Tài khoản không tồn tại");
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(cmUserRepository.getRoleSlugOfAccount(accUsername.trim())));
        return new org.springframework.security.core.userdetails.User(
                account.getAccUsername(), account.getAccPassword(), account.getAccIsActive()==1,
                true, true, true, authorities);
    }
}
