* How to run project

Here i have created two prperties file one for development and second-one for deployment.

-> If you want to run this project in localhost then just install mongodb database in your pc and then give credentails
   in application-dev.properties file.

-> Allthogh i hosted application on railway.app (Which is providing free tier for hosting). So, you can directly access the
   API from postman.

Here's the domain of hosted application: https://ggslcompany-production.up.railway.app/

API Documentation:

EmployeeDetails

1) Add Employee

   RequestType: POST
   URL: https://ggslcompany-production.up.railway.app/employee/add

   Data Input: Body:  {
                         "employeeName": "Anish Mathakiya",
                         "phoneNumber": "97772397432",
                         "email": "anumathakiya073@gmail.com",
                         "reportsTo": "64b9668a4da1e2001c3581d0",  //can be null aslo
                         "profileImage": "profile-image.jpg"
                       }

   Data OutPut:        {
                            "employeeId": "7f78dfba-a552-4122-87ee-ef8f5b3e039c",
                            "employeeName": "Anish Mathakiya",
                            "phoneNumber": "97772397432",
                            "email": "anumathakiya073@gmail.com",
                            "reportsTo": "64b9668a4da1e2001c3581d0",
                            "profileImage": "profile-image.jpg"
                        }

2) Get All Employee

   RequestType: GET
   URL: https://ggslcompany-production.up.railway.app/employee/getAll

   Data Input: Param:- pageNumber: 1
                       pageSize: 10
                       sortBy: employeeName
                       sortDir: asc

3) Update Employee

   RequestType: PUT
   URL: https://ggslcompany-production.up.railway.app/employee/update/{employeeId}

   Data Input: Pathvariable: 7f78dfba-a552-4122-87ee-ef8f5b3e039c

               Body:  {
                         "employeeName": "Anish Mathakiya",
                         "phoneNumber": "97772397432",
                         "email": "anumathakiya073@gmail.com",
                         "reportsTo": "64b9668a4da1e2001c3581d0",  //can be null aslo
                         "profileImage": "profile-image.jpg"
                       }

    Data OutPut:  Updated

4) Delete Employee

   RequestType: Delete
   URL: https://ggslcompany-production.up.railway.app/employee/delete/{employeeId}

   Data Input: Pathvariable: 7f78dfba-a552-4122-87ee-ef8f5b3e039c

   Data OutPut:  Deleted

5) nth Level Employee

   RequestType: Get
   URL: https://ggslcompany-production.up.railway.app/employee/level/{employeeId}/{nthLevel}

   Data Input: Pathvariable: 7f78dfba-a552-4122-87ee-ef8f5b3e039c, 1

   Data Output: {
                    "employeeId": "1a1848a1-1e37-473d-afe4-63b9981f901e",
                    "employeeName": "Nasir Sherasiya",
                    "phoneNumber": "89342187t31",
                    "email": "nasir@example.com",
                    "reportsTo": "7f78dfba-a552-4122-87ee-ef8f5b3e039c",
                    "profileImage": "profile-image.jpg"
                }
