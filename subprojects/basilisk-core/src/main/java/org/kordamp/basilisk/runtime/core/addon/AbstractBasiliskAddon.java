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
package org.kordamp.basilisk.runtime.core.addon;

import basilisk.core.BasiliskApplication;
import basilisk.core.addon.BasiliskAddon;
import basilisk.core.artifact.BasiliskController;
import basilisk.core.artifact.BasiliskModel;
import basilisk.core.artifact.BasiliskView;
import basilisk.core.mvc.MVCGroup;
import basilisk.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static basilisk.util.AnnotationUtils.nameFor;
import static java.util.Objects.requireNonNull;

/**
 * @author Andres Almiray
 */
public class AbstractBasiliskAddon implements BasiliskAddon {
    private final Logger log;

    @Inject
    public AbstractBasiliskAddon() {
        log = LoggerFactory.getLogger("basilisk.addon." + getClass().getSimpleName());
    }

    @Nonnull
    @Override
    public Logger getLog() {
        return log;
    }

    @Override
    public void init(@Nonnull BasiliskApplication application) {
        // empty
    }

    @Nonnull
    @Override
    public Map<String, Map<String, Object>> getMvcGroups() {
        return Collections.emptyMap();
    }

    @Nonnull
    @Override
    public List<String> getStartupGroups() {
        return Collections.emptyList();
    }

    @Override
    public boolean canShutdown(@Nonnull BasiliskApplication application) {
        return true;
    }

    @Override
    public void onShutdown(@Nonnull BasiliskApplication application) {
        // empty
    }

    @Nonnull
    public static Map<String, Map<String, Object>> mvcgroup(@Nonnull Class<? extends MVCGroup> g,
                                                            @Nonnull Class<? extends BasiliskModel> m,
                                                            @Nonnull Class<? extends BasiliskView> v,
                                                            @Nonnull Class<? extends BasiliskController> c) {
        requireNonNull(g, "Argument 'g' must not be null");
        requireNonNull(v, "Argument 'm' must not be null");
        requireNonNull(c, "Argument 'v' must not be null");
        requireNonNull(g, "Argument 'c' must not be null");

        return CollectionUtils.<String, Map<String, Object>>map()
            .e(nameFor(g, true), CollectionUtils.<String, Object>map()
                .e("model", m.getName())
                .e("view", v.getName())
                .e("controller", c.getName())
            );
    }
}
