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

##version 1.0

### Comentarios y Explicación

- **`LaunchSimulator`**: 
- Esta clase inicia la aplicación. 
- Utiliza `SwingUtilities.invokeLater` para asegurarse de que la GUI se inicie en el hilo de despacho de 
- eventos de Swing, que es el hilo apropiado para manejar componentes de la interfaz de usuario.

- **`CountdownUI`**: 
- Esta clase gestiona la interfaz gráfica de usuario. 
- Incluye botones para iniciar y cancelar la cuenta regresiva, un campo de texto para ingresar los segundos, 
- y una barra de progreso y un reloj MM:SS para mostrar el tiempo restante. 
- Los métodos `startCountdown` y `cancelCountdown` manejan las acciones correspondientes a cada botón.

- **`CountdownTask`**: 
- Extiende la clase `Thread` y se encarga de la lógica de la cuenta regresiva. 
- Actualiza la barra de progreso y maneja la interrupción de la cuenta regresiva si el usuario decide cancelarla.
- Se formatea el tiempo restante en el formato MM:SS y se muestra en la etiqueta de texto 
- 'countdownLabel' de la interfaz de usuario.
- Se utiliza `SwingUtilities.invokeLater` para asegurarse de que la actualización de la interfaz de usuario se 
- realice en el hilo de despacho de eventos de Swing, lo cual es esencial para mantener la estabilidad y el 
- rendimiento de la aplicación

## version 1.1
### cambios en LaunchSimulator
Se incorpora un registo de eventos más importantes en el archivo 'launch.log' en la carpeta 'logs' del proyecto.
Se ha integrado java.util.logging para registrar los eventos en el archivo de log.

### cambios en CountdownUI
Se ha añadido soporte para java.util.logging a través de un objeto Logger.
Se ha Modificado el constructor para aceptar un objeto Logger.
Se ha Modificado la función startCountdown para incluir logging.

### cambios en CountdownTask
Se ha añadido soporte para java.util.logging a través de un objeto Logger.
Se ha Modificado el constructor para aceptar un objeto Logger.
Incorporado el logging de eventos utilizando java.util.logging.Logger. 
Se ha añadido el objeto Logger al constructor y utilizado en el método run para registrar eventos.