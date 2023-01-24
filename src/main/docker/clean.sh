docker-compose down
docker rmi -f $(docker images ouiuo/timetable -a -q)
