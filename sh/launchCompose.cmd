call ant war
docker build -t chess-game .
docker container stop mysql-db
docker container rm mysql-db
docker container stop chess
docker container rm chess
docker-compose up -d
docker exec -i mysql-db sh -c "exec mysql -uroot -p$MYSQL_ROOT_PASSWORD" < ./sql/chess_db.sql