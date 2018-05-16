/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.policy.keyless;

import io.gravitee.gateway.api.ExecutionContext;
import io.gravitee.gateway.api.Request;
import io.gravitee.gateway.api.Response;
import io.gravitee.policy.api.ChainScope;
import io.gravitee.policy.api.PolicyChain;
import io.gravitee.policy.api.annotations.Category;
import io.gravitee.policy.api.annotations.OnRequest;
import io.gravitee.policy.api.annotations.Policy;
import io.gravitee.policy.api.annotations.Scope;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
@Policy(
        category = @Category(io.gravitee.policy.api.Category.SECURITY),
        scope = @Scope(ChainScope.SECURITY)
)
public class KeylessPolicy {

    /**
     * Code for a key-less call (no application / subscription required)
     */
    static final String APPLICATION_NAME_ANONYMOUS = "1";

    @OnRequest
    public void onRequest(Request request, Response response, ExecutionContext executionContext, PolicyChain policyChain) {
        executionContext.setAttribute(ExecutionContext.ATTR_APPLICATION, APPLICATION_NAME_ANONYMOUS);
        executionContext.setAttribute(ExecutionContext.ATTR_USER_ID, request.remoteAddress());
        policyChain.doNext(request, response);
    }
}
