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
package basilisk.core.editors;

import basilisk.core.formatters.Formatter;
import basilisk.core.formatters.IntegerFormatter;

import static basilisk.util.BasiliskNameUtils.isBlank;

/**
 * @author Andres Almiray
 */
public class IntegerPropertyEditor extends AbstractPropertyEditor {
    protected void setValueInternal(Object value) {
        if (null == value) {
            super.setValueInternal(null);
        } else if (value instanceof CharSequence) {
            handleAsString(String.valueOf(value));
        } else if (value instanceof Number) {
            handleAsNumber((Number) value);
        } else {
            throw illegalValue(value, Integer.class);
        }
    }

    protected void handleAsString(String str) {
        try {
            super.setValueInternal(isBlank(str) ? null : Integer.parseInt(str));
        } catch (NumberFormatException e) {
            throw illegalValue(str, Integer.class, e);
        }
    }

    protected void handleAsNumber(Number number) {
        super.setValueInternal(number.intValue());
    }

    protected Formatter<Integer> resolveFormatter() {
        return isBlank(getFormat()) ? null : new IntegerFormatter(getFormat());
    }
}
