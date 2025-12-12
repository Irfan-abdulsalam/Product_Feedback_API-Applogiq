# Product Feedback API

Endpoints

POST http://localhost:8082/api/products – Create product 
GET http://localhost:8082/api/products/P123 – Fetch product

### Request
```json
{
  "sku": "P123",
  "name": "Laptop",
  "category": "Electronics",
  "feedback": {
    "customerName": "Irfan",
    "rating": 5,
    "comment": "Very good"
  }
}
```
