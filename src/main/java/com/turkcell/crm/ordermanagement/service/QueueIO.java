package com.turkcell.crm.ordermanagement.service;

public interface QueueIO {

    void queueToDocumentDB() throws InterruptedException;

}
