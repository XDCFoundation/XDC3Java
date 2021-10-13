# XDC3Java
### Usage

JAR file 

### Add XDC3Java to your project:

To add module dependency in android application

To add module dependency in your project follow the steps below :

go to file menu — project structure — modules — click ‘+’ — select import gradle Project — select the sub-module folder (git repo which is created by us) — select “XDC3Java“ folder — finish

you can see the  folder in your android project

Now we have to add Module Dependency in the application project build.gradle file

put “implementation project(":XDC3Java")“ in dependencies

Now we can access the methods of XDC3Java library in Any

### Accessing methods of XRC20 in our project
```
  XDC20Client.getInstance().getTokenoinfo(token_address, new TokenDetailCallback() {
                    @Override
                    public void success(TokenDetailsResponse tokenDetailsResponse)
                    {
                       // you can proceed as you like after getting succes response.
                    }

                    @Override
                    public void failure(Throwable t)
                    {
                        Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(String message)
                    {
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                    }
                });
```

Now, we can interact with the XRC20 read methods.

name() → string Returns the name of the token.
```
tokenResponse.getName();
```
 balanceOf(address token,address account) → uint256  Returns the amount of tokens owned by account.
```
 tokenResponse.getBalance();
```
For write methods, we need to create an instance of XDC20Client Client and we need token owner private key
```
Credentials creds = org.web3j.crypto.Credentials.create(PRIVATE_KEY);
```
transfer(address token, address recipient, uint256 amount) → Moves amount tokens from the caller’s account to recipient. It will return a transaction hash.
```
  String trasaction_hash = XDC20Client.getInstance().TransferXdc(private_key, sender_address, receiver_address, new BigInteger(String.valueOf(token_totransfer), Long.parseLong(gasprice), Long.parseLong(gaslimit;
```
approve(address token, address spender, uint256 amount) → Sets amount as the allowance of spender over the caller’s tokens. It will return a transaction hash.
```
approved_hash = XDC20Client.getInstance().approveXRC20Token(Spender_address, privatekey, allownce_spender, value_approve);                    
```
For increase Allowance and decrease Allowance we need an instance of XRC20 and private key of owner: 
```
decreaseAllowance(String privatekey, address token, address owner, address spender, uint256 subtractedValue)Automatically decreases the allowance granted to spender by the caller.
```
This is an alternative to approve.

Emits an Approval event indicating the updated allowance.
```
 approved_hash = XDC20Client.getInstance().decreaseAllownce(decrease_owner, decrease_spender, privatekey, decrease_allownce, Spender_address);                     
```



### Accessing methods of XRC721 in our project
```
  XDC721Client.getInstance().getTokenoinfo(token_address, new TokenDetailCallback() {
                    @Override
                    public void success(Token721DetailCallback tokenDetailsResponse)
                    {
                       // you can proceed as you like after getting succes response.
                    }

                    @Override
                    public void failure(Throwable t)
                    {
                        Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(String message)
                    {
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                    }
                });
```

Now, we can interact with the XRC721 read methods.

name() → string Returns the name of the token.
```
tokenResponse.getName();
```
 symbol() → uint256  Returns the Symbol of token.
```
 tokenResponse.getSymbol();
```
For write methods, we need to create an instance of XDC721Client Client and we need token owner private key
```
Credentials creds = org.web3j.crypto.Credentials.create(PRIVATE_KEY);
```
transferfrom(address token, address recipient, uint256 amount) → Transfer ownership of an NFT. It will return a transaction hash.
```
  String trasaction_hash = XDC721Client.getInstance().transferfrom(tokenAddress, privatekey, tokenid, receiverAddress);
```
approve(address token, address spender, uint256 tokenid) → Change or reaffirm the approved address for an NFT. It will return a transaction hash.
```
approved_hash = XDC721Client.getInstance().approve(tokenAddress, privatekey, tokenid, receiverAddress);                    
```
