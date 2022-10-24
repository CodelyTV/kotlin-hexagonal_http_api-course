En este video mostramos la version más sencilla del controller pero
creo que es interesante hacer ver qué pasa cuando provocamos errores si no 
los controlamos nosotras

Request válida
```
curl --location --request POST 'http://localhost:8080/course' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "97fa5af4-bd81-45d5-974f-d5a3970af252",
    "name": "Test 1"
}'
```


Qué pasa si no mandamos name o id
```
{
    "timestamp": "2022-08-21T17:19:53.384+00:00",
    "status": 400,
    "error": "Bad Request",
    "path": "/course"
}
```

Qué pasa si mandamos un id o un name inválido

```
{
    "timestamp": "2022-08-21T17:20:40.385+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/course"
}
```
