pragma solidity ^0.8.0;

import "./token/ERC20/ERC20.sol";

contract MyToken is ERC20{
    uint public INITIAL_SUPPLY = 10000000;
    
    constructor() ERC20("ClubCoin","CC"){
        _mint(msg.sender, INITIAL_SUPPLY); 
        
    }

    struct Recode {
        address fromAddr;
        address toAddr;
        uint value;
        string date;
        string message;
    }
    Recode[] public Recodes;
    
    function saveRecode(address _fromAddr,address _toAddr,uint _value,string memory _date,string memory _message) public
    {
        Recodes.push(
            Recode({
                fromAddr: _fromAddr,
                toAddr: _toAddr,
                value: _value,
                date: _date,
                message: _message
            })
        );
    }
    
    function loadRecode() external returns (Recode[] memory)
    {
         return Recodes;
    }
}
