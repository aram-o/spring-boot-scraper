#!/bin/sh
echo ************************************
echo Starting the mongo setup
echo ************************************

sleep 10 | echo Sleeping 10

mongo --host mongonode:27017 --quiet --eval "db.isMaster()['primary']" dbSetup.js
