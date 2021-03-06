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
package org.kordamp.basilisk.gradle

import org.gradle.api.Project

/**
 * @author Andres Almiray
 */
class BasiliskExtension {
    String version = '1.0.0-SNAPSHOT'

    boolean disableDependencyResolution

    boolean includeDefaultRepositories = true

    boolean generateProjectStructure = true

    boolean applicationProject = true

    String applicationIconName = 'basilisk.icns'

    Map applicationProperties = [:]

    BasiliskExtension(Project project) {
    }
}
