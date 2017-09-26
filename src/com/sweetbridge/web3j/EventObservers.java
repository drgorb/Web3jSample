package com.sweetbridge.web3j;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import rx.Subscription;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class EventObservers {
    static Logger log = Logger.getLogger("main");

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));
        Subscription subscription = web3.blockObservable(false).subscribe(block -> {
            log.info("transactions in block " + block.getBlock().getNumber().toString() + " " + String.valueOf(block.getBlock().getTransactions().size()));
            for (EthBlock.TransactionResult t : block.getBlock().getTransactions()) {
                EthGetTransactionReceipt transactionReceipt = null;
                try {
                    transactionReceipt = web3.ethGetTransactionReceipt(t.get().toString()).sendAsync().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                TransactionReceipt txr = transactionReceipt.getTransactionReceipt().get();
                log.info(txr.getFrom() + " => " + txr.getTo());
            }
        });
    }
}
