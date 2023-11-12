package com.mmorpg.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties("mongo-database")
public class MongoConfiguration {
    private String databaseName;
    private String characterCollection;
}
