# **Weekly Meals App - [Tomás Vazquez](https://www.linkwdin.com/in/tomas-vazquez)**
***
## Información General

Aplicación, para organizar tus comidas de la semana, desarrollada sobre Android nativo:
- en Kotlin con Jetpack Compose:
- Clean Architecture y principios SOLID.
- con POO.
- con el concepto MVVM.
- y utilizando:
  - Patrones de diseño.
  - Corrutinas.
  - ROOM para la BD local.
  - Libreria Accompanist: Pager y Ui Controller.
***
## Detalle de la Aplicación

Comienza con un **Main Screen** donde se observa el view pager por día de la semana.
Cada pager tiene dos tarjetas, una para el almuerzo y otra para la cena, y al hacer pinchar en ellas se da vuelta para mostrar la comida.

Al ir al lapiz nos lleva a la pantalla de edición ***"Edit"*** donde podremos ingresar las comidas de cada día y guardar en una base de datos local.

Al pinchar en compartir podremos enviar un deeplink a quien querramos para que pueda guardar en su dispositivo las comidas ingresadas por nosotros.
