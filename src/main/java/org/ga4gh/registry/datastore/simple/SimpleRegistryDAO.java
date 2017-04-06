package org.ga4gh.registry.datastore.simple;

import org.apache.log4j.Logger;
import org.ga4gh.registry.bean.ServerNodeBean;
import org.ga4gh.registry.datastore.RegistryDAO;
import org.ga4gh.registry.util.RegistryConstants;
import org.ga4gh.registry.util.RegistryException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Simple implementation of a registry DAO
 * <br/>
 * To use as a refernce
 *
 * Created by mduby on 4/5/17.
 */
@Component
public class SimpleRegistryDAO implements RegistryDAO {
    // instance variables
    private final Logger daoLog = Logger.getLogger(this.getClass().getName());

    // cached map; use hashtable for synchronization
    private Map<String, ServerNodeBean> serverMap = new Hashtable<String, ServerNodeBean>();

    /**
     * list all the stored server nodes
     *
     * @return
     * @throws RegistryException
     */
    @Override
    public List<ServerNodeBean> getAllServerNodes() throws RegistryException {
        return new ArrayList<ServerNodeBean>(this.serverMap.values());
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

        // make sure the type is valid
        if (!RegistryConstants.RegistryType.TYPE_LIST.contains(type)) {
            String message = "Got request for incorrect server node type: " + type;
            this.daoLog.error(message);
            throw new RegistryException(message);
        }

        // get an iterator
        Iterator<ServerNodeBean> iterator = this.serverMap.values().iterator();

        // for each node, check the type
        while (iterator.hasNext()) {
            ServerNodeBean bean = iterator.next();

            if (type.equalsIgnoreCase(bean.getType())) {
                serverNodeBeanList.add(bean);
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
        // verify the server node
        this.verifyNode(serverNodeBean);

        // make sure not already registered
        if (this.serverMap.get(serverNodeBean.getUrl()) != null) {
            String message = "Server node with url: " + serverNodeBean.getUrl() + " already is registered";
            this.daoLog.error(message);
            throw new RegistryException(message);
        }

        // add to the map
        this.serverMap.put(serverNodeBean.getUrl(), serverNodeBean);
    }

    /**
     * delete a server node from the cache
     *
     * @param serverNodeBean                the server node to delete
     * @throws RegistryException            if the server node is not registered
     */
    @Override
    public void deleteServerNode(ServerNodeBean serverNodeBean) throws RegistryException {
        // verify the server node
        this.verifyNode(serverNodeBean);

        // make sure not already registered
        if (this.serverMap.get(serverNodeBean.getUrl()) == null) {
            String message = "Server node with url: " + serverNodeBean.getUrl() + " already not registered so cannot be deleted";
            this.daoLog.error(message);
            throw new RegistryException(message);
        }

        // add to the map
        this.serverMap.remove(serverNodeBean.getUrl());
    }

    /**
     * update the server node
     *
     * @param serverNodeBean                the server node to update
     * @throws RegistryException            if the server node is npt registered
     */
    @Override
    public void updateServerNode(ServerNodeBean serverNodeBean) throws RegistryException {
        // verify the server node
        this.verifyNode(serverNodeBean);

        // make sure not already registered
        if (this.serverMap.get(serverNodeBean.getUrl()) == null) {
            String message = "Server node with url: " + serverNodeBean.getUrl() + " already not registered so cannot be updated";
            this.daoLog.error(message);
            throw new RegistryException(message);
        }

        // add to the map
        this.serverMap.put(serverNodeBean.getUrl(), serverNodeBean);
    }

    /**
     * method to verify server node entries
     *
     * @param serverNodeBean
     * @throws RegistryException
     */
    protected void verifyNode(ServerNodeBean serverNodeBean) throws RegistryException {
        // make sure url is not null
        if (serverNodeBean.getUrl() == null) {
            String message = "Got server node with null url";
            this.daoLog.error(message);
            throw new RegistryException(message);
        }

        // make sure type is not null
        if (serverNodeBean.getType() == null) {
            String message = "Got server node with null server node type";
            this.daoLog.error(message);
            throw new RegistryException(message);
        }

        // make sure type is correct
        if (!RegistryConstants.RegistryType.TYPE_LIST.contains(serverNodeBean.getType())) {
            String message = "Got server node with incorrect server node type: " + serverNodeBean.getType();
            this.daoLog.error(message);
            throw new RegistryException(message);
        }
    }

}
