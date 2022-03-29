package com.XDCJava;

import static java.lang.String.join;

import com.XDCJava.Model.TokenDetailsResponse;
import com.XDCJava.Model.WalletData;
import com.XDCJava.callback.CreateAccountCallback;
import com.XDCJava.callback.EventCallback;
import com.XDCJava.callback.TokenDetailCallback;
import com.XDCJava.contracts.src.main.java.XRC20;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Bip39Wallet;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.MnemonicUtils;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.ObjectMapperFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class XDC20Client {
    Web3j web3;
    public static XDC20Client instance;

    public static XDC20Client getInstance() {
        if (instance == null)
            instance = new XDC20Client();

        return instance;
    }


    public Boolean isWeb3jConnected() {
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

    public void generateWallet(File walletDirectory, String Password, CreateAccountCallback createAccountCallback) {

        try {



            Bip39Wallet walletName = WalletUtils.generateBip39Wallet(Password, walletDirectory);
            System.out.println("wallet location: " + walletDirectory + "/" + walletName);
            Credentials credentials = WalletUtils.loadBip39Credentials(Password, walletName.getMnemonic());
            String accountAddress = credentials.getAddress();
            System.out.println("Account address: " + accountAddress);

            ECKeyPair ecKeyPair = credentials.getEcKeyPair();
            String privateKey = ecKeyPair.getPrivateKey().toString(16);
            String publickeyKey = ecKeyPair.getPublicKey().toString(16);
            System.out.println("privateKey: " + ecKeyPair.getPrivateKey());
            System.out.println("sPrivatekeyInHex: " + privateKey);

            String seedPhrase = walletName.getMnemonic();


            WalletData walletData = new WalletData();
            ///  accountAddress = accountAddress.replace("0x", "xdc");
            walletData.setAccountAddress(accountAddress);
            walletData.setPrivateKey(privateKey);
            walletData.setPublickeyKey(publickeyKey);
            walletData.setSeedPhrase(seedPhrase);


            createAccountCallback.success(walletData);



           /* Bip39Wallet walletName = WalletUtils.generateBip39Wallet(Password, walletDirectory);

            Bip32ECKeyPair masterKeypair = Bip32ECKeyPair.generateKeyPair(MnemonicUtils.generateSeed(walletName.getMnemonic(), Password));

// custom derivation path
            int[] derivationPath = {44 | Bip32ECKeyPair.HARDENED_BIT, 60 | Bip32ECKeyPair.HARDENED_BIT, 0 | Bip32ECKeyPair.HARDENED_BIT, 0, 0};

// Derived the key using the derivation path
            Bip32ECKeyPair derivedKeyPair = Bip32ECKeyPair.deriveKeyPair(masterKeypair, derivationPath);

// Load the wallet for the derived key
            Credentials credentials = Credentials.create(derivedKeyPair);

            String accountAddress = credentials.getAddress();
            System.out.println("Account address: " + accountAddress);

            ECKeyPair ecKeyPair = credentials.getEcKeyPair();
            String privateKey = ecKeyPair.getPrivateKey().toString(16);
            String publickeyKey = ecKeyPair.getPublicKey().toString(16);
            System.out.println("privateKey: " + ecKeyPair.getPrivateKey());
            System.out.println("sPrivatekeyInHex: " + privateKey+"........."+"seeds: "+walletName.getMnemonic());

            String seedPhrase = walletName.getMnemonic();


            WalletData walletData = new WalletData();
            ///  accountAddress = accountAddress.replace("0x", "xdc");
            walletData.setAccountAddress(accountAddress);
            walletData.setPrivateKey(privateKey);
            walletData.setPublickeyKey(publickeyKey);
            walletData.setSeedPhrase(seedPhrase);


            createAccountCallback.success(walletData);*/

            /*String seedPhrase = walletName.getMnemonic();
+
+            Credentials restoreCredentials = WalletUtils.loadBip39Credentials("1234567890",
+                    seedPhrase);
+            ECKeyPair restoredPrivateKey = restoreCredentials.getEcKeyPair();
+            String restoredAccountAddress = restoreCredentials.getAddress();*/

            /*setupBouncyCastle();

            WalletFile walletFile;
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();
            walletFile = Wallet.createStandard(Password, ecKeyPair);
            System.out.println("address " + walletFile.getAddress());
            ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(walletFile);
            System.out.println("keystore json file " + jsonStr);*/




           /* NetworkParameters params = TestNet3Params.get();
            Wallet wallet = Wallet.createDeterministic(params, Script.ScriptType.P2PKH);

            DeterministicSeed seed = wallet.getKeyChainSeed();
            //masterKey = HDKeyDerivation.createMasterPrivateKey(seed);
            System.out.println("seed: " + seed.toString());

            System.out.println("creation time: " + seed.getCreationTimeSeconds());
            System.out.println("mnemonicCode: " + seed.getMnemonicCode());*/


           /* int entropyLen = 16;
            byte[] entropy = new byte[entropyLen];
            SecureRandom random = new SecureRandom();
            random.nextBytes(entropy);
            List<String> words = MnemonicCode.INSTANCE.toMnemonic(entropy);
            @SuppressWarnings("NewApi")
            String mnemonic = join(" ", words);
            String passphrase = "";
            byte seed[] = MnemonicCode.toSeed(words, mnemonic);
            DeterministicKey masterKey = HDKeyDerivation.createMasterPrivateKey(seed);
            List<ChildNumber> keyPath = HDUtils.parsePath("44H/0H/0H");
            DeterministicHierarchy hierarchy = new DeterministicHierarchy(masterKey);
            DeterministicKey walletKey = hierarchy.get(keyPath, false, true);
            walletKey.getPrivKey();
            System.out.println("getPrivKey: " + walletKey.getPrivKey());
            System.out.println("seed: " + words);
            Credentials credentials = Credentials.create(walletKey.getPrivKey().toString());
            System.out.println("seed: " + credentials.getAddress());*/



        } catch (Exception e) {
            e.printStackTrace();
        }





    }


    public void importWallet(String seedPhrase, String Password, File path, CreateAccountCallback createAccountCallback)
    {

        try {


           // Bip39Wallet walletName = WalletUtils.generateBip39WalletFromMnemonic(Password,seedPhrase, path);

            Credentials credentials = WalletUtils.loadBip39Credentials(Password, seedPhrase);
            String accountAddress = credentials.getAddress();
            System.out.println("Account address: " + accountAddress);

           /* Credentials restoreCredentials = WalletUtils.loadBip39Credentials(Password,
                    seedPhrase);
            ECKeyPair restoredPrivateKey = restoreCredentials.getEcKeyPair();
            String restoredAccountAddress = restoreCredentials.getAddress();




            WalletData walletData = new WalletData();
            //   restoredAccountAddress = restoredAccountAddress.replace("0x", "xdc");
            walletData.setAccountAddress(restoredAccountAddress);
            walletData.setPrivateKey(restoredPrivateKey.getPrivateKey().toString(16));
            walletData.setPublickeyKey(restoredPrivateKey.getPublicKey().toString(16));
            walletData.setSeedPhrase(seedPhrase);
*/

          /*  Bip32ECKeyPair masterKeypair = Bip32ECKeyPair.generateKeyPair(MnemonicUtils.generateSeed(seedPhrase, Password));

// custom derivation path
            int[] derivationPath = {44 | Bip32ECKeyPair.HARDENED_BIT, 60 | Bip32ECKeyPair.HARDENED_BIT, 0 | Bip32ECKeyPair.HARDENED_BIT, 0, 0};

// Derived the key using the derivation path
            Bip32ECKeyPair derivedKeyPair = Bip32ECKeyPair.deriveKeyPair(masterKeypair, derivationPath);

// Load the wallet for the derived key
            Credentials credentials = Credentials.create(derivedKeyPair);*/

            WalletData walletData = new WalletData();
            //   restoredAccountAddress = restoredAccountAddress.replace("0x", "xdc");
            walletData.setAccountAddress(credentials.getAddress());
            walletData.setPrivateKey(credentials.getEcKeyPair().getPrivateKey().toString(16));
            walletData.setPublickeyKey(credentials.getEcKeyPair().getPublicKey().toString(16));
            walletData.setSeedPhrase(seedPhrase);

            createAccountCallback.success(walletData);


        } catch (Exception e) {
            e.printStackTrace();
            createAccountCallback.failure(e.getMessage());
        }
    }


    /**
     * @param Privatekey private key of account.
     * @return A Address of contract.
     * @dev Function to check private key is valid or not.
     */
    public String getContractAddress(String Privatekey) {

        if (isWeb3jConnected()) {
            try {
                Credentials creds = org.web3j.crypto.Credentials.create(Privatekey);
                if (creds.getAddress() != null && creds.getAddress().length() > 0) {
                    return creds.getAddress();
                } else {
                    return "Please Enter Valid private key";
                }
            } catch (Exception e) {
                return e.getMessage();

            }

        } else {
            return "Please check your Connection";
        }

    }


    public void getTokenoinfo(String token_address, TokenDetailCallback tokenDetailCallback) {


        if (isWeb3jConnected()) {
            ClientTransactionManager transactionManager = new ClientTransactionManager(web3,
                    token_address);

            try {
                XRC20 javaToken = com.XDCJava.contracts.src.main.java.XRC20.load(token_address, web3, transactionManager, new DefaultGasProvider());
                getinfo(javaToken, token_address, tokenDetailCallback);
            } catch (Exception exception) {
                exception.printStackTrace();
                tokenDetailCallback.failure(exception.getMessage());
            }

        } else {

            //Show Error
            tokenDetailCallback.failure("Connection has been failed");
        }


    }


    /**
     * @param owner_address   The address which owns the funds.
     * @param spender_address The address which will spend the funds.
     * @param token_address   for which need to check allowance.
     * @return A uint256 specifying the amount of tokens still available for the spender.
     * @dev Function to check the amount of tokens that an owner allowed to a spender.
     */
    public String getAllowance(String token_address, String owner_address, String spender_address) {

        if (isWeb3jConnected()) {

            ClientTransactionManager transactionManager = new ClientTransactionManager(web3,
                    owner_address);
            XRC20 javaToken = com.XDCJava.contracts.src.main.java.XRC20.load(token_address, web3, transactionManager, new DefaultGasProvider());
            try {
                BigInteger allowance = javaToken.allowance(owner_address, spender_address).send();
                return String.valueOf(allowance);
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }


        } else {
            return "check your connection";
        }


    }

    /**
     * @param token_address The address for which checking balance of
     * @param owner_address The address to query the balance of
     * @return An uint256 representing the amount owned by the passed address.
     * @dev Gets the balance of the specified address.
     */
    public String getBalance(String token_address, String owner_address) {

        if (isWeb3jConnected()) {

            ClientTransactionManager transactionManager = new ClientTransactionManager(web3,
                    owner_address);
            XRC20 javaToken = com.XDCJava.contracts.src.main.java.XRC20.load(token_address, web3, transactionManager, new DefaultGasProvider());


            try {
                BigInteger balance = javaToken.balanceOf(owner_address).send();
                balance = converHexToDecimal(balance);
                return String.valueOf(balance);
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }


        } else {
            return "check your connection";
        }


    }


    /**
     * @param owner_address The address to query the XDC balance
     * @return An uint256 representing the amount owned by the passed address.
     * @dev Gets the balance of the specified address.
     */
    public String getXdcBalance(String owner_address) {

        if (isWeb3jConnected()) {

            try {
                EthGetBalance balance = web3.ethGetBalance(owner_address, DefaultBlockParameterName.LATEST).send();

                return String.valueOf(converHexToDecimal(balance.getBalance()));
            } catch (IOException e) {
                e.printStackTrace();
                return String.valueOf(e.getMessage());
            }


        } else {
            return "check your connection";
        }


    }

    public void getinfo(XRC20 javaToken, String token_address, TokenDetailCallback tokenDetailCallback) {
        try {

            /**
             * @return the symbol of the token.
             */
            String symbol = javaToken.symbol().send();
            /**
             * @return Total number of tokens in existence
             */
            BigInteger totalSupply = converHexToDecimal(javaToken.totalSupply().send());
            /**
             * @return the name of the token.
             */
            String name = javaToken.name().send();
            /**
             * @return the number of decimals of the token.
             */
            BigInteger decimal = javaToken.decimals().send();

            String contract = javaToken.getContractAddress();
            TokenDetailsResponse tokenResponse = new TokenDetailsResponse();
            tokenResponse.setAllowance(BigInteger.valueOf(0));
            tokenResponse.setSymbol(symbol);
            tokenResponse.setTotalSupply(totalSupply);
            tokenResponse.setName(name);
            tokenResponse.setToken_address(token_address);
            tokenResponse.setDecimal(decimal);
            tokenResponse.setcontract(contract);
            tokenDetailCallback.success(tokenResponse);

        } catch (Exception exception) {
            exception.printStackTrace();
            tokenDetailCallback.failure(exception.getMessage());
        }

    }


    /**
     * @param PRIVATE_KEY_TRANSACTION spender's private key.
     * @param FROM_ADDRESS            The address from XDC need to transfer.
     * @param TO_ADDRESS              The address to transfer to.
     * @param value                   The amount to be transferred.
     * @param eventCallback
     * @dev Transfer XDC for a specified address
     * @para
     */
    @SuppressWarnings("NewApi")
    public void TransferXdc(String PRIVATE_KEY_TRANSACTION, String FROM_ADDRESS, String TO_ADDRESS, String value, EventCallback eventCallback) {

        if (isWeb3jConnected()) {


            EthGetTransactionCount ethGetTransactionCount = null;
            try {
                ethGetTransactionCount = web3.ethGetTransactionCount(
                        FROM_ADDRESS, DefaultBlockParameterName.LATEST).sendAsync().get();
            } catch (
                    ExecutionException e) {
                e.printStackTrace();
                eventCallback.failure(e.getMessage());
            } catch (
                    InterruptedException e) {
                e.printStackTrace();
                eventCallback.failure(e.getMessage());
            }

            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            BigInteger gasPrice = null;
            try {
                EthGasPrice ethGasPrice = web3.ethGasPrice().sendAsync().get();
                gasPrice = ethGasPrice.getGasPrice();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
                    nonce, gasPrice, BigInteger.valueOf(50005), TO_ADDRESS, convertDecimalToHex(value));

            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, Credentials.create(PRIVATE_KEY_TRANSACTION));
            String hexValue = Numeric.toHexString(signedMessage);


            EthSendTransaction ethSendTransaction = null;
            try {
                ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();
            } catch (
                    ExecutionException e) {
                e.printStackTrace();
                eventCallback.failure(e.getMessage());

            } catch (
                    InterruptedException e) {
                e.printStackTrace();
                eventCallback.failure(e.getMessage());
            }

            String transactionHash = ethSendTransaction.getTransactionHash();
            eventCallback.success(transactionHash);

        } else {
            eventCallback.failure("Failed");
        }

    }


    /**
     * @param spender_address The address which will spend the funds.
     * @param value           The amount of tokens to be spent.
     * @param token_address   Token Address.
     * @param private_key     Owner Private key.
     * @dev Approve the passed address to spend the specified amount of tokens on behalf of msg.sender.
     * Beware that changing an allowance with this method brings the risk that someone may use both the old
     * and the new allowance by unfortunate transaction ordering. One possible solution to mitigate this
     * race condition is to first reduce the spender's allowance to 0 and set the desired value afterwards:
     */
    public String approveXRC20Token(String token_address, String private_key, String spender_address, String value) throws ExecutionException, InterruptedException, IOException {

        //Load the required data for the approve, with the private key
        if (isWeb3jConnected()) {
            Credentials credentials = Credentials.create(private_key);
            // Get nonce, the number of transactions
            BigInteger nonce;
            //token owner - wallet
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
            //XRC20 token contract method
            org.web3j.abi.datatypes.Function function = new Function(
                    "approve",
                    Arrays.asList(new Address(spender_address), new Uint256(BigInteger.valueOf(Long.parseLong(value)))),
                    Collections.singletonList(new TypeReference<Type>() {
                    }));
            //Create RawTransaction transaction object
            String encodedFunction = FunctionEncoder.encode(function);
            //token address
            RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit,
                    token_address, encodedFunction);

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


    /**
     * @param receiver_add  The address to transfer to.
     * @param value         The amount to be transferred.
     * @param token_address Token which need to be transfered
     * @param private_key   Owner private key to do Transaction.
     * @dev Transfer token for a specified address
     */
    public String transferXRC20Token(String token_address, String private_key, String receiver_add, String value) throws ExecutionException, InterruptedException, IOException {
        if (isWeb3jConnected()) {
            //Load the required documents for the transfer, with the private key
            Credentials credentials = Credentials.create(private_key);
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
            //XRC20 token contract method

            Function function = new Function(
                    "transfer",
                    Arrays.asList(new Address(receiver_add), new Uint256(convertDecimalToHex(value))),
                    Collections.singletonList(new TypeReference<Type>() {
                    }));
            //Create RawTransaction transaction object
            String encodedFunction = FunctionEncoder.encode(function);
            RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit,
                    token_address, encodedFunction);

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


    /**
     * @param spender_address The address which will spend the funds.
     * @param value           The amount of tokens to increase the allowance by.
     * @param owner_Address   Token Owner Address
     * @param private_key     Owner Private key
     * @param token_address   Token Address for which , allownce need to to increase.
     * @dev Increase the amount of tokens that an owner allowed to a spender.
     * approve should be called when allowed_[_spender] == 0. To increment
     * allowed value is better to use this function to avoid 2 calls (and wait until
     * the first transaction is mined)
     */
    public String increaseAllownce(String owner_Address, String spender_address, String private_key, String value, String token_address) throws Exception {
        if (isWeb3jConnected()) {
            //Load the required documents for the transfer, with the private key
            Credentials credentials = Credentials.create(private_key);
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
            //XRC20 token contract method
            com.XDCJava.contracts.src.main.java.XRC20 javaToken = com.XDCJava.contracts.src.main.java.XRC20.load(token_address, web3, credentials, new DefaultGasProvider());
            BigInteger allowance = javaToken.allowance(owner_Address, spender_address).send();

            allowance = allowance.add(BigInteger.valueOf(Long.parseLong(value)));
            Function function = new Function(
                    "approve",
                    Arrays.asList(new Address(spender_address), new Uint256(allowance)),
                    Collections.singletonList(new TypeReference<Type>() {
                    }));
            //Create RawTransaction transaction object
            String encodedFunction = FunctionEncoder.encode(function);
            RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit,
                    token_address, encodedFunction);

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

    /**
     * @param spender_address The address which will spend the funds.
     * @param value           The amount of tokens to decrease the allowance by.
     * @param owner_Address   Token Owner Address
     * @param private_key     Owner Private key
     * @param token_address   Token Address for which , allownce need to to decrease.
     * @dev Decrease the amount of tokens that an owner allowed to a spender.
     * approve should be called when allowed_[_spender] == 0. To decrement
     * allowed value is better to use this function to avoid 2 calls (and wait until
     * the first transaction is mined)
     * From MonolithDAO Token.sol
     */
    public String decreaseAllownce(String owner_Address, String spender_address, String private_key, String value, String token_address) throws Exception {
        if (isWeb3jConnected()) {
            //Load the required documents for the transfer, with the private key
            Credentials credentials = Credentials.create(private_key);
            // Get nonce, the number of transactions
            BigInteger nonce;
            EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(owner_Address, DefaultBlockParameterName.LATEST).sendAsync().get();
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
            //XRC20 token contract method

            com.XDCJava.contracts.src.main.java.XRC20 javaToken = XRC20.load(token_address, web3, credentials, new DefaultGasProvider());
            BigInteger allowance = javaToken.allowance(owner_Address, spender_address).send();

            allowance = allowance.subtract(BigInteger.valueOf(Long.parseLong(value)));
            Function function = new Function(
                    "approve",
                    Arrays.asList(new Address(spender_address), new Uint256(allowance)),
                    Collections.singletonList(new TypeReference<Type>() {
                    }));
            //Create RawTransaction transaction object
            String encodedFunction = FunctionEncoder.encode(function);
            RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit,
                    token_address, encodedFunction);

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


    /**
     * @param spender_address address The address which you want to send tokens from
     * @param to_address      address The address which you want to transfer to
     * @param value           uint256 the amount of tokens to be transferred
     * @param private_key     Spender's Private key
     * @param token_Address   Token Address
     * @dev Transfer tokens from one address to another
     */
    public String transferfrom(String spender_address, String to_address, String private_key, String value, String token_Address) throws Exception {
        if (isWeb3jConnected()) {
            //Load the required documents for the transfer, with the private key
            //spender privatekey
            Credentials credentials = Credentials.create(private_key);
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
            //XRC20 token contract method
            final Function function = new Function(
                    "transferFrom",
                    Arrays.<Type>asList(new Address(spender_address),
                            new Address(to_address),
                            new Uint256(BigInteger.valueOf(Long.parseLong(value)))),
                    Collections.<TypeReference<?>>emptyList());


            //Create RawTransaction transaction object
            String encodedFunction = FunctionEncoder.encode(function);
            RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit,
                    token_Address, encodedFunction);

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


    public BigInteger convertDecimalToHex(String hexValue) {
        BigInteger value
                = new BigInteger(hexValue);
        BigInteger hexvalue
                = new BigInteger(AppConstants.hex_to_dec);

        return value.multiply(hexvalue);

    }

    public BigInteger converHexToDecimal(BigInteger hexValue) {

        BigInteger hexvalue
                = new BigInteger(AppConstants.hex_to_dec);

        return hexValue.divide(hexvalue);

    }


   /* private void setupBouncyCastle() {
        final Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        if (provider == null) {
            // Web3j will set up the provider lazily when it's first used.
            return;
        }
        if (provider.getClass().equals(BouncyCastleProvider.class)) {
            // BC with same package name, shouldn't happen in real life.
            return;
        }
        // Android registers its own BC provider. As it might be outdated and might not include
        // all needed ciphers, we substitute it with a known BC bundled in the app.
        // Android's BC has its package rewritten to "com.android.org.bouncycastle" and because
        // of that it's possible to have another BC implementation loaded in VM.
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }
*/

}
