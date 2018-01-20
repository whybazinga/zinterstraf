package com.vvopaa.universalsite.repository;

import com.vvopaa.universalsite.model.Client;

public interface ClientDao {

    Client getClientByClientId(String clientId);
}
