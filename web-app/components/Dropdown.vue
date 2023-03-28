<template>
  <!-- Dropdown menu -->
  <div
    id="dropdown"
    class="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700"
  >
    <ul
      class="py-2 text-sm text-gray-700 dark:text-gray-200"
      aria-labelledby="dropdownDefaultButton"
    >
      <li
        v-for="(option, index) in options"
        :key="index"
        @click="$emit('item-selected', $event)"
      >
        <span
          class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white"
          >{{ option.texts }}</span
        >
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  props: {
    options: {
      type: Array,
      default: () => [],
    },
    selectedOption: {
      type: String,
      default: "",
    },
  },
  emits: ["item-selected"],
};
</script>
