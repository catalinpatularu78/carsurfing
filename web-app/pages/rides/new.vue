<template>
  <section>
    <h1
      class="text-3xl font-semibold text-teal-500 lg:text-4xl dark:text-white text-center py-5"
    >
      Create a carpool
    </h1>
    <form
      v-if="!rideCreated && isLoggedIn"
      class="flex flex-col items-center my-7"
      @submit.prevent="submitCreateRide"
    >
      <div class="max-w-sm">
        <div class="mb-6 max-w-full">
          <label
            for="from-location"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Start Point</label
          >
          <input
            v-model="fromLocation"
            type="text"
            id="from-location"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
            placeholder="Start Point"
          />
        </div>
        <div class="mb-6 max-w-full">
          <label
            for="to-location"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Destination</label
          >
          <input
            v-model="toLocation"
            type="text"
            id="to-location"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
            placeholder="Destination"
            required
          />
        </div>
        <div class="mb-6 max-w-full">
          <label
            for="spaces-left"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Spaces Available</label
          >
          <input
            v-model="spacesLeft"
            type="number"
            id="spaces-left"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
            min="1"
            max="10"
            placeholder="3"
            required
          />
        </div>
        <div class="mb-6 max-w-full">
          <label
            for="stop-1"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Stop 1</label
          >
          <input
            v-model="stop1"
            type="text"
            id="stop-1"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
          />
        </div>
        <div class="mb-6 max-w-full">
          <label
            for="stop-2"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Stop 2</label
          >
          <input
            v-model="stop2"
            type="text"
            id="stop-2"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
          />
        </div>
        <div class="mb-6 max-w-full">
          <label
            for="stop-3"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Stop 3</label
          >
          <input
            v-model="stop3"
            type="text"
            id="stop-3"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
          />
        </div>
        <div class="mb-6 max-w-full">
          <label
            for="departure-date"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Departure Date</label
          >
          <input
            v-model="dateOfDeparture"
            type="date"
            id="departure-date"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
          />
        </div>
        <div class="mb-6 max-w-full">
          <label
            for="departure-time"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Departure Time</label
          >
          <input
            v-model="estimatedDepartureTime"
            type="time"
            id="departure-time"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
          />
        </div>
        <ErrorMessage
          v-if="showError"
          message="New journey could not be created. Please try again."
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
      v-else-if="isLoggedIn"
      title="New journey created"
      message="We'll let you know when new carpoolers join!"
    ></SuccessMessage>

    <LoginPrompt v-else></LoginPrompt>
  </section>
</template>
<script>
import { ref, computed } from "vue";
import ErrorMessage from "~~/components/ErrorMessage.vue";
import SuccessMessage from "~~/components/SuccessMessage.vue";
import LoginPrompt from "~~/components/LoginPrompt.vue";

import { useMainStore } from "~~/MainStore";
export default {
  components: {
    ErrorMessage,
    SuccessMessage,
    LoginPrompt,
  },
  setup() {
    const rideCreated = ref(false);
    const showError = ref(false);
    const fromLocation = ref("");
    const toLocation = ref("");
    const dateOfDeparture = ref("");
    const estimatedDepartureTime = ref("");
    const stop1 = ref("");
    const stop2 = ref("");
    const stop3 = ref("");
    const spacesLeft = ref("");

    const data = computed(() => ({
      fromLocation: fromLocation.value,
      toLocation: toLocation.value,
      dateOfDeparture: dateOfDeparture.value,
      estimatedDepartureTime: estimatedDepartureTime.value,
      stop1: stop1.value,
      stop2: stop2.value,
      stop3: stop3.value,
      spacesLeft: spacesLeft.value,
      driverId: useMainStore().userId,
    }));

    const loginToken = useCookie("loginToken");

    async function submitCreateRide() {
      await fetch("http://localhost:9091/rideapi/rides", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${loginToken.value}`,
        },
        body: JSON.stringify(data.value),
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.responseCode === "SUCCESS") {
            rideCreated.value = true;
          } else {
            showError.value = true;
          }
        });
    }

    return {
      submitCreateRide,
      fromLocation,
      toLocation,
      dateOfDeparture,
      estimatedDepartureTime,
      stop1,
      stop2,
      stop3,
      spacesLeft,
      rideCreated,
      showError,
      isLoggedIn: computed(() => useMainStore().isLoggedIn),
    };
  },
};
</script>
