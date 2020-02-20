#!/bin/sh
docker build . -t mnj1
echo
echo
echo "To run the docker container execute:"
echo "    $ docker run -p 8777:8777 mnj1"
