# Instrucciones para Configurar el Formulario de Google

## Paso 1: Crear el Formulario de Google

1. Ve a [Google Forms](https://forms.google.com)
2. Clic en "Formulario en blanco"
3. Configura el formulario con los siguientes campos:
   - **Nombre completo** (Texto corto, obligatorio)
   - **Email** (Texto corto, obligatorio, validación de email)
   - **Teléfono** (Texto corto, obligatorio)
   - **Dirección** (Texto largo, obligatorio)
   - **Fecha de nacimiento** (Fecha, opcional)
   - **Género** (Opción múltiple, opcional)
   - Cualquier otro campo que necesites

4. Configura el formulario:
   - Ve a la pestaña "Configuración" (⚙️)
   - Activa "Recopilar direcciones de correo electrónico"
   - Activa "Enviar una copia de las respuestas por correo electrónico" (opcional)

## Paso 2: Obtener el Enlace del Formulario

1. Clic en el botón "Enviar" (arriba a la derecha)
2. Selecciona el ícono de "Enlace" (🔗)
3. Clic en "Acortar URL" si lo deseas
4. Copia el enlace (ejemplo: `https://forms.gle/XXXXXXXXXX`)

## Paso 3: Configurar el Enlace en la Aplicación

1. Abre el archivo `QRGeneratorActivity.kt`
2. Busca la línea:
   ```kotlin
   private val formularioGoogleUrl = "https://forms.gle/TU_FORMULARIO_AQUI"
   ```
3. Reemplaza `"https://forms.gle/TU_FORMULARIO_AQUI"` con tu enlace real:
   ```kotlin
   private val formularioGoogleUrl = "https://forms.gle/XXXXXXXXXX"
   ```

## Paso 4: Probar la Funcionalidad

1. Compila y ejecuta la aplicación
2. Inicia sesión en la app
3. Ve a "Ver QR Formulario"
4. Verifica que:
   - Se genera correctamente el código QR
   - Al hacer clic en "Abrir Formulario de Google" se abre el formulario en el navegador
   - Al escanear el QR con otra app, también se abre el formulario

## Notas Importantes

- El formulario de Google debe estar configurado como **público** para que cualquiera pueda acceder
- Si quieres restringir el acceso, puedes configurar el formulario para que solo usuarios con cuenta de Google puedan acceder
- El código QR se genera automáticamente con la URL del formulario
- Los usuarios pueden escanear el QR o hacer clic en el botón para acceder al formulario

## Ejemplo de URL de Formulario de Google

```
https://forms.gle/1a2b3c4d5e6f7g8h9
```

## Personalización Adicional

Si quieres personalizar más el formulario:
- Agrega más campos según tus necesidades
- Configura validaciones personalizadas
- Agrega imágenes o videos
- Configura mensajes de confirmación personalizados

