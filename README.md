# apifabc

## TEST Práctico del proceso de selección de la empresa ROSHKA ([https://www.roshka.com/](https://www.roshka.com/))
Este proyecto es una API desarrollado en lenguaje JAVA, utilizando Spring, que tiene como objetivo la **apificación** de la página web de ABC Digital ([https://www.abc.com.py/](https://www.abc.com.py/)), específicamente la acción del botón de búsqueda de noticias, que se hace a través de una petición GET a ([https://www.abc.com.py/buscar/](https://www.abc.com.py/buscar/)). 
Como el resultado de esta petición es un HTML, se debe utilizar la técnica de *Web scraping* (es una técnica utilizada mediante programas de software para extraer información de sitios web).

Una vez logremos extraer la información, retornamos los datos en un JSON, según las especificaciones técnicas proveídas por la empresa reclutadora.


# REQUISITOS DEL TEST

#Detalles de la solución
Programar un API RESTful en el lenguaje indicado por el evaluador, que implemente la siguiente consulta:
http://API-IMPLEMENTADO/consulta?q={TEXTO QUE SE BUSCA}
El parámetro debe ser pasado a través de la URL mencionada. Por detrás, el API deberá llamar al web site de ABC para obtener la información que tendrá que ser
devuelta en formato JSON (en este caso la respuesta va a ser una lista de objetos JSON):

	[ { "fecha": "2020-05-20 19:23:05", "enlace": "https://www.abc.com.py/enlace", "enlace_foto": "https://www.abc.com.py/enlace.png", "titulo": "Buenas Noticias 02", "resumen": "Esta es la primera buena noticia del día" }, { "fecha": "2020-05-20 19:23:05", "enlace": "https://www.abc.com.py/enlace", "enlace_foto": "https://www.abc.com.py/enlace.png", "titulo": "Buenas Noticias 02", "resumen": "Esta es la segunda buena noticia del día" } ]

Cada objeto JSON representa una noticia devuelta. Este objeto tiene que tener las siguientes propiedades:
1. fecha: fecha/hora de la noticia en formato ISO-8601
2. enlace: URL de enlace a la noticia
3. enlace_foto: URL a la foto de la noticia
4. titulo: título de la noticia
5. resumen: resumen de la noticia
6. contenido_foto: contenido de la foto en base64 (solo para el BONO)
7. content_type_foto: content_type de la foto (solo para el BONO)
El STATUS HTTP de retorno debe ser 200 en caso de éxito.

# Valores de retorno
* Si no se encuentra ninguna noticia hay que retornar un error 404:

	{ "codigo": "g267", "error": "No se encuentran noticias para el texto: {TEXTO DE BÚSQUEDA}" }

* Si el query string es inválido (ejemplo, no tiene el valor para la variable q, o dicho valor es vacío) hay que retornar un error 400:

	{ "codigo": "g268", "error": "Parámetros inválidos" }

* Si hay un error interno, hay que tratarlo y retornar un error 500 :

	{ "codigo": "g100", "error": "Error interno del servidor" }


#BONUS
Como esfuerzo extra si puede implementar lo siguiente:

### Seguridad a través de un API KEY
Pedir a través de un encabezado HTTP un API KEY que se valide con una base de datos, para ver si está habilitado a realizar la operación solicitada, y procesar la petición (o no) de acuerdo a esto.

* Si no está habilitado el API Key para operar, retornar un código HTTP 403: 

	{ "codigo": "g103", "error": "No autorizado" }
 	
###Usar el header Accept para distintos tipos de resultado
* En concreto, implementar el Accept para:

	-application/xml (devolver el resultado en XML)
	-application/json (devolver el resultado en JSON)
	-text/plain (devolver el resultado en TEXTO PLANO)
	-text/html (devolver el resultado en HTML simple)

###Firma con un HMAC-SHA256 el API KEY
- Firmar digitalmente el API KEY con una clave

####Contenido de la foto en formato base64
Si la búsqueda se realiza con un parámetro en la consulta que sea f=true , entonces, el API deberá devolver el atributo extra en el objeto que sea la foto codificada en base64, junto con su Content-Type correspondiente.

###Documentación en SWAGGER
Documentar el API en SWAGGER para presentarlo al usuario.

# 
# 
#------------------------------------------------------------------------------------------------------------

# 

# Pasos a Seguir para probar la aplicacion:
* Descargar el codigo de este repositorio.
* Levantar en un entorno de desarrollo con Java 11
* Una vez compilado, ejecutar el proyecto
* El proyecto se levanta en el puurto 8080 y en el contexto /apifabc

## Probando la API

**Autentitación**
* Esta API necesita autenticación del parte del usuario, los datos de acceso son:
		- Tipo de Autenticación: Basic Header
		- Username : userAbc
		- Password: passAbc
		- EncryptedPass: **ec149017c69421bfc3753b8b67a50f8d28a7c3e1f0e3125c34ee81a7386baa30**
		
* Se deberá de utilizar la contraseña cifrada (Ya que por seguridad, la contraseña debe de viajar cifrada por HTTP)


# Peticiones de prueba
### Buscamos el Texto 'Paraguay' en las noticias (sin solicitar las imágenes en Base64)

- Link: [http://localhost:8080/apifabc/api/consulta/?q=Paraguay](http://localhost:8080/apifabc/api/consulta/?q=Paraguay)

### Buscamos el Texto 'Paraguay' en las noticias (solicitando las imágenes en Base64)

- Link: [http://localhost:8080/apifabc/api/consulta/?q=Paraguay&f=true](http://localhost:8080/apifabc/api/consulta/?q=Paraguay&f=true)


# Observaciones
* Las peticiones deben hacerse por método GET
* La autenticación utilizada es Basic Auth del Header de la petición (Username, Password)
* La aplicación utiliza una autenticación por Base de Datos H2
* Variar los Accepts del Header de la petición entre (**application/xml** | **application/json** | **text/plain** | **text/html**), para obtener distintos formatos de resultados
* Tecnologías utilizadas: **Java 11**, **Spring Boot**, **Spring Security**, **Lombook**, **JPA**, **Jsoup**, **Gson**, **Playwright**, **Commons io**, **H2 Database**, **Swagger**.
* Desarrrollado con el IDE STS 4
* Link de la documentación de SWAGGER: [http://localhost:8080/apifabc/swagger-ui/index.html#/](http://localhost:8080/apifabc/swagger-ui/index.html#/)




# 
# 
# 
* Desarrollador por **José Acosta** [BIO SOluciones Tecnológicas](http://bio.com.py/)

