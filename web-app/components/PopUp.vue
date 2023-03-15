<template>
  <div>
    <button
      @click="isOpen"
      type="button"
      class="mt-5 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
    >
      Show modal
    </button>
    <Modal :size="size" v-if="isOpen" @close="$emit('close')">
      <template #header>
        <slot name="header"></slot>
      </template>
      <template #body>
        <slot name="body"></slot>
      </template>
      <template #footer>
        <div class="flex justify-end">
          <button
            @click="$emit('close')"
            type="button"
            class="text-white bg-teal-700 hover:bg-teal-800 focus:ring-4 focus:outline-none focus:ring-teal-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-teal-600 dark:hover:bg-teal-700 dark:focus:ring-teal-800"
          >
            Book now
          </button>
        </div>
      </template>
    </Modal>
  </div>
</template>
<script>
import { Modal } from "flowbite-vue";
export default {
  components: {
    Modal,
  },
  props: {
    isOpen: {
      type: Boolean,
      default: true,
    },
  },
  emits: ["close"],
};
</script>
