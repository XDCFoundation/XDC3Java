package com.XDCJava.contracts.src.main.java;

import java.math.BigInteger;
import java.util.Arrays;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;



public class XRC721Enumerable extends Contract
 {
    private static final String BINARY = "0x60556023600b82828239805160001a607314601657fe5b30600052607381538281f3fe73000000000000000000000000000000000000000030146080604052600080fdfea265627a7a72315820f256544127bf2cd708f7eb50daaaaaa2534cfc6c7a7c45df350c41f134b0371964736f6c63430005110032";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TOKENOFOWNERBYINDEX = "tokenOfOwnerByIndex";

    public static final String FUNC_TOKENBYINDEX = "tokenByIndex";

    @Deprecated
    protected XRC721Enumerable(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected XRC721Enumerable(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected XRC721Enumerable(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected XRC721Enumerable(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

     public XRC721Enumerable(String binary, String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
         super(binary,contractAddress, web3j, credentials, gasPrice, gasLimit);
     }

     public XRC721Enumerable(String binary, String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
         super(binary,contractAddress, web3j, credentials,contractGasProvider);
     }

     public XRC721Enumerable(String binary, String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
         super(binary,contractAddress, web3j, transactionManager,contractGasProvider);
     }

     public XRC721Enumerable(String binary, String contractAddress, Web3j web3j,TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
         super(binary,contractAddress, web3j, transactionManager, gasPrice, gasLimit);
     }


     public RemoteCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> tokenOfOwnerByIndex(String _owner, BigInteger _index) {
        final Function function = new Function(FUNC_TOKENOFOWNERBYINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner), 
                new Uint256(_index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> tokenByIndex(BigInteger _index) {
        final Function function = new Function(FUNC_TOKENBYINDEX, 
                Arrays.<Type>asList(new Uint256(_index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static XRC721Enumerable load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new XRC721Enumerable(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static XRC721Enumerable load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new XRC721Enumerable(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

     public static XRC721Enumerable load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
         return new XRC721Enumerable(contractAddress, web3j, credentials, contractGasProvider);
     }

     public static XRC721Enumerable load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
         return new XRC721Enumerable(contractAddress, web3j, transactionManager, contractGasProvider);
     }


}
