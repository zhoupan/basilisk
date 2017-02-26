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
package basilisk.exceptions;

import javax.annotation.Nonnull;

/**
 * @author Andres Almiray
 * @since 1.0.0
 */
public class BasiliskViewInitializationException extends MVCGroupException {
    private static final long serialVersionUID = 1468552404292588715L;

    private final String mvcType;
    private final String mvcId;
    private final String viewClassname;

    public BasiliskViewInitializationException(@Nonnull String mvcType, @Nonnull String mvcId, @Nonnull String viewClassname, @Nonnull Throwable cause) {
        super(format(mvcType, mvcId, viewClassname), checkNonNull(cause, "cause"));
        this.mvcType = checkNonBlank(mvcType, "mvcType");
        this.mvcId = checkNonBlank(mvcId, "mvcId");
        this.viewClassname = checkNonBlank(viewClassname, "viewClassname");
    }

    private static String format(@Nonnull String mvcType, @Nonnull String mvcId, @Nonnull String viewClassname) {
        return "Unexpected error when initializing view of type " + checkNonNull(viewClassname, "viewClassname")
            + " belonging to mvcgroup [" + mvcType + ":" + mvcId + "]";
    }

    @Nonnull
    public String getMvcType() {
        return mvcType;
    }

    @Nonnull
    public String getMvcId() {
        return mvcId;
    }

    @Nonnull
    public String getViewClassname() {
        return viewClassname;
    }
}
