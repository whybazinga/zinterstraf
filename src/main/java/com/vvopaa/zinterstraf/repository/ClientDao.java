package com.vvopaa.zinterstraf.repository;

import com.vvopaa.zinterstraf.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client, Long> {

    Client findByClientId(String clientId);
}
