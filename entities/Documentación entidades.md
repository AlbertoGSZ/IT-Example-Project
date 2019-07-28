# IT-Example-Proyect || Documentación entidades



### **Relaciones**

Se observa una sola entidad, denominada en adelante ¨Person¨, de relación reflexiva, pues obedece a una estructura
de arbol similar al genealógico, en el cual de una figura parten varias en estructura ramificada.
Cada persona puede tener un jefe o no así como una lista de personas bajo su mando (si la hay).

### **Información de entidad**

- Nombre: Cadena de caracteres.
- Apellidos: Cadena de caracteres.
- Fecha de nacimiento: Objeto Date (). Requisito fecha anterior al dia que transcurre (límite a valorar).
- Edad: Número entero no menor que cero, actualizado en base a la fecha de nacimiento (seguramente innecesario).
- Residencia: Cadena de caracteres.  
- Nacionalidad: ENUM.
- Sexo: ENUM.
- DNI: Cadena de caracteres.
- Alias: Cadena de caracteres.
- Historial delictivo: Lista MAP de fechas (objetos DATE) y documentos PDF. NO VACÍO.
- Rango: ENUM.
- Jefe: Objeto Person.
- Subordinados: Lista de objetos Person.

Por lógica solo he querido designar como necesaria la presencia de un delito para crear un objeto Person dado que en muchos casos no ya faltarán datos, sino que solo tendrémos un delito que ha llamado la atención y necesidad de crear una ficha delictiva, aun sin lograr saber el sexo siquiera del delincuente. 

Por el mismo motivo no he querido ahondar en ciertos aspectos como la configuración estructural del DNI (distinto en cada país caso de haberlo), la residencia (a veces se puede tener una localización exacta, unas coordenadas de un sitio sin nombre o un paradero general...), luego si por norma carezco de detalles que me permitan configurar la creación del objeto, prefiero pecar de cauto y no delimitar apoyado por suposiciones personales.
