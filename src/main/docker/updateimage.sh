sh clean.sh
mvn -f ../../.. clean package -DskipTests=true
cp /home/vazya/Downloads/timetable/target/timetable-0.0.1-SNAPSHOT.jar ./
docker tag timetable ouiuo/timetable:0.10
docker build -t timetable .
