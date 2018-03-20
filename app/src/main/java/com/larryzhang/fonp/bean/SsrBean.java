package com.larryzhang.fonp.bean;

/**
 * ssr的配置文件列表
 *
 * @author zhangqiang
 * @date 2018/3/20
 */

public class SsrBean {

    /**
     * server : 64.137.198.70
     * password : doub.io/sszhfx/*doub.bid/sszhfx/*4010
     * obfs : tls1.2_ticket_auth
     * method : chacha20
     * local_port : 1080
     * local_address : 127.0.0.1
     * ssr_protocol : auth_sha1_v4
     * server_port : 4010
     * group : ShadowSocks-Share
     */

    private String server;
    private String password;
    private String obfs;
    private String method;
    private int local_port;
    private String local_address;
    private String ssr_protocol;
    private int server_port;
    private String group;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getObfs() {
        return obfs;
    }

    public void setObfs(String obfs) {
        this.obfs = obfs;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getLocal_port() {
        return local_port;
    }

    public void setLocal_port(int local_port) {
        this.local_port = local_port;
    }

    public String getLocal_address() {
        return local_address;
    }

    public void setLocal_address(String local_address) {
        this.local_address = local_address;
    }

    public String getSsr_protocol() {
        return ssr_protocol;
    }

    public void setSsr_protocol(String ssr_protocol) {
        this.ssr_protocol = ssr_protocol;
    }

    public int getServer_port() {
        return server_port;
    }

    public void setServer_port(int server_port) {
        this.server_port = server_port;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
