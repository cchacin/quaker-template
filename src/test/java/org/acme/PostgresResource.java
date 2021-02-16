package org.acme;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Collections;
import java.util.Map;
/**
 * FROM: https://www.morling.dev/blog/quarkus-and-testcontainers/
 */
public class PostgresResource implements
        QuarkusTestResourceLifecycleManager {

  static PostgreSQLContainer<?> db =
      new PostgreSQLContainer<>("postgres")
        .withDatabaseName("postgres")
        .withUsername("postgres")
        .withPassword("postgres");

  @Override
  public Map<String, String> start() {
    db.start();
    return Collections.singletonMap(
        "quarkus.datasource.jdbc.url", String.format("jdbc:tracing:postgresql://%s:%d/postgres",db.getHost(), db.getMappedPort(5432))
    );
  }

  @Override
  public void stop() { 
    db.stop();
  }
}