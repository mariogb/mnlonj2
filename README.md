# mnlonj2
Basic JWT auth 


# Build and then Run postgres 

cd docks/postgresql

docker build -t eg_postgresql .

docker run -v ./db1:/var/lib/postgresql -P --name pg_test eg_postgresql


## Native

sdk install java 19.2.1-grl
$ sdk use java 19.2.1-grl
The native-image tool was extracted from the base GraalVM distribution. Currently it is available as an early adopter plugin. To install it, run:

Installing native-image tool
$ gu install native-image 
You only need to do that the first time to install the native-image tool.
Building Graal native image
$ sdk use java 19.2.1-grl
$ ./gradlew assemble
$ native-image --no-server -cp build/libs/complete-*-all.jar 




