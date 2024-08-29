Create a REST based web application (Spring Boot, REST API, Spring DATA, JPA) that supports customer management:
- CRUD operations
    - **New customer can be created**
        - Name (max length is 100 characters)
        - Age (must be positive number)
        - Date of birth (supported date format is yyyy-MM-dd)
        - Address (max length is 200 characters, can be null)
        - Gender (M - Male, F - Female, can be null)
    - **Existing customers can be fetched**
        - Pagination and ordering are supported (default order by name, default pagination size=10)
    - **An existing customer can be updated**
        - Any details can be changed
    - **An existing customer can be deleted**
        - Any existing customer can be deleted
- Proper data and action validations are in place!
    - Example for failing validations:
        - Update a customer that does not exist -> Throw exception!
        - Provided customer name is null or empty! -> Throw exception!
        - etc
- Health check available
    - Endpoint to check whether service is up and running
- For write operations (CREATE, UPDATE, DELETE) audit entries to be created
    - Fields
        - action: describe the action that was executed
        - customerId: write operation on which customer (might be null)
        - request: contains the incoming request (might be null)
        - status: outcome of the action (SUCCESS, FAILED)
        - creation datetime: when the audit event got created
    - Audit entries must be created and persisted regardless the outcome of the customer write operation!
    - Audit entries can be fetched (default sorting is LATEST FIRST, default pagination size = 100)
- The class names and package names should be descriptive of the functionality it serves!

For persistent storage an in-memory H2 DB or a dockerized database can be used!

For building tool please use Maven or Gradle!

For incoming requests and outgoing responses please use JSON format!