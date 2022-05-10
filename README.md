
#APP exemplo de uso do Kafka consumer para pegar uma mensagem e enviá-la via SES da aws para um email cadastrado.

## Para este teste de aplicação deve-se configurar as variáveis de ambiente:
- AWS_ACCESS_KEY, chave acesso da conta  aws.
- AWS_SECRET_KEY, passowrd conta aws.
- AWS_SES_EMAIL, email configura na aws para envio de emails.

- KAFKA_GROUP_ID_READER, grupo que faz parte este kafka consumer.
- KAFKA_TOPIC, topico que esta ouvindo.
- KAFKA_HOST, host que esta consultando.

## Obervações
- Não faz parte deste projeto o kafka producer, está sendo enviado msg via terminal 





