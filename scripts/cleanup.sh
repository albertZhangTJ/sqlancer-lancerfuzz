#1/bin/bash
echo "If you need commands to clean up stuff after stopping"
echo "Add it to scripts/cleanup.sh"

sudo docker stop postgres-dev
sudo docker rm postgres-dev