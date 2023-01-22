docker rmi ouiuo/timetable:latest
cp /home/vazya/Downloads/timetable/target/timetable-0.0.1-SNAPSHOT.jar ./
docker tag timetable ouiuo/timetable:0.6
docker build -t timetable .
