# START SEQUENCE
# eureka-server -> admin-service-> authentication-service -> employee-service -> consumer-service (dummy)

<h1> Service Functions </h1>

    -> Added jwt filtering in consumer-service
    -> employee-service         =>  Employee Model => Cassandra Database => created basic CRUD services
    -> admin-service            =>  User Model (admin) => H2 Database   => created basic login, signup services
    -> authentication-service   =>  Token Service => creating and getting the decoded token 
    -> consumer-service         =>  Proxies of above 3 services created here 
                                    For accessing : 
                                                    User -> signup, login (and then generate token)
                                                    employee -> CRUD ops (all WITH token required Authorizaiton)
                                                    authentication -> required for generating token at login time