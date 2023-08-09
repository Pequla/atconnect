# AT Connect

The connection proxy for the admin tools connect application.

### HTTP Status Codes
- `201 Created` - After each POST Request
- `401 Anauthorized` - Server is not registered yet
- `400 Bad Request` - General error

> On successful response can also contain an empty array, make sure to check if the array is empty

Example of the error response:
```json
{
    "name": "java.lang.RuntimeException",
    "message": "Server not registered",
    "path": "/api/command/ce75983a-54fe-49e5-a738-eda6ea1f6473",
    "timestamp": 1691156373137
}
```

### `POST /api/server/register`
> Request body and `x-token` header is required. Registers a new server to the proxy.
```json
{
    "uuid": "ce75983a-54fe-49e5-a738-eda6ea1f6473"
}
```

### `GET /api/log/{uuid}`
>  Retrieve logs for the server using the UUID as the path parameter. Query parameter `?last={logId}` is also available. If The last id is not specified you will automatically receive last 100 log lines.

Example usage: `/api/log/ce75983a-54fe-49e5-a738-eda6ea1f6473?last=16`

### `POST /api/log`
> Request body is required. Adds a log from the server to database.
```json
{
    "uuid": "ce75983a-54fe-49e5-a738-eda6ea1f6473",
    "value": "[Now]: Server starting, please wait"
}
```

### `GET /api/command/{uuid}`
> Retrieves que of commands for the server with specified UUID. Keep in mind that as soon as you retrieve the commands they will be marked as executed.

Example usage: `/api/command/ce75983a-54fe-49e5-a738-eda6ea1f6473`

### `POST /api/command`
> Request body is required. Adds a command to the server execution que.
```json
{
    "uuid": "ce75983a-54fe-49e5-a738-eda6ea1f6473",
    "value": "gamemode ProNoob2016 survival"
}
```

### `GET /api/heartbeat/{uuid}`
> Retrieve server's online status. If the server hasn't requested any new commands from `GET /api/command/{uuid}` for over 30 seconds it will be considered offline
```json
{
    "uuid": "ce75983a-54fe-49e5-a738-eda6ea1f6473",
    "online": true
}
```