package org.ga4gh.registry.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Date;

/**
 * Bean class to encapsulate the server settings
 * <br/>
 * Tried using Jackson json serialization, but didn't like cryptic error messages, so went with manual parsing. Keeping annotations anyway just in case
 *
 * Created by mduby on 4/5/17.
 */
@Entity
@Table(name = "registry_server_node")
public class ServerNodeBean {
    // instance variables
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="web_name")
    private String webName;

    @Column(name = "type")
    private String type = null;

    @Column(name="url", unique = true)
    private String url = null;

    @Version
    @Column(name="version")
    private long version;

    @Column(name="last_updated", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date lastUpdated;

    public ServerNodeBean() {
        super();
    }

    public ServerNodeBean(String type, String url) {
        this.type = type;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
