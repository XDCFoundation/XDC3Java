package com.XDCJava.contracts.src.main.java;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;


public class NFTCreate extends Contract
{
   // private static final String BINARY = "";
    private static final String BINARY = "";

    public static final String FUNC_GETAPPROVED = "getApproved";


    protected NFTCreate(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }



    public NFTCreate(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, transactionManager, gasPrice,gasLimit);
    }



    @Deprecated
    public static NFTCreate load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFTCreate(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NFTCreate load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFTCreate(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }



}
