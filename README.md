## Start with initializing firebase and gcloud CLI
https://firebase.google.com/docs/cli#sign-in-test-cli
https://cloud.google.com/sdk/docs/install 

    gcloud auth login
    gcloud config set project [PROJECT_ID]
    gcloud auth print-access-token

## Test connecting to collection
    curl -X GET \
    "https://firestore.googleapis.com/v1/projects/springboot-firebase-testing/databases/firestore-springboot/documents/employee/QTzXS0eCNz81f3jJyp9R" \
    -H "Authorization: Bearer $(gcloud auth print-access-token)"

## Run application, test in postman

    curl --location 'http://localhost:8080/create-employee' \
    --header 'Content-Type: application/json' \
    --data '{
    "name": "Jane Doe",
    "position": "Software Engineer",
    "salary": 85000
    }'

    curl --location 'http://localhost:8080/get-employee?accessToken=$(gcloud auth print-access-token)'