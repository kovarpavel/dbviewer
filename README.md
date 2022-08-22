# DB Viewer for PostgreSQL DBs
This application provide API endpoints for managing connections for PostgreSQL databases and provide informational endpoints.
For storing connections, application use just H2 in memory database.
Application can be run via gradle: `./gradlew clean build && ./gradlew bootRun`

## Connection Management API calls
Create connection `curl --request POST \
--url http://localhost:8080/connection \
--header 'Content-Type: application/json' \
--data '{
"name": "connection_name",
"databaseName": "database_name",
"hostName": "localhost",
"port": "5432",
"username": "user",
"password": "pwd"
}'`

Update connection `curl --request PUT \
--url http://localhost:8080/connection/1 \
--header 'Content-Type: application/json' \
--data '{
"name": "updated_name",
"databaseName": "updated_dbName",
"hostName": "newHotName",
"port": "5432",
"username": "newUser",
"password": "newPassword"
}'`

Get connections list: `curl --request GET \
--url http://localhost:8080/connection/ \
--header 'Content-Type: application/json' `

Get Connection: `curl --request GET \
--url http://localhost:8080/connection/{connection_id}`

Delete connection: `curl --request DELETE \
--url http://localhost:8080/connection/{connection_id}`


## Database information API calls
Get database schemas list: `curl --request GET \
--url http://localhost:8080/connection/{connection_id}/schemas`

Get schemas tables list: `curl --request GET \
--url http://localhost:8080/connection/{connection_id}/{schema_name}/tables`

Get table columns list: `curl --request GET \
--url http://localhost:8080/connection/{connection_id}/{schema_name}/{table_name}/columns`

Get table data preview (first 5 lines): `curl --request GET \
--url http://localhost:8080/connection/{connection_id}/{schema_name}/{table_name}/data`