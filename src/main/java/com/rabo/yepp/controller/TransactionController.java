package com.rabo.yepp.controller;

import com.github.javafaker.Faker;
import com.rabo.yepp.model.Article;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<>(new Message("Transaction done"), HttpStatus.OK);

    }

    @GetMapping(value="/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    // http://localhost:port/api/article
    public ResponseEntity<List<Transaction>> getAllArticles(){
        List<Transaction> transactions = transactionRepository.findAll();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    private void loadDatabase(){
        Faker faker = new Faker();

        Transaction transaction = new Transaction(
                2309334878L,
                faker.finance().iban("NL"),
                faker.finance().iban("NL"),
                93884.00);

        transactionRepository.save(transaction);

        transaction = new Transaction(
                3487823093L,
                faker.finance().iban("NL"),
                faker.finance().iban("NL"),
                35894.00);

        transactionRepository.save(transaction);
    }
}
