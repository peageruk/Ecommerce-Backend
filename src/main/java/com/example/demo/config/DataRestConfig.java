package com.example.demo.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.example.demo.entity.Country;
import com.example.demo.entity.Product;
import com.example.demo.entity.State;



@Configuration
public class DataRestConfig implements RepositoryRestConfigurer{
	
    @Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,CorsRegistry cors) {
    	
    	config.exposeIdsFor(Product.class);
    	config.exposeIdsFor(Country.class);
		config.setBasePath("/api");
		
		
		HttpMethod[] theunsupportmethod= {HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT};
				

		
		config.getExposureConfiguration()
		.forDomainType(Country.class)
		.withItemExposure((metdata, httpMethods) -> httpMethods.disable(theunsupportmethod) )
		.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theunsupportmethod));
		
		config.getExposureConfiguration()
		.forDomainType(State.class)
		.withItemExposure((metdata, httpMethods) -> httpMethods.disable(theunsupportmethod) )
		.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theunsupportmethod));
		
		
		
		
	}

	
	
	

}
