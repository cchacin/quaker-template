#!/usr/bin/env bash

export ACCESS_TOKEN=$(
  curl --insecure -X POST http://localhost:9090/auth/realms/quarkus/protocol/openid-connect/token \
    --user backend-service:secret \
    -H 'content-type: application/x-www-form-urlencoded' \
    -d 'username=alice&password=alice&grant_type=password' | jq --raw-output '.access_token'
)

echo $ACCESS_TOKEN
