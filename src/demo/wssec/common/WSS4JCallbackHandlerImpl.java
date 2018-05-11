/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package demo.wssec.common;

import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import java.io.IOException;

public class WSS4JCallbackHandlerImpl implements CallbackHandler {

    private byte[] secret;

    public WSS4JCallbackHandlerImpl() {
    }

    public WSS4JCallbackHandlerImpl(byte[] secret) {
        this.secret = secret;
    }

    @Override
    public void handle(javax.security.auth.callback.Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

//        if (pc.getUsage() == org.apache.wss4j.dom.WSPasswordCallback.DECRYPT || pc.getUsage() == org.apache.wss4j.WSPasswordCallback.SIGNATURE) {
        if ("wss40rev".equals(pc.getIdentifier())) {
            pc.setPassword("security");
        } else {
            pc.setPassword("default");
        }
/*        } else {
            throw new UnsupportedCallbackException(pc, "Unrecognized CallbackHandlerImpl");
        }
*/
        if (pc.getUsage() == WSPasswordCallback.SECRET_KEY ||
                pc.getUsage() == WSPasswordCallback.SECURITY_CONTEXT_TOKEN) {
            pc.setKey(secret);
        }
    }
}
