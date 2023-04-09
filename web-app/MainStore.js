import { defineStore } from 'pinia'

export const useMainStore = defineStore('main', {
  state: () => ({
    isLoggedIn: false,
    bookingsRequested: [],
  }),
  actions: {
    setIsLoggedIn() {
      this.isLoggedIn = true;
    },
    setIsLoggedOut() {
      this.isLoggedIn = false;
    },
  },
  persist: {
    paths: ['isLoggedIn', 'bookingsRequested'],
  },
})