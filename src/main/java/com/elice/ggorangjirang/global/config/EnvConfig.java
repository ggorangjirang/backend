package com.elice.ggorangjirang.global.config;

import java.util.Objects;
import java.util.Properties;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

@Configuration
public class EnvConfig implements PropertySourceFactory {

  @Override
  public PropertySource<?> createPropertySource(String name, EncodedResource resource) {
    YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
    factoryBean.setResources(resource.getResource());
    Properties properties = factoryBean.getObject();
    assert properties != null;
    return new PropertiesPropertySource(
        Objects.requireNonNull(resource.getResource().getFilename()), properties);
  }
}
