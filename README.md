# Instant Username Search API

[![Build Status](https://travis-ci.org/instant-username-search/instant-username-search-api.svg?branch=master)](https://travis-ci.org/umutcanbolat/instant-username-search-api)

Rest API of the instant-username-search project.

## Client Repository

Check the client repo here:
[instant-username-search](https://github.com/umutcanbolat/instant-username-search)

## Installation

- A [PostgreSQL](https://www.postgresql.org/download/) DB instance is needed to get the project up and running. After DB installation, a DB named `ius` must be created manually.

- Following environment variables needs to be set for a successful DB connection. You can also edit these in [application.yml](src/main/resources/application.yml).

| Environment Variable       | Explanation                                                        |
| -------------------------- | ------------------------------------------------------------------ |
| SPRING_DATASOURCE_URL      | DB connection url. Example: `jdbc:postgresql://localhost:5432/ius` |
| SPRING_DATASOURCE_USERNAME | Postgres DB username.                                              |
| SPRING_DATASOURCE_PASSWORD | Postgres DB user password.                                         |

- You need maven installed on your system to run the project. Cd into the project directory and run the following command to start the application. Rest API will accept requests at port 8080.

```sh
mvn spring-boot:run
```

## Endpoints

- `/check/{service}/{username}`: Request to `/check/github/torvalds` will check the availability of the username `torvalds` on `GitHub`.

```json
{
  "service": "GitHub",
  "url": "https://www.github.com/torvalds",
  "available": false,
  "message": null
}
```

- `/services/getAll` will list the all available websites to check for username.

```json
[
  {
    "service": "Instagram",
    "endpoint": "/instagram/{username}"
  },
  {
    "service": "Twitter",
    "endpoint": "/twitter/{username}"
  },
  {
    "service": "Facebook",
    "endpoint": "/facebook/{username}"
  },
  {
    "service": "YouTube",
    "endpoint": "/youtube/{username}"
  },
  {
    "service": "Blogger",
    "endpoint": "/blogger/{username}"
  },
  {
    "service": "Reddit",
    "endpoint": "/reddit/{username}"
  }
]
```

## Credits

Developed by [Umut Canbolat](https://github.com/umutcanbolat).

## License

This project is licensed under the GNU General Public License v3.0 (GPL 3.0) - see the [LICENSE](LICENSE) file for details
