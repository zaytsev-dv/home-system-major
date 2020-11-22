# This project was conceived solely for educational purposes and fun :) 

## Architecture:
![image](https://github.com/zaytsev-dv/home-system-major/blob/dev/web/src/main/resources/home-system-diagram.jpg)


## Stack:
* Java 8
* Spring
* Lombok
* Jpa
* Hibernate
* Hibernate validator
* Jfrog
* Postgres
* Liquibase
* ELK
* Telegram bot library (https://github.com/rubenlagus/TelegramBots/tree/master/telegrambots-spring-boot-starter)
* Jaeger
* Prometheus
* Grafana
* Nginx
* Docker
* Github actions



## How to start
1. Edit file /.env (```SMTP_USER, SMTP_PASSWORD, TELEGRAM_BOT_TOKEN```)
 
2. If you want build from docker hub image uncomment: ``zaytsevdv/home-system-major:dev-latest`` (needed image see on docker hub) 
2. ```docker-compose up (-d)```

3. Telegram bot you will find at: [t.me/my_home_system_bot](t.me/my_home_system_bot)

4. Docker images available at https://hub.docker.com/repository/docker/zaytsevdv/home-system-major 

swagger documentation will be available at the link: ``localhost/api``

<details><summary><b>if you want use custom host</b></summary>

1. Set desired host to etc/hosts
2. Edit file /nginx/conf.d/app.conf

    ```sh
    server {
            listen 80 default_server;
            server_name your_host;
    ```
