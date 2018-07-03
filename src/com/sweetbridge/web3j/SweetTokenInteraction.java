package com.sweetbridge.web3j;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Bytes32;
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

public class SweetTokenInteraction {
    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(2000000L);
    private static final String USER_ADDRESS = "0x00a329c0648769a73afac7f9381e08fb43dbea72";
    static Logger log = Logger.getLogger("main");

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException, CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {

        Web3j web3 = Web3j.build(new HttpService("http://localhost:8544"));

        Credentials credentials = WalletUtils.loadCredentials(
                "",
                "UTC--2017-10-04T17-25-53Z--381774ba-bd39-8af2-1c8c-b57608a9d32e");

        Roles roles = Roles.deploy(
                web3, credentials, GAS_PRICE, GAS_LIMIT,
                BigInteger.ZERO).get();

        SweetToken token = SweetToken.deploy(
                web3, credentials, GAS_PRICE, GAS_LIMIT,
                BigInteger.ZERO,
                "SweetToken",
                new Bytes32(("SWT").getBytes()),
                        new Address(roles.getContractAddress())).get();

        log.info("contract address " + token.getContractAddress());

        Uint256 balance = token.balanceOf(new Address(USER_ADDRESS)).get();
        log.info(USER_ADDRESS + " balance " + balance.getValue());

        Uint256 supply = token.totalSupply().get();
        log.info("tokens supply " + supply.getValue());

        TransactionReceipt rcpt = token.transfer(new Address("0xa6b8b75d85b6d2c22b8e9e2768f2f405cf09005a"),
                new Uint256(new BigDecimal("1e26").toBigInteger())).get();

        balance = token.balanceOf(new Address(USER_ADDRESS)).get();
        log.info(USER_ADDRESS + " balance " + balance.getValue());

        Runtime.getRuntime().halt(0);
    }
}
