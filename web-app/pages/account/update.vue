<template>
  <section>
    <form
      v-if="!profileUpdated"
      class="flex flex-col items-center my-7"
      @submit.prevent="submitUpdate"
    >
      <div class="max-w-sm min-w-[20%]">
        <h1
          class="text-3xl font-semibold text-teal-500 lg:text-4xl dark:text-white text-center py-5"
        >
          Edit your profile
        </h1>
        <div class="grid grid-rows-1 grid-cols-8">
          <Icon
            name="material-symbols:person-pin-circle-outline-rounded"
            class="w-48 h-48 mb-4 text-teal-200 z-10 col-start-1 col-end-4 row-span-full"
          ></Icon>
          <Icon
            name="material-symbols:person-pin-circle-outline-rounded"
            class="w-48 h-48 mb-4 text-teal-400 z-20 col-start-3 col-end-6 row-span-full"
          ></Icon>
          <Icon
            name="material-symbols:person-pin-circle-outline-rounded"
            class="w-48 h-48 mb-4 text-teal-600 z-30 col-start-5 col-end-8 row-span-full"
          ></Icon>
        </div>

        <div class="mb-6 max-w-full">
          <label
            for="description"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Description</label
          >
          <textarea
            v-model="description"
            type="text"
            id="description"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
            placeholder="Describe yourself for other carpoolers..."
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
          message="Edit could not be made. Please try again."
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
    <SuccessMessage
      v-else
      title="Edit sent"
      message="Thanks for updating your description"
    ></SuccessMessage>
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
    const route = useRoute();
    const profileUpdated = ref(false);
    const showError = ref(false);
    const description = ref("");
    const password = ref("");
    const phone = ref("");
    const username = computed(() => useMainStore().username);
    const email = computed(() => useMainStore().email);

    const data = computed(() => ({
      description: description.value,
      password: password.value,
      phone: phone.value,
      username: username.value,
      email: email.value,
    }));

    const loginToken = useCookie("loginToken");

    async function submitUpdate() {
      await fetch(`http://localhost:9092/api/user/${useMainStore().userId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${loginToken.value}`,
        },
        body: JSON.stringify(data.value),
      }).then((response) => {
        if (response) {
          profileUpdated.value = true;
        } else {
          showError.value = true;
        }
      });
    }

    return {
      submitUpdate,
      description,
      profileUpdated,
      showError,
      route,
      password,
      phone,
      username,
      email,
    };
  },
};
</script>
