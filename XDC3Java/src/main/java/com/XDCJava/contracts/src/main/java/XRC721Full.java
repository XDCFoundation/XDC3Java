package com.XDCJava.contracts.src.main.java;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class XRC721Full extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b5060405162001a4e38038062001a4e833981810160405260408110156200003757600080fd5b81019080805160405193929190846401000000008211156200005857600080fd5b9083019060208201858111156200006e57600080fd5b82516401000000008111828201881017156200008957600080fd5b82525081516020918201929091019080838360005b83811015620000b85781810151838201526020016200009e565b50505050905090810190601f168015620000e65780820380516001836020036101000a031916815260200191505b50604052602001805160405193929190846401000000008211156200010a57600080fd5b9083019060208201858111156200012057600080fd5b82516401000000008111828201881017156200013b57600080fd5b82525081516020918201929091019080838360005b838110156200016a57818101518382015260200162000150565b50505050905090810190601f168015620001985780820380516001836020036101000a031916815260200191505b5060405250839150829050620001be6301ffc9a760e01b6001600160e01b036200024516565b620001d96380ac58cd60e01b6001600160e01b036200024516565b620001f463780e9d6360e01b6001600160e01b036200024516565b815162000209906009906020850190620002ca565b5080516200021f90600a906020840190620002ca565b506200023b635b5e139f60e01b6001600160e01b036200024516565b505050506200036f565b6001600160e01b03198082161415620002a5576040805162461bcd60e51b815260206004820152601c60248201527f5852433136353a20696e76616c696420696e7465726661636520696400000000604482015290519081900360640190fd5b6001600160e01b0319166000908152602081905260409020805460ff19166001179055565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200030d57805160ff19168380011785556200033d565b828001600101855582156200033d579182015b828111156200033d57825182559160200191906001019062000320565b506200034b9291506200034f565b5090565b6200036c91905b808211156200034b576000815560010162000356565b90565b6116cf806200037f6000396000f3fe608060405234801561001057600080fd5b506004361061010b5760003560e01c80634f6ccce7116100a2578063a22cb46511610071578063a22cb46514610349578063b88d4fde14610377578063c87b56dd1461043d578063d3fc98641461045a578063e985e9c5146104df5761010b565b80634f6ccce7146102e15780636352211e146102fe57806370a082311461031b57806395d89b41146103415761010b565b806318160ddd116100de57806318160ddd1461022f57806323b872dd146102495780632f745c591461027f57806342842e0e146102ab5761010b565b806301ffc9a71461011057806306fdde031461014b578063081812fc146101c8578063095ea7b314610201575b600080fd5b6101376004803603602081101561012657600080fd5b50356001600160e01b03191661050d565b604080519115158252519081900360200190f35b61015361052c565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561018d578181015183820152602001610175565b50505050905090810190601f1680156101ba5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6101e5600480360360208110156101de57600080fd5b50356105c3565b604080516001600160a01b039092168252519081900360200190f35b61022d6004803603604081101561021757600080fd5b506001600160a01b038135169060200135610625565b005b610237610736565b60408051918252519081900360200190f35b61022d6004803603606081101561025f57600080fd5b506001600160a01b0381358116916020810135909116906040013561073c565b6102376004803603604081101561029557600080fd5b506001600160a01b038135169060200135610791565b61022d600480360360608110156102c157600080fd5b506001600160a01b03813581169160208101359091169060400135610810565b610237600480360360208110156102f757600080fd5b503561082b565b6101e56004803603602081101561031457600080fd5b5035610891565b6102376004803603602081101561033157600080fd5b50356001600160a01b03166108eb565b610153610953565b61022d6004803603604081101561035f57600080fd5b506001600160a01b03813516906020013515156109b4565b61022d6004803603608081101561038d57600080fd5b6001600160a01b038235811692602081013590911691604082013591908101906080810160608201356401000000008111156103c857600080fd5b8201836020820111156103da57600080fd5b803590602001918460018302840111640100000000831117156103fc57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610a80945050505050565b6101536004803603602081101561045357600080fd5b5035610ad8565b61022d6004803603606081101561047057600080fd5b6001600160a01b03823516916020810135918101906060810160408201356401000000008111156104a057600080fd5b8201836020820111156104b257600080fd5b803590602001918460018302840111640100000000831117156104d457600080fd5b509092509050610bbd565b610137600480360360408110156104f557600080fd5b506001600160a01b0381358116916020013516610c07565b6001600160e01b03191660009081526020819052604090205460ff1690565b60098054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156105b85780601f1061058d576101008083540402835291602001916105b8565b820191906000526020600020905b81548152906001019060200180831161059b57829003601f168201915b505050505090505b90565b60006105ce82610c35565b6106095760405162461bcd60e51b815260040180806020018281038252602c815260200180611517602c913960400191505060405180910390fd5b506000908152600260205260409020546001600160a01b031690565b600061063082610891565b9050806001600160a01b0316836001600160a01b031614156106835760405162461bcd60e51b81526004018080602001828103825260218152602001806116496021913960400191505060405180910390fd5b336001600160a01b038216148061069f575061069f8133610c07565b6106da5760405162461bcd60e51b81526004018080602001828103825260388152602001806115ba6038913960400191505060405180910390fd5b60008281526002602052604080822080546001600160a01b0319166001600160a01b0387811691821790925591518593918516917f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92591a4505050565b60075490565b6107463382610c52565b6107815760405162461bcd60e51b815260040180806020018281038252603181526020018061166a6031913960400191505060405180910390fd5b61078c838383610cf6565b505050565b600061079c836108eb565b82106107d95760405162461bcd60e51b815260040180806020018281038252602b81526020018061161e602b913960400191505060405180910390fd5b6001600160a01b03831660009081526005602052604090208054839081106107fd57fe5b9060005260206000200154905092915050565b61078c83838360405180602001604052806000815250610a80565b6000610835610736565b82106108725760405162461bcd60e51b815260040180806020018281038252602c8152602001806114eb602c913960400191505060405180910390fd5b6007828154811061087f57fe5b90600052602060002001549050919050565b6000818152600160205260408120546001600160a01b0316806108e55760405162461bcd60e51b81526004018080602001828103825260298152602001806114966029913960400191505060405180910390fd5b92915050565b60006001600160a01b0382166109325760405162461bcd60e51b815260040180806020018281038252602a81526020018061156c602a913960400191505060405180910390fd5b6001600160a01b03821660009081526003602052604090206108e590610d15565b600a8054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156105b85780601f1061058d576101008083540402835291602001916105b8565b6001600160a01b038216331415610a12576040805162461bcd60e51b815260206004820152601960248201527f5852433732313a20617070726f766520746f2063616c6c657200000000000000604482015290519081900360640190fd5b3360008181526004602090815260408083206001600160a01b03871680855290835292819020805460ff1916861515908117909155815190815290519293927f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31929181900390910190a35050565b610a8b84848461073c565b610a9784848484610d19565b610ad25760405162461bcd60e51b81526004018080602001828103825260328152602001806114646032913960400191505060405180910390fd5b50505050565b6060610ae382610c35565b610b1e5760405162461bcd60e51b815260040180806020018281038252602f815260200180611435602f913960400191505060405180910390fd5b6000828152600b602090815260409182902080548351601f600260001961010060018616150201909316929092049182018490048402810184019094528084529091830182828015610bb15780601f10610b8657610100808354040283529160200191610bb1565b820191906000526020600020905b815481529060010190602001808311610b9457829003601f168201915b50505050509050919050565b610bc78484610e4c565b610ad28383838080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250610e6d92505050565b6001600160a01b03918216600090815260046020908152604080832093909416825291909152205460ff1690565b6000908152600160205260409020546001600160a01b0316151590565b6000610c5d82610c35565b610c985760405162461bcd60e51b815260040180806020018281038252602c8152602001806115f2602c913960400191505060405180910390fd5b6000610ca383610891565b9050806001600160a01b0316846001600160a01b03161480610cde5750836001600160a01b0316610cd3846105c3565b6001600160a01b0316145b80610cee5750610cee8185610c07565b949350505050565b610d01838383610ed0565b610d0b8382611014565b61078c8282611109565b5490565b6000610d2d846001600160a01b0316611147565b610d3957506001610cee565b60405163e82388f360e01b815233600482018181526001600160a01b03888116602485015260448401879052608060648501908152865160848601528651600095928a169463e82388f39490938c938b938b939260a4019060208501908083838e5b83811015610db3578181015183820152602001610d9b565b50505050905090810190601f168015610de05780820380516001836020036101000a031916815260200191505b5095505050505050602060405180830381600087803b158015610e0257600080fd5b505af1158015610e16573d6000803e3d6000fd5b505050506040513d6020811015610e2c57600080fd5b50516001600160e01b031916630a85bd0160e11b14915050949350505050565b610e56828261114d565b610e608282611109565b610e698161127e565b5050565b610e7682610c35565b610eb15760405162461bcd60e51b815260040180806020018281038252602c8152602001806114bf602c913960400191505060405180910390fd5b6000828152600b60209081526040909120825161078c9284019061137c565b826001600160a01b0316610ee382610891565b6001600160a01b031614610f285760405162461bcd60e51b81526004018080602001828103825260298152602001806115436029913960400191505060405180910390fd5b6001600160a01b038216610f6d5760405162461bcd60e51b81526004018080602001828103825260248152602001806115966024913960400191505060405180910390fd5b610f76816112c2565b6001600160a01b0383166000908152600360205260409020610f97906112ff565b6001600160a01b0382166000908152600360205260409020610fb890611316565b60008181526001602052604080822080546001600160a01b0319166001600160a01b0386811691821790925591518493918716917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef91a4505050565b6001600160a01b03821660009081526005602052604081205461103e90600163ffffffff61131f16565b6000838152600660205260409020549091508082146110d9576001600160a01b038416600090815260056020526040812080548490811061107b57fe5b906000526020600020015490508060056000876001600160a01b03166001600160a01b0316815260200190815260200160002083815481106110b957fe5b600091825260208083209091019290925591825260069052604090208190555b6001600160a01b03841660009081526005602052604090208054906111029060001983016113fa565b5050505050565b6001600160a01b0390911660009081526005602081815260408084208054868652600684529185208290559282526001810183559183529091200155565b3b151590565b6001600160a01b0382166111a8576040805162461bcd60e51b815260206004820181905260248201527f5852433732313a206d696e7420746f20746865207a65726f2061646472657373604482015290519081900360640190fd5b6111b181610c35565b15611203576040805162461bcd60e51b815260206004820152601c60248201527f5852433732313a20746f6b656e20616c7265616479206d696e74656400000000604482015290519081900360640190fd5b600081815260016020908152604080832080546001600160a01b0319166001600160a01b03871690811790915583526003909152902061124290611316565b60405181906001600160a01b038416906000907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908290a45050565b600780546000838152600860205260408120829055600182018355919091527fa66cc928b5edb82af9bd49922954155ab7b0942694bea4ce44661d9a8736c6880155565b6000818152600260205260409020546001600160a01b0316156112fc57600081815260026020526040902080546001600160a01b03191690555b50565b805461131290600163ffffffff61131f16565b9055565b80546001019055565b600082821115611376576040805162461bcd60e51b815260206004820152601e60248201527f536166654d6174683a207375627472616374696f6e206f766572666c6f770000604482015290519081900360640190fd5b50900390565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106113bd57805160ff19168380011785556113ea565b828001600101855582156113ea579182015b828111156113ea5782518255916020019190600101906113cf565b506113f692915061141a565b5090565b81548183558181111561078c5760008381526020902061078c9181019083015b6105c091905b808211156113f6576000815560010161142056fe5852433732314d657461646174613a2055524920717565727920666f72206e6f6e6578697374656e7420746f6b656e5852433732313a207472616e7366657220746f206e6f6e20585243373231526563656976657220696d706c656d656e7465725852433732313a206f776e657220717565727920666f72206e6f6e6578697374656e7420746f6b656e5852433732314d657461646174613a2055524920736574206f66206e6f6e6578697374656e7420746f6b656e585243373231456e756d657261626c653a20676c6f62616c20696e646578206f7574206f6620626f756e64735852433732313a20617070726f76656420717565727920666f72206e6f6e6578697374656e7420746f6b656e5852433732313a207472616e73666572206f6620746f6b656e2074686174206973206e6f74206f776e5852433732313a2062616c616e636520717565727920666f7220746865207a65726f20616464726573735852433732313a207472616e7366657220746f20746865207a65726f20616464726573735852433732313a20617070726f76652063616c6c6572206973206e6f74206f776e6572206e6f7220617070726f76656420666f7220616c6c5852433732313a206f70657261746f7220717565727920666f72206e6f6e6578697374656e7420746f6b656e585243373231456e756d657261626c653a206f776e657220696e646578206f7574206f6620626f756e64735852433732313a20617070726f76616c20746f2063757272656e74206f776e65725852433732313a207472616e736665722063616c6c6572206973206e6f74206f776e6572206e6f7220617070726f766564a265627a7a723158202a23a05187e4d9e472bc1f284166a14764949936b5a5bc73e369009f159f1e5f64736f6c63430005110032";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_safeTransferFrom = "safeTransferFrom";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOKENBYINDEX = "tokenByIndex";

    public static final String FUNC_TOKENOFOWNERBYINDEX = "tokenOfOwnerByIndex";

    public static final String FUNC_TOKENURI = "tokenURI";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected XRC721Full(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected XRC721Full(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected XRC721Full(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected XRC721Full(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalForAllEventResponse>() {
            @Override
            public ApprovalForAllEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new Address(160, to),
                new Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new Address(160, owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED, 
                Arrays.<Type>asList(new Uint256(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String owner, String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new Address(160, owner),
                new Address(160, operator)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(String _to, BigInteger _tokenId, String _uri) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new Address(160, _to),
                new Uint256(_tokenId),
                new Utf8String(_uri)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> ownerOf(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new Uint256(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new Address(160, from),
                new Address(160, to),
                new Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId, byte[] _data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new Address(160, from),
                new Address(160, to),
                new Uint256(tokenId),
                new org.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String to, Boolean approved) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new Address(160, to),
                new Bool(approved)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> tokenByIndex(BigInteger index)
    {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENBYINDEX, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> tokenOfOwnerByIndex(String owner, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENOFOWNERBYINDEX, 
                Arrays.<Type>asList(new Address(160, owner),
                new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> tokenURI(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENURI, 
                Arrays.<Type>asList(new Uint256(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokenId)
    {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new Address(160, from),
                new Address(160, to),
                new Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static XRC721Full load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new XRC721Full(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static XRC721Full load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new XRC721Full(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static XRC721Full load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new XRC721Full(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static XRC721Full load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new XRC721Full(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<XRC721Full> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(name),
                new Utf8String(symbol)));
        return deployRemoteCall(XRC721Full.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<XRC721Full> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(name),
                new Utf8String(symbol)));
        return deployRemoteCall(XRC721Full.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<XRC721Full> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(name),
                new Utf8String(symbol)));
        return deployRemoteCall(XRC721Full.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<XRC721Full> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(name),
                new Utf8String(symbol)));
        return deployRemoteCall(XRC721Full.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String approved;

        public BigInteger tokenId;
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String owner;

        public String operator;

        public Boolean approved;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger tokenId;
    }
}
