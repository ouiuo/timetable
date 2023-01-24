docker-compose down
cd /home/vazya/Downloads/timetable-bot/src/main/docker
sh /home/vazya/Downloads/timetable-bot/src/main/docker/updateimage.sh

cd /home/vazya/Downloads/timetable/src/main/docker
sh /home/vazya/Downloads/timetable/src/main/docker/updateimage.sh
docker-compose up