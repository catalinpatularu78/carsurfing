<template>
  <section>
    <form
      v-if="!rideCreated"
      class="flex flex-col items-center my-7"
      @submit.prevent="submitCreateRide"
    >
      <div class="max-w-sm">
        <h1
          class="text-3xl font-semibold text-teal-500 lg:text-4xl dark:text-white text-center py-5"
        >
          Create a carpool
        </h1>
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
        <div
          v-if="showError"
          class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-6"
          role="alert"
        >
          <strong class="font-bold">Error found</strong>
          <span class="block"
            >New journey could not be created. Please try again.</span
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
          <p class="font-bold">New journey created</p>
          <p class="text-sm">We'll let you know when new carpoolers join!</p>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import { ref, computed } from "vue";
export default {
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
      // Hardcoding this for now
      driverId: 1,
    }));

    async function submitCreateRide() {
      await fetch("http://localhost:10555/rideapi/rides", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
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
    };
  },
};
</script>
