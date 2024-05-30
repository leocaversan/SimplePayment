<h1 align="center" 
    style="font-weight: bold;"
    >
    Rest with cache 💻
</h1>
<div align="center" >
    <img align="center" alt="Python" height="45" width="70" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg">
    <img align="center" alt="Mongo" height="20" width="70" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/sqlite/sqlite-plain-wordmark.svg">
    <img align="center" alt="FastAPI" height="80" width="70" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original-wordmark.svg">
    <img align="center" alt="docker" height="60" width="70" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/docker/docker-original-wordmark.svg">
</div>

<p align="center">
    • <a href="#started">Getting Started</a> 
    • <a href="#routes">API Endpoints</a> 
</p>

<p align="center">
    <b>
        This API is a simple payment, that you can registrate transactions between two users.
    </b>
</p>

<h2 id="started" align="center" >
    🚀 Getting started
</h2>

To run this project localy you will need to install `docker` and `docker compose`. After install thats components just follow this steps.

<h3> 
    Prerequisites 
</h3>

Here are all prerequisites necessary to runner the project.

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)


<h3>
    Starting
</h3>


- To run the app, run the command.
````bash
docker-compose up 
````

<h2 id="routes">
    📍 API Authentication
</h2>

To access the functions is required authentication with another service, that simulate authorized and unthorized.

Was created a fake database to simulate the users, you can use this information to send requests.


<h2 id="routes">
    📍 API Endpoints
</h2>

Here you can list the main routes of your API, and what are their expected request.
​
| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /transactions/</kbd>     | Return all the products on database, see [request details](#get-transactions)
| <kbd>POST /transactions/</kbd>    | Realize and register Transactions on database, see [request details](#post-transactions)

<h3 id="get-transactions">
    GET /transactions/
</h3>

**RESPONSE**
```json
[
  {
    "payee": 1,
    "createdAt": "2024-05-30T17:41:57.919936",
    "id": 140,
    "payer": 2,
    "value": 15.00
  }
]
```

<h3 id="post-transactions">
    POST /transactions
</h3>

**REQUEST**
```json
{
  "value":15,
  "payer":2,
  "payee":1
}
```
**RESPONSE**
```json
{
  "id": 1,
  "payer": 2,
  "payee": 1,
  "value": 15.00,
  "createdAt": "2024-05-30T23:00:14.938076551"
}
```
