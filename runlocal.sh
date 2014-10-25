#!/bin/bash

function tryStep {
    $1

    RESULT=$?
    if [ $RESULT != 0 ];
    then
        echo "Failed to complete previous step. Exit code: '$RESULT'"
        exit 1
    fi
}

tryStep "mvn clean install"
tryStep "foreman start"
