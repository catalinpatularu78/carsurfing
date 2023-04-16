<script setup></script>
<template>
  <div>
    <nav-bar></nav-bar>
    <div class="page"><slot></slot></div>
    <footer
      class="p-4 bg-teal-50 rounded-lg shadow md:flex md:items-center md:justify-between md:p-6 dark:bg-gray-800"
    >
      <span class="text-sm text-gray-500 sm:text-center dark:text-gray-400"
        >© 2023
        <a class="hover:underline">CarSurfing</a>
      </span>
      <ul
        class="flex flex-wrap items-center mt-3 text-sm text-gray-500 dark:text-gray-400 sm:mt-0"
      >
        <li>
          <a href="#" class="mr-4 hover:underline md:mr-6">Find a Carpool</a>
        </li>
        <li>
          <a href="#" class="mr-4 hover:underline md:mr-6">Create a Carpool</a>
        </li>
        <li><a href="#" class="mr-4 hover:underline md:mr-6">Sign Up </a></li>
      </ul>
    </footer>
  </div>
</template>
<style scoped>
.page {
  min-height: 750px;
}
</style>
