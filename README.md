SPURISTO (Tracker in Esperanto)
==================================

Inspired by
-----------

This project is inspired by [Komadu project](https://github.com/Data-to-Insight-Center/komadu)

How to run the project
----------------------

Start two Docker containers: RabbitMQ and ArangoDB

```
docker run -d --hostname localhost --name rabbit -p 8880:15672 -p 5672:5672 -p 5671:5671 -p 4369:4369 -p 25672:25672 -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin rabbitmq:3-management
```

```
docker run -e ARANGO_ROOT_PASSWORD=root -p 8529:8529 -d -v /Users/dmartin/arangodata:/var/lib/arangodb3 arangodb
```

Then start the (Spring Boot) application:

```
mvn spring-boot:run
```

You are ready to go.

You can publish data in RabbitMQ queues and look at the relations between entities with this URL:

```
http://localhost:6789/path?key=YourEntityKeyHere
```


What's useful for a data lineage service
----------------------------------------

Some use cases:

Describe a model training
* identify the data sets used
  * main data set and derived data sets (training, test...)
  At least a macro description : id of the data set (file path, other ?) and a description
  At most, the exact set described as a collection of entities
  
* identify the set of parameters used
  An entity describing the set, with the use of parameters could be enough

* identify the algorithm used
  should be an attribute of the model ?

* identify the actions (data cleansing, fixing...), the intermediates entities and when the actions occur
  Can this be described as a plan?

* identify the agent running the action
* identify the output (model version)

Describe a prediction, on behalf of a user, based on a specific version of a model and a set of data
* identify the action and the time of the action
* identity who is the user and the role he is playing in the action
* identify the model version used and the generic model it is linked to
* identify the data provided in order to have a prediction
* identify the result


Expected methods:

* being able to create an entity with the minimum set of optional attributes
* being able to add relations between entities (derived, revision)

* being able to create an activity with the minimum set of optional attributes
* being able to add relations between activities (derived, revision)

* being able to create an agent with the minimum set of optional attributes
* being able to add relations between entities (derived, revision)
