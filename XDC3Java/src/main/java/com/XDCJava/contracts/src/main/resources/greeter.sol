pragma solidity ^0.5.0;

import "https://github.com/OpenZeppelin/openzeppelin-contracts/blob/docs-org/contracts/token/ERC721/ERC721.sol";
import "https://github.com/OpenZeppelin/openzeppelin-contracts/blob/docs-org/contracts/token/ERC721/ERC721Enumerable.sol";
import "https://github.com/OpenZeppelin/openzeppelin-contracts/blob/docs-org/contracts/token/ERC721/ERC721Metadata.sol";



contract greeter is ERC721, ERC721Enumerable, ERC721Metadata{

    /* Owner of this contract */
    address owner;

    /* Counter for deposits calls */
    uint public deposits;

    /* Configurable greeting */
    string greeting;


    // Token name
    string private _name;

    // Token symbol
    string private _symbol;


    /* Constructor runs when contract is deployed */
    /*  function greeter(string memory name, string memory symbol) public {
          owner = msg.sender;
          //greeting = _greeting;
          deposits = 0;
          super._mint(_to, _tokenId);
          super._setTokenURI(_tokenId, _uri);
      }*/


    constructor (string memory name, string memory symbol)
    public ERC721Metadata(name, symbol)
    {
    }

    /*constructor (string memory name, string memory symbol) public {
           _name = name;
           _symbol = symbol;

           // register the supported interfaces to conform to ERC721 via ERC165
           ///_registerInterface(_INTERFACE_ID_ERC721_METADATA);
       }

   */
    /*
     * Default function.
     * 'payable': Allows to move funds to contract.
     * Changes state: Costs gas and needs contract transaction.
     */
    function() payable external {
        deposits += 1;
    }

    /* Main function */
    /* function greet() constant returns (string) {
         return greeting;
     }*/

    /* Function to recover the funds on the contract */
    /*function kill() external {
        if (msg.sender == owner)
            selfdestruct(owner);
    }*/
}