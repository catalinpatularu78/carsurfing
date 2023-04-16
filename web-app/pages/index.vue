<template>
  <div>
    <section
      class="flex flex-col items-center justify-center bg-gradient-to-r from-purple-900 to-blue-800 h-128 relative z-1 py-5"
    >
      <img
        class="absolute h-[inherit] left-20 opacity-30"
        src="~/assets/images/leaf.png"
      />
      <h1
        class="z-10 mb-4 text-4xl font-bold pt-8 leading-none tracking-tight text-white md:text-5xl lg:text-6xl dark:text-white"
      >
        CarSurfing: Eco-friendly community carpooling
      </h1>
      <p
        class="z-10 mb-6 text-center text-lg font-normal text-teal-200 lg:text-xl sm:px-16 xl:px-48 dark:text-gray-400"
      >
        Join today and find your next road trip adventure or commuting buddy.
        Available throughout Ireland.
      </p>
      <NuxtLink
        link="/"
        isactive="false"
        component="a"
        linkattr="href"
        to="/rides"
      >
        <span
          class="z-10 inline-flex items-center justify-center px-5 py-3 text-base font-medium text-center text-white bg-teal-500 rounded-lg hover:bg-teal-800 focus:ring-4 focus:ring-blue-300 dark:focus:ring-blue-900"
        >
          Start CarSurfing
          <svg
            class="w-5 h-5 ml-2 -mr-1"
            fill="currentColor"
            viewBox="0 0 20 20"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              fill-rule="evenodd"
              d="M10.293 3.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-4.293-4.293a1 1 0 010-1.414z"
              clip-rule="evenodd"
            ></path>
          </svg>
        </span>
      </NuxtLink>
    </section>
    <section class="bg-white dark:bg-gray-900">
      <div class="container px-6 py-10 mx-auto flex flex-col">
        <h1
          class="text-3xl font-semibold text-purple-500 lg:text-4xl dark:text-white text-center pt-5"
        >
          How to Start CarSurfing
        </h1>
        <div
          class="grid grid-cols-1 gap-8 mt-8 xl:mt-12 xl:gap-12 md:grid-cols-2 xl:grid-cols-3"
        >
          <div
            class="p-8 space-y-3 border-2 border-purple-400 dark:border-blue-300 rounded-xl"
          >
            <span class="inline-block text-purple-500 dark:text-blue-400">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="w-8 h-8"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M17.657 18.657A8 8 0 016.343 7.343S7 9 9 10c0-2 .5-5 2.986-7C14 5 16.09 5.777 17.656 7.343A7.975 7.975 0 0120 13a7.975 7.975 0 01-2.343 5.657z"
                />
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M9.879 16.121A3 3 0 1012.015 11L11 14H9c0 .768.293 1.536.879 2.121z"
                />
              </svg>
            </span>

            <h1
              class="text-2xl font-semibold text-gray-700 capitalize dark:text-white"
            >
              Find a Carpool
            </h1>

            <p class="text-gray-500 dark:text-gray-300 pb-4">
              Joining a carpool can be a cost-effective and social way to
              travel, while also helping to alleviate traffic congestion and
              reduce pollution.
            </p>

            <NuxtLink
              link="/"
              isactive="false"
              component="a"
              linkattr="href"
              to="/rides"
            >
              <span
                class="inline-flex p-2 text-purple-500 capitalize transition-colors duration-200 transform bg-purple-100 rounded-full dark:bg-blue-500 dark:text-white hover:underline hover:text-blue-600 dark:hover:text-purple-500"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  class="w-6 h-6"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M13 9l3 3m0 0l-3 3m3-3H8m13 0a9 9 0 11-18 0 9 9 0 0118 0z"
                  />
                </svg>
              </span>
            </NuxtLink>
          </div>

          <div
            class="p-8 space-y-3 border-2 border-purple-400 dark:border-blue-300 rounded-xl"
          >
            <span class="inline-block text-purple-500 dark:text-blue-400">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="w-8 h-8"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M11 4a2 2 0 114 0v1a1 1 0 001 1h3a1 1 0 011 1v3a1 1 0 01-1 1h-1a2 2 0 100 4h1a1 1 0 011 1v3a1 1 0 01-1 1h-3a1 1 0 01-1-1v-1a2 2 0 10-4 0v1a1 1 0 01-1 1H7a1 1 0 01-1-1v-3a1 1 0 00-1-1H4a2 2 0 110-4h1a1 1 0 001-1V7a1 1 0 011-1h3a1 1 0 001-1V4z"
                />
              </svg>
            </span>

            <h1
              class="text-2xl font-semibold text-gray-700 capitalize dark:text-white"
            >
              Create a Carpool
            </h1>

            <p class="text-gray-500 dark:text-gray-300 pb-4">
              By sharing the ride with others, you can split the cost of fuel
              and tolls, reducing your expenses significantly.
            </p>
            <NuxtLink
              link="/"
              isactive="false"
              component="a"
              linkattr="href"
              to="/rides/new"
            >
              <span
                class="inline-flex p-2 text-purple-500 capitalize transition-colors duration-200 transform bg-purple-100 rounded-full dark:bg-blue-500 dark:text-white hover:underline hover:text-blue-600 dark:hover:text-purple-500"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  class="w-6 h-6"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M13 9l3 3m0 0l-3 3m3-3H8m13 0a9 9 0 11-18 0 9 9 0 0118 0z"
                  />
                </svg>
              </span>
            </NuxtLink>
          </div>

          <div
            class="p-8 space-y-3 border-2 border-purple-400 dark:border-blue-300 rounded-xl"
          >
            <span class="inline-block text-purple-500 dark:text-blue-400">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="w-8 h-8"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z"
                />
              </svg>
            </span>

            <h1
              class="text-2xl font-semibold text-gray-700 capitalize dark:text-white"
            >
              Review your Journey
            </h1>

            <p class="text-gray-500 dark:text-gray-300 pb-4">
              Reviewing a carpool driver benefits the entire community by
              promoting responsible and reliable carpooling practices.
            </p>

            <NuxtLink
              link="/"
              isactive="false"
              component="a"
              linkattr="href"
              to="/login"
            >
              <span
                class="inline-flex p-2 text-purple-500 capitalize transition-colors duration-200 transform bg-purple-100 rounded-full dark:bg-blue-500 dark:text-white hover:underline hover:text-blue-600 dark:hover:text-purple-500"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  class="w-6 h-6"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M13 9l3 3m0 0l-3 3m3-3H8m13 0a9 9 0 11-18 0 9 9 0 0118 0z"
                  />
                </svg>
              </span>
            </NuxtLink>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
