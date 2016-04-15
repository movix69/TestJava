package com.websystique.springmvc.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SprintMVCInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { StringMVCConfiguration.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
 
 }