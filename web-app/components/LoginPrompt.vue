<template>
  <div class="flex flex-col w-full items-center">
    <Icon
      name="carbon:vehicle-api"
      class="w-48 h-48 mb-4 text-teal-200 z-10"
    ></Icon>
    <NuxtLink
      link="/login"
      isactive="false"
      component="a"
      linkattr="href"
      to="/login"
      class="block py-2 pr-4 pl-3 mb-8 rounded md:p-0 bg-blue-700 md:bg-transparent text-white md:text-teal-500 dark:text-white text-center text-lg"
      >Login to continue</NuxtLink
    >
  </div>
</template>
