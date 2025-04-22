## VoltPay Performance Testing 

This project provides a simple API /api/write/{count} which produces "count" amount of Write Events to be consumed by volpay-writer service.
Relies on Kafka to be enabled, check instructions in Voltpay-Writer README


## Kafka 
1. ``docker exec -it kafka1 bash`` 
2. ``cd ../../bin`` -> folder with scripts 
3. [VERIFY EVENTS PRODUCED] ``kafka-console-consumer --bootstrap-server kafka1:29092 --topic write-topic --property print.key=true --property print.timestamp=true --property print.partition=true --from-beginning``
