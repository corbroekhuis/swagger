package com.rabo.yepp.controller;

import com.github.javafaker.Faker;
import com.rabo.yepp.model.Message;
import com.rabo.yepp.model.Transaction;
import com.rabo.yepp.repository.Repository;
import com.rabo.yepp.repository.RepositoryImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Tag(name = "API for customer financial transactions",
     description = "This API offers all services needed to handle customer financial transactions")
public class TransactionController {

    private static Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private Repository<Transaction> transactionRepository = new RepositoryImpl<>();

    public TransactionController(){
        loadDatabase();
    }

    @Operation(summary = "This service may be removed in future versions. Please use transaction/json", deprecated = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message confirming transaction done"),
            @ApiResponse(responseCode = "500", description = "Invalid call", content = @Content),
    })
    @PutMapping(value = "/transaction/xml", consumes = {MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<Message> transactionAsXml(@RequestBody Transaction transaction) {

        logger.info( transaction.toString());
        return new ResponseEntity<>(new Message("Transaction done"), HttpStatus.OK);
    }

    @Operation(summary = "Send transaction as JSON and store in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message confirming transaction done"),
            @ApiResponse(responseCode = "500", description = "Invalid call", content = @Content),
    })
    @PutMapping(value = "/transaction/json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> transactionAsJson(@RequestBody Transaction transaction) {

        logger.info( transaction.toString());
        transactionRepository.save( transaction);
        return new ResponseEntity<>(new Message("Transaction done"), HttpStatus.OK);
    }

    @GetMapping(value="/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    // http://localhost:port/api/transaction
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> transactions = transactionRepository.findAll();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returning transaction found"),
            @ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content),
    })
    @GetMapping(value="/transaction/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    // http://localhost:port/api/transaction/2
    public ResponseEntity<Transaction> findTransactionById(@PathVariable long id){
        Optional<Transaction> transaction = transactionRepository.findById( id);

        if(transaction.isPresent()){
            return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returning transaction found"),
            @ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content),
    })
    @DeleteMapping(value="/transaction/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    // http://localhost:port/api/transaction/2
    public ResponseEntity<Transaction> deleteTransactionById(@PathVariable long id){
        Optional<Transaction> transaction = transactionRepository.findById( id);

        if(transaction.isPresent()){
            transactionRepository.deleteById( id);
            return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    private void loadDatabase(){
        Faker faker = new Faker();

        Transaction transaction = new Transaction(
                faker.finance().iban("NL"),
                faker.finance().iban("NL"),
                93884.00);

        transactionRepository.save(transaction);

        transaction = new Transaction(
                faker.finance().iban("NL"),
                faker.finance().iban("NL"),
                35894.00);

        transactionRepository.save(transaction);
    }
}
