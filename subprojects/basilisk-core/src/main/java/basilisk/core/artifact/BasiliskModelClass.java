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
package basilisk.core.artifact;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents a Model class in Basilisk.<p>
 *
 * @author Andres Almiray
 */
public interface BasiliskModelClass extends BasiliskClass {
    /**
     * "model"
     */
    String TYPE = "model";
    /**
     * "Model"
     */
    String TRAILING = "Model";

    /**
     * Matches all public properties that are not event handlers nor
     * have a Closure as their value.<p>
     *
     * @return an array containing the names of all model properties.
     */
    @Nonnull
    String[] getPropertyNames();

    /**
     * Matches all public methods and closure properties whose name
     * matches the event handler convention, i.e, starts with "on" and
     * is followed by at least one uppercase character.<p>
     *
     * @return an array containing the names of all event handlers.
     */
    @Nonnull
    String[] getEventNames();

    /**
     * Sets a value of a model property.
     *
     * @param model        the model to be affected
     * @param propertyName the name of the property to update
     * @param value        new value
     */
    void setModelPropertyValue(@Nonnull BasiliskModel model, @Nonnull String propertyName, @Nullable Object value);

    /**
     * Returns the value of a model property.
     *
     * @param model        the model to query
     * @param propertyName the name of the property to query
     * @return the value of the property
     */
    @Nullable
    Object getModelPropertyValue(@Nonnull BasiliskModel model, @Nonnull String propertyName);
}