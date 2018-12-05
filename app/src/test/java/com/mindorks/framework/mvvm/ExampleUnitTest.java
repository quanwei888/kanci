/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.mindorks.framework.mvvm;

import org.junit.Test;

import javax.inject.Inject;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    public static class Car {

        @Inject Engine engine;

        public Car() {
            DaggerExampleUnitTest_CarComponent.builder().build().inject(this);
        }

        public Engine getEngine() {
            return this.engine;
        }
    }

    public static class Engine {
        int version = 0;
        BB b;

        @Inject
        Engine(BB b) {
            this.b = b;
        }

        public void run() {
            System.out.println("引擎转起来了~~~" + version);
        }
    }

    @Module
    public static class BeanModule {
        @Provides
        public Engine providerBean(BB b) {
            Engine engine = new Engine(b);
            return engine;
        }
        @Provides
        public BB providerBB() {
            B b = new B();
            return b;
        }
    }

    interface BB {

    }
    public static class B implements BB {
        @Inject
        public B() {
            System.out.println("引擎转起来了~");
        }
    }

    @Component(modules = BeanModule.class)
    public static interface CarComponent {
        void inject(Car car);
    }

    @Test
    public void addition_isCorrect() throws Exception {
        new Car().engine.run();
        assertEquals(4, 2 + 2);
    }
}