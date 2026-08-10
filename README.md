# R & R Accountancy Services

Spring Boot + PostgreSQL + Thymeleaf + Bootstrap web application.

## Start everything (one command)

```
docker compose up --build
```

This builds the app image, starts PostgreSQL (`DataEngineering` DB, persistent
volume, healthcheck) and the Spring Boot app (runs Flyway migrations on boot),
then serves the site at:

**http://localhost:7005**

(App listens on port `7000` inside its container; Postgres is reachable on the
host at `localhost:5433` if you need a DB client.)

## Default Super Administrator login

- Username: `admin`
- Password: `Basal123$`

## Stop

```
docker compose down
```

Add `-v` to also drop the Postgres data volume.
