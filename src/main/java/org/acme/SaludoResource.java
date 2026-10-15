package org.acme;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/saludo")
public class SaludoResource {
    
    @Inject 
    AppConfig config;

    @ConfigProperty(name = "app.bienvenida")
    String bienvenida;

    @GET
    public String saludar() {
        return bienvenida + " — " + config.modo();
    }
}
