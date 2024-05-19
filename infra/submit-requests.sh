#!/bin/bash

echo -e "\n*** Submitting requests to Product Service ***\n"
printf "\n\nCreating Product One\n\n"
curl -X POST http://localhost:8081/v1/products -H 'Content-Type: application/json' -d '{"name": "One", "description": "Description One", "price": 1}'
printf "\n\nCreating Product Two\n\n"
curl -X POST http://localhost:8081/v1/products -H 'Content-Type: application/json' -d '{"name": "Two", "description": "Description Two", "price": 2}'
printf "\n\nCreating Product Three\n\n"
curl -X POST http://localhost:8081/v1/products -H 'Content-Type: application/json' -d '{"name": "Three", "description": "Description Three", "price": 3}'
printf "\n\nCreating Product With Invalid Name And Description\n\n"
curl -X POST http://localhost:8081/v1/products -H 'Content-Type: application/json' -d '{"name": "   ", "description": "   ", "price": 3}'
printf "\n\nDeleting Product One\n\n"
curl -X DELETE http://localhost:8081/v1/products/1
printf "\n\nGetting Product One After Delete\n\n"
curl http://localhost:8081/v1/products/1
printf "\n\nUpdating Product Two\n\n"
curl -X PATCH http://localhost:8081/v1/products/2 -H 'Content-Type: application/json' -d '{"name": "New Name Two", "description": "New Description Two", "price": 12}'
printf "\n\nGetting Product Three\n\n"
curl http://localhost:8081/v1/products/3
printf "\n\nGetting list of Products\n\n"
curl "http://localhost:8081/v1/products?from=0&size=10&sort=price_desc"
printf "\n\nGetting Product Infos\n\n"
curl -X POST http://localhost:8081/v1/products/product-info -H 'Content-Type: application/json' -d '{"productNames": ["New Name Two", "Three", "Four"]}'
