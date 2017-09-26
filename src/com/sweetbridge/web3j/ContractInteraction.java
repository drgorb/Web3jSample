package com.sweetbridge.web3j;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import static org.web3j.tx.ManagedTransaction.GAS_PRICE;

public class ContractInteraction {
    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(2000000L);
    private static final String USER_ADDRESS = "0x3532727c1126ddad9a6e9f935b74e41e7b1d4025";
    static Logger log = Logger.getLogger("main");

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException, CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {

        Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));

        Credentials credentials = WalletUtils.loadCredentials(
                "",
                "UTC--2017-09-04T10-00-18.714316113Z--3532727c1126ddad9a6e9f935b74e41e7b1d4025");

        Token token = Token.deploy(
                web3, credentials, GAS_PRICE, GAS_LIMIT,
                BigInteger.ZERO,
                new Uint256(new BigDecimal("1e27").toBigInteger()),
                new Uint256(new BigDecimal("1e3").toBigInteger())).get();

        log.info("contract address " + token.getContractAddress());

        Uint256 balance = token.balances(new Address(USER_ADDRESS)).get();
        log.info(USER_ADDRESS + " balance " + balance.getValue());

        Uint256 supply = token.supply().get();
        log.info("tokens supply " + supply.getValue());

        TransactionReceipt rcpt = token.transfer(new Address("0xa6b8b75d85b6d2c22b8e9e2768f2f405cf09005a"),
                new Uint256(new BigDecimal("1e26").toBigInteger())).get();

        balance = token.balances(new Address(USER_ADDRESS)).get();
        log.info(USER_ADDRESS + " balance " + balance.getValue());

        Runtime.getRuntime().halt(0);
    }
}
