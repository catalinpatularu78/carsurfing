import { defineStore } from 'pinia'

export const useMainStore = defineStore('main', {
  state: () => ({
    isLoggedIn: false,
    bookingsRequested: [],
    userId: null,
    username: null,
    email: null,

  }),
  actions: {
    setIsLoggedIn() {
      this.isLoggedIn = true;
    },
    setUser(data) {
      this.userId = data.id;
      this.username = data.username;
      this.email = data.email;
    },
    setIsLoggedOut() {
      this.isLoggedIn = false;
      const loginToken = useCookie("loginToken");
      loginToken.value = '';
    },
  },
  persist: {
    paths: ['isLoggedIn', 'bookingsRequested', 'userId'],
  },
})