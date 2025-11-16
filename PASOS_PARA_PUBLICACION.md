# Pasos para Publicar la Aplicación RealTech en Google Play Store

## Requisitos Previos

1. **Cuenta de Desarrollador de Google Play**
   - Crear una cuenta en [Google Play Console](https://play.google.com/console)
   - Costo único: $25 USD (pago único de por vida)
   - Completar el perfil de desarrollador

2. **Google Maps API Key**
   - Obtener una clave API de Google Maps Platform
   - Visitar: https://console.cloud.google.com/
   - Habilitar "Maps SDK for Android"
   - Crear una clave API y reemplazar `YOUR_GOOGLE_MAPS_API_KEY` en `AndroidManifest.xml`

## Preparación de la Aplicación

### 1. Configurar la Clave de Google Maps

Editar `app/src/main/AndroidManifest.xml`:
```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="TU_CLAVE_API_AQUI" />
```

### 2. Configurar la Versión de la Aplicación

Editar `app/build.gradle.kts`:
```kotlin
defaultConfig {
    applicationId = "com.example.onlinestore"  // Cambiar por tu ID único
    versionCode = 1  // Incrementar en cada actualización
    versionName = "1.0"  // Versión visible para usuarios
}
```

**Importante:** Cambiar `applicationId` por un identificador único (ej: `com.tudominio.realtech`)

### 3. Generar Keystore para Firma

Ejecutar en la terminal (desde la raíz del proyecto):
```bash
keytool -genkey -v -keystore realtech-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias realtech
```

Guardar el archivo `realtech-release-key.jks` en un lugar seguro y recordar las contraseñas.

### 4. Configurar Firma en build.gradle.kts

Crear archivo `keystore.properties` en la raíz del proyecto:
```properties
storePassword=tu_contraseña_del_keystore
keyPassword=tu_contraseña_del_alias
keyAlias=realtech
storeFile=../realtech-release-key.jks
```

Agregar al inicio de `app/build.gradle.kts`:
```kotlin
val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
}
```

Agregar en la sección `android`:
```kotlin
signingConfigs {
    create("release") {
        keyAlias = keystoreProperties["keyAlias"] as String
        keyPassword = keystoreProperties["keyPassword"] as String
        storeFile = file(keystoreProperties["storeFile"] as String)
        storePassword = keystoreProperties["storePassword"] as String
    }
}

buildTypes {
    release {
        isMinifyEnabled = true
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
        signingConfig = signingConfigs.getByName("release")
    }
}
```

### 5. Generar APK/AAB Firmado

**Opción A: Generar AAB (Recomendado para Play Store)**
```bash
./gradlew bundleRelease
```
El archivo estará en: `app/build/outputs/bundle/release/app-release.aab`

**Opción B: Generar APK**
```bash
./gradlew assembleRelease
```
El archivo estará en: `app/build/outputs/apk/release/app-release.apk`

## Publicación en Google Play Store

### 1. Crear la Aplicación en Play Console

1. Iniciar sesión en [Google Play Console](https://play.google.com/console)
2. Clic en "Crear aplicación"
3. Completar:
   - Nombre de la app: "RealTech"
   - Idioma predeterminado: Español
   - Tipo de app: Aplicación
   - Gratis o de pago: Gratis

### 2. Configurar Contenido de la Tienda

#### Información de la Tienda:
- **Título:** RealTech - Tienda Virtual
- **Descripción corta:** Tu tienda virtual de tecnología con productos de calidad
- **Descripción completa:** 
  ```
  RealTech es tu tienda virtual de tecnología donde puedes:
  - Explorar una amplia gama de productos tecnológicos
  - Ver detalles completos de cada producto
  - Agregar productos a tu carrito de compras
  - Realizar compras de forma sencilla
  - Escanear códigos QR para vinculación rápida
  - Encontrar nuestra ubicación física en el mapa
  
  Características:
  ✓ Interfaz intuitiva y fácil de usar
  ✓ Catálogo de productos actualizado
  ✓ Sistema de carrito de compras
  ✓ Escáner de códigos QR integrado
  ✓ Mapa con ubicación de la tienda
  ```

#### Gráficos Requeridos:
- **Icono de la aplicación:** 512x512 px (PNG, sin transparencia)
- **Capturas de pantalla:** Mínimo 2, máximo 8
  - Teléfono: 16:9 o 9:16, mínimo 320px
  - Tableta: 16:9 o 9:16, mínimo 320px
- **Imagen destacada:** 1024x500 px (opcional pero recomendado)

### 3. Configurar Clasificación de Contenido

1. Completar el cuestionario de clasificación de contenido
2. Seleccionar las categorías apropiadas
3. Configurar restricciones de edad si aplica

### 4. Configurar Precios y Distribución

1. Seleccionar países donde estará disponible
2. Configurar como aplicación gratuita
3. Configurar opciones de distribución

### 5. Subir el AAB

1. Ir a "Producción" > "Crear versión"
2. Subir el archivo `.aab` generado
3. Agregar notas de la versión:
   ```
   Versión inicial de RealTech
   - Catálogo de productos tecnológicos
   - Carrito de compras funcional
   - Escáner de códigos QR
   - Mapa con ubicación de la tienda
   ```

### 6. Revisar y Publicar

1. Revisar toda la información
2. Completar la lista de verificación de publicación
3. Clic en "Enviar para revisión"
4. Esperar la aprobación de Google (generalmente 1-3 días)

## Checklist Final Antes de Publicar

- [ ] Keystore generado y guardado de forma segura
- [ ] Google Maps API Key configurada
- [ ] ApplicationId único configurado
- [ ] Versión de la app configurada correctamente
- [ ] AAB firmado generado
- [ ] Icono de la aplicación creado
- [ ] Capturas de pantalla preparadas
- [ ] Descripción de la app completa
- [ ] Política de privacidad (si es requerida)
- [ ] Pruebas realizadas en dispositivos reales
- [ ] Permisos justificados en la descripción

## Notas Importantes

1. **Política de Privacidad:** Si la app recopila datos del usuario, se requiere una política de privacidad accesible desde una URL.

2. **Permisos:** Asegúrate de justificar todos los permisos solicitados:
   - Cámara: Para escanear códigos QR
   - Ubicación: Para mostrar la ubicación de la tienda en el mapa

3. **Pruebas:** Realiza pruebas exhaustivas antes de publicar:
   - Probar en diferentes dispositivos Android
   - Verificar que el escáner QR funcione correctamente
   - Verificar que el mapa muestre la ubicación
   - Probar el flujo completo de compra

4. **Actualizaciones:** Para actualizar la app:
   - Incrementar `versionCode` en `build.gradle.kts`
   - Actualizar `versionName`
   - Generar nuevo AAB firmado
   - Subir en Play Console

## Soporte

Para más información, consulta:
- [Documentación de Google Play Console](https://support.google.com/googleplay/android-developer)
- [Guía de publicación de Android](https://developer.android.com/distribute/googleplay/start)


