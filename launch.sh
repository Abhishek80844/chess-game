## Chess app
#docker stop mysql_db
#docker rm mysql_db
docker run -d -p 3306:3306 --name mysql_db -e MYSQL_ROOT_PASSWORD=q1w2e3r4 --mount src=mysql-db-data,dst=/var/lib/mysql mysql
docker-compose up -d
## Review database content with mycli
## mycli mysql://root@localhost:3306/chess
