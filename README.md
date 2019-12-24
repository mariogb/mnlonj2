# mnlonj2
Basic JWT auth 


# Build and then Run postgres 

cd docks/postgresql

docker build -t eg_postgresql .

docker run -v ./db1:/var/lib/postgresql -P --name pg_test eg_postgresql
