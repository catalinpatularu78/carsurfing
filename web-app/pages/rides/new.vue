<template>
  <section>
    <form class="flex flex-col items-center my-7">
      <div class="max-w-sm">
        <h1
          class="text-3xl font-semibold text-teal-400 lg:text-4xl dark:text-white text-center py-5"
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
            type="time"
            id="departure-time"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
          />
        </div>
        <button
          type="submit"
          class="text-white bg-purple-800 hover:bg-purple-900 focus:ring-4 focus:outline-none focus:ring-purple-300 font-medium rounded-lg text-sm w-full sm:w-full px-5 py-2.5 text-center dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-800"
        >
          Submit
        </button>
      </div>
    </form>
  </section>
</template>
