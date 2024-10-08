# Currency Converter Application

## Descripción

Esta es una aplicación de conversión de divisas que permite a los usuarios consultar tasas de cambio y realizar conversiones entre diferentes monedas. La aplicación utiliza una interfaz gráfica de usuario (GUI) construida con Java Swing y se conecta a una API externa para obtener tasas de cambio actualizadas.

## Funcionalidades

- Consultar tasas de cambio entre diferentes pares de divisas.
- Realizar conversiones de divisas.
- Filtrar tasas de cambio de una moneda específica.
- Guardar registros de conversiones y filtros en archivos JSON.

## Requisitos

Para ejecutar esta aplicación, necesitas tener instalados los siguientes componentes:

- JDK 11 o superior
- Maven (opcional, para gestionar dependencias)

## Instalación

 ## Clona este repositorio en tu máquina local:

   git clone https://github.com/jose2806/CurrencyConverter.git
   
## Uso
- Al iniciar la aplicación, se mostrará una ventana con botones para cada par de divisas disponible.
- Haz clic en un par de divisas para realizar una conversión.
- Utiliza el botón "Filtrar" para obtener tasas de cambio para una moneda específica.
- Los resultados de las conversiones y los filtros se guardarán en archivos JSON.

## Archivos Generados
- **conversiones.json**: Contiene registros de todas las conversiones realizadas.
- **filtraciones.json**: Contiene registros de las tasas de cambio filtradas por moneda.

## Tecnologías Usadas
- Java
- Swing (para la GUI)
- Gson (para el manejo de JSON)
- API de tasas de cambio
