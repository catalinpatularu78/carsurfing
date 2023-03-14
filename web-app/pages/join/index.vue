<template>
  <section :class="{ 'flex items-center justify-center my-7': userCreated }">
    <form
      v-if="!userCreated"
      @submit.prevent="submitSignup"
      class="flex flex-col items-center my-7"
    >
      <div class="max-w-sm">
        <h1
          class="text-3xl font-semibold text-teal-500 lg:text-4xl dark:text-white text-center py-5"
        >
          Join CarSurfing
        </h1>
        <div class="mb-6 max-w-full">
          <label
            for="first-name"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >First Name</label
          >
          <input
            type="text"
            v-model="firstName"
            id="first-name"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
            placeholder="Joseph"
            required
          />
        </div>
        <div class="mb-6 max-w-full">
          <label
            for="middle-name"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Middle Name</label
          >
          <input
            type="text"
            v-model="middleName"
            id="middle-name"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
            placeholder="Joseph"
          />
        </div>
        <div class="mb-6 max-w-full">
          <label
            for="last-name"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Last Name</label
          >
          <input
            type="text"
            id="last-name"
            v-model="lastName"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
            placeholder="Bloggs"
          />
        </div>
        <div class="mb-6 max-w-full">
          <label
            for="email"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Email</label
          >
          <input
            type="email"
            id="email"
            v-model="email"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
            placeholder="name@carsurfing.com"
            required
          />
        </div>
        <div class="mb-6 max-w-full">
          <label
            for="phone"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Phone</label
          >
          <input
            type="tel"
            id="phone"
            v-model="phone"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
            placeholder="0851234567"
            required
          />
        </div>
        <div class="mb-6 max-w-full">
          <label
            for="description"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Description</label
          >
          <textarea
            rows="4"
            cols="50"
            v-model="description"
            id="description"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
            placeholder="Add description here..."
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
        <div
          v-if="showError"
          class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-6"
          role="alert"
        >
          <strong class="font-bold">Signup</strong>
          <span class="block"
            >Sign up could not be completed. Please try again.</span
          >
          <span
            @click="showError = false"
            class="absolute top-0 bottom-0 right-0 px-4 py-3"
          >
            <svg
              class="fill-current h-6 w-6 text-red-500"
              role="button"
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 20 20"
            >
              <title>Close</title>
              <path
                d="M14.348 14.849a1.2 1.2 0 0 1-1.697 0L10 11.819l-2.651 3.029a1.2 1.2 0 1 1-1.697-1.697l2.758-3.15-2.759-3.152a1.2 1.2 0 1 1 1.697-1.697L10 8.183l2.651-3.031a1.2 1.2 0 1 1 1.697 1.697l-2.758 3.152 2.758 3.15a1.2 1.2 0 0 1 0 1.698z"
              />
            </svg>
          </span>
        </div>
        <button
          type="submit"
          class="text-white bg-purple-800 hover:bg-purple-900 focus:ring-4 focus:outline-none focus:ring-purple-300 font-medium rounded-lg text-sm w-full sm:w-full px-5 py-2.5 text-center dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-800"
        >
          Submit
        </button>
      </div>
    </form>
    <div
      v-else
      class="bg-teal-100 border-t-4 border-teal-500 rounded-b text-teal-900 px-4 py-3 shadow-md"
      role="alert"
    >
      <div class="flex">
        <div class="py-1">
          <svg
            class="fill-current h-6 w-6 text-teal-500 mr-4"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 20 20"
          >
            <path
              d="M2.93 17.07A10 10 0 1 1 17.07 2.93 10 10 0 0 1 2.93 17.07zm12.73-1.41A8 8 0 1 0 4.34 4.34a8 8 0 0 0 11.32 11.32zM9 11V9h2v6H9v-4zm0-6h2v2H9V5z"
            />
          </svg>
        </div>
        <div>
          <p class="font-bold">User account created</p>
          <p class="text-sm">You've signed up to CarSurfing!</p>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import { ref, computed } from "vue";
export default {
  setup() {
    const userCreated = ref(false);
    const showError = ref(false);
    const firstName = ref("");
    const middleName = ref("");
    const lastName = ref("");
    const phone = ref("");
    const email = ref("");
    const description = ref("");
    const password = ref("");

    const data = computed(() => ({
      firstName: firstName.value,
      middleName: middleName.value,
      lastName: lastName.value,
      phone: phone.value,
      email: email.value,
      description: description.value,
      password: password.value,
      verified: false,
    }));

    async function submitSignup() {
      await fetch("http://localhost:10556/userapi/users", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data.value),
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.responseCode === "SUCCESS") {
            userCreated.value = true;
          } else {
            showError.value = true;
          }
        });
    }

    return {
      submitSignup,
      firstName,
      middleName,
      lastName,
      phone,
      email,
      description,
      password,
      userCreated,
      showError,
    };
  },
};
</script>
