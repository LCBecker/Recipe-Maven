# Recipe-Maven

![Landing Page](https://raw.githubusercontent.com/LCBecker/Recipe-Maven-UI/master/app/images/LandingPage.png)

To run this application, you will need to have MongoDB installed and running (mongod) before executing the jar file.

To run:

1. cd Recipe-Maven
2. java -jar target/recipemaven-0.0.1-SNAPSHOT.jar server

The UI is in a separate [repo](https://github.com/LCBecker/Recipe-Maven-UI). To run both the backend and front end locally, you will need nginx with the following config:

```
    server {
    listen       80;
    server_name  localhost;

    #charset koi8-r;

    #access_log  logs/host.access.log  main;
    location /recipes {
        proxy_pass http://127.0.0.1:8080;
    }

    location / {
        proxy_pass http://127.0.0.1:9000;
    }
```
