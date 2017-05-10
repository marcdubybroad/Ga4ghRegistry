package org.ga4gh.registry.datastore.jpa;

import org.apache.log4j.Logger;
import org.ga4gh.registry.bean.ServerNodeBean;
import org.ga4gh.registry.datastore.RegistryDAO;
import org.ga4gh.registry.util.RegistryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    // instance variables
    private final Logger daoLog = Logger.getLogger(this.getClass().getName());

    @Autowired
    private RegistryRepository registryRepository;

    /**
     * list all the stored server nodes
     *
     * @return
     * @throws RegistryException
     */
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

    /**
     * list all the server nodes of a given type
     *
     * @param type
     * @return
     * @throws RegistryException
     */
    @Override
    public List<ServerNodeBean> getAllServerNodesOfType(String type) throws RegistryException {
        // local variables
        List<ServerNodeBean> serverNodeBeanList = new ArrayList<ServerNodeBean>();
        Iterator<ServerNodeBean> serverNodeBeanIterator = null;

        // get the iteratore from the repository
        serverNodeBeanIterator = this.registryRepository.findAll().iterator();

        // populate the list
        while (serverNodeBeanIterator.hasNext()) {
            ServerNodeBean serverNodeBean = serverNodeBeanIterator.next();
            if (type.equalsIgnoreCase(serverNodeBean.getType())) {
                serverNodeBeanList.add(serverNodeBean);
            }
        }

        // return
        return serverNodeBeanList;
    }

    /**
     * add a server node to the cache
     *
     * @param serverNodeBean                the node to be added
     * @throws RegistryException            if the node is already registered
     */
    @Override
    public void addServerNode(ServerNodeBean serverNodeBean) throws RegistryException {
        try {
            this.registryRepository.save(serverNodeBean);

        } catch (DataIntegrityViolationException exception) {
            String errorMessage = "Got new server node creation error due to duplicate url/type combination: " + exception.getMessage();
            this.daoLog.error(errorMessage);
            throw new RegistryException(errorMessage);
        }
    }

    @Override
    public void deleteServerNode(ServerNodeBean serverNodeBean) throws RegistryException {

    }

    @Override
    public void updateServerNode(ServerNodeBean serverNodeBean) throws RegistryException {

    }
}
