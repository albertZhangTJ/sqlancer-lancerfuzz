#!/bin/bash
echo "If you need commands to spin-up your DB instance before start"
echo "Add it to scripts/db_setup.sh"

sudo docker run -d \
  --name postgres-dev \
  -e POSTGRES_USER=sqlancer \
  -e POSTGRES_PASSWORD=sqlancer \
  -p 5432:5432 \
  postgres:latest