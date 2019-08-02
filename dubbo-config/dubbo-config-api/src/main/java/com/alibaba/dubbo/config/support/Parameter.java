/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.config.support;

import com.alibaba.dubbo.config.AbstractInterfaceConfig;
import com.alibaba.dubbo.config.AbstractReferenceConfig;
import com.alibaba.dubbo.config.AbstractServiceConfig;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

/**
 * Parameter
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Parameter {

    /**
     * 键
     * @return
     */
    String key() default "";

    /**
     * 是否必填
     * @return
     */
    boolean required() default false;

    /**
     * 是否忽略
     * @return
     */
    boolean excluded() default false;

    /**
     * 是否转义
     * @return
     */
    boolean escaped() default false;

    /**
     * 是否为属性
     * 目前用于事件通知:http://dubbo.apache.org/zh-cn/docs/user/demos/events-notify.html
     * @return
     */
    boolean attribute() default false;

    /**
     * 是否拼接默认属性，参见{@link com.alibaba.dubbo.config.AbstractConfig#appendParameters(Map, Object)}方法
     * #append()=true的属性，有如下4个：
     * {@link AbstractInterfaceConfig#getFilter()}
     * {@link AbstractInterfaceConfig#getListener()}
     * {@link AbstractReferenceConfig#getFilter()}
     * {@link AbstractReferenceConfig#getListener()}
     * {@link AbstractServiceConfig#getFilter()}
     * {@link AbstractServiceConfig#getFilter()}
     *
     * 以AbstractServiceConfig举例
     * ProviderConfig和ServiceConfig继承AbstractServiceConfig类，那么filter、listener 对应的相同的键
     * 下面我们以filter举例
     * 在ServiceConfig中，默认会<b>继承</b> ProviderConfig配置的filter和listener
     * 所以这个属性就是用于像ServiceConfig的这种情况，从ProviderConfig读取父属性
     * 举个例子，如果ProviderConfig.filter=aaaFilter，ServiceConfig.filter=bbbFilter,最红暴露到
     * Dubbo URL时，参数为service.filter=aaaFilter,bbbFilter
     * @return
     */
    boolean append() default false;

}