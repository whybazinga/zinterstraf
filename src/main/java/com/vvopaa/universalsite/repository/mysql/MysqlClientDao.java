package com.vvopaa.universalsite.repository.mysql;

import com.vvopaa.universalsite.model.Client;
import com.vvopaa.universalsite.repository.ClientDao;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MysqlClientDao extends AbstractMysqlDao<Integer, Client> implements ClientDao {

    @Override
    public Client getClientByClientId(String clientId) {
        CriteriaBuilder builder = getBuilder();
        CriteriaQuery<Client> query = builder.createQuery(Client.class);
        Root<Client> root = query.from(Client.class);
        query.select(root).where(builder.equal(root.get("clientId"), clientId));
        Query<Client> q = getSession().createQuery(query);
        List<Client> existingListClients = q.getResultList();
        if (!existingListClients.isEmpty()) {
            return existingListClients.get(0);
        }
        return null;
    }
}
