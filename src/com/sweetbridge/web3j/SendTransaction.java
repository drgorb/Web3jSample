package com.sweetbridge.web3j;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class SendTransaction {
    static Logger log = Logger.getLogger("main");

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));

        EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(
                "0x3532727c1126ddad9a6e9f935b74e41e7b1d4025",
                DefaultBlockParameterName.LATEST).sendAsync().get();

        Transaction transaction = Transaction.createEtherTransaction(
                "0x3532727c1126ddad9a6e9f935b74e41e7b1d4025",
                ethGetTransactionCount.getTransactionCount(),
                new BigDecimal("2e10").toBigInteger(),
                new BigInteger("22000"),
                "0x951603b001b1fcfb97e1b51895c22bc368604d57",
                new BigDecimal("2e18").toBigInteger()
        );

        EthSendTransaction transactionResponse = web3.ethSendTransaction(transaction).sendAsync().get();
        String transactionHash = transactionResponse.getTransactionHash();

        while(true)
            try{
                Thread.sleep(1000);
                EthGetTransactionReceipt txr = web3.ethGetTransactionReceipt(transactionHash).sendAsync().get();
                if(txr.getTransactionReceipt().isPresent()) {
                    log.info(txr.getTransactionReceipt().get().getTransactionIndex().toString());
                    break;
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        Runtime.getRuntime().halt(0);
    }
}
