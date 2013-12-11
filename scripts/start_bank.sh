#!/bin/sh
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=5005,suspend=n -cp .:bank.jar bank.Bank 127.0.0.1 9876 bank -v
