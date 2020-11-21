# This project was conceived solely for educational purposes and fun :) 

## Architecture:
![image](https://github.com/zaytsev-dv/home-system-major/blob/dev/web/src/main/resources/home-system-diagram.jpg)


## Stack:
* Java 8
* Spring
* Lombok
* Hibernate
* Postgres
* ELK
* Telegram bot library (https://github.com/rubenlagus/TelegramBots/tree/master/telegrambots-spring-boot-starter)
* Nginx

## How to start
```docker-compose up (-d)```

swagger documentation will be available at the link: ``localhost/api``

<details><summary><b>if you want use custom host</b></summary>

1. Set desired host to etc/hosts
2. Edit file /nginx/conf.d/app.conf

    ```sh
    server {
            listen 80 default_server;
            server_name your_host;
    ```