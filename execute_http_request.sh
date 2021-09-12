curl -X POST -H 'Content-Type: application/json' \
    -d '{"contentName": "public content", "contentData": "I am the client"}' \
    'http://localhost:8080/content/log'
