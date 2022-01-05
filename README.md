# brockhaus-coding-challenge
7-day coding challenge given by Brockhaus AG as part of an application to a job listing.

## Tasks 

Create a program that helps a shop manage their inventory by tracking the quality and expiration date of a product. If a products quality is subpar or has expired, the program should help to tell that the product has to be cleared out and disposed.

A product has the following properties:
- designation/name
- quality
- expiration date
- price

There are cheese and wine products with following properties:
- They have a base price that does not change.
- The final price is determined through the quality rating of the product (base price + 0.1€ * quality rating).
- Cheese has to have a quality of atleast 30, otherwise it can not be put on shelves or has to be cleared out when it reaches a quality rating of 30.
- Cheese has an expiration date between 50 to 100 days in the future.
- Cheese loses one quality point per day.
- Cheese has a price that is updated every day.
- Wine is accepted as long as the quality rating is not negative.
- Wine does not lose quality points, instead it gains a quality point every 10 days after the expiration date until it reaches a rating of 50.
- Wine does not expire.
- Wine prices do not change after they have ben put on shelves.

A terminal program is desired with self designated starting values. Every day, the program has to output an overview of the products with their properties and has to clarify which products have to be cleared out and disposed. Property entry does not have to be part of the program, the products can be implemented with fixed properties. Any number of products are allowed.
