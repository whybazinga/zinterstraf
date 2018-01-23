package com.vvopaa.zinterstraf.repository;

import com.vvopaa.zinterstraf.model.Client;

public interface ClientDao {

    Client getClientByClientId(String clientId);
}
