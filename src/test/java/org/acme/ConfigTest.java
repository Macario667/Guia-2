package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.junit.QuarkusTestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestProfile(ConfigTest.TestConfig.class)
public class ConfigTest {

    @Inject
    AppConfig config;

    @Test
    public void testConfigOverride() {
        assertEquals("App de test", config.nombre());
        assertEquals(AppConfig.Modo.TEST, config.modo());
    }

    public static class TestConfig implements QuarkusTestProfile {
        @Override
        public Map<String, String> getConfigOverrides() {
            return Map.of(
                "app.nombre", "App de test",
                "app.modo", "TEST"
            );
        }
    }
}