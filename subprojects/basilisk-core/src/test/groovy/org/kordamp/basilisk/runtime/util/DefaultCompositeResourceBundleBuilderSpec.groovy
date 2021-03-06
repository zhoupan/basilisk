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
package org.kordamp.basilisk.runtime.util

import basilisk.core.ApplicationClassLoader
import basilisk.core.env.Environment
import basilisk.core.env.Metadata
import basilisk.core.injection.Injector
import basilisk.core.resources.ResourceHandler
import basilisk.util.AnnotationUtils
import basilisk.util.CompositeResourceBundleBuilder
import basilisk.util.Instantiator
import basilisk.util.PropertiesReader
import basilisk.util.ResourceBundleLoader
import basilisk.util.ResourceBundleReader
import com.google.guiceberry.GuiceBerryModule
import com.google.guiceberry.junit4.GuiceBerryRule
import com.google.inject.AbstractModule
import org.junit.Rule
import org.kordamp.basilisk.runtime.core.DefaultApplicationClassLoader
import org.kordamp.basilisk.runtime.core.env.EnvironmentProvider
import org.kordamp.basilisk.runtime.core.env.MetadataProvider
import org.kordamp.basilisk.runtime.core.resources.DefaultResourceHandler
import spock.lang.Specification
import spock.lang.Unroll

import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider
import javax.inject.Singleton

import static com.google.inject.util.Providers.guicify
import static org.mockito.Mockito.mock

@Unroll
class DefaultCompositeResourceBundleBuilderSpec extends Specification {
    @Rule
    final GuiceBerryRule guiceBerry = new GuiceBerryRule(TestModule)

    @Inject private CompositeResourceBundleBuilder bundleBuilder
    @Inject private Provider<Injector> injector
    @Inject @Named('class') private ResourceBundleLoader resourceBundleLoader1
    @Inject @Named('properties') private ResourceBundleLoader resourceBundleLoader2

    def 'Create throws #exception'() {
        given:
        injector.get().getInstances(ResourceBundleLoader) >> [resourceBundleLoader1, resourceBundleLoader2]

        when:
        bundleBuilder.create(filename, locale)

        then:
        thrown(exception)

        where:
        filename | locale         | exception
        null     | Locale.default | IllegalArgumentException
        'empty'  | null           | NullPointerException
        'empty'  | Locale.default | IllegalArgumentException
    }

    def 'Excercise bundle creation with #filename'() {
        given:
        injector.get().getInstances(ResourceBundleLoader) >> [resourceBundleLoader1, resourceBundleLoader2]

        when:
        bundleBuilder.create(filename)

        then:
        thrown(IllegalArgumentException)

        where:
        filename << [
            'org.kordamp.basilisk.runtime.util.NotFoundBundle',
            'org.kordamp.basilisk.runtime.util.NotABundle',
            'org.kordamp.basilisk.runtime.util.BrokenBundle']
    }

    static final class TestModule extends AbstractModule {
        @Override
        protected void configure() {
            install(new GuiceBerryModule())
            bind(ApplicationClassLoader).to(DefaultApplicationClassLoader).in(Singleton)
            bind(ResourceHandler).to(DefaultResourceHandler).in(Singleton)
            bind(PropertiesReader).toProvider(guicify(new PropertiesReader.Provider())).in(Singleton)
            bind(ResourceBundleReader).toProvider(guicify(new ResourceBundleReader.Provider())).in(Singleton)
            bind(Metadata).toProvider(guicify(new MetadataProvider())).in(Singleton)
            bind(Environment).toProvider(guicify(new EnvironmentProvider())).in(Singleton)
            bind(Instantiator).to(DefaultInstantiator).in(Singleton)
            bind(ResourceBundleLoader).annotatedWith(AnnotationUtils.named('class')).to(ClassResourceBundleLoader).in(Singleton)
            bind(ResourceBundleLoader).annotatedWith(AnnotationUtils.named('properties')).to(PropertiesResourceBundleLoader).in(Singleton)
            bind(Injector).toProvider(guicify({ mock(Injector) } as Provider<Injector>)).in(Singleton)
            bind(CompositeResourceBundleBuilder).to(DefaultCompositeResourceBundleBuilder).in(Singleton)
        }
    }
}
