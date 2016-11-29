/*
 * Copyright 2016 the original author or authors.
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
package org.example;

import basilisk.core.artifact.BasiliskModel;
import basilisk.metadata.ArtifactProviderFor;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.kordamp.basilisk.runtime.core.artifact.AbstractBasiliskModel;

import javax.annotation.Nonnull;

@ArtifactProviderFor(BasiliskModel.class)
public class SampleModel extends AbstractBasiliskModel {
    private StringProperty input;
    private StringProperty output;

    @Nonnull
    public final StringProperty inputProperty() {
        if (input == null) {
            input = new SimpleStringProperty(this, "input", "");
        }
        return input;
    }

    @Nonnull
    public String getInput() {
        return input == null ? null : inputProperty().get();
    }

    public void setInput(String input) {
        inputProperty().set(input);
    }

    @Nonnull
    public final StringProperty outputProperty() {
        if (output == null) {
            output = new SimpleStringProperty(this, "output", "");
        }
        return output;
    }

    @Nonnull
    public String getOutput() {
        return output == null ? null : outputProperty().get();
    }

    public void setOutput(String output) {
        outputProperty().set(output);
    }
}