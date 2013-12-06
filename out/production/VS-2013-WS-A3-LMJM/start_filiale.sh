#!/bin/sh
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=5006,suspend=n  -cp .:filiale.jar filiale.Filiale 127.0.0.1 9876 bank fil -v
