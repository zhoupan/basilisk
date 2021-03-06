/*
 * Copyright 2008-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kordamp.basilisk.runtime.core.artifact;

import basilisk.core.artifact.BasiliskController;
import basilisk.core.artifact.BasiliskControllerClass;
import basilisk.core.controller.Action;
import basilisk.core.controller.ActionManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static basilisk.util.BasiliskNameUtils.requireNonBlank;

/**
 * Base implementation of the BasiliskController interface.
 *
 * @author Andres Almiray
 */
public abstract class AbstractBasiliskController extends AbstractBasiliskMvcArtifact implements BasiliskController {
    @Nonnull
    @Override
    protected String getArtifactType() {
        return BasiliskControllerClass.TYPE;
    }

    @Nonnull
    protected ActionManager getActionManager() {
        return getApplication().getActionManager();
    }

    public void invokeAction(@Nonnull String name, Object... args) {
        getActionManager().invokeAction(this, requireNonBlank(name, "Argument 'name' must not be blank"), args);
    }

    @Nullable
    protected Action actionFor(@Nonnull String actionName) {
        return getActionManager().actionFor(this, actionName);
    }
}
