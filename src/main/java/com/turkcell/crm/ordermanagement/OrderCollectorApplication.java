package com.turkcell.crm.ordermanagement;

import com.turkcell.crm.ordermanagement.service.QueueIO;
import com.turkcell.crm.ordermanagement.service.impl.QueueIOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableMongoRepositories(basePackages = "com.turkcell.crm.ordermanagement.repository")
@SpringBootApplication
public class OrderCollectorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OrderCollectorApplication.class, args);
    }

    final
    QueueIO queueIO;

    public OrderCollectorApplication(QueueIO queueIO) {
        this.queueIO = queueIO;
    }

    /**
     * getting begin queueIO operations
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        queueIO.queueToDocumentDB();
    }
}
