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
    </footer>
  </div>
</template>
<style scoped>
.page {
  min-height: 750px;
}
</style>
