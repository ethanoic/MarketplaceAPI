FORMAT: 1A
HOST: http://polls.apiblueprint.org/

# Marketplace API

An web service that allows a client application to retrieve products for sale
users to register, buyers to enquire and sellers to post.

# Group Products Resources

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

Return 1 product details based on id, the actions list return depends on the state of the product
If a product is available for sale, actions will return a link to make an offer. 
Else there will not be a link in actions list to make an offer

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
            ],
            "sold": false,
            "actions": [
                {
                    "name": "make-offer",
                    "href": "/products/1/offers",
                    "method": "POST"
                },
                {
                    "name": "view-comments",
                    "href": "/products/1/comments",
                    "method": "GET"
                },
                {
                    "name": "view-existing-offers",
                    "href": "/products/1/offers",
                    "method": "GET"
                }
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
            "offer": "/products/1001/offers/5001",
            "actions": [
                {
                    "name": "remove-offer",
                    "href": "/products/1001/offers/5001",
                    "method": "DELETE
                }
            ]
        }

## Cancel an offer to a product [/products/{id}/offers/{offerId}]

### Buyer Removes the offer for a product [DELETE]

+ Parameter

    + offerId: 1728371 - (number) unique identifier for an offer to a product
    
+ Response 200 (application/json)

        {
            "message": "Offer successfully removed"
        }
        
## Cancel an offer to a product with optional parameters [/v1.1/products/{id}/offers/{offerId}{?vote}]

### Buyer removes the offer and submit a vote value from 1 to 5 [DELETE]

+ Parameters

    + id: 1 (number) - id of product
    + offerId: 9001 (number) - id of offer to cancel
    + vote: 3 (number) - vote value provided by potential buyer
    
+ Response 200 (application/json) 

        {
            "message": "Offer successfully removed"
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
            
# Group User Resources

## Get User Profile Resource [/users/{id}]

### Get 1 user profile details [GET]

Gets 1 user profile with HATEOAS links that provide context to gettting 
data based on the type of user (Seller or Buyer)

+ Parameters
    
    + id: 90001 (number) - unique identifier for a user
    
+ Response 200 (application/json)

        {
            "alias": "JERRY KING",
            "shopName": "CASTLE ROCK",
            "registeredSince": "2010-04-05T12:00:00",
            "isVerified": true,
            "actions": [
                {
                    "name": "view-comments",
                    "href": "/users/90001/comments",
                    "method": "GET"
                },
                {
                    "name": "view-items-for-sale",
                    "href": "/users/90001/products",
                    "method": "GET"
                }
            ]
        }

# Group Account Resources

## Accounts Resource [/accounts]

### Create buyer/seller account [POST]

+ Request

        {
            "firstName": "Richard",
            "lastName": "Tan",
            "email": "richard.tan.1980@gmail.com",
            "mobile": "99887654",
            "countryCode": "65"
        }
        
+ Response 200 (application/json)

# Group One Time Password

## One Time Password Verification [/one-time-password/{code}]

This is a centralised one time password verification for accounts, password resets etc.

### OTP Verification [POST]

+ Parameter

    + code: "123456" (string) - one time password generated by system
    
+ Response 200 (application/json)

        {
            "message": "Your account is verified"
        }

