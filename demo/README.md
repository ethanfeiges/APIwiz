This project is a basic API implementation using Java Spring Boot with a local database. This API will allow a client to create, read, update, and delete people objects with specified names and unique IDs

<u>Rules:</u>

Create a person object:
POST: /api/v1/person

Required JSON body properties: "name"
Non-required JSON body properties: "ID"


Read all people objects:
GET: /api/v1/person


Read person object with unique ID:
GET: /api/v1/person/ID



Update person object with unique ID:
UPDATE: /api/v1/person/ID

Required JSON body properties: "name"
Non-required JSON body properties: "ID"



Delete person object with unique ID:
DELETE: /api/v1/person/ID




