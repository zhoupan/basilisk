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
package basilisk.core.env;

import javax.annotation.Nonnull;

import static basilisk.util.BasiliskNameUtils.isNotBlank;
import static basilisk.util.BasiliskNameUtils.requireNonBlank;
import static java.util.Objects.requireNonNull;

/**
 * @author Andres Almiray
 */
public class Feature {
    private static final String ERROR_FEATURE_NAME_BLANK = "Argument 'featureName' cannot be blank";

    public static boolean isFeatureEnabled(@Nonnull String featureName) {
        requireNonBlank(featureName, ERROR_FEATURE_NAME_BLANK);
        return isNotBlank(System.getProperty(featureName)) && Boolean.getBoolean(featureName);
    }

    public static void setFeatureEnabled(@Nonnull String featureName, boolean enabled) {
        requireNonBlank(featureName, ERROR_FEATURE_NAME_BLANK);
        System.setProperty(featureName, String.valueOf(enabled));
    }

    public static void withFeature(@Nonnull String featureName, @Nonnull Runnable runnable) {
        requireNonNull(runnable, "Argument 'runnable' cannot be null");
        if (isFeatureEnabled(featureName)) {
            runnable.run();
        }
    }
}
