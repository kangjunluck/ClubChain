import axios from "axios";
// axios 객체 생성
// 서버 돌아가는 주소값 입력
export default axios.create({
  baseURL: "http://j5b1021.p.ssafy.io:8080/",
  headers: {
    "Content-type": "application/json",
  },
});
