## SERVICE PRODUCT

### Descripción
Este microservicio se encarga de la creación de productos.

### Instalación

Para ejecutar este servicio localmente, sigue estos pasos:

1. **Instalar LocalStack:**

   ```bash
   docker pull localstack/localstack
2. **Instalación de AWS CLI**

   Puedes descargar la AWS CLI desde - *[AWS CLI](https://aws.amazon.com/es/cli/)*


3. **Ejecución de Docker Compose**

   El archivo *docker-compose* necesario se encuentra en el directorio resources.
   ```bash
   docker-compose up


4. **Creación de la tabla DynamoDB**

   Validar tener el AWS CLI configurada para apuntar a LocalStack:
   ~~~
   aws --endpoint-url=http://localhost:4566 dynamodb create-table `
    --table-name productos `
    --attribute-definitions AttributeName=ProductosId,AttributeType=S `
    --key-schema AttributeName=ProductosId,KeyType=HASH `
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5
   ~~~
   validar la creación de la tabla:
    ~~~
   aws --endpoint-url=http://localhost:4566 --region us-east-1 dynamodb list-tables
    ~~~

### Ejecución de la aplicación
Para levantar la aplicación:

`mvn spring-boot:run`

### Prueba de funcionalidad

1. **Probar desde Swagger**

   *[swagger-ui](http://localhost:9090/swagger-ui/index.html)*

2. **Colecciones de postman**

