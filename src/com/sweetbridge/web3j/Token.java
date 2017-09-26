package com.sweetbridge.web3j;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.3.0.
 */
public final class Token extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b60405160408061033783398101604052808051919060200180519150505b6000828155600382905560018054600160a060020a03191633600160a060020a031690811790915581526002602052604090208054830190555b50505b6102be806100796000396000f300606060405236156100805763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663047fc9aa811461008557806327e235e3146100aa5780638da5cb5b146100db578063a035b1fe1461010a578063a6f2ae3a1461012f578063a9059cbb14610139578063c20524031461015d575b600080fd5b341561009057600080fd5b610098610172565b60405190815260200160405180910390f35b34156100b557600080fd5b610098600160a060020a0360043516610178565b60405190815260200160405180910390f35b34156100e657600080fd5b6100ee61018a565b604051600160a060020a03909116815260200160405180910390f35b341561011557600080fd5b610098610199565b60405190815260200160405180910390f35b61013761019f565b005b341561014457600080fd5b610137600160a060020a03600435166024356101de565b005b341561016857600080fd5b610137610236565b005b60005481565b60026020526000908152604090205481565b600154600160a060020a031681565b60035481565b600354600054349091029081106101b557600080fd5b600160a060020a0333166000908152600260205260408120805483019055805482900390555b50565b600160a060020a0333166000908152600260205260409020548190101561020457600080fd5b600160a060020a03338116600090815260026020526040808220805485900390559184168152208054820190555b5050565b60015433600160a060020a039081169116141561025257600080fd5b33600160a060020a03166108fc30600160a060020a0316319081150290604051600060405180830381858888f19350505050151561028f57600080fd5b5b5600a165627a7a72305820e92eb81046c1e885cf4d09694433bf9a8f679acb1762ec66757847f439a2d0b00029";

    private Token(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Token(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<Uint256> supply() {
        Function function = new Function("supply", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> balances(Address param0) {
        Function function = new Function("balances", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> price() {
        Function function = new Function("price", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> buy(BigInteger weiValue) {
        Function function = new Function("buy", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function, weiValue);
    }

    public Future<TransactionReceipt> transfer(Address _to, Uint256 _amount) {
        Function function = new Function("transfer", Arrays.<Type>asList(_to, _amount), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> payOut() {
        Function function = new Function("payOut", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<Token> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Uint256 _supply, Uint256 _priceWeiPerToken) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_supply, _priceWeiPerToken));
        return deployAsync(Token.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Future<Token> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Uint256 _supply, Uint256 _priceWeiPerToken) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_supply, _priceWeiPerToken));
        return deployAsync(Token.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Token load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Token(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Token load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Token(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
