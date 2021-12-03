# WS-4

# Security Usage

# Authentication
```
curl -X POST -vu client:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=user&username=user&grant_type=password&scope=read%20write&client_secret=javainuse&client_id=client" | json_pp

curl -X POST -vu client:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=admin&username=admin&grant_type=password&scope=read%20write&client_secret=javainuse&client_id=client" | json_pp
```
# Usage
```
curl http://localhost:8080/user -H "Authorization: Bearer <token>"
curl http://localhost:8080/admin -H "Authorization: Bearer <token>"
```


# Refresh token 
```
 curl -X POST -vu client:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=user&username=user&grant_type=refresh-token&refresh_token=<token> &client_secret=javainuse&client_id=client" | json_pp
```
# Authorization
# User : Read
# Admin : Create/Update/Delete

## User
```
curl http://localhost:8080/getC -H "Authorization: Bearer <token>"
```

## Admin :
```
curl -X DELETE http://localhost:8080/deleteC/{id} -H "Authorization: Bearer <token>"

curl -X POST http://localhost:8080/createC -H "Authorization: Bearer <token>" -d "id=2&factory_id=1&model=Toyota&year=2018&fuel=gasoline&doors=4&cost=150.000&color=blue"

curl -X PUT http://localhost:8080/createC -H "Authorization: Bearer <token>" -d "id=2&factory_id=1&model=Toyota&year=2018&fuel=gasoline&doors=4&cost=150.000&color=blue"

```

## CRUD Usage

# Create

```
curl -X POST http://localhost:8080/createC -d "id=&factory_id=&model=&year=&fuel=&doors=&cost=&color"

curl -X POST http://localhost:8080/createF -d "id=&name=&country_code="
```
# Read
```
curl http://localhost:8080/getC
curl http://localhost:8080/getC/{id}

curl http://localhost:8080/getF
curl http://localhost:8080/getF/{id}
```

# Update 

```
curl -X PUT http://localhost:8080/putC -d "id=&factory_id=&model=&year=&fuel=&doors=&cost=&color"

curl -X PUT http://localhost:8080/putF -d "id=&name=&country_code="
```
# Delete 
```
curl -X DELETE http://localhost:8080/deleteC/{id}
curl -X DELETE http://localhost:8080/deleteF/{id}
```

# Upload CSV file

```
curl -F file=@"file.csv" http://localhost:8080/file
```
