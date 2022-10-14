docker build --tag frontend .
docker container run --name frontend \
                     --rm \
                      -p 80:8080 \
                     --network oracle-network \
                     --env ES_UPSA_SSI_MPREST_FRONTEND_MODEL_BACKENDSERVICE_MP_REST_URL="http://localhost:8080" \
                     frontend