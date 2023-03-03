<script setup></script>
<template>
  <nav class="border-gray-200 bg-white px-2 sm:px-4 py-2.5 dark:bg-gray-900">
    <div class="container flex flex-wrap justify-between items-center mx-auto">
      <a href="/" class="flex items-center"
        ><img
          src="~/assets/images/logo.png"
          class="mr-3 h-6 sm:h-10"
          alt="CarSurfing logo" /><span
          class="self-center text-xl font-semibold whitespace-nowrap dark:text-white"
        ></span></a
      ><button
        type="button"
        class="inline-flex items-center p-2 ml-3 text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
        aria-controls="navbar-default"
        aria-expanded="false"
      >
        <span class="sr-only">Open main menu</span
        ><svg
          class="w-6 h-6"
          fill="none"
          stroke="currentColor"
          viewBox="0 0 24 24"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M12 6V4m0 2a2 2 0 100 4m0-4a2 2 0 110 4m-6 8a2 2 0 100-4m0 4a2 2 0 110-4m0 4v2m0-6V4m6 6v10m6-2a2 2 0 100-4m0 4a2 2 0 110-4m0 4v2m0-6V4"
          ></path>
        </svg>
      </button>
      <div class="w-full md:block md:w-auto hidden">
        <ul
          class="flex flex-col p-4 mt-4 rounded-lg border border-gray-100 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0 dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700"
        >
          <li>
            <a
              link="/"
              isactive="true"
              component="a"
              linkattr="href"
              class="block py-2 pr-4 pl-3 rounded md:p-0 bg-blue-700 md:bg-transparent text-white md:text-teal-500 dark:text-white"
              href="/"
              >Home</a
            >
          </li>
          <li>
            <NuxtLink
              link="/"
              isactive="false"
              component="a"
              linkattr="href"
              class="block py-2 pr-4 pl-3 rounded md:p-0 text-gray-700 hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-teal-500 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
              to="/rides"
              >Find Ride</NuxtLink
            >
          </li>
          <li>
            <NuxtLink
              link="/"
              isactive="false"
              component="a"
              linkattr="href"
              class="block py-2 pr-4 pl-3 rounded md:p-0 text-gray-700 hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-teal-500 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
              to="/rides/new"
              >Find Ride</NuxtLink
            >
          </li>
          <li>
            <NuxtLink
              link="/"
              isactive="false"
              component="a"
              linkattr="href"
              class="block py-2 pr-4 pl-3 rounded md:p-0 text-gray-700 hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-teal-500 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
              to="/join"
              >Join</NuxtLink
            >
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
