mvn clean compile package -Dp.type=jar
##Obfuscate the java jar
#java -jar $PROGUARD_HOME/lib/proguard.jar @proguard/chess-proguard.pro
mvn package -Dp.type=war
##Deploy to jfrog artifacts
#mvn deploy -Dusername=sebastianrevuelta@gmail.com -Dpassword=$1
#If needed stop and run mysql container
#docker container stop mysql-db
#docker rm -f mysql-db
#docker volume prune --force
#docker volume create mysql-db-data
#docker run -d -p 8306:3306 --name mysql-db -e MYSQL_ROOT_PASSWORD=platas76 --mount src=mysql-db-data,dst=/var/lib/mysql mysql
#docker container stop chess
#docker container rm chess
docker build -t chess-game .
docker-compose up -d
