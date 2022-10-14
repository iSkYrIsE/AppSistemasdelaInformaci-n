export SERVICE_BACKEND_MP_REST_URL="http://localhost:8080"
java -jar /opt/payara/micro/payara-micro-5.2020.4.jar
 --deploy ./target/frontend-1.0.war --contextroot / --port 80
