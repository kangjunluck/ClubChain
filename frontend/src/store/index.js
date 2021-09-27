import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState()],
  // state == 데이터. 해당 어플리케이션의 핵심. 앱을 작동하는 원본 소스
  // state가 변경되면 해당 state를 공유하는 컴포넌트의 DOM은 알아서 렌더링 된다
  // state는 mutaions에 정의된 메서드를 통해서만 조작됨
  state: {
    isLogin: false,
    credentials: {
      userEmail: null,
      useraccount: null,
      usernickname: null,
      userthumbnail: null,
    }
  },

  // actionsm 에서 commit() 메서드에 의해 호출
  // state를 변경하는 로직
  // 비동깆거으로 동작하면 state가 변화하는 시점이 달라질 수 있기 때문에 동기적 코드만 작성
  // 첫 인자로 state가 들어옴
  // UPPER_CASE 로 주로 작성햐
  mutations: {
    LOGIN_INFO: function (state, credentials) {
      state.credentials = credentials;
    },
    IS_LOGIN: function(state) {
      state.isLogin = !state.isLogin;
    }
  },
  // 뷰에서 사용자 입력에 대해 반응적으로 상태를 바꾸는 법 (methods)
  // 컴포넌트는 dispatch() 를 사용하여 actions 내의 메소드를 호출
  // be api와 토신하여 data fetching 등 호출, 동기 & 비동기적 작업 포함 가능
  // 항상 context가 인자로 넘어옴
  // state를 직접 변경하지 않는다 (가능 하지만 하지 않는것)
  // mutations에 정의된 메서드를 commit 메서드로 호출
  actions: {
    logininfo: function (context, credentials) {
      context.commit ('LOGIN_INFO', credentials)
    },
    isLogin: function (context) {
      context.commit('IS_LOGIN')
    }
  },
  getters: {
    isLogin(state) {
      return state.username !== "  ";
    }
  },
  modules: {},
  // Getters
  // state를 변경하지 않고 활용하여 계산을 수행 (computed와 유사). state를 기준으로 계산
});
