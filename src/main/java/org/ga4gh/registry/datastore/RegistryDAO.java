package org.ga4gh.registry.datastore;

import org.ga4gh.registry.bean.ServerNodeBean;
import org.ga4gh.registry.util.RegistryException;

import java.util.List;

/**
 * Interface to be implemented by any DAO that manages registry information
 *
 * Created by mduby on 4/5/17.
 */
public interface RegistryDAO {

    /**
     * list all the stored server nodes
     *
     * @return
     * @throws RegistryException
     */
    public List<ServerNodeBean> getAllServerNodes() throws RegistryException;

    /**
     * list all the server nodes of a given type
     *
     * @param type
     * @return
     * @throws RegistryException
     */
    public List<ServerNodeBean> getAllServerNodesOfType(String type) throws RegistryException;

    /**
     * add a server node to the cache
     *
     * @param serverNodeBean                the node to be added
     * @throws RegistryException            if the node is already registered
     */
    public void addServerNode(ServerNodeBean serverNodeBean) throws RegistryException;

    /**
     * delete a server node from the cache
     *
     * @param serverNodeBean                the server node to delete
     * @throws RegistryException            if the server node is not registered
     */
    public void deleteServerNode(ServerNodeBean serverNodeBean) throws RegistryException;

    /**
     * update the server node
     *
     * @param serverNodeBean                the server node to update
     * @throws RegistryException            if the server node is npt registered
     */
    public void updateServerNode(ServerNodeBean serverNodeBean) throws RegistryException;
}
