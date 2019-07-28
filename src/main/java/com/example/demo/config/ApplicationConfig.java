/**
 * @(#)ApplicationConfig.java    10/04/17
 *
 *
 */

package com.example.demo.config;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Configuration
@ComponentScan(basePackages = { "com.example.demo" })
public class ApplicationConfig extends WebMvcConfigurerAdapter {


	
	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager();
	}

	
	@Bean
	public EhCacheFactoryBean Cache() {
		EhCacheFactoryBean cacheFactoryBean = new EhCacheFactoryBean();
		cacheFactoryBean.setCacheName("configCache");

		return cacheFactoryBean;
	}
	
	@Bean
	public EhCacheFactoryBean Cache1() {
		EhCacheFactoryBean cacheFactoryBean = new EhCacheFactoryBean();
		cacheFactoryBean.setCacheName("getStudent");

		return cacheFactoryBean;
	}

	



}
