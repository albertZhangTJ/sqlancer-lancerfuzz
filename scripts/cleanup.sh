#1/bin/bash
echo "If you need commands to clean up stuff after stopping"
echo "Add it to scripts/cleanup.sh"

docker stop postgres-dev
docker rm postgres-dev