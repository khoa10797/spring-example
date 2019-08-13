package com.dangkhoa.springexample.configuration;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.dangkhoa.springexample.repositories", mongoTemplateRef = "mongoTemplate")
public class MultipleMongoConfig {

    @Value("${mongodb.user.uri}")
    private String MONGO_URI;

    @Primary
    @Bean(name = "mongoFactory")
    public MongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(new MongoClientURI(MONGO_URI));
    }

    @Bean(name = "mongoConverter")
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory, MongoMappingContext context){
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        context.setFieldNamingStrategy(new SnakeCaseFieldNamingStrategy());
        return new MappingMongoConverter(dbRefResolver, context);
    }

    @Primary
    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("mongoFactory") MongoDbFactory mongoDbFactory, @Qualifier("mongoConverter") MongoConverter mongoConverter) {
        return new MongoTemplate(mongoDbFactory, mongoConverter);
    }
}
