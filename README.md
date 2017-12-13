# RSO: Orders microservice

## Prerequisites

```bash
docker run -d --name rso-orders -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=order -p 5433:5432 postgres:latest
```

## Run application in Docker

```bash
docker run -p 8081:8081 jmezna/rso-orders
```

## Metrics

[Prometheus Operator](https://coreos.com/operators/prometheus/docs/latest/user-guides/getting-started.html)