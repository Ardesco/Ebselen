package com.lazerycode.ebselen.customhandlers;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

class JettyServer {

    private Server jettyServer;

    public void startJettyServer(int port) throws Exception {
        String resourceBasePath = this.getClass().getResource("/web").toExternalForm();
        jettyServer = new Server(port);
        WebAppContext webapp = new WebAppContext();
        webapp.setResourceBase(resourceBasePath);
        jettyServer.setHandler(webapp);
        jettyServer.start();
    }

    public void stopJettyServer() throws Exception {
        jettyServer.stop();
    }
}