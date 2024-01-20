# Cuenta_Regresiva_Apollo

## Descripción
En el contexto de la ingeniería informática, el manejo eficiente de las operaciones en
segundo plano y la multitarea es crucial, especialmente en situaciones que emulan eventos
críticos en tiempo real, como el lanzamiento de un cohete.
Este ejercicio está inspirado en el histórico lanzamiento del Apollo 11 el 16 de julio de 1969,
el cual llevó al primer hombre a la Luna. Los estudiantes deben crear una simulación de la
cuenta regresiva para el lanzamiento, enfocándose en la sincronización de hilos y
la comunicación en tiempo real con la interfaz de usuario.

Enunciado del Ejercicio:

Desarrolla una aplicación que simule la cuenta regresiva para el lanzamiento del Apollo 11. La interfaz debe permitir al usuario ingresar una cantidad de segundos y mostrar una barra de progreso y una etiqueta de texto que reflejen la cuenta atrás en tiempo real.

1-La cuenta atrás debe iniciarse cuando el usuario pulse un botón.
2-La aplicación debe permitir al usuario cancelar la cuenta atrás en cualquier momento.
3-Al finalizar la cuenta, se debe mostrar un mensaje en una ventana emergente que indique si
el lanzamiento ha sido exitoso o cancelado.

## version 1.0

### Comentarios y Explicación

**`model.LaunchSimulator`**:
Esta clase inicia la aplicación.
Utiliza `SwingUtilities.invokeLater` para asegurarse de que la GUI se inicie en el hilo de despacho de eventos de Swing, que es el hilo apropiado para manejar componentes de la interfaz de usuario.

**`view.CountdownUI`**:
Esta clase gestiona la interfaz gráfica de usuario.
Incluye botones para iniciar y cancelar la cuenta regresiva, un campo de texto para ingresar los segundos, y una barra de progreso y un reloj MM:SS para mostrar el tiempo restante.
Los métodos `startCountdown` y `cancelCountdown` manejan las acciones correspondientes a cada botón.

**`model.CountdownTask`**:
Extiende la clase `Thread` y se encarga de la lógica de la cuenta regresiva.
Actualiza la barra de progreso y maneja la interrupción de la cuenta regresiva si el usuario decide cancelarla.
Se formatea el tiempo restante en el formato MM:SS y se muestra en la etiqueta de texto
'countdownLabel' de la interfaz de usuario.
Se utiliza `SwingUtilities.invokeLater` para asegurarse de que la actualización de la interfaz de usuario se realice en el hilo de despacho de eventos de Swing, lo cual es esencial para mantener la estabilidad y el rendimiento de la aplicación

## version 1.1
### cambios en model.LaunchSimulator
Se incorpora un registo de eventos más importantes en el archivo 'launch.log' en la carpeta 'logs' del proyecto.
Se ha integrado java.util.logging para registrar los eventos en el archivo de log.

### cambios en view.CountdownUI
Se ha añadido soporte para java.util.logging a través de un objeto Logger.
Se ha Modificado el constructor para aceptar un objeto Logger.
Se ha Modificado la función startCountdown para incluir logging.

### cambios en model.CountdownTask
Se ha añadido soporte para java.util.logging a través de un objeto Logger.
Se ha Modificado el constructor para aceptar un objeto Logger.
Incorporado el logging de eventos utilizando java.util.logging.Logger.
Se ha añadido el objeto Logger al constructor y utilizado en el método run para registrar eventos.

## version 2.0
Se amplía la aplicación para que lance dos hilos concurrentes: el primero corresponde a la cuenta atrás, mientras que el segundo monitorea y registra el estado del primero (activo, cancelado, completado), posiblemente simulando un registro de las condiciones del lanzamiento.

### cambios en model.LaunchSimulator
Se ha añadido un segundo hilo para registrar el estado de la cuenta atrás.
Se ha añadido un segundo objeto Logger para registrar los eventos del segundo hilo.
Se ha añadido un segundo objeto model.CountdownTask para ejecutar el segundo hilo.
Se ha añadido un segundo objeto Thread para ejecutar el segundo hilo.

### cambios en model.CountdownTask
Se añadió un enumerado State para manejar los estados de la tarea (ACTIVE, CANCELLED, COMPLETED).
Se añadieron registros (logging) para cada cambio importante en el estado de la tarea (inicio, cancelación, finalización).

### se crea la clase model.MonitorTask
Se crea la clase model.MonitorTask para registrar el estado de la cuenta atrás.
Se crea el constructor de la clase model.MonitorTask.
Se crea el método run de la clase model.MonitorTask.
Se crea el método setState de la clase model.MonitorTask.
Se crea el método getState de la clase model.MonitorTask.
Se crea el método logState de la clase model.MonitorTask.

##Versión 3.0 Reestructuración de directorios src.main y src.test para soporte de múltiples hilos
### creación de package src.main y refactor de las clases
Se crea el package src.main.
Se mueven las clases model.CountdownTask, model.LaunchSimulator y model.MonitorTask al package src.main.
Se crea la clase LaunchPhase.java en el package src.main.

### creación de package src.test
Se crea el package src.test. 
Se crea la clase **LaunchPhaseTest.java** con los TDD.
Se crea la prueba de **Inicio Secuencial de Fases**
Esta prueba verifica la ejecución secuencial de las fases del lanzamiento. Cada fase debe empezar solo después de que la anterior haya terminado. Si todas las fases alcanzan el estado COMPLETED, la prueba pasa.

Se crea la prueba de **Cancelación de Fases**
Esta prueba asegura que si una fase se cancela, no debería iniciarse. Se considera un 'pass' si la fase cancelada no inicia después de una fase completada anteriormente.

Se crea la prueba de **Transición de Estado de Fases**
Esta prueba verifica la correcta transición de estados en una fase. Una fase debe poder pasar de INICIADA a COMPLETADA. La prueba es exitosa si la fase alcanza el estado COMPLETED.

## Versión 4.0 se añade soporte para cuatro hilos pruebas de TDD
Se modifica la estructura del proyecto para adecuarse al estandard de Maven.
Se añade soporte para cuatro hilos.
Se añade soporte para cuatro hilos en model.LaunchSimulator.
Se añade soporte para cuatro hilos en model.CountdownTask.
Se añade soporte para cuatro hilos en model.MonitorTask.
Se añade soporte para cuatro hilos en LaunchPhase.
Se añade soporte para cuatro hilos en LaunchPhaseTest.
Se añade soporte para cuatro hilos en view.CountdownUI.

## Version 4.1 se añaden funcionales de la interfaz gráfica

### version 4.1.1 se añade cohete en la interfaz gráfica
se crea la clase view.RocketAnimationPanel es responsable de la visualización del cohete y su animación.
En view.CountdownUI, se crea una instancia de view.RocketAnimationPanel y se añade al layout.
El método updateCountdown de view.CountdownUI ahora también llama a updatePosition de view.RocketAnimationPanel para actualizar la posición del cohete con cada tick de la cuenta regresiva.
### version 4.1.2 se añade sonido en la interfaz gráfica
Se crea la clase model.SoundPlayer para reproducir sonidos.
Se crea la carpeta resources/sounds y se añade el archivo 'tic-tac-27828.mp3'.
Se crea el método playSound en view.CountdownUI para reproducir el sonido.

## Version 5.0 se refactoriza el codigo para establecer una estructura de modelo vista controlador
### se crea el package controller
Se crea el package controller.
Se crea la clase controller.CountdownController y sus métodos

### se crea el package model
Se crea el package model.
Se crea la clase model.CountdownTask y sus métodos
Se crea la clase model.LaunchSimulator y sus métodos
Se crea la clase model.MonitorTask y sus métodos

### se crea el package view
Se crea el package view.
Se crea la clase view.CountdownUI y sus métodos
Se crea la clase view.RocketAnimationPanel y sus métodos
Se crea la clase view.SoundPlayer y sus métodos

### se crea el package resources
Se crea el package resources.
Se crea la carpeta resources/sounds y se añade el archivo 'tic-tac-27828.mp3'.
Se crea la carpeta resources/images y se añade el archivo 'rocket.png'.

### se crea el package test
Se crea el package test.
Se crea la clase test.LaunchPhaseTest y sus métodos


