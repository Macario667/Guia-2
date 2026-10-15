package org.acme;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

@ConfigMapping(prefix = "app")
public interface AppConfig {

    String nombre();

    @WithDefault("1.0.0")
    String version();

    @WithDefault("DESARROLLO")
    Modo modo();

    String bienvenida();

    // Nested config: app.autor.nombre, app.autor.email
    Autor autor();

    interface Autor {
        String nombre();
        String email();
    }

    enum Modo {
        DESARROLLO, PRODUCCION, TEST
    }
}