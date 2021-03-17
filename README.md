# Quaker Template

This is an opinionated [Quarkus][1] template including a set of extensions, dependencies and example code to have ready to production application in minutes.

## What is in there?

- [JPA][2] entity extended with [Panache][3] to perform CRUD operations against a database.
- [Flyway][4] migrations ready to use to create the required database schema.
- [OpenAPI][5] spec file file to generate the API contract from the spec using the contract first approach.
- [JAX-RS][6] endpoint to expose data obtained from the database.
- [MapStruct][7] mapper to use code generation to convert the JPA entities to the generated OpenAPI models

### docker-compose configuration

`docker-compose.yml` file including all the containers that you need in order to have Database, OpenTracing collector, OpenTracing UI, API gateway, Metrics collector, Metrics visualization:

![context](/docs/diagrams/context.png)

Create the docker containter for the application with:

```sh
$ mvn package
```

Run the application stack with:

```sh
$ docker-compose up
```

#### Traefik2

> The world’s most popular cloud-native application proxy that helps developers and operations teams build, deploy and run modern microservices applications quickly and easily.

Traefik will create entrypoints for all the containers in the `docker-compose.yml` file using the `http://${container-name}.localhost` notation.

Go to http://traefik.localhost

![traefik](/docs/images/traefik.png)

#### Portainer

> Portainer is the definitive open source container management tool for Kubernetes, Docker, Docker Swarm and Azure ACI. It allows anyone to deploy and manage containers without the need to write code.

Portainer is a web interface to manage docker resources, this is not required if you manage how to use docker commands but it is really convenient and saves a lot of time when dealing with multiple containers

Go to http://portainer.localhost

![portainer](/docs/images/portainer.png)

#### Jaeger UI

> Jaeger: open source, end-to-end distributed tracing
>
> Monitor and troubleshoot transactions in complex distributed systems

Quarkus uses the OpenTracing standard to generate traces and Jaeguer is a collector and UI to store and visualize the tracing information, jaeguer collects not only the information from the application but also from Traefik, JDBC and more.

![jaeguer](/docs/images/jaeguer.png)


#### Prometheus

> Prometheus fundamentally stores all data as time series: streams of timestamped values belonging to the same metric and the same set of labeled dimensions. Besides stored time series, Prometheus may generate temporary derived time series as the result of queries.

The application expose the `/metrics` enpoint using the prometheus format to expose all the micrometer generated metrics, prometheus is configured to collect all these metrics and can be accessed to generate graphs and visualize the data there, but a better visualization tool like grafana is also provided.

Go to http://prometheus.localhost

![prometheus](/docs/images/prometheus.png)

#### Grafana

> Grafana has become the world’s most popular technology used to compose observability dashboards with everything from Prometheus & Graphite metrics, to logs and application data to power plants and beehives.

Grafana allows you to visualize the metrics collected from prometheus, and there are 3 provisioned dashboards to make your live easier:

Go to http://grafana.localhost

##### Application metrics

![grafana-quarkus](/docs/images/grafana-quarkus.png)

##### Traefik metrics

![grafana-traefik](/docs/images/grafana-traefik.png)

##### Database metrics

![grafana-postgres](/docs/images/grafana-postgres.png)

#### PgAdmin

> pgAdmin is the most popular and feature rich Open Source administration and development platform for PostgreSQL, the most advanced Open Source database in the world.

Go to http://pgadmin.localhost

username: `admin@admin.org`
password: `admin`

Then, when expanding the servers in the Browser window, the password is `postgres`

![pgadmin](/docs/images/pgadmin.png)

#### Keycloak

> Open Source Identity and Access Management
>
> Add authentication to applications and secure services with minimum fuss. No need to deal with storing users or authenticating users. It's all available out of the box.

Keycloak is already provisioned with a `Quarkus` realm including 2 users, a client Id and roles that you can use to test the authentication and authorization in your applications, including the use of JWT.

Go to http://keycloack.localhost

The creadentials for the administration console are:

username: `admin`
password: `admin`

![keycloak](/docs/images/keycloak.png)

#### HttpBin

> A simple HTTP Request & Response Service.

Go to http://httpbin.localhost

![httpbin](/docs/images/httpbin.png)


#### Application

Go to http://application.localhost

![application](/docs/images/application.png)





[1]: https://quarkus.io/
[2]: https://projects.eclipse.org/projects/ee4j.jpa
[3]: https://quarkus.io/guides/hibernate-orm-panache
[4]: https://quarkus.io/guides/flyway
[5]: https://github.com/OpenAPITools/openapi-generator
[6]: https://projects.eclipse.org/projects/ee4j.jaxrs
[7]: https://mapstruct.org/
[8]: JAX-RS
[10]: https://traefik.io/
[11]: https://www.portainer.io/
[12]: https://www.jaegertracing.io/
[13]: https://prometheus.io/
[14]: https://grafana.com/
[15]: https://www.postgresql.org/
[16]: https://www.pgadmin.org/
[17]: https://github.com/prometheus-community/postgres_exporter
[18]: https://www.keycloak.org/
[19]: https://httpbin.org/
