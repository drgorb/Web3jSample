package com.sweetbridge.web3j;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.RawTransaction;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class SendTransactionOffline {
    static Logger log = Logger.getLogger("main");

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException, CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {

        Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));

        EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(
                "0x3532727c1126ddad9a6e9f935b74e41e7b1d4025",
                DefaultBlockParameterName.LATEST).sendAsync().get();

        RawTransaction transaction = RawTransaction.createEtherTransaction(
                ethGetTransactionCount.getTransactionCount(),
                new BigDecimal("2e10").toBigInteger(),
                new BigInteger("22000"),
                "0x951603b001b1fcfb97e1b51895c22bc368604d57",
                new BigDecimal("2e18").toBigInteger()
        );

        Credentials credentials = WalletUtils.loadCredentials(
                "",
                "UTC--2017-09-04T10-00-18.714316113Z--3532727c1126ddad9a6e9f935b74e41e7b1d4025");

        byte[] signedMessage = TransactionEncoder.signMessage(transaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);

        EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();
        String transactionHash = ethSendTransaction.getTransactionHash();

        while(true)
            try{
                Thread.sleep(5000);
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
