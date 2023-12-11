package com.estudos.microservice.eclienteapi.core.config;


import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;



@Component
public class RabbitMQConection {
    private static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    //está função é executada assim que nossa classe é instanciada pelo Spring, devido a anotação @Component
    @PostConstruct
    private void adiciona(){
        Queue filaCliente = this.fila(RabbitmqConstantes.FILA_CLIENTE);
        Queue filaServico   = this.fila(RabbitmqConstantes.FILA_SERVICO);

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoCliente = this.relacionamento(filaCliente, troca);
        Binding ligacaoServico   = this.relacionamento(filaServico, troca);

        //Criando as filas no RabbitMQ
        this.amqpAdmin.declareQueue(filaCliente);
        this.amqpAdmin.declareQueue(filaServico);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoCliente);
        this.amqpAdmin.declareBinding(ligacaoServico);
    }
}
