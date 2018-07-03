package com.sweetbridge.web3j;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint128;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.3.0.
 */
public final class SweetToken extends Contract {
    private static final String BINARY = "60606040526003805460ff19169055601260065534156200001f57600080fd5b6040516200130d3803806200130d833981016040528080518201919060200180519190602001805160008054600160a060020a03191633600160a060020a0316179055915083905082828281816040518082805190602001908083835b602083106200009d5780518252601f1990920191602091820191016200007c565b6001836020036101000a038019825116818451161790925250505091909101925060409150505190819003902060025560018054600160a060020a031916600160a060020a03929092169190911790555060058380516200010392916020019062000113565b505060045550620001b892505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200015657805160ff191683800117855562000186565b8280016001018555821562000186579182015b828111156200018657825182559160200191906001019062000169565b506200019492915062000198565b5090565b620001b591905b808211156200019457600081556001016200019f565b90565b61114580620001c86000396000f3006060604052361561012d5763ffffffff60e060020a60003504166306fdde03811461013457806307da68f5146101be578063095ea7b3146101d157806313af40351461020757806318160ddd146102265780631ca03b8e1461024b5780631ef3755d1461029c57806323b872dd146102af57806327cfc219146102d7578063313ce56714610302578063392f5f641461031557806370a0823114610344578063718570001461036357806375f12b21146103825780638402181f146103955780638da5cb5b146103c0578063904c6094146103d357806390bc1693146103e657806395d89b4114610405578063a9059cbb14610418578063afa202ac1461043a578063c47f002714610459578063d7dfa0dd146104aa578063dd62ed3e146104bd578063e3c33a9b146104e2575b600080fd5b005b341561013f57600080fd5b610147610533565b60405160208082528190810183818151815260200191508051906020019080838360005b8381101561018357808201518382015260200161016b565b50505050905090810190601f1680156101b05780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156101c957600080fd5b6101326105d1565b34156101dc57600080fd5b6101f3600160a060020a0360043516602435610641565b604051901515815260200160405180910390f35b341561021257600080fd5b610132600160a060020a0360043516610733565b341561023157600080fd5b6102396107bd565b60405190815260200160405180910390f35b341561025657600080fd5b6101f360046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061082795505050505050565b34156102a757600080fd5b610132610912565b34156102ba57600080fd5b6101f3600160a060020a036004358116906024351660443561097f565b34156102e257600080fd5b610132600160a060020a03600435166001608060020a0360243516610a6c565b341561030d57600080fd5b610239610b83565b341561032057600080fd5b610328610b89565b604051600160a060020a03909116815260200160405180910390f35b341561034f57600080fd5b610239600160a060020a0360043516610b98565b341561036e57600080fd5b6101f3600160a060020a0360043516610bf3565b341561038d57600080fd5b6101f3610c53565b34156103a057600080fd5b6101f3600160a060020a03600435166001608060020a0360243516610c5c565b34156103cb57600080fd5b610328610c83565b34156103de57600080fd5b610239610c92565b34156103f157600080fd5b6101326001608060020a0360043516610c98565b341561041057600080fd5b610239610d65565b341561042357600080fd5b6101f3600160a060020a0360043516602435610d6b565b341561044557600080fd5b610132600160a060020a0360043516610e57565b341561046457600080fd5b61013260046024813581810190830135806020601f82018190048102016040519081016040528181529291906020840183838082843750949650610ebd95505050505050565b34156104b557600080fd5b610328610f35565b34156104c857600080fd5b610239600160a060020a0360043581169060243516610f44565b34156104ed57600080fd5b6101f360046024813581810190830135806020601f82018190048102016040519081016040528181529291906020840183838082843750949650610fc895505050505050565b60058054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105c95780601f1061059e576101008083540402835291602001916105c9565b820191906000526020600020905b8154815290600101906020018083116105ac57829003601f168201915b505050505081565b60408051908101604052600781527f73746f7070657200000000000000000000000000000000000000000000000000602082015260005433600160a060020a0390811691161480610626575061062681610827565b151561063157600080fd5b506003805460ff19166001179055565b600354600090819060ff161561065657600080fd5b600754600160a060020a031663e1f21c6733868660006040516020015260405160e060020a63ffffffff8616028152600160a060020a0393841660048201529190921660248201526044810191909152606401602060405180830381600087803b15156106c257600080fd5b6102c65a03f115156106d357600080fd5b5050506040518051915050801561072c5783600160a060020a031633600160a060020a03167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9258560405190815260200160405180910390a35b9392505050565b60005433600160a060020a0390811691161461074e57600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0383811691909117918290557fce241d7ca1f669fee44b6fc00b8eba2df3bb514eed0f6f668f8f89096e81ed949116604051600160a060020a03909116815260200160405180910390a150565b600754600090600160a060020a03166318160ddd82604051602001526040518163ffffffff1660e060020a028152600401602060405180830381600087803b151561080757600080fd5b6102c65a03f1151561081857600080fd5b50505060405180519150505b90565b600154600254600091600160a060020a031690638b51ca4290846040518082805190602001908083835b602083106108705780518252601f199092019160209182019101610851565b6001836020036101000a038019825116818451161790925250505091909101925060409150505180910390203360006040516020015260405160e060020a63ffffffff861602815260048101939093526024830191909152600160a060020a03166044820152606401602060405180830381600087803b15156108f257600080fd5b6102c65a03f1151561090357600080fd5b50505060405180519392505050565b60408051908101604052600981527f7265737461727465720000000000000000000000000000000000000000000000602082015260005433600160a060020a0390811691161480610967575061096781610827565b151561097257600080fd5b506003805460ff19169055565b600354600090819060ff161561099457600080fd5b600754600160a060020a03166323b872dd86868660006040516020015260405160e060020a63ffffffff8616028152600160a060020a0393841660048201529190921660248201526044810191909152606401602060405180830381600087803b1515610a0057600080fd5b6102c65a03f11515610a1157600080fd5b50505060405180519050905083600160a060020a031685600160a060020a03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef8560405190815260200160405180910390a3949350505050565b60035460ff1615610a7c57600080fd5b60408051908101604052600681527f6d696e74657200000000000000000000000000000000000000000000000000006020820152610ab981610827565b1515610ac457600080fd5b600754600160a060020a03166327cfc219848460405160e060020a63ffffffff8516028152600160a060020a0390921660048301526001608060020a03166024820152604401600060405180830381600087803b1515610b2357600080fd5b6102c65a03f11515610b3457600080fd5b50505082600160a060020a03167f9f494565851dbcb31fb5198ca217cda6833282fadb96ba9431bd19c82afc1dd3836040516001608060020a03909116815260200160405180910390a2505050565b60065481565b600154600160a060020a031681565b600754600090600160a060020a03166370a0823183836040516020015260405160e060020a63ffffffff8416028152600160a060020a039091166004820152602401602060405180830381600087803b15156108f257600080fd5b6000805433600160a060020a03908116911614610c0f57600080fd5b600160a060020a0382161515610c2157fe5b5060078054600160a060020a03831673ffffffffffffffffffffffffffffffffffffffff199091161790556001919050565b60035460ff1681565b60035460009060ff1615610c6f57600080fd5b61072c8333846001608060020a031661097f565b600054600160a060020a031681565b60025481565b60035460ff1615610ca857600080fd5b600754600160a060020a0316637261e469338360405160e060020a63ffffffff8516028152600160a060020a0390921660048301526001608060020a03166024820152604401600060405180830381600087803b1515610d0757600080fd5b6102c65a03f11515610d1857600080fd5b50505033600160a060020a03167f38d762ef507761291a578e921acfe29c1af31a7331ea03e391cf16cfc4d4f581826040516001608060020a03909116815260200160405180910390a250565b60045481565b600354600090819060ff1615610d8057600080fd5b600754600160a060020a031663beabacc833868660006040516020015260405160e060020a63ffffffff8616028152600160a060020a0393841660048201529190921660248201526044810191909152606401602060405180830381600087803b1515610dec57600080fd5b6102c65a03f11515610dfd57600080fd5b50505060405180519050905083600160a060020a031633600160a060020a03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef8560405190815260200160405180910390a39392505050565b60005433600160a060020a03908116911614610e7257600080fd5b60015430600160a060020a0390811691161415610e8e57600080fd5b6001805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b60408051908101604052600581527f61646d696e000000000000000000000000000000000000000000000000000000602082015260005433600160a060020a0390811691161480610f125750610f1281610827565b1515610f1d57600080fd5b6005828051610f30929160200190611081565b505050565b600754600160a060020a031681565b600754600090600160a060020a031663dd62ed3e8484846040516020015260405160e060020a63ffffffff8516028152600160a060020a03928316600482015291166024820152604401602060405180830381600087803b1515610fa757600080fd5b6102c65a03f11515610fb857600080fd5b5050506040518051949350505050565b600154600254600091600160a060020a031690633037cea390846040518082805190602001908083835b602083106110115780518252601f199092019160209182019101610ff2565b6001836020036101000a0380198251168184511617909252505050919091019250604091505051809103902060006040516020015260405160e060020a63ffffffff851602815260048101929092526024820152604401602060405180830381600087803b15156108f257600080fd5b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106110c257805160ff19168380011785556110ef565b828001600101855582156110ef579182015b828111156110ef5782518255916020019190600101906110d4565b506110fb9291506110ff565b5090565b61082491905b808211156110fb57600081556001016111055600a165627a7a72305820ecb30074d4f112f4b509ebbe11838fab71c38300825043014bf2dc4323e4afbc0029";

    private SweetToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private SweetToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<LogBurnEventResponse> getLogBurnEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogBurn", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogBurnEventResponse> responses = new ArrayList<LogBurnEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogBurnEventResponse typedResponse = new LogBurnEventResponse();
            typedResponse.src = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.wad = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogBurnEventResponse> logBurnEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogBurn", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogBurnEventResponse>() {
            @Override
            public LogBurnEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogBurnEventResponse typedResponse = new LogBurnEventResponse();
                typedResponse.src = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.wad = (Uint256) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public List<LogMintEventResponse> getLogMintEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogMint", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogMintEventResponse> responses = new ArrayList<LogMintEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogMintEventResponse typedResponse = new LogMintEventResponse();
            typedResponse.src = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.wad = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogMintEventResponse> logMintEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogMint", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogMintEventResponse>() {
            @Override
            public LogMintEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogMintEventResponse typedResponse = new LogMintEventResponse();
                typedResponse.src = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.wad = (Uint256) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public List<LogSetOwnerEventResponse> getLogSetOwnerEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogSetOwner", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogSetOwnerEventResponse> responses = new ArrayList<LogSetOwnerEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogSetOwnerEventResponse typedResponse = new LogSetOwnerEventResponse();
            typedResponse.newOwner = (Address) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogSetOwnerEventResponse> logSetOwnerEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogSetOwner", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogSetOwnerEventResponse>() {
            @Override
            public LogSetOwnerEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogSetOwnerEventResponse typedResponse = new LogSetOwnerEventResponse();
                typedResponse.newOwner = (Address) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Transfer", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.from = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.to = (Address) eventValues.getIndexedValues().get(1);
            typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Transfer", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.from = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.to = (Address) eventValues.getIndexedValues().get(1);
                typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Approval", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.owner = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.spender = (Address) eventValues.getIndexedValues().get(1);
            typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ApprovalEventResponse> approvalEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Approval", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.owner = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.spender = (Address) eventValues.getIndexedValues().get(1);
                typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Future<Utf8String> name() {
        Function function = new Function("name", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> stop() {
        Function function = new Function("stop", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> approve(Address guy, Uint256 wad) {
        Function function = new Function("approve", Arrays.<Type>asList(guy, wad), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> setOwner(Address owner_) {
        Function function = new Function("setOwner", Arrays.<Type>asList(owner_), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> totalSupply() {
        Function function = new Function("totalSupply", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> senderHasRole(Utf8String roleName) {
        Function function = new Function("senderHasRole", 
                Arrays.<Type>asList(roleName), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> restart() {
        Function function = new Function("restart", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> transferFrom(Address src, Address dst, Uint256 wad) {
        Function function = new Function("transferFrom", Arrays.<Type>asList(src, dst, wad), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> mintFor(Address recipient, Uint128 wad) {
        Function function = new Function("mintFor", Arrays.<Type>asList(recipient, wad), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> decimals() {
        Function function = new Function("decimals", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> roles() {
        Function function = new Function("roles", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> balanceOf(Address who) {
        Function function = new Function("balanceOf", 
                Arrays.<Type>asList(who), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> setLogic(Address logic_) {
        Function function = new Function("setLogic", Arrays.<Type>asList(logic_), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bool> stopped() {
        Function function = new Function("stopped", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> pull(Address src, Uint128 wad) {
        Function function = new Function("pull", Arrays.<Type>asList(src, wad), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bytes32> contractHash() {
        Function function = new Function("contractHash", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> burn(Uint128 wad) {
        Function function = new Function("burn", Arrays.<Type>asList(wad), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bytes32> symbol() {
        Function function = new Function("symbol", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> transfer(Address dst, Uint256 wad) {
        Function function = new Function("transfer", Arrays.<Type>asList(dst, wad), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> setRolesContract(Address roles_) {
        Function function = new Function("setRolesContract", Arrays.<Type>asList(roles_), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> setName(Utf8String name_) {
        Function function = new Function("setName", Arrays.<Type>asList(name_), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> logic() {
        Function function = new Function("logic", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> allowance(Address owner, Address spender) {
        Function function = new Function("allowance", 
                Arrays.<Type>asList(owner, spender), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> hasRole(Utf8String roleName) {
        Function function = new Function("hasRole", 
                Arrays.<Type>asList(roleName), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<SweetToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Utf8String name_, Bytes32 symbol_, Address rolesContract) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(name_, symbol_, rolesContract));
        return deployAsync(SweetToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Future<SweetToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Utf8String name_, Bytes32 symbol_, Address rolesContract) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(name_, symbol_, rolesContract));
        return deployAsync(SweetToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static SweetToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SweetToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static SweetToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SweetToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class LogBurnEventResponse {
        public Address src;

        public Uint256 wad;
    }

    public static class LogMintEventResponse {
        public Address src;

        public Uint256 wad;
    }

    public static class LogSetOwnerEventResponse {
        public Address newOwner;
    }

    public static class TransferEventResponse {
        public Address from;

        public Address to;

        public Uint256 value;
    }

    public static class ApprovalEventResponse {
        public Address owner;

        public Address spender;

        public Uint256 value;
    }
}
