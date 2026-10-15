# Patrón Modelo-Vista-Controlador con JavaFX

Ejemplos para Desarrollo de Interfaces (2º DAM). Java 17+, JavaFX 21, sin frameworks.

```
mvc-javafx/          → ejemplos para el alumnado
soluciones-javafx/   → soluciones de las tareas (proyecto aparte, para no repartirlo)
```

## Ejemplos (`mvc-javafx`)

Cada paquete tiene un `Main` (el que se ejecuta) y un `App` (la aplicación JavaFX, que crea y conecta las piezas).

| Paquete | Qué enseña |
|---|---|
| `mvc.contador` | MVC básico: el controlador cambia el modelo y luego refresca la vista |
| `mvc.contadorfxml` | El mismo contador con la vista en **FXML** y un controlador con `@FXML`. Reutiliza el modelo de `mvc.contador` sin cambiar ni una línea |
| `mvc.login` | Regla de negocio en el modelo (`UsuarioService.validar`). Usuarios: `admin/1234`, `alumno/dam2`, `profe/javafx` |
| `mvc.compra` | Patrón Observer con `ObservableList` y `bind()`: dos vistas que se actualizan solas |
| `mvc.tableview` | `TableView` conectada a una `ObservableList`: la tabla se actualiza sola al cambiar el modelo |
| `mvc.calculadoraespagueti` | Todo en una clase, a propósito. Punto de partida para refactorizar (instrucciones en la cabecera de `CalculadoraEspagueti`) |

### Reglas que cumplen todos los ejemplos

1. El **modelo** nunca importa `javafx.scene` ni `javafx.stage` (nada gráfico).
   Sí puede usar `javafx.beans` y `javafx.collections` (propiedades y listas observables), porque no
   dibujan nada y funcionan sin ventanas: el modelo se sigue probando con JUnit.
2. La **vista** no tiene lógica: pinta datos y ofrece métodos `alPulsarX(Runnable)` para que el controlador se enganche.
3. Todo arranca con `Application.launch(...)`: JavaFX llama a `App.start()` desde su hilo de interfaz.

> **Para debatir en clase.** En `mvc.contadorfxml` no existe una clase `VistaContador`: la vista es el
> fichero `.fxml`, y el controlador recibe los nodos (`@FXML Label lblValor`) y los modifica él mismo.
> Es la forma habitual de trabajar con JavaFX, pero mezcla un poco controlador y vista.
> ¿Qué se gana y qué se pierde respecto a `mvc.contador`?

## Soluciones (`soluciones-javafx`)

| Paquete | Tarea |
|---|---|
| `soluciones.conversor` | Conversor de temperatura ºC ↔ ºF en MVC |
| `soluciones.banco` | Cuenta bancaria con dos vistas (saldo y movimientos). El saldo se pinta en rojo por debajo de 100 €: *qué* es un saldo bajo lo decide el modelo (`saldoBajoProperty`) y el color lo pone la vista. 4 tests JUnit 5 (`CuentaBancariaTest`) |
| `soluciones.agenda` | Agenda de contactos con `TableView`: alta, baja, modificación y búsqueda con `FilteredList` (el filtro se mantiene solo tras cada cambio). 3 tests JUnit 5 (`AgendaTest`) |

## Cómo ejecutar

JavaFX no viene con el JDK desde Java 11: Maven lo descarga la primera vez (hace falta conexión a Internet).

**IDE (IntelliJ, NetBeans, Eclipse, VS Code):** abrir la carpeta `mvc-javafx` o `soluciones-javafx` como
proyecto Maven y ejecutar el **`Main`** del paquete que se quiera (no el `App`).
Si se ejecuta el `App`, Java se niega a arrancar con `JavaFX runtime components are missing`.
Con un JDK en español el mensaje sale mal traducido (*«el método principal debe devolver un valor
del tipo void en la clase {0}»*), pero es el mismo problema.

**Maven:**

```powershell
cd mvc-javafx
mvn javafx:run                            # arranca mvc.contador
mvn javafx:run "-Dmain=mvc.compra.Main"   # arranca otro ejemplo

cd ..\soluciones-javafx
mvn test                                  # ejecuta los tests de JUnit 5
mvn javafx:run "-Dmain=soluciones.banco.Main"
```

> En PowerShell las comillas de `"-Dmain=..."` son obligatorias: sin ellas, PowerShell parte el
> argumento por los puntos y Maven no encuentra la clase. En bash o en `cmd` no hacen falta.

> Al ejecutar desde el IDE puede salir el aviso
> `Unsupported JavaFX configuration: classes were loaded from 'unnamed module'`.
> Es inofensivo: aparece porque los ejemplos no usan `module-info.java`, para que sean más sencillos.
