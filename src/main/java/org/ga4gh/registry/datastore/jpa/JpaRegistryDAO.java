package org.ga4gh.registry.datastore.jpa;

import org.ga4gh.registry.bean.ServerNodeBean;
import org.ga4gh.registry.datastore.RegistryDAO;
import org.ga4gh.registry.util.RegistryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class to use JPA to retrieve registrty information from the database
 *
 * Created by mduby on 5/10/17.
 */
@Component
public class JpaRegistryDAO implements RegistryDAO {

    @Autowired
    private RegistryRepository registryRepository;

    @Override
    public List<ServerNodeBean> getAllServerNodes() throws RegistryException {
        // local variables
        List<ServerNodeBean> serverNodeBeanList = new ArrayList<ServerNodeBean>();
        Iterator<ServerNodeBean> serverNodeBeanIterator = null;

        // get the iteratore from the repository
        serverNodeBeanIterator = this.registryRepository.findAll().iterator();

        // populate the list
        while (serverNodeBeanIterator.hasNext()) {
            serverNodeBeanList.add(serverNodeBeanIterator.next());
        }

        // return
        return serverNodeBeanList;
    }

    @Override
    public List<ServerNodeBean> getAllServerNodesOfType(String type) throws RegistryException {
        return null;
    }

    @Override
    public void addServerNode(ServerNodeBean serverNodeBean) throws RegistryException {

    }

    @Override
    public void deleteServerNode(ServerNodeBean serverNodeBean) throws RegistryException {

    }

    @Override
    public void updateServerNode(ServerNodeBean serverNodeBean) throws RegistryException {

    }
}
