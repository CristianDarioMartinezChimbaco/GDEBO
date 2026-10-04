# Construcción de prompts para ChatGPT

El softaware GESTION DE VENTAS DON EDGAR tiene como objetivo ayudar al ala gestion de cuentas por cobrar y de sus productos que el dueño, don edgar, vende en su tienda,

Actualmente, se tienen las siguiente implementacoion de Java  (openjdk 25.0.4.1 2026-08-18 OpenJDK Runtime Environment (build 25.0.4.1+1-1-24.04.4-Ubuntu) OpenJDK 64-Bit Server VM (build 25.0.4.1+1-1-24.04.4-Ubuntu, mixed mode, sharing) javac 25.0.4.1) java fx y maven (Maven home: /usr/share/maven Java version: 25.0.4.1, vendor: Ubuntu, runtime: /usr/lib/jvm/java-25-openjdk-amd64 Default locale: es_CO, platform encoding: UTF-8 OS name: "linux", version: "7.0.0-34-generic", arch: "amd64", family: "unix"):

├── database
│   └── gestion_productos_ventas.db
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── gestion
│   │   │           ├── Aplicacion.java
│   │   │           ├── controller
│   │   │           │   ├── categoria
│   │   │           │   │   └── CategoriaControlador.java
│   │   │           │   ├── lote
│   │   │           │   │   ├── LoteControlador.java
│   │   │           │   │   └── LoteFormularioControlador.java
│   │   │           │   ├── MenuLateralControlador.java
│   │   │           │   └── producto
│   │   │           │       ├── ProductoControlador.java
│   │   │           │       └── ProductoFormularioControlador.java
│   │   │           ├── model
│   │   │           │   ├── Categoria.java
│   │   │           │   ├── Cliente.java
│   │   │           │   ├── Lote.java
│   │   │           │   ├── MotivosMovimientoInventario.java
│   │   │           │   ├── Persona.java
│   │   │           │   ├── Producto.java
│   │   │           │   ├── UnidadesMedida.java
│   │   │           │   ├── Usuario.java
│   │   │           │   └── Venta.java
│   │   │           ├── repository
│   │   │           │   ├── CategoriaRepositorio.java
│   │   │           │   ├── ConexionBaseDatos.java
│   │   │           │   ├── LoteRepositorio.java
│   │   │           │   └── ProductoRepositorio.java
│   │   │           └── service
│   │   │               ├── LoteServicio.java
│   │   │               └── ProductoServicio.java
│   │   └── resources
│   │       ├── com
│   │       │   └── gestion
│   │       │       └── view
│   │       │           ├── Acceso.fxml
│   │       │           ├── css
│   │       │           │   └── estilos.css
│   │       │           ├── lote
│   │       │           │   ├── LoteAgregar.fxml
│   │       │           │   ├── LoteEditar.fxml
│   │       │           │   └── Lote.fxml
│   │       │           ├── MenuLateral.fxml
│   │       │           └── producto
│   │       │               ├── Categorias.fxml
│   │       │               ├── ProductoAgregar.fxml
│   │       │               ├── ProductoEditar.fxml
│   │       │               └── Producto.fxml
│   │       └── images
│   │           └── lpg_logo.png
│   └── test
│       └── java
│           └── com
│               └── gestion
│                   └── AppTest.java
El objetivo es que construyas la logica para historico de lotes, considerando lo siguente: 
La clase Lote contiene los siguientes atributos: id, producto, cantidad, fechaIngreso, fechaVencimiento, precioUnitario, estado.