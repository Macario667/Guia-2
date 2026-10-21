# quarkus-config-demo

Ejemplo de configuración tipada de una aplicación Quarkus, con propiedades jerárquicas, valores predeterminados y valores distintos por perfil.

## Guías adaptadas

- [Configuring Your Application](https://quarkus.io/guides/config): define y consume `app.*` en los recursos.
- [Configuration Reference Guide](https://quarkus.io/guides/config-reference): sirve para entender la precedencia de fuentes y perfiles que intervienen al resolver esas propiedades.

La configuración tipada usa SmallRye Config. No es una implementación de configuración basada en variables Java ad hoc: `AppConfig` define el contrato y Quarkus lo inyecta.

## Cómo se aplica

- `AppConfig` tiene el prefijo `app`, valores predeterminados para `version` y `modo`, una sección anidada `autor` y el enum `Modo`.
- `application.properties` define `app.nombre`, `app.autor.*` y `app.bienvenida`. La bienvenida combina propiedades con `${...}`.
- `application-dev.properties` y `application-prod.properties` reemplazan el nombre y el modo para los perfiles `dev` y `prod`.
- `InfoResource` expone parte de la configuración como JSON en `GET /info`.
- `SaludoResource` expone la bienvenida y el modo en `GET /saludo`.
- `ConfigTest` usa un perfil de prueba y sobreescribe propiedades para comprobar la inyección.

## Ejecutar y probar

```powershell
.\mvnw.cmd quarkus:dev
```

Prueba los dos recursos:

```powershell
curl.exe http://localhost:8080/info
curl.exe http://localhost:8080/saludo
```

Para arrancar con el perfil de producción:

```powershell
.\mvnw.cmd quarkus:dev -Dquarkus.profile=prod
```

Observa cómo cambian `app.nombre` y `app.modo`. Ejecuta las pruebas:

```powershell
.\mvnw.cmd test
```

## Ejercicio para explicar perfiles y precedencia

1. Cambia `app.nombre` en `application.properties` y observa `/info`.
2. Añade la misma propiedad a `application-dev.properties`; compara el perfil `dev` con el perfil predeterminado.
3. Pasa una propiedad por línea de comandos, por ejemplo `-Dapp.nombre=Temporal`, y comprueba cuál valor prevalece.
4. Explica por qué `@WithDefault` solo proporciona un valor si no hay otra fuente configurada.

Usa valores de demostración; evita poner credenciales o secretos en propiedades del repositorio.
