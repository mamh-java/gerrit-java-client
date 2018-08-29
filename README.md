gerrit-java-client
======================

# A gerrit API Client for Java


Introduction
-----------

Java implementation of the [Gerrit Code Review Tool] REST API.

first only for get account.

next step will support GET/POST/PUT/DELETE http request method

support Endpoints
-----------
```java

/access/
    Access Right related REST endpoints

/accounts/
    Account related REST endpoints

/changes/
    Change related REST endpoints

/config/
    Config related REST endpoints

/groups/
    Group related REST endpoints

/plugins/
    Plugin related REST endpoints

/projects/
    Project related REST endpoints

/Documentation/
    Documentation related REST endpoints

```


Usage
-------
```java

URI uri = new URI("http://10.0.63.21:8081");
client = new GerritHttpClient(uri, "mage","http password");

Account o = client.get("a/accounts/bright.ma", Account.class);
System.out.println(o);

```