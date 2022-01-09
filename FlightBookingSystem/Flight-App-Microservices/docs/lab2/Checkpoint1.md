Flight Booking System
---------------------



##  _Feature list for laboratory work number 2_



###  1.ELK stack

I would implement Elasticsearch logstash and kibana for logs registering for each of existing services of my lab. ELK stack will be running on dockerized container.


###  2.Database redundancy/replication + failover
 For the ticket service I am going to implement a master-slave replication of Mysql database using Spring boot properties.
to provide two identical databases for specified service. There is a link which I am going to use for the documentation of 
replication implementation provided here: https://medium.com/swlh/a-complete-guide-to-setting-up-multiple-datasources-in-spring-8296d4ff0935

![img.png](master-slave.png)

###  3.EhCache distributed cache using consistency hashing.

For Cache service I will implement distributed caching for flight service calls using caching policy introduced
into cache service configuration file.

###  4. All the microservices are going to be runt from the docker container using docker compose file


-------------------------



####  _No new technologies will be introduced_

-------------------------

### Final Architecture Diagram
![img_1.png](architecture_diagram.png)

