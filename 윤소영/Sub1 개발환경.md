# Sub1 개발환경

# 1. 스켈레톤 프로젝트 받기

1. git clone (내려받기)

    ```bash
    git clone [https://lab.ssafy.com/s05-blockchain/skeleton-project.git](https://lab.ssafy.com/s05-blockchain/skeleton-project.git)
    ```

2. 본인의 Repository로 변경

    → 팀 프로젝트 repo 로 변경하면 될듯?

    ```bash
    git remote set-url origin https://lab.ssafy.com/s05-blockchain/S05P21B102.git
    git remot -v
    ```

    ![Untitled](Sub1%20%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%92%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC%20d5ad018c33d047fd8963772c0070c69b/Untitled.png)

# 2. 가상 머신 구현

## ① VirtualBox 설치

1. 공식 사이트에서 OS 버전에 맞는 설치 파일 다운로드

    [공식 사이트](https://www.virtualbox.org/wiki/Download_Old_Builds_6_0)

    ![Untitled](Sub1%20%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%92%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC%20d5ad018c33d047fd8963772c0070c69b/Untitled%201.png)

    → 명세에 나온 버전으로 맞췄다.

2. 설치 파일 실행

    ![Untitled](Sub1%20%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%92%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC%20d5ad018c33d047fd8963772c0070c69b/Untitled%202.png)

    → 전부 기본 세팅대로 설치

3. 설치 종료 후 프로그램 실행, 버전 확인

    ![Untitled](Sub1%20%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%92%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC%20d5ad018c33d047fd8963772c0070c69b/Untitled%203.png)

## ② Vagant 설치

1. 공식 사이트에서 OS 버전에 맞는 설치 파일 다운로드

    [공식 사이트](https://www.vagrantup.com/downloads)

    ![Untitled](Sub1%20%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%92%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC%20d5ad018c33d047fd8963772c0070c69b/Untitled%204.png)

    → 안정성, 호환성 확인 버전으로 선택하여 다운로드

2. 설치 파일 실행

    → 세팅 바꿀 필요 없이 next ... install

3. 필요시 OS 재부팅
4. 설치 여부 및 버전 확인

    ```bash
    > vagrant version
    ```

    ![Untitled](Sub1%20%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%92%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC%20d5ad018c33d047fd8963772c0070c69b/Untitled%205.png)

5. 호스트와 가상 머신 간 파일 전송 플러그인 설치

    ```bash
    > vagrant plugin install vagrant-scp
    ```

    ![Untitled](Sub1%20%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%92%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC%20d5ad018c33d047fd8963772c0070c69b/Untitled%206.png)

## ③ 가상 머신 생성 및 구동

1. 원하는 작업 디렉토리에서 Vagrant 초기화

    ```bash
    >vagrant init
    ```

    ![Untitled](Sub1%20%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%92%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC%20d5ad018c33d047fd8963772c0070c69b/Untitled%207.png)

2. 생성된 Vagrantfile 내용 수정

    → 필요에 따라 임의 수정 가능

    우분투 설치 필요 없음 [https://emflant.tistory.com/230](https://emflant.tistory.com/230)

    ```bash
    # 루비 언어로 작성됨
    # vm 몇대를 이러이러한 사양으로 만들어 줘! 
    vms = {
    	'eth0' => '10',
    	'eth1' => '11'
    }

    Vagrant.configure("2") do |config| # Vagrant Version
    	config.vm.box = "ubuntu/bionic64" # os 지정. 미리 만들어져 있는 이미지를 vagrant cloud 에서 찾아서 따로 대입도 가능
    	vms.each do |key, value|
    		config.vm.define "#{key}" do |node| 
    			node.vm.network "private_network", ip: "192.168.50.#{value}" #net 구성 ip
    			if "#{key}" == "eth0"
    				node.vm.network "forwarded_port", guest: 8545, host:8545
    			end
    			node.vm.hostname = "#{key}"	
    			node.vm.provider = "virtualbox" do |nodev|
    				nodev.memory = 2048 #VM에 할당할 메모리
    			end
    		end
    	end
    end
    ```

3. 가상 머신 구동 명령어 실행

    ```bash
    C:\Users\ssafy\bc-ethereum> vagrant up
    ```

    → syntax 에러 발생중

    ```bash
    # -*- mode: ruby -*-
    # vi: set ft=ruby :

    # All Vagrant configuration is done below. The "2" in Vagrant.configure
    # configures the configuration version (we support older styles for
    # backwards compatibility). Please don't change it unless you know what
    # you're doing.

    VAGRANT_API_VERSION="2"

    vms = {
      'eth0' => '10',
      'eth1' => '11'
    }

    Vagrant.configure(VAGRANT_API_VERSION) do |config|
      config.vm.box = "ubuntu/bionic64"
      vms.each do |key, value|
        config.vm.define "#{key}" do |node|
          node.vm.network "private_network", ip: "192.168.50.#{value}"
          if "#{key}" == "eth0"
            node.vm.network "forwarded_port", guest: 8545, host: 8545
          end
          node.vm.hostname = "#{key}"
          node.vm.provider "virtualbox" do |nodev|
            nodev.memory = 2048
          end
        end
      end
    end
    ```

    → 해결...

    ![Untitled](Sub1%20%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%92%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC%20d5ad018c33d047fd8963772c0070c69b/Untitled%208.png)

4. 가상 머신 구동 상태 확인

    ```bash
    C:\Users\ssafy\bc-ethereum> vagrant status
    ```

    잘 돌아감

    ![Untitled](Sub1%20%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%92%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC%20d5ad018c33d047fd8963772c0070c69b/Untitled%209.png)

5. 가상머신 접속(eth0 기준)

    ```bash
    C:\Users\ssafy\bc-ethereum> vagrant ssh eth0
    ```

    → 계정 패스워드 설정 및 sshd_config 수정 시 IP를 통한 SSH로도 접속 가능

    ![Untitled](Sub1%20%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF%E1%84%92%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC%20d5ad018c33d047fd8963772c0070c69b/Untitled%2010.png)