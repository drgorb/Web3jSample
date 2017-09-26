package com.sweetbridge.web3j;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class SimpleCall {
    static Logger log = Logger.getLogger("main");

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        Web3j web3 = Web3j.build(new HttpService("https://kovan.infura.io/NgjvCOUF5UIhCgRKndzD"));
        Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
        String clientVersion = web3ClientVersion.getResult();
        log.info("clientVersion " + clientVersion);
        String result = web3.web3ClientVersion().sendAsync().get().getResult();
        log.info("result " + result);

        Runtime.getRuntime().halt(0);
    }
}
