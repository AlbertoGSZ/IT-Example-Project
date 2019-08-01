# IT-Example-Proyect || Documentación entidades



### **Relaciones**

Se observan dos entidadas, denominadas en adelante ¨Person¨ y "CaseReport".

La entidad Person, de relación reflexiva, pues obedece a una estructura
de arbol similar al genealógico, en el cual de una figura parten varias en estructura ramificada.
Cada persona puede tener un jefe o no así como una lista de personas bajo su mando (si la hay).
De igual manera, la entidad ¨Person¨, tendrá una lista de objetos ¨CaseReport¨ que será el historial delictivo.

### **Información de entidad: Person**

- Name: Cadena de caracteres.
- Surname: Cadena de caracteres.
- BirthDate: Objeto Date (). Requisito fecha anterior al dia que transcurre (límite a valorar).
- Age: Número entero no menor que cero, actualizado en base a la fecha de nacimiento (seguramente innecesario).
- Adress: Cadena de caracteres.  
- Nationality: ENUM.
- Sex: ENUM.
- ID: int. Generación aleatoria no repetible.
- Alias: Cadena de caracteres.
- FelonyRecordsDigital: Lista de objetos ¨CaseReport¨.
- Rank: int.
- Chief: Objeto Person.
- Subordinates: Lista de objetos Person.

Por lógica solo he querido designar como necesaria la presencia de un delito para crear un objeto Person dado que en muchos casos no ya faltarán datos, sino que solo tendrémos un delito que ha llamado la atención y necesidad de crear una ficha delictiva, aun sin lograr saber el sexo siquiera del delincuente. 

Por el mismo motivo no he querido ahondar en ciertos aspectos como la configuración estructural del DNI (distinto en cada país caso de haberlo), la residencia (a veces se puede tener una localización exacta, unas coordenadas de un sitio sin nombre o un paradero general...), luego si por norma carezco de detalles que me permitan configurar la creación del objeto, prefiero pecar de cauto y no delimitar apoyado por suposiciones personales.


### **Información de entidad: CaseReport**

- IDCode: int. Generación aleatoria no repetible.
- Date: Objeto Date. Fecha final, momento de creación del informe.
- Mugshot: Objeto Image.
- PersonDetails: Cadena de caracteres. No vacío.  
- EventDescription: Cadena de caracteres. No vacío.
- EvidencePhotos: Lista de objetos Image.
- FelonyRecordPDF: Objeto PDF.
