package com.XDCJava;

import android.util.Log;

import com.XDC.Example.Config20;
import com.XDCJava.Model.TokenDetailsResponse;
import com.XDCJava.callback.TokenDetailCallback;

import junit.framework.TestCase;

import java.math.BigInteger;

public class XDC20ClientTest extends TestCase {

    private static final String TAG = "TestXDC20" ;

    public void testGetTokenoinfo() {
        try {
            XDC20Client.getInstance().getTokenoinfo(Config20.TOKEN_ADDRESS, new TokenDetailCallback() {
                @Override
                public void success(TokenDetailsResponse tokenApiModel)
                {

                    assertEquals(tokenApiModel.getName(),"SDK Testing");
                    assertEquals(tokenApiModel.getDecimal(), BigInteger.valueOf(18));
                    assertEquals(tokenApiModel.getSymbol(),"SDK");
                }

                @Override
                public void failure(Throwable t) {

                }

                @Override
                public void failure(String message) {

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void testGetAllowance() {

        try {
            String allowance = XDC20Client.getInstance().getAllowance(Config20.TOKEN_ADDRESS,Config20.OWNER_ADDRESS,Config20.SPENDER_ADDRESS);
            System.out.println("testAllowance: "+allowance);
            assertNotNull(allowance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testGetBalance() {
        try {
            String balance = XDC20Client.getInstance().getBalance(Config20.TOKEN_ADDRESS,Config20.OWNER_ADDRESS);
            System.out.println("testBalance: "+balance);
            assertNotNull(balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void testTransferXdc() {
        try {
            String transferXdc = XDC20Client.getInstance().TransferXdc(Config20.OWNER_PRIVATE_KEY,Config20.OWNER_ADDRESS,Config20.SPENDER_ADDRESS,BigInteger.valueOf(50000), Long.valueOf(30000),Long.valueOf(30000));
            System.out.println("testTransferXdc: "+transferXdc);
            assertNotNull(transferXdc);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void testApproveXRC20Token() {
        try {
            String approveXRC20Token = XDC20Client.getInstance().approveXRC20Token(Config20.TOKEN_ADDRESS,Config20.OWNER_PRIVATE_KEY,Config20.SPENDER_ADDRESS,Config20.VALUE);
            System.out.println("testApprove: "+approveXRC20Token);
            assertNotNull(approveXRC20Token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testTransferXRC20Token() {

        try {
            String transferXRC20Token = XDC20Client.getInstance().transferXRC20Token(Config20.TOKEN_ADDRESS,Config20.OWNER_PRIVATE_KEY,Config20.SPENDER_ADDRESS,Config20.VALUE);
            System.out.println("testTransferXRC20Token: "+transferXRC20Token);
            assertNotNull(transferXRC20Token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testIncreaseAllownce() {
        try {
            String increaseAllownce = XDC20Client.getInstance().increaseAllownce(Config20.OWNER_ADDRESS,Config20.SPENDER_ADDRESS,Config20.OWNER_PRIVATE_KEY,Config20.VALUE,Config20.TOKEN_ADDRESS);
            System.out.println("testIncreaseAllownce: "+increaseAllownce);
            assertNotNull(increaseAllownce);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testDecreaseAllownce() {
        try {
            String decreaseAllownce = XDC20Client.getInstance().decreaseAllownce(Config20.OWNER_ADDRESS,Config20.SPENDER_ADDRESS,Config20.OWNER_PRIVATE_KEY,Config20.VALUE,Config20.TOKEN_ADDRESS);
            System.out.println("testDecreaseAllownce: "+decreaseAllownce);
            assertNotNull(decreaseAllownce);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testTransferfrom() {

        try {
            String transferfrom = XDC20Client.getInstance().transferfrom(Config20.SPENDER_ADDRESS,Config20.TRANSFER_FROM_ADDRESS,Config20.SPENDER_PRIVATE_KEY,Config20.VALUE,Config20.TOKEN_ADDRESS);
            System.out.println("testTransferfrom: "+transferfrom);
            assertNotNull(transferfrom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testImportWallet()
    {
    }

    public void testGetXdcBalance() {
    }
}