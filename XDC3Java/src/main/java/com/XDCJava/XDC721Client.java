package com.XDCJava;

import com.XDCJava.Model.Token721DetailsResponse;
import com.XDCJava.Model.TokenDetailsResponse;
import com.XDCJava.callback.Token721DetailCallback;
import com.XDCJava.contracts.src.main.java.Greeter;
import com.XDCJava.contracts.src.main.java.XRC165;
import com.XDCJava.contracts.src.main.java.XRC721;
import com.XDCJava.contracts.src.main.java.XRC721Enumerable;
import com.XDCJava.contracts.src.main.java.XRC721Full;
import com.XDCJava.contracts.src.main.java.XRC721Metadata;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;


public class XDC721Client {
    public static Web3j web3;
    public static XDC721Client instance;
    XRC721 javaToken;
    String symbol, name;
    TokenDetailsResponse tokenResponse;
    private WalletFile wallet;

    public static XDC721Client getInstance() {
        if (instance == null)
            instance = new XDC721Client();

        return instance;
    }


    public static Boolean isWeb3jConnected() {
        web3 = Web3j.build(new

                HttpService(AppConstants.BASE_URL));
        try {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
            if (!clientVersion.hasError()) {
                //Connected
                return true;
            } else {
                //Show Error
                return false;
            }
        } catch (
                Exception e) {
            //Show Error
            return false;
        }
    }

    public void getTokenoinfo(String tokenAddress, Token721DetailCallback tokenDetailCallback) {


        if (isWeb3jConnected()) {
            ClientTransactionManager transactionManager = new ClientTransactionManager(web3,
                    tokenAddress);
            javaToken = null;
            try {
                XRC721Metadata javaToken = XRC721Metadata.load(tokenAddress, web3, transactionManager, new DefaultGasProvider());
                getinfo(javaToken, tokenAddress, tokenDetailCallback);
            } catch (Exception exception) {
                exception.printStackTrace();
                tokenDetailCallback.failure(exception.getMessage());
            }

        } else {

            //Show Error
            tokenDetailCallback.failure("Connection has been failed");
        }

    }


    @SuppressWarnings("NewApi")
    public void deploy_contract(String privatekey, Token721DetailCallback tokenDetailCallback) {


        if (isWeb3jConnected()) {
            Credentials credentials = Credentials.create(privatekey);
            try {

                BigInteger nonce;
                EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
                if (ethGetTransactionCount == null) {
                    tokenDetailCallback.failure("failed");
                }
                nonce = ethGetTransactionCount.getTransactionCount();
                EthGasPrice ethGasPrice = web3.ethGasPrice().sendAsync().get();
                if (ethGasPrice == null) {
                    tokenDetailCallback.failure("failed");
                }


                String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(1),
                        new org.web3j.abi.datatypes.generated.Uint8(1),
                        new org.web3j.abi.datatypes.Utf8String("Bhavisha123"),
                        new org.web3j.abi.datatypes.Utf8String("BHV"),
                        new org.web3j.abi.datatypes.generated.Uint8(18)));

                XRC721 javaToken1 = XRC721.deploy(web3, credentials, new DefaultGasProvider(), encodedConstructor).send();


                System.out.println("Account address: " + javaToken1.getContractAddress());

               /* org.web3j.protocol.core.methods.response.EthCall response  =     web3.ethCall(Transaction.createEthCallTransaction(
                        credentials.getAddress(),
                        "0x0",
                        "0x0"),
                        DefaultBlockParameter.valueOf("latest")).sendAsync().get();


                response.getValue();
*/
                RawTransaction rawTransaction = RawTransaction.createContractTransaction(nonce, ethGasPrice.getGasPrice(), BigInteger.valueOf(300000), BigInteger.valueOf(0), encodedConstructor);

                //Signature Transaction
                byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
                String hexValue = Numeric.toHexString(signMessage);
                //Send the transaction
                EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();
                // EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(transaction).sendAsync().get();


                String hash = null;
                try {
                    hash = ethSendTransaction.getTransactionHash();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                if (hash != null) {

                    EthGetTransactionReceipt transactionReceipt =
                            web3.ethGetTransactionReceipt(hash).send();
                    String contractAddress = null;
                    if (transactionReceipt.getTransactionReceipt().isPresent()) {
                        contractAddress = transactionReceipt.getTransactionReceipt().get().getContractAddress();
                        tokenDetailCallback.success(contractAddress);
                        /*final Function function = new Function(
                                "_mint",
                                Arrays.<Type>asList(new Address(contractAddress),new Uint256(Long.parseLong("2"))),Collections.<TypeReference<?>>emptyList());

                        XRC721 javaToken1 = XRC721.load(credentials.getAddress(), web3, credentials, new DefaultGasProvider());
*/

                    /*    final org.web3j.abi.datatypes.Function function_t = new Function(
                                "_mint",
                                Arrays.<Type>asList(new Address(credentials.getAddress()),
                                        new Address(contractAddress),
                                        new Uint256(Long.parseLong("10"))),
                                Collections.<TypeReference<?>>emptyList());

                        String encodedFunction = FunctionEncoder.encode(function_t);
                        //token address
                        RawTransaction rawTransaction2 = RawTransaction.createTransaction(nonce, ethGasPrice.getGasPrice(), BigInteger.valueOf(300000),
                                contractAddress, encodedFunction);
                        //Signature Transaction
                        byte[] signMessage2 = TransactionEncoder.signMessage(rawTransaction2, credentials);
                        String hexValue2 = Numeric.toHexString(signMessage2);
                        //Send the transaction
                        EthSendTransaction ethSendTransaction2 = web3.ethSendRawTransaction(hexValue2).sendAsync().get();
                        String hash2 = ethSendTransaction2.getTransactionHash();
                        if (hash2 != null)
                        {
                            EthGetTransactionReceipt transactionReceipt2 =
                                    web3.ethGetTransactionReceipt(hash).send();
                            String contractAddress2 = null;
                            if (transactionReceipt.getTransactionReceipt().isPresent()) {
                                contractAddress2 = transactionReceipt.getTransactionReceipt().get().getContractAddress();

                            }

                        }

*/
                    } else {
                        // try again
                        tokenDetailCallback.failure("failed");
                    }


                } else {
                    tokenDetailCallback.failure("failed");
                }


            } catch (Exception e) {
                e.printStackTrace();
                tokenDetailCallback.failure(e.getMessage());
            }


        } else {

            //Show Error
            tokenDetailCallback.failure("Connection has been failed");
        }

    }

    public void deploy_NFT(String privatekey, Token721DetailCallback tokenDetailCallback)
    {


        if (isWeb3jConnected()) {
            Credentials credentials = Credentials.create(privatekey);
            try {

                BigInteger nonce;
                EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
                if (ethGetTransactionCount == null) {
                    tokenDetailCallback.failure("failed");
                }
                nonce = ethGetTransactionCount.getTransactionCount();
                EthGasPrice ethGasPrice = web3.ethGasPrice().sendAsync().get();
                if (ethGasPrice == null) {
                    tokenDetailCallback.failure("failed");
                }


                XRC721Full contract = XRC721Full.deploy(
                        web3,
                        credentials,
                        Contract.GAS_PRICE,
                        Contract.GAS_LIMIT,
                        "finalNFT", "111").send();


                @SuppressWarnings("NewApi") TransactionReceipt txReceipt = contract
                        .getTransactionReceipt()
                        .get();

                // get tx hash and tx fees
                String deployHash = txReceipt.getTransactionHash();
                BigInteger deployFees = txReceipt
                        .getCumulativeGasUsed()
                        .multiply(AppConstants.GAS_PRICE);

                System.out.println("Deploy hash: " + deployHash);
                String contractAddress = contract.getContractAddress();
                System.out.println("Contract address: " + contractAddress);


                mintToken(contractAddress,privatekey,tokenDetailCallback);

            } catch (Exception e) {
                e.printStackTrace();
                tokenDetailCallback.failure(e.getMessage());
            }


        } else {

            //Show Error
            tokenDetailCallback.failure("Connection has been failed");
        }

    }

    @SuppressWarnings("NewApi")
    public void deploy_contract2(String privatekey, Token721DetailCallback tokenDetailCallback)
    {


        if (isWeb3jConnected()) {
            Credentials credentials = Credentials.create(privatekey);
            try {

                BigInteger nonce;
                EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
                if (ethGetTransactionCount == null) {
                    tokenDetailCallback.failure("failed");
                }
                nonce = ethGetTransactionCount.getTransactionCount();
                EthGasPrice ethGasPrice = web3.ethGasPrice().sendAsync().get();
                if (ethGasPrice == null) {
                    tokenDetailCallback.failure("failed");
                }







       /*         String encodedConstructor =
                        FunctionEncoder.encodeConstructor(
                                Arrays.asList(
                                        new Uint256(1),
                                        new Utf8String("Bhavisha123"),
                                        new Uint8(BigInteger.TEN),
                                        new Utf8String("Bhavisha123")));


                RawTransaction rawTransaction = RawTransaction.createContractTransaction(
                        nonce,
                        ethGasPrice.getGasPrice(),
                        BigInteger.valueOf(300000),
                        BigInteger.ZERO,
                        "0x6060604052341561000f57600080fd5b6040516103cc3803806103cc833981016040528080519091019050600181805161003d92916020019061005f565b505060008054600160a060020a03191633600160a060020a03161790556100fa565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100a057805160ff19168380011785556100cd565b828001600101855582156100cd579182015b828111156100cd5782518255916020019190600101906100b2565b506100d99291506100dd565b5090565b6100f791905b808211156100d957600081556001016100e3565b90565b6102c3806101096000396000f30060606040526004361061004b5763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663a41368628114610050578063cfae3217146100a3575b600080fd5b341561005b57600080fd5b6100a160046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061012d95505050505050565b005b34156100ae57600080fd5b6100b6610144565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156100f25780820151838201526020016100da565b50505050905090810190601f16801561011f5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b60018180516101409291602001906101ed565b5050565b61014c61026b565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156101e25780601f106101b7576101008083540402835291602001916101e2565b820191906000526020600020905b8154815290600101906020018083116101c557829003601f168201915b505050505090505b90565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061022e57805160ff191683800117855561025b565b8280016001018555821561025b579182015b8281111561025b578251825591602001919060010190610240565b5061026792915061027d565b5090565b60206040519081016040526000815290565b6101ea91905b8082111561026757600081556001016102835600a165627a7a723058206cfb726ed213c2fe842a4c886c8089e918b6de9c6cdfb372fa459eca4840c5740029" + encodedConstructor);
                byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
                String hexValue = Numeric.toHexString(signedMessage);
                EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue)
                        .sendAsync().get();*/


                String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(1),
                        new org.web3j.abi.datatypes.generated.Uint8(1),
                        new org.web3j.abi.datatypes.Utf8String("Bhavisha123"),
                        new org.web3j.abi.datatypes.Utf8String("Bhavisha123"),
                        new org.web3j.abi.datatypes.generated.Uint8(18)));


                //XRC721 javaToken1 = XRC721.deploy(web3,credentials,ethGasPrice.getGasPrice(),BigInteger.valueOf(300000),encodedConstructor).send();
                //System.out.println("Account address: " + javaToken1.getContractAddress());


                Greeter contract = Greeter.deploy(
                        web3,
                        credentials,
                        Contract.GAS_PRICE,
                        Contract.GAS_LIMIT,
                        BigInteger.ZERO,
                        new Utf8String("hello world"), new Utf8String("123")).send();


                TransactionReceipt txReceipt = contract
                        .getTransactionReceipt()
                        .get();

                // get tx hash and tx fees
                String deployHash = txReceipt.getTransactionHash();
                BigInteger deployFees = txReceipt
                        .getCumulativeGasUsed()
                        .multiply(AppConstants.GAS_PRICE);

                System.out.println("Deploy hash: " + deployHash);
                String contractAddress = contract.getContractAddress();
                System.out.println("Contract address: " + contractAddress);


                Utf8String message = contract
                        .greet()
                        .send();

                System.out.println("Message returned by Contract.greet(): " + message.toString());


                RawTransaction rawTransaction = RawTransaction.createContractTransaction(
                        nonce, ethGasPrice.getGasPrice(), BigInteger.valueOf(300000), BigInteger.valueOf(0), encodedConstructor);

                //Signature Transaction
                byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
                String hexValue = Numeric.toHexString(signMessage);
                //Send the transaction
                EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();
                // EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(transaction).sendAsync().get();


                String hash = null;
                try {
                    hash = ethSendTransaction.getTransactionHash();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                /*if (hash != null) {

                    EthGetTransactionReceipt transactionReceipt =
                            web3.ethGetTransactionReceipt(hash).send();
                    String contractAddress = null;
                    if (transactionReceipt.getTransactionReceipt().isPresent()) {
                        contractAddress = transactionReceipt.getTransactionReceipt().get().getContractAddress();
                        tokenDetailCallback.success(contractAddress);
                        *//*final Function function = new Function(
                                "_mint",
                                Arrays.<Type>asList(new Address(contractAddress),new Uint256(Long.parseLong("2"))),Collections.<TypeReference<?>>emptyList());

                        XRC721 javaToken1 = XRC721.load(credentials.getAddress(), web3, credentials, new DefaultGasProvider());
*//*

                 *//*    final org.web3j.abi.datatypes.Function function_t = new Function(
                                "_mint",
                                Arrays.<Type>asList(new Address(credentials.getAddress()),
                                        new Address(contractAddress),
                                        new Uint256(Long.parseLong("10"))),
                                Collections.<TypeReference<?>>emptyList());

                        String encodedFunction = FunctionEncoder.encode(function_t);
                        //token address
                        RawTransaction rawTransaction2 = RawTransaction.createTransaction(nonce, ethGasPrice.getGasPrice(), BigInteger.valueOf(300000),
                                contractAddress, encodedFunction);
                        //Signature Transaction
                        byte[] signMessage2 = TransactionEncoder.signMessage(rawTransaction2, credentials);
                        String hexValue2 = Numeric.toHexString(signMessage2);
                        //Send the transaction
                        EthSendTransaction ethSendTransaction2 = web3.ethSendRawTransaction(hexValue2).sendAsync().get();
                        String hash2 = ethSendTransaction2.getTransactionHash();
                        if (hash2 != null)
                        {
                            EthGetTransactionReceipt transactionReceipt2 =
                                    web3.ethGetTransactionReceipt(hash).send();
                            String contractAddress2 = null;
                            if (transactionReceipt.getTransactionReceipt().isPresent()) {
                                contractAddress2 = transactionReceipt.getTransactionReceipt().get().getContractAddress();

                            }

                        }

*//*
                    } else {
                        // try again
                        tokenDetailCallback.failure("failed");
                    }


                } else {
                    tokenDetailCallback.failure("failed");
                }*/


            } catch (Exception e) {
                e.printStackTrace();
                tokenDetailCallback.failure(e.getMessage());
            }


        } else {

            //Show Error
            tokenDetailCallback.failure("Connection has been failed");
        }

    }


    public String mintToken(String tokenAddress,String privatekey,Token721DetailCallback tokenDetailCallback) throws ExecutionException, InterruptedException {
        if (isWeb3jConnected()) {
            //Load the required documents for the transfer, with the private key
            //spender privatekey
            Credentials credentials = Credentials.create(privatekey);
            // Get nonce, the number of transactions
            BigInteger nonce;
            EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
            if (ethGetTransactionCount == null) {
                return null;
            }
            nonce = ethGetTransactionCount.getTransactionCount();
            //gasPrice and gasLimit can be set manually
            BigInteger gasPrice;
            EthGasPrice ethGasPrice = web3.ethGasPrice().sendAsync().get();
            if (ethGasPrice == null) {
                return null;
            }
            gasPrice =ethGasPrice.getGasPrice();
            //BigInteger.valueOf(4300000L) If the transaction fails, it is probably a problem with the setting of the fee.
            BigInteger gasLimit = BigInteger.valueOf(3000000L);



            final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                    "mint",
                    Arrays.<Type>asList(new Address(credentials.getAddress()),
                            new Uint256(Long.parseLong("22")),
                            new Utf8String("https://github.com/ethereum/solc-js")),
                    Collections.<TypeReference<?>>emptyList());

            //Create RawTransaction transaction object
            String encodedFunction = FunctionEncoder.encode(function);
            RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit,
                    tokenAddress, encodedFunction);

            //Signature Transaction
            byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signMessage);
            //Send the transaction
            EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();
            String hash = ethSendTransaction.getTransactionHash();
            if (hash != null)
            {
                tokenDetailCallback.success(tokenAddress);
                return hash;
            } else {
                return "Failed";
            }
        } else {
            return "Failed";
        }
    }


    public void getinfo(XRC721Metadata javaToken, String tokenAddress, Token721DetailCallback tokenDetailCallback) {
        try {

            /// @notice An abbreviated name for NFTs in this contract
            symbol = javaToken.symbol().send();

            /// @notice A descriptive name for a collection of NFTs in this contract
            name = javaToken.name().send();

            Token721DetailsResponse tokenResponse = new Token721DetailsResponse();
            tokenResponse.setSymbol(symbol);
            tokenResponse.setName(name);
            tokenResponse.setTokenAddress(tokenAddress);
            tokenDetailCallback.success(tokenResponse);

        } catch (Exception exception) {
            exception.printStackTrace();
            tokenDetailCallback.failure(exception.getMessage());
        }

    }


    /// @notice A distinct Uniform Resource Identifier (URI) for a given asset.
    /// @dev Throws if `tokenid` is not a valid NFT. URIs are defined in RFC
    ///  3986. The URI may point to a JSON file that conforms to the "XRC721
    ///  Metadata JSON Schema"
    /// @param tokenid The identifier for an NFT
    /// @param tokenAddress NFT address
    public String getTokenUri(String tokenAddress, String tokenid) {
        if (isWeb3jConnected()) {
            ClientTransactionManager transactionManager = new ClientTransactionManager(web3,
                    tokenAddress);
            try {
                XRC721Metadata javaToken1 = XRC721Metadata.load(tokenAddress, web3, transactionManager, new DefaultGasProvider());
                String balance = javaToken1.tokenURI(BigInteger.valueOf(Long.parseLong(tokenid))).send();
                return String.valueOf(balance);
            } catch (Exception exception) {
                exception.printStackTrace();
                return exception.getMessage();
            }

        } else {

            //Show Error
            return "Connection has been failed";
        }
    }

    /// @notice Count all NFTs assigned to an owner
    /// @dev NFTs assigned to the zero address are considered invalid, and this
    ///  function throws for queries about the zero address.
    /// @param ownerAddress An address for whom to query the balance
    /// @param tokenAddress NFT address
    /// @return The number of NFTs owned by `ownerAddress`, possibly zero
    public String getBalance(String tokenAddress, String ownerAddress) {


        if (isWeb3jConnected()) {
            ClientTransactionManager transactionManager = new ClientTransactionManager(web3,
                    tokenAddress);
            try {
                XRC721 javaToken1 = XRC721.load(tokenAddress, web3, transactionManager, new DefaultGasProvider());
                BigInteger balance = javaToken1.balanceOf(ownerAddress).send();


                return String.valueOf(balance);
            } catch (Exception exception) {
                exception.printStackTrace();
                return exception.getMessage();
            }

        } else {

            //Show Error
            return "Connection has been failed";
        }


    }


    /// @notice Count NFTs tracked by this contract
    /// @param tokenAddress NFT address
    /// @return A count of valid NFTs tracked by this contract, where each one of
    ///  them has an assigned and queryable owner not equal to the zero address
    public String gettotalSupply(String tokenAddress) {


        if (isWeb3jConnected()) {
            ClientTransactionManager transactionManager = new ClientTransactionManager(web3,
                    tokenAddress);
            try {
                XRC721 javaToken1 = XRC721.load(tokenAddress, web3, transactionManager, new DefaultGasProvider());
                BigInteger totalSupply = javaToken1.totalSupply().send();
                return String.valueOf(totalSupply);
            } catch (Exception exception) {
                exception.printStackTrace();
                return exception.getMessage();
            }

        } else {

            //Show Error
            return "Connection has been failed";
        }
    }


    /// @notice Enumerate valid NFTs
    /// @dev Throws if `index` >= `totalSupply()`.
    /// @param index A counter less than `totalSupply()`
    /// @param tokenAddress NFT address
    /// @return The token identifier for the `index`th NFT,
    ///  (sort order not specified)
    public static String gettokenByIndex(String tokenAddress, String index) {
        if (isWeb3jConnected()) {
            ClientTransactionManager transactionManager = new ClientTransactionManager(web3,
                    tokenAddress);
            try {
                XRC721Enumerable javaToken1 = XRC721Enumerable.load(tokenAddress, web3, transactionManager, new DefaultGasProvider());
                BigInteger token = javaToken1.tokenByIndex(new BigInteger(index)).send();
                return String.valueOf(token);
            } catch (Exception exception) {
                exception.printStackTrace();
                return exception.getMessage();
            }

        } else {

            //Show Error
            return "Connection has been failed";
        }
    }


    /// @notice Enumerate NFTs assigned to an owner
    /// @dev Throws if `index` >= `balanceOf(ownerAddress)` or if
    ///  `ownerAddress` is the zero address, representing invalid NFTs.
    /// @param ownerAddress An address where we are interested in NFTs owned by them
    /// @param tokenAddress NFT address
    /// @param index A counter less than `balanceOf(_owner)`
    /// @return The token identifier for the `index`th NFT assigned to `ownerAddress`,
    ///   (sort order not specified)
    public static String tokenOfOwnerByIndex(String tokenAddress, String ownerAddress, String index) {
        if (isWeb3jConnected()) {
            ClientTransactionManager transactionManager = new ClientTransactionManager(web3,
                    tokenAddress);
            try {
                XRC721Enumerable javaToken1 = XRC721Enumerable.load(tokenAddress, web3, transactionManager, new DefaultGasProvider());
                BigInteger token = javaToken1.tokenOfOwnerByIndex(ownerAddress, new BigInteger(index)).send();


                return String.valueOf(token);
            } catch (Exception exception) {
                exception.printStackTrace();
                return exception.getMessage();
            }

        } else {

            //Show Error
            return "Connection has been failed";
        }
    }


    /// @notice Find the owner of an NFT
    /// @dev NFTs assigned to zero address are considered invalid, and queries
    ///  about them do throw.
    /// @param tokenid The identifier for an NFT
    /// @param tokenAddress NFT address
    /// @return The address of the owner of the NFT
    public String getOwnerof(String tokenAddress, String tokenid) {
        if (isWeb3jConnected()) {
            ClientTransactionManager transactionManager = new ClientTransactionManager(web3,
                    tokenAddress);
            try {
                XRC721 javaToken1 = XRC721.load(tokenAddress, web3, transactionManager, new DefaultGasProvider());
                String ownerOf = javaToken1.ownerOf(BigInteger.valueOf(Long.parseLong(tokenid))).send();
                return ownerOf;
            } catch (Exception exception) {
                exception.printStackTrace();
                return exception.getMessage();
            }

        } else {

            //Show Error
            return "Connection has been failed";
        }
    }

    /// @notice Query if a contract implements an interface
    /// @param interfaceID The interface identifier, as specified in XRC-165
    /// @dev Interface identification is specified in XRC-165. This function
    ///  uses less than 30,000 gas.
    /// @return `true` if the contract implements `interfaceID` and
    ///  `interfaceID` is not 0xffffffff, `false` otherwise
    @SuppressWarnings("NewApi")
    public boolean getsupportInterface(String tokenAddress, String interfaceID) {


        if (isWeb3jConnected()) {
            ClientTransactionManager transactionManager = new ClientTransactionManager(web3,
                    tokenAddress);
            try {
                XRC165 javaToken2 = XRC165.load(tokenAddress, web3, transactionManager, new DefaultGasProvider());

                ByteBuffer b = ByteBuffer.allocate(4);
                b.putInt(0x80ac58cd);
                byte[] result = b.array();

                Boolean supportInterface = javaToken2.supportsInterface(result).send();
                return supportInterface;
            } catch (Exception exception) {
                exception.printStackTrace();
                return false;
            }
        } else {
            //Show Error
            return false;
        }
    }


    /// @notice Change or reaffirm the approved address for an NFT
    /// @dev The zero address indicates there is no approved address.
    ///  Throws unless `msg.sender` is the current NFT owner, or an authorized
    ///  operator of the current owner.
    /// @param receiverAddress The new approved NFT controller
    /// @param tokenid The NFT to approve
    /// @param tokenAddress The NFT
    /// @param privatekey NFT owner Privatekey
    public static String approve(String tokenAddress, String privatekey, String tokenid, String receiverAddress) throws Exception {

        if (isWeb3jConnected()) {
            //spender privatekey
            Credentials credentials = Credentials.create(privatekey);
            // Get nonce, the number of transactions
            BigInteger nonce;
            EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
            if (ethGetTransactionCount == null) {
                return null;
            }
            nonce = ethGetTransactionCount.getTransactionCount();
            //gasPrice and gasLimit can be set manually
            BigInteger gasPrice;
            EthGasPrice ethGasPrice = web3.ethGasPrice().sendAsync().get();
            if (ethGasPrice == null) {
                return null;
            }
            gasPrice = ethGasPrice.getGasPrice();
            BigInteger gasLimit = BigInteger.valueOf(60000L);
            //receiver key - token id


            XRC721 javaToken1 = XRC721.load(tokenAddress, web3, credentials, new DefaultGasProvider());

            final Function function = new Function(
                    "approve",
                    Arrays.<Type>asList(new Address(receiverAddress),
                            new Uint256(BigInteger.valueOf(Long.parseLong(tokenid)))),
                    Collections.<TypeReference<?>>emptyList());

            //Create RawTransaction transaction object
            String encodedFunction = FunctionEncoder.encode(function);
            //token address
            RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit,
                    tokenAddress, encodedFunction);
            //Signature Transaction
            byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signMessage);
            //Send the transaction
            EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();
            String hash = ethSendTransaction.getTransactionHash();
            if (hash != null) {
                return hash;
            } else {
                return "Failed";
            }
        } else {
            return "Failed";
        }

    }

    /// @notice Get the approved address for a single NFT
    /// @dev Throws if `tokenId` is not a valid NFT.
    /// @param tokenId The NFT to find the approved address for
    /// @param tokenAddress Token address , of which getting owner
    /// @return The approved address for this NFT, or the zero address if there is none
    public String getApproved(String tokenAddress, String tokenId) {


        if (isWeb3jConnected()) {
            ClientTransactionManager transactionManager = new ClientTransactionManager(web3,
                    tokenAddress);
            try {
                XRC721 javaToken1 = XRC721.load(tokenAddress, web3, transactionManager, new DefaultGasProvider());
                String getApproved = javaToken1.getApproved(BigInteger.valueOf(Long.parseLong(tokenId))).send();
                return getApproved;
            } catch (Exception exception) {
                exception.printStackTrace();
                return (exception.getMessage());
            }

        } else {

            //Show Error
            return "Connection has been failed";
        }
    }


    /// @notice Query if an address is an authorized operator for another address
    /// @param ownerAddress The address that owns the NFTs
    /// @param OperatorAddress The address that acts on behalf of the owner
    /// @param tokenAddress NFT address
    /// @return True if `_operator` is an approved operator for `_owner`, false otherwise
    public boolean isApprovedForAll(String tokenAddress, String ownerAddress, String OperatorAddress) {


        if (isWeb3jConnected()) {
            ClientTransactionManager transactionManager = new ClientTransactionManager(web3,
                    tokenAddress);
            try {
                XRC721 javaToken = XRC721.load(tokenAddress, web3, transactionManager, new DefaultGasProvider());
                Boolean isApproved = javaToken.isApprovedForAll(ownerAddress, OperatorAddress).send();

                return isApproved;

            } catch (Exception exception) {
                exception.printStackTrace();
                return false;
            }

        } else {

            //Show Error
            return false;
        }
    }


    /// @notice Enable or disable approval for a third party ("operator") to manage
    ///  all of `msg.sender`'s assets
    /// @dev Emits the ApprovalForAll event. The contract MUST allow
    ///  multiple operators per owner.
    /// @param OperatorAddress Address to add to the set of authorized operators
    /// @param booleanvalue True if the operator is approved, false to revoke approval
    /// @param tokenAddress NFT address
    /// @param privatekey Private key of owner
    public String setApprovalForAll(String tokenAddress, String privatekey, String OperatorAddress, String booleanvalue) throws Exception {


        if (isWeb3jConnected()) {
            //spender privatekey
            Credentials credentials = Credentials.create(privatekey);
            // Get nonce, the number of transactions
            BigInteger nonce;
            EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
            if (ethGetTransactionCount == null) {
                return null;
            }
            nonce = ethGetTransactionCount.getTransactionCount();
            //gasPrice and gasLimit can be set manually
            BigInteger gasPrice;
            EthGasPrice ethGasPrice = web3.ethGasPrice().sendAsync().get();
            if (ethGasPrice == null) {
                return null;
            }
            gasPrice = ethGasPrice.getGasPrice();
            //BigInteger.valueOf(4300000L) If the transaction fails, it is probably a problem with the setting of the fee.
            BigInteger gasLimit = BigInteger.valueOf(60000L);
            final org.web3j.abi.datatypes.Function function = new Function(
                    "setApprovalForAll",
                    Arrays.<Type>asList(new Address(OperatorAddress),
                            new Bool(Boolean.parseBoolean(booleanvalue))),
                    Collections.<TypeReference<?>>emptyList());


            //Create RawTransaction transaction object
            String encodedFunction = FunctionEncoder.encode(function);
            RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit,
                    tokenAddress, encodedFunction);

            //Signature Transaction
            byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signMessage);
            //Send the transaction
            EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();
            String hash = ethSendTransaction.getTransactionHash();
            if (hash != null) {
                return hash;
            } else {
                return "Failed";
            }
        } else {
            return "Failed";
        }

    }


    /// @notice Transfers the ownership of an NFT from one address to another address
    /// @dev This works identically to the other function with an extra data parameter,
    ///  except this function just sets data to "".
    /// @param tokenAddress NFT address
    /// @param receiverAddress The new owner
    /// @param tokenid The NFT to transfer
    /// @param privatekey NFT owner Privatekey
    public String safeTransferFrom(String tokenAddress, String privatekey, String receiverAddress, String tokenid) throws Exception {

        if (isWeb3jConnected()) {
            //Load the required documents for the transfer, with the private key
            //spender privatekey
            Credentials credentials = Credentials.create(privatekey);
            // Get nonce, the number of transactions
            BigInteger nonce;
            EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
            if (ethGetTransactionCount == null) {
                return null;
            }
            nonce = ethGetTransactionCount.getTransactionCount();
            //gasPrice and gasLimit can be set manually
            BigInteger gasPrice;
            EthGasPrice ethGasPrice = web3.ethGasPrice().sendAsync().get();
            if (ethGasPrice == null) {
                return null;
            }
            gasPrice = BigInteger.valueOf(3000000L);
            //BigInteger.valueOf(4300000L) If the transaction fails, it is probably a problem with the setting of the fee.
            BigInteger gasLimit = BigInteger.valueOf(3000000L);
            final org.web3j.abi.datatypes.Function function = new Function(
                    "safeTransferFrom",
                    Arrays.<Type>asList(new Address(credentials.getAddress()),
                            new Address(receiverAddress),
                            new Uint256(Long.parseLong(tokenid))),
                    Collections.<TypeReference<?>>emptyList());
            //Create RawTransaction transaction object
            String encodedFunction = FunctionEncoder.encode(function);
            RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit,
                    tokenAddress, encodedFunction);

            //Signature Transaction
            byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signMessage);
            //Send the transaction
            EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();
            String hash = ethSendTransaction.getTransactionHash();
            if (hash != null) {
                return hash;
            } else {
                return "Failed";
            }
        } else {
            return "Failed";
        }
    }


    /// @notice Transfer ownership of an NFT -- THE CALLER IS RESPONSIBLE
    ///  TO CONFIRM THAT `receiverAddress` IS CAPABLE OF RECEIVING NFTS OR ELSE
    ///  THEY MAY BE PERMANENTLY LOST
    /// @dev Throws unless `msg.sender` is the current owner, an authorized
    ///  operator, or the approved address for this NFT. Throws if `owner address` is
    ///  not the current owner. Throws if `receiverAddress` is the zero address. Throws if
    ///  `tokenid` is not a valid NFT.
    /// @param tokenAddress NFT address
    /// @param receiverAddress The new owner
    /// @param tokenid The NFT to transfer
    /// @param privatekey NFT owner Privatekey
    public String transferfrom(String tokenAddress, String privatekey, String receiverAddress, String tokenid) throws Exception {
        if (isWeb3jConnected()) {
            //spender privatekey
            Credentials credentials = Credentials.create(privatekey);
            // Get nonce, the number of transactions
            BigInteger nonce;
            EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
            if (ethGetTransactionCount == null) {
                return null;
            }
            nonce = ethGetTransactionCount.getTransactionCount();
            //gasPrice and gasLimit can be set manually
            BigInteger gasPrice;
            EthGasPrice ethGasPrice = web3.ethGasPrice().sendAsync().get();
            if (ethGasPrice == null) {
                return null;
            }
            // gasPrice = ethGasPrice.getGasPrice();
            gasPrice = BigInteger.valueOf(3000000L);
            //BigInteger.valueOf(4300000L) If the transaction fails, it is probably a problem with the setting of the fee.
            BigInteger gasLimit = BigInteger.valueOf(3000000L);
            /*final Function function = new Function(
                    "transferFrom",
                    Arrays.<Type>asList(new Address(credentials.getAddress()),
                            new Address(receiverAddress),
                            new Uint256(Long.parseLong(tokenid))),
                    Collections.<TypeReference<?>>emptyList());
*/

            final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                    "transferFrom",
                    Arrays.<Type>asList(new Address(160, credentials.getAddress()),
                            new Address(160, receiverAddress),
                            new Uint256(Long.parseLong(tokenid))),
                    Collections.<TypeReference<?>>emptyList());


            //Create RawTransaction transaction object
            String encodedFunction = FunctionEncoder.encode(function);
            RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, ethGasPrice.getGasPrice(), gasLimit,
                    tokenAddress, encodedFunction);

            //Signature Transaction
            byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signMessage);
            //Send the transaction
            EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();
            String hash = ethSendTransaction.getTransactionHash();
            if (hash != null) {
                return hash;
            } else {
                return "Failed";
            }
        } else {
            return "Failed";
        }
    }

}
