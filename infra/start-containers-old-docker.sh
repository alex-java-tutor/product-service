#!/bin/bash

echo -e "\n*** Starting Docker Containers ***\n"

docker volume rm infra_db-data

docker-compose up -d postgres


