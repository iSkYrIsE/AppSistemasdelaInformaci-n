export DATABASE_NAME="jdbc/oracleDockerPayara" 
export DATABASE_DRIVER="oracle.jdbc.pool.OracleDataSource" 
export DATABASE_TYPE="oracle" 
export DATABASE_URL="thin:@localhost:1521:xe"
export DATABASE_USER="rmiguelre"
export DATABASE_PASSWORD="veBnEtrG"
export MP_OPENAPI_SERVERS="http://localhost:8080"  
java -jar /opt/payara/micro/payara-micro-5.2020.4.jar --deploy ./target/backend-1.0.war --contextroot /