# Taxonomia_Bloom

# Sistema de Evaluación con Taxonomía de Bloom

Este proyecto es un sistema de evaluación interactivo desarrollado en Java, diseñado para administrar exámenes y evaluar el rendimiento de los usuarios, con un enfoque en la clasificación de preguntas según la Taxonomía de Bloom.
## Tabla de Contenidos

- [Funcionalidades](#funcionalidades)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Configuración y Ejecución](#configuración-y-ejecución)
  - [Requisitos Previos](#requisitos-previos)
  - [Configuración de la Base de Datos](#configuración-de-la-base-de-datos)
  - [Clonar el Repositorio](#clonar-el-repositorio)
  - [Compilación y Ejecución](#compilación-y-ejecución)
- [Uso](#uso)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)
- [Contacto](#contacto)


## Funcionalidades

El sistema ofrece las siguientes características principales:

* **Administración de Exámenes:** Permite seleccionar diferentes exámenes cargados desde una base de datos.
* **Presentación de Preguntas:** Muestra preguntas de opción múltiple y verdadero/falso con sus respectivas opciones de respuesta.
* **Navegación del Examen:** Facilita la navegación entre preguntas (anterior/siguiente).
* **Control de Tiempo:** Implementa un contador de tiempo para el examen.
* **Clasificación por Taxonomía de Bloom:** Muestra un desglose de las preguntas por nivel de la Taxonomía de Bloom (Conocimiento, Comprensión, Aplicación, Análisis, Síntesis, Evaluación).
* **Revisión de Resultados:** Al finalizar el examen, permite revisar las respuestas, mostrando cuáles fueron correctas e incorrectas.
* **Conexión a Base de Datos:** Se conecta a una base de datos MySQL para gestionar las preguntas y exámenes.

## Tecnologías Utilizadas

* **Lenguaje:** Java
* **Interfaz Gráfica (GUI):** Swing (parte de Java SE)
* **Base de Datos:** MySQL
* **Conectividad a Base de Datos:** JDBC

## Estructura del Proyecto

El proyecto sigue una estructura de diseño MVC (Modelo-Vista-Controlador) para una mejor organización y separación de responsabilidades.

```
  
├── src
│   ├── Controlador
│   │   ├── Conexion.java           // Maneja la conexión a la base de datos.
│   │   ├── Control.java            // Lógica de control para la evaluación del examen.
│   │   ├── ControlI.java           // Lógica de control para la pantalla de inicio del examen.
│   │   └── ControlR.java           // Lógica de control para la pantalla de resultados.
│   ├── Modelo
│   │   ├── Listar.java             // Clases para listar y recuperar datos de la BD (preguntas).
│   │   └── Pregunta.java           // Modelo de datos para una pregunta.
│   ├── Vista
│   │   ├── Evaluacion.java         // Interfaz gráfica para la presentación del examen.
│   │   ├── InicioExamen.java       // Interfaz gráfica para la selección e inicio del examen.
│   │   └── Resultado.java          // Interfaz gráfica para mostrar los resultados del examen.
│   └── taxonomia_bloom
│       └── Taxonomia_Bloom.java    // Clase principal que inicia la aplicación.
└── pom.xml (o equivalente para gestión de dependencias si usas Maven/Gradle)
```
### Descripción de Clases Clave:

* **`Controlador/Conexion.java`**: Establece y gestiona la conexión con la base de datos MySQL. Contiene métodos para abrir y cerrar la conexión.
* **`Controlador/Control.java`**: Orquesta la lógica del examen, mostrando preguntas, gestionando las respuestas del usuario y calculando el tiempo.
* **`Controlador/ControlI.java`**: Maneja la interacción en la pantalla de inicio, incluyendo la selección del examen y la visualización de la taxonomía de Bloom asociada.
* **`Controlador/ControlR.java`**: Procesa y muestra los resultados del examen, destacando las respuestas correctas e incorrectas.
* **`Modelo/Listar.java`**: Contiene métodos para interactuar con la base de datos y obtener la lista de preguntas para un examen específico.
* **`Modelo/Pregunta.java`**: Define la estructura de una pregunta, incluyendo enunciado, opciones de respuesta, respuesta correcta, tiempo y nivel de taxonomía de Bloom.
* **`Vista/Evaluacion.java`**: La ventana principal donde se presenta el examen, mostrando la pregunta actual y las opciones de respuesta.
* **`Vista/InicioExamen.java`**: La ventana de inicio donde el usuario selecciona el examen y ve un resumen de las preguntas.
* **`Vista/Resultado.java`**: La ventana que muestra el resumen de los resultados del examen.
* **`taxonomia_bloom/Taxonomia_Bloom.java`**: El punto de entrada de la aplicación.

## Configuración y Ejecución

Sigue estos pasos para configurar y ejecutar el proyecto en tu máquina local.

### Requisitos Previos

Asegúrate de tener instalado lo siguiente:

* **Java Development Kit (JDK) 8 o superior:** Puedes descargarlo desde el sitio oficial de Oracle o a través de OpenJDK.
* **MySQL Server:** Necesitas una instancia de MySQL en ejecución.
* **MySQL Connector/J:** El controlador JDBC para MySQL. Si usas Maven o Gradle, se gestionará como dependencia. Si no, necesitarás descargar el archivo `.jar` y añadirlo a tu classpath.

### Configuración de la Base de Datos

1.  **Crear la Base de Datos:**
    Crea una base de datos MySQL llamada `mydb`. Puedes hacerlo a través de MySQL Workbench, phpMyAdmin, o la línea de comandos de MySQL:

    ```sql
    CREATE DATABASE mydb;
    USE mydb;
    ```

2.  **Crear la Tabla de Preguntas:**
    Crea una tabla `preguntas` con la siguiente estructura. Asegúrate de incluir campos para todas las propiedades de la clase `Pregunta` (id, enunciado, respuesta_1, respuesta_2, respuesta_3, respuesta_4, respuestaCorrecta, tiempo, tipo, nivel).

    ```sql
    CREATE TABLE preguntas (
        id_pregunta INT PRIMARY KEY AUTO_INCREMENT,
        id_examen INT NOT NULL,
        enunciado TEXT NOT NULL,
        respuesta_1 VARCHAR(255) NOT NULL,
        respuesta_2 VARCHAR(255) NOT NULL,
        respuesta_3 VARCHAR(255),
        respuesta_4 VARCHAR(255),
        respuestaCorrecta INT NOT NULL,
        tiempo INT NOT NULL,
        tipo VARCHAR(50) NOT NULL, -- Ej: "Opción Múltiple", "Verdadero Falso"
        nivel VARCHAR(50) NOT NULL -- Ej: "Conocimiento", "Comprensión", "Aplicación", etc.
    );
    ```

3.  **Insertar Datos de Prueba (Opcional):**
    Puedes insertar algunas preguntas de ejemplo para probar el sistema:

    ```sql
    INSERT INTO preguntas (id_examen, enunciado, respuesta_1, respuesta_2, respuesta_3, respuesta_4, respuestaCorrecta, tiempo, tipo, nivel) VALUES
    (1, '¿Cuál es la capital de Francia?', 'Berlín', 'Madrid', 'París', 'Roma', 3, 1, 'Opción Múltiple', 'Conocimiento'),
    (1, 'El agua hierve a 100°C a nivel del mar.', 'Verdadero', 'Falso', NULL, NULL, 1, 0, 'Verdadero Falso', 'Comprensión'),
    (2, '¿Qué hace el método `getConnection()` en JDBC?', 'Cierra la conexión', 'Establece una conexión con la base de datos', 'Ejecuta una consulta SQL', 'Prepara una sentencia SQL', 2, 2, 'Opción Múltiple', 'Análisis');
    ```

4.  **Configurar Credenciales de la Base de Datos:**
    En el archivo `Controlador/Conexion.java`, asegúrate de que `user` y `password` coincidan con las credenciales de tu usuario de MySQL. Por defecto, están configurados como `root` y `1234`.

    ```java
    // Controlador/Conexion.java
    String user="root"; // Cambia si tu usuario de MySQL es diferente
    String password="1234"; // Cambia si tu contraseña de MySQL es diferente
    String url="jdbc:mysql://localhost:3306/mydb"; // Asegúrate de que el puerto y el nombre de la BD sean correctos
    ```

### Clonar el Repositorio

Abre tu terminal o Git Bash y ejecuta el siguiente comando:

```bash
git clone [https://github.com/tu-usuario/nombre-del-repositorio.git](https://github.com/tu-usuario/nombre-del-repositorio.git)
cd nombre-del-repositorio

Compilación y Ejecución
Desde un IDE (NetBeans, IntelliJ IDEA, Eclipse):

Abre el proyecto en tu IDE preferido.
Asegúrate de que el MySQL Connector/J JAR esté añadido como dependencia de tu proyecto (si no usas un gestor de dependencias como Maven/Gradle, descárgalo y añádelo manualmente a las librerías del proyecto).
Ejecuta la clase Taxonomia_Bloom.java (clic derecho -> Run File/Run As Java Application).
Desde la Línea de Comandos:

Navega a la raíz del proyecto en tu terminal.

Compila los archivos Java:

Bash

javac -cp "ruta/al/mysql-connector-j.jar:src" src/taxonomia_bloom/Taxonomia_Bloom.java src/Controlador/*.java src/Modelo/*.java src/Vista/*.java
Nota: Reemplaza "ruta/al/mysql-connector-j.jar" con la ruta real al archivo JAR del conector MySQL. En Windows, usa ; en lugar de : para separar el classpath.
Ejecuta la aplicación:

Bash

java -cp "ruta/al/mysql-connector-j.jar:src" taxonomia_bloom.Taxonomia_Bloom
Uso
Al iniciar la aplicación, se mostrará la ventana InicioExamen.
Selecciona un examen del combobox. Verás un resumen de las preguntas y los niveles de la Taxonomía de Bloom.
Haz clic en "Iniciar Prueba" para comenzar el examen.
Responde las preguntas seleccionando la opción deseada y navega usando los botones "Anterior" y "Siguiente".
Una vez finalizado el examen (o cuando se acabe el tiempo), se mostrará la ventana Resultado.
En la ventana de resultados, puedes hacer clic en "Revisar Examen" para ver las preguntas con tus respuestas y las respuestas correctas marcadas en verde.
Contribuciones
Las contribuciones son bienvenidas. Si deseas mejorar este proyecto, sigue estos pasos:

Haz un "fork" de este repositorio.
Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
Realiza tus cambios y haz "commit" (git commit -am 'feat: Añadir nueva funcionalidad X').
Sube tus cambios a tu "fork" (git push origin feature/nueva-funcionalidad).
Abre un "Pull Request".
Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
