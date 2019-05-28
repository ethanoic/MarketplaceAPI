FORMAT: 1A
HOST: http://polls.apiblueprint.org/

# Marketplace API

An web service that allows a client application to retrieve products for sale
users to register, buyers to enquire and sellers to post.

# Group Products Resource

## Get All Products [/products{?category}{?pageSize}{?pageIndex}]

Products resource allows clients to get information about products
offerid
### Get all products based on category [GET]

As a buyer I can retrieve a list of products based on category 
and define a page index and page size that controls how much data is returned

+ Parameters
    + category (string, optional) - send a category to filter the results
        + Default: ''
        
    + pageSize (number, optional) - number of result to retrieve
        + Default: 20 
    
    + pageIndex (number, optional) - number of result to retrieve
        + Default: 0 

+ Response 200 (application/json)

        {
            "list": [
                {
                    "id": 1,
                    "name": "PS4 VITA (2016)",
                    "seller": "Larry",
                    "price": 130,
                    "shortDescription": "Sitting in cupboard collecting dust",
                    "likes": 100,
                    "image": "http://mymarketingplace.com.sg/storage/images/1.jpg"
                },
                {
                    "id": 2,
                    "name": "Playstation",
                    "seller": "KK",
                    "price": 40,
                    "shortDescription": "Used.",
                    "likes": 1,
                    "image": "http://mymarketingplace.com.sg/storage/images/2.jpg"
                },
                {
                    "id": 3,
                    "name": "Nintendo 64Bit",
                    "seller": "PP",
                    "price": 20,
                    "shortDescription": "Used.",
                    "likes": 1,
                    "image": "http://mymarketingplace.com.sg/storage/images/3.jpg"
                }
            ],
            "next": "/products?category=Electroncis&pageSize=20&pageIndex=2",
            "prev": "/products?category=Electroncis&pageSize=20&pageIndex=0",
            "totalCount": 78291
        }

## One Product Resource [/products/{id}]

Return 1 product details based on id

### Get product details [GET]

+ Parameters
    + id: 1 (number) - id of the product
    
+ Response 200 (application/json)
        
        {
            "id": 1,
            "name": "PS4 VITA (2016)",
            "seller": "Larry",
            "price": 130,
            "likes": 100,
            "images": [
                "http://mymarketingplace.com.sg/storage/images/1.jpg",
                "http://mymarketingplace.com.sg/storage/images/2.jpg",
                "http://mymarketingplace.com.sg/storage/images/3.jpg",
            ],
            "fullDescription": "blah blah blah...",
            "dealTypes": [
                "Meet up",
                "Delivery"
            ]
        }

### Update Product [PUT]

+ Parameters
    + id: 1 (number) - id of the product
    
+ Request

        {
            "name": "PS4 VITA (2016)",
            "price": 130,
            "images": [
                "http://mymarketingplace.com.sg/storage/images/1.jpg",
                "http://mymarketingplace.com.sg/storage/images/2.jpg",
                "http://mymarketingplace.com.sg/storage/images/3.jpg",
            ],
            "fullDescription": "blah blah blah...",
            "dealTypes": [
                "Meet up",
                "Delivery"
            ]
        }

+ Response 200 (application/json)

        {
            "success": true,
            "message": "Product details will be updated in 1 hours time"
        }
        
### Delete Product [DELETE]

+ Response 200 (application/json)

        {
            "success": true,
            "message": "Product will be removed in 1 hours time"
        }
        
## Add an Offer to a product [/products/{id}/offers]

### Buyer Add an Offer for a product [POST]

+ Parameters
    + id: 1 (number) - id of the product

+ Request

        {
            "buyerId": 100,
            "price": 50.00,
            "meetUpLocation": "East"
        }
        
+ Response 200 (application/json)

        {
            "offer": "/products/1001/offers/5001"
        }

## Add Product [/products]

### Post to Product [POST]

+ Request

        {
            "name": "PS4 VITA (2016)",
            "price": 130,
            "images": [
                "http://mymarketingplace.com.sg/storage/images/1.jpg",
                "http://mymarketingplace.com.sg/storage/images/2.jpg",
                "http://mymarketingplace.com.sg/storage/images/3.jpg",
            ],
            "fullDescription": "blah blah blah...",
            "dealTypes": [
                "Meet up",
                "Delivery"
            ]
        }

+ Response 200 (application/json)

    + Body
    
            {
                "id": 1
            }
