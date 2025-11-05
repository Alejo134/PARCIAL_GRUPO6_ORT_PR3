1)Utilizamos una arquitectura Model-View-ViewModel, con base de datos local(Room) para la persistencia de datos. 
Algo bien moderno que se ajusta a lo visto en clase. 

2)Si, tuvimos objetos stateless para manejar componentes. Por ejemplo: ButtonLog, FinancialInfoCard, etc. Que no almacenaban datos ni gestionaban estados por si mismos.
También tuvimos stateful con los que gestionamos estados internos y almacenamos valores que podían cambiar.
Por ejemplo: LoginScreen, PasswordTextField(gestiona su estado de visibilidad), etc.
La eleccion se define en dos reglas generales y son:

-Si es un componente que se va a reutilizar en muchos lugares es stateless.

-Si es una pantalla que coordina varios componentes es stateful para gestionar el estado de logica de la misma.

3)Sin dudas una de las mejoras que le hariamos es implementar firebase.

4)El manejo de errores actual en el código se reparte en las tres capas (UI, ViewModel, Repositorio)
La estrategia más adecuada sería Centralizar el Mapeo de Errores en la Capa de Repositorio usando Clases Selladas (Sealed Classes)
El objetivo es "traducir" los errores técnicos (como IOException o HTTP 404) a errores que la aplicación entienda (como "Sin Red" o "Usuario no encontrado")

5)La estrategia que utilizamos fue la vista en clases. Con un archivo strings.xml que almacene los recursos.
En caso de necesitar otros idiomas se sigue la misma metodología.
