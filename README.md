# springbatch_mongo
Spring Batch Mongodb repository plugin

This project aims to create a Mongo DB Based job repository for use with Spring Batch. This makes use of Spring Data MongoDB module for Spring's support for Mongo DB.

In many Mongo DB based implementations, Mongo DB is the only database in use and adding a persistence store just for job repository adds too many overheads from infrastructure and cost perspective. Hence, a job repository based on Mongo DB (i.e. storing the data in configurable Mongo DB collections), removes the requirement of having an additional infrastructure or cost overhead.

Note that the Mongo DB based implementation tries to be as similar as possible, to the JDBC variant of JobRepository that ships with Spring Batch module. Obvious minor changes in terms of document structure against table structures in RDBMS are made. However, logically, functionality provided by both is exactly same.

