# Sub1 기능/과제

# 1. 기능/과제 목록

### 1) 프라이빗 이더리움 네트워크 구축

### 2) 이더리움 계정 생성

### 3) 이더리움 트랜잭션 생성

### 4) 스마트 컨트랙트 배포

# 2. 기능/과제 상세

- 구성도
- 노드별 Geth 설정
- 프라이빗 이더리움 네트워크 정보

## ① 프라이빗 이더리움 네트워크 구축

### 1) 가상 머신 구성

- [x]  Virtual 박스 설치

    → 개발환경 설정에서 끝냄

- [x]  Vagrant 설치

    → 개발환경 설정에서 끝냄

- [x]  이더리움 네트워크 구축용 VM 2대 생성 (VM name: eth0, eht1)

    → 개발환경 설정에서 끝냄

### 2) 이더리움 eth0 노드 구성

- [x]  이더리움 소프트웨어는 Geth 1.9(stable) 이상을 사용
- [x]  Geth는 가상 머신 상에서 동작하도록 구축

    ```bash
    vagrant up
    vagrant ssh eht0
    $ sudo apt-get udate
    $ sudo apt-get install software-properties-common
    $ sudo add-apt-repository -y ppa:ehtereum/ethereum
    $ sudo apt-get install ethereum
    $ geth version

    # eth01 가상 머신에서 디렉토리 생성
    $ mkdir -p dev/eth_localdata
    $ cd dev/eth_localdata

    # 프라이빗 이더리움을 위한 genesis 블록 파일 생성
    $ vi CustomGenesis.json

    # Geth 초기화
    $ geth --datadir /home/vagarnt/dev/eth_localdata init /home/vagarnt/dev/eth_localdata/CustomGenesis.json
    ```

- [ ]  eth0 노드의 경우 RPC API를 호출할 수 있도록 활성화
- 참고 [https://dongsik93.github.io/pro/2019/08/22/pro-blockchain-private-ethereum/](https://dongsik93.github.io/pro/2019/08/22/pro-blockchain-private-ethereum/)

[제목 없음](https://www.notion.so/ced3586810aa40de9f457eba298e0058)

```bash
geth --datadir ~/dev/eth_localdata --networkid 921 --maxpeers 2 --port 30303 --rpc --rpcport 8545 --mine console
```

### 3) 이더리움 eth1 노드 구성

- [x]  이더리움 소프트웨어는 Geth 1.9(stable) 이상을 사용
- [x]  Geth는 가상 머신 상에서 동작하도록 구축

```bash
// 다른 cmd 창. vagrant up은 eth0에서 했으므로 생략 가능
vagrant ssh eth1
$ sudo apt-get udate
$ sudo apt-get install software-properties-common
$ sudo add-apt-repository -y ppa:ehtereum/ethereum
$ sudo apt-get install ethereum
$ geth version

# eth01 가상 머신에서 디렉토리 생성
$ mkdir -p dev/eth_localdata
$ cd dev/eth_localdata

# Geth 초기화
$ geth --datadir ~/dev/eth_localdata init CustomGenesis.json
```

---

Genesis Block은 블록체인에서 가장 첫번째로 생성되는 블록으로, 이전 블록에 대한 정보를 갖고 있지 않은 유일한 블록입니다. 이더리움은 이 블록에 많은 것들을 저장할 수 있도록 다양하나 옵션을 지원하며, 원하는 옵션을 구현함으로써 사설 블록체인 네트워크를 구축할 수 있습니다. 네트워크에 참여하는 노드들은 모두 이 블록을 가지고 있어야 참여할 수 있습니다.

```bash
# Genesis bloack 만들기 - 초기 블록
{
    "config": {
        "chainId": 921,
        "homesteadBlock": 0,
        "eip155Block": 0,
        "eip158Block": 0,
				"eip150Block": 0,
     },
     "nonce": "0xdeadbeefdeadbeef",
     "timestamp": "0x00",
     "extraData": "0x00",
     "gasLimit": "9999999",
     "difficulty": "0x10",
     "mixhash": "0x0000000000000000000000000000000000000000000000000000000000000000",
     "coinbase": "0x3333333333333333333333333333333333333333",
     "alloc": {}
}
```

- config : 이더리운 관련 설정
- conifg.chainId: 현재 chain을 구별하느 값. replay attack으로부터 보호해줌
    - replay attack? 네트워크 공격의 한 종류. 유효한 데이터 전송을 악의적으로 반복시키거나 지연시킴
- config.homesteadBlock : 이더리움의 4단계 로드맵 중 두번째 메이저 단계. 0 == true
- config.eip155Block : 개발자들이 이더리움을 업그레이드 하기 위해 제안된 아이디어. replayattack을 막기 위한 설정
- config.eip158Block : state clearing. 어카운트에서 상태 변경이 이루어지고, 이 변경으로 인해 계정이 nonce=0, balance=0, code및 storage가 빈 값이 되면 어카운트를 삭제

    → 여기까진 사설 블록체인을 만들 때 기본적으로 동일!

- difficulty : 채굴 난이도. 값이 클수록 난이도 상승, 채굴이 오래 걸림
- gasLimit : 블록당 가스(수수료)
- timestamp: 해당 블록이 취득된 시점. 유닉스 타임스탬프 값이 들어감. 어차피 최초의 블록이므로 0으로 설정해도 ok. 블록 간 순서 및 난이도 조절 (간격이 짧으면 쉽고, 길면 어려움)
- nonce, minhash : 블록이 제대로 채굴되는지 증명하는 옵션. 이 두개가 조합된 hash값이 일정한 수 이하힌 nonce를 찾아 블록체인을 증명한다. 가장 최근에 추가된 블록의 헤더의 해시가 nonce 값의 조합으로 일정 수를 찾아 작업증명을 완료하게 된다. 공격자가 잘못된 nonce로 블록을 만들 경우 빠르게 확인하기 위해서이다. mixhash는 nonce를 찾기 위한 중간 계산 값. 제네시스 블록에서는 둘 다 의미가 없느넫, 채굴이 아니고 내가 만든 거니가! 랜덤한 값을 넣어 누군가 우연히 똑같은 제네시스 블록으로 체인을 연결하지 않도록 할 수도 있다.
- coinbase : 블록 채굴시 주어지는 보상. alloc 맘대로 설정할거면 없어도 됨
- parentHash : 제네시스 블록은 부모가 없다.
- extraData
- alloc : 블록 생성과 동시에 여기에 주소를 적어두면 이더를 원하는 만큼 보낼 수 있음

- eip150Block 안하고 초기화하자 에러 생겨서 추가했음!

    ![Untitled](Sub1%20%E1%84%80%E1%85%B5%E1%84%82%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%203358f61301624e40a2f15bd06ef9be19/Untitled.png)

    ```bash
    : geth --datadir ~/dev/eth_localdata --networkid 921 --http --http.addr "0.0.0.0" --http.port "8545" --http.api "admin, eth, debug, miner, net, txpool, personal, web3"  --port "30303" --maxpeers 2 --mine console
    ```

    오류 발생! mine 빼면 동작함→ 초기 계정 없어서 그런듯? 빼고 설정한다음 다시 넣으면 동작 됨

## ② 이더리움 계정 생성

> geth 콘솔에서 명령어를 사용하여 진행할 수 있음

### 1) 계정 생성

- [x]  사용자 계정은 노드마다 최소 1개 이상 생성
- [x]  계정 생성에 따른 keystore 파일 확인
1. 위의 긴 geth 명령어로 실행 후
2. `personal.newAccount()` 로 계정생성
3. 확인

    ![Untitled](Sub1%20%E1%84%80%E1%85%B5%E1%84%82%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%203358f61301624e40a2f15bd06ef9be19/Untitled%201.png)

4. 종료 (ctrl+d)
5. keystore 파일 확인

    ![Untitled](Sub1%20%E1%84%80%E1%85%B5%E1%84%82%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%203358f61301624e40a2f15bd06ef9be19/Untitled%202.png)

### 2) 코인베이스 설정

- [x]  코인베이스 : 블록 생성에 따른 보상금 지급 계정
- [x]  노드마다 생성한 계정 중 하나를 코인베이스로 설정
- 자동 설정된다
- 확인 방법
    1. geth 실행

        ```bash
        geth --datadir ~/dev/eth_localdata --networkid 921 --maxpeers 2 --port 30303 --rpc --rpcport 8545 --rpcaddr 0.0.0.0 --rpcapi "admin, net, miner, eth, rpc, web3, txpool, debug, db, personal" --mine console
        --http --http.addr 0.0.0.0 --http.port "8545" --http.corsdomain "*"   
        ```

    2. eth.coinbase 로 확인

        ```bash
        > eth.coinbase
        ```

    3. 결과

        ![Untitled](Sub1%20%E1%84%80%E1%85%B5%E1%84%82%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%203358f61301624e40a2f15bd06ef9be19/Untitled%203.png)

### 3) 마이닝 시작

- [x]  모든 노드에서 마이닝 시작
    - `miner.start(thread_num)`
    - null 리턴돼도 정상! 버전 차이
    - thread_num 비워도 동작함

        ![Untitled](Sub1%20%E1%84%80%E1%85%B5%E1%84%82%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%203358f61301624e40a2f15bd06ef9be19/Untitled%204.png)

- [x]  마이닝 진행 상태 확인
    - `eth.mining` 채굴되고 있는가?

        ![Untitled](Sub1%20%E1%84%80%E1%85%B5%E1%84%82%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%203358f61301624e40a2f15bd06ef9be19/Untitled%205.png)

    - 해시 속도 `eth.hashrate`
        - 채굴하는데 사용하는 연산력
        - 1 이상 나와야 정상

            ![Untitled](Sub1%20%E1%84%80%E1%85%B5%E1%84%82%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%203358f61301624e40a2f15bd06ef9be19/Untitled%206.png)

- [x]  일정량 마이닝 진행 후 중단

    → 가상 머신에서 구동하는 환경 상 호스팅 pc에 발생하는 부하를 고려하여 중단

    - `miner.stop()`

### 4) 마이닝 결과 확인

- [x]  계정별 잔액 확인
    - `eth.getBalance(eth.coinbase)`

        ![Untitled](Sub1%20%E1%84%80%E1%85%B5%E1%84%82%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%203358f61301624e40a2f15bd06ef9be19/Untitled%207.png)

        ![Untitled](Sub1%20%E1%84%80%E1%85%B5%E1%84%82%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%203358f61301624e40a2f15bd06ef9be19/Untitled%208.png)

- [x]  생성된 블록 수 조회
    - `eth.blockNumber`

        ![Untitled](Sub1%20%E1%84%80%E1%85%B5%E1%84%82%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%203358f61301624e40a2f15bd06ef9be19/Untitled%209.png)

        ![Untitled](Sub1%20%E1%84%80%E1%85%B5%E1%84%82%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%203358f61301624e40a2f15bd06ef9be19/Untitled%2010.png)

- [x]  블록의 상세 정보 조회
    - eth.getBlock(1)

        ![Untitled](Sub1%20%E1%84%80%E1%85%B5%E1%84%82%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%203358f61301624e40a2f15bd06ef9be19/Untitled%2011.png)

        ![Untitled](Sub1%20%E1%84%80%E1%85%B5%E1%84%82%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%203358f61301624e40a2f15bd06ef9be19/Untitled%2012.png)

## ③ 이더리움 트랜잭션 생성

> Geth 콘솔에서 명령어를 사용하여 진행할 수 있음

### 1) 트랜잭션 생성

- [x]  계정 간 이더 전송 트랜잭션 생성

    ```bash
    eth0: "0x981713beb94841102ea7489e37999c970e46d18f"
    eth1: "0x19378bc30d1322f61656c4c95b9324c64071353d"

    from: "0x981713beb94841102ea7489e37999c970e46d18f"
    to: "0x19378bc30d1322f61656c4c95b9324c64071353d"
    tx= {from:from, to:to, value:web3.toWei(2,"ether")}
    eth.sendTransaction(tx)
    # 권한 해제 오류가 나오면 실행시 --allow-insecure-unlock 옵션 추가하기

    personal.unlockAccount(eth.accounts[0], "비밀번호")
    > true

    # 트랜잭션 해시 값 확인을 위해 변수에 저장
    tx_hash = eth.sendTransaction(tx) 

    # 해시 값 확인
    eht.getTransaction(tx_hash)

    # 잔액 확인
    web3.fromWei(eth.getBalance(from), "ether")
    ```

- [x]  전송할 이더량은 임의로 지정
- [x]  트랜잭션 해시 값 확인
- [x]  트랜잭션 상태 조회

### 2) 트랜잭션 결과 확인

- [x]  마이닝 재시작(일정 시간 수행)
- [x]  트랜잭션 상태 조회
- [x]  마이닝에 따른 트랜잭션 처리가 완료되면 마이닝 중단
- 확인을 위해 이더의 약 90%를 전송, from의 잔액이 줄어들은 것을 확인
- 하지만 받는 쪽의 잔액은 늘지 않았다.
- 연결문제?

## ④ 스마트 컨트랙트 배포

### 1) eth0 노드 확인

- [ ]  VirtualBox 또는 Vagrant 에서 eth0 VM 에 대한 포트 포워딩 확인
    - Host 8545 → Guest 8545
- [ ]  eth0의 keystore을 json 파일로 저장

### 2) Metamask 설정

- [ ]  Chrome 확장 프로그램 검색
- [ ]  Metamask 설치 및 실행
- [ ]  Metamask의 Custom RPC 옵션 설정
- [ ]  Metamask에서 계정 Import(eth0의 keystore json 파일 활용)
- [ ]  Metamask에서 계정 및 잔액 정보 확인

### 3) 스마트 컨트랙트 배포(Remix)

- [ ]  Remix 접속
- [ ]  Deploy & Run Transactions으로 이동
- [ ]  Environment를 로컬 이더리움 네트워크와 연동
- [ ]  기본 제공 예제 중 1개를 선택하여 코드 내용 확인
- [ ]  Compile 및 Deploy 수행 후 결과 확인

### 4) 블록 정보 조회

- [ ]  스켈레톤 프로젝트의 이더리움 네트워크 정보를 맞게 수정
- [ ]  스켈레톤 프로젝트 구동
- [ ]  각 블록 및 트랜잭션 정보 확인