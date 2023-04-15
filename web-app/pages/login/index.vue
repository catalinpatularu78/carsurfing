<template>
  <section>
    <form
      v-if="!loginSent"
      class="flex flex-col items-center my-7"
      @submit.prevent="submitLogin"
    >
      <div class="max-w-sm">
        <h1
          class="text-3xl font-semibold text-teal-500 lg:text-4xl dark:text-white text-center py-5"
        >
          Login
        </h1>
        <div class="mb-6 max-w-full">
          <label
            for="first-name"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Username</label
          >
          <input
            type="text"
            v-model="username"
            id="first-name"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
            placeholder="321Username"
            required
          />
        </div>
        <div class="mb-6">
          <label
            for="password"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Password</label
          >
          <input
            type="password"
            id="password"
            v-model="password"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
            required
          />
        </div>
        <ErrorMessage
          v-if="showError"
          message="Login unsuccessful. Please try again"
          @hide-error="showError = false"
        ></ErrorMessage>
        <button
          type="submit"
          class="text-white bg-purple-800 hover:bg-purple-900 focus:ring-4 focus:outline-none focus:ring-purple-300 font-medium rounded-lg text-sm w-full sm:w-full px-5 py-2.5 text-center dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-800"
        >
          Submit
        </button>
      </div>
    </form>
    <SuccessMessage v-else title="Login Successful"></SuccessMessage>
  </section>
</template>
<script>
import { ref, computed } from "vue";
import ErrorMessage from "~~/components/ErrorMessage.vue";
import SuccessMessage from "~~/components/SuccessMessage.vue";
import { useMainStore } from "~~/MainStore";
export default {
  components: {
    ErrorMessage,
    SuccessMessage,
  },
  setup() {
    const username = ref("");
    const password = ref("");
    const showError = ref(false);
    const loginSent = ref(false);
    const loginToken = useCookie("loginToken");

    const data = computed(() => ({
      username: username.value,
      password: password.value,
    }));

    async function submitLogin() {
      await fetch("http://localhost:9092/api/auth/signin", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data.value),
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.tokenType === "Bearer") {
            loginToken.value = data.accessToken;
            loginSent.value = true;
            useMainStore().setUser(data);
            console.log();
            navigateTo("/account");
          } else {
            showError.value = true;
          }
        });
      useMainStore().setIsLoggedIn();
      loginSent.value = true;
    }

    return {
      username,
      password,
      loginSent,
      submitLogin,
      showError,
    };
  },
};
</script>
