docker build --tag backend .
docker container run --name backend \
                     --rm \
                      -p 8080:8080 \
                     --env DATABASE_NAME="jdbc/oracleDockerPayara" \
                     --env DATABASE_DRIVER="oracle.jdbc.pool.OracleDataSource" \
                     --env DATABASE_TYPE="oracle" \
                     --env DATABASE_URL="thin:@oracle11gXE:1521:xe" \
                     --env DATABASE_USER="rmiguelre" \
                     --env DATABASE_PASSWORD="veBnEtrG" \
                     --network oracle-network \
                     backend