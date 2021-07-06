package com.example.transactiondemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
//@EnableTransactionManagement
public class TransactionDemoApplication implements CommandLineRunner {
    @Autowired
    private AddressService addressService;
    @Autowired
    private PersonService personService;

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(TransactionDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        TransactionDemoApplication app = applicationContext.getBean(TransactionDemoApplication.class);
        try{
            app.fa();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void fa() throws Exception{
        personService.insertPerson();
        try {
            applicationContext.getBean(TransactionDemoApplication.class).fb();
        } catch (Exception e){}
//        throw new Exception("person insert error");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void fb()  throws Exception{
        addressService.insertAddress();
        throw new Exception("person insert error");
    }
}
