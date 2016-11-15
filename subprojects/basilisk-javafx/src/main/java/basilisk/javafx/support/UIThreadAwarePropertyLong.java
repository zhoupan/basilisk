/*
 * Copyright 2008-2016 the original author or authors.
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
package basilisk.javafx.support;

import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;

import javax.annotation.Nonnull;

/**
 * @author Andres Almiray
 * @since 2.9.0
 */
class UIThreadAwarePropertyLong extends AbstractUIThreadAwareProperty<Long> implements Property<Long> {
    UIThreadAwarePropertyLong(@Nonnull Property<Long> delegate) {
        super(delegate);
    }

    @Override
    public void bind(ObservableValue<? extends Long> observable) {
        getDelegate().bind(observable);
    }

    @Override
    public Long getValue() {
        return getDelegate().getValue();
    }

    @Override
    public void setValue(Long value) {
        getDelegate().setValue(value);
    }
}
