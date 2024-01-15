docker-compose down

cd /c/Java\ projects/timetable-bot/src/main/docker/
sh /c/Java\ projects/timetable-bot/src/main/docker/updateimage.sh

cd /c/Java\ projects/timetable/src/main/docker/
sh /c/Java\ projects/timetable/src/main/docker/updateimage.sh
docker-compose up