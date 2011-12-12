/*
 * Copyright (c) 2010-2011 Ardesco Solutions - http://www.ardescosolutions.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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