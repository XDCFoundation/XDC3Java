package com.XDCJava;


import java.math.BigInteger;

public class AppConstants {


   public static final String BASE_URL = "https://rpc.apothem.network/";
    public static final String  hex_to_dec = "1000000000000000000";

   // public static final String BASE_URL = "https://ropsten.infura.io/v3/b2f4b3f635d8425c96854c3d28ba6bb0";
//  public static final String BASE_URL = "https://ropsten.infura.io/v3/ab6722395ac345879dcd1bd954dac1fb";


    // see https://www.reddit.com/r/ethereum/comments/5g8ia6/attention_miners_we_recommend_raising_gas_limit/
    public static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);

    // http://ethereum.stackexchange.com/questions/1832/cant-send-transaction-exceeds-block-gas-limit-or-intrinsic-gas-too-low
    public static final BigInteger GAS_LIMIT_ETHER_TX = BigInteger.valueOf(21_000);
}
