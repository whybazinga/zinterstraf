package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.repository.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("clientServiceImpl")
@Transactional
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDao.getClientByClientId(clientId);
    }
}
