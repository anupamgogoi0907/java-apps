### Well Known Endpoints
```
curl --location 'http://localhost:8080/realms/master/.well-known/openid-configuration'
```

### Token Endpoing
```
curl --location --request POST 'http://localhost:8080/realms/master/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=demo' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'client_secret=KLtic1TWp18whRw4L9jOOGNKxuJsB1w1' \
--data-urlencode 'scope=openid' \
--data-urlencode 'username=agogoi' \
--data-urlencode 'password=admin'
```