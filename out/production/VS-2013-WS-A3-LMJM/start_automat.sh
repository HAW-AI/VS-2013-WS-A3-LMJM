#!/bin/sh
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=5007,suspend=n -cp .:geldautomat.jar geldautomat.Geldautomat 127.0.0.1 9876 bank-fil -v
