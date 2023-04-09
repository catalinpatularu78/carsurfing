<template>
  <div>
    <Modal v-if="isOpen" @close="$emit('close')">
      <template #header>
        <slot name="header"></slot>
      </template>
      <template #body>
        <slot name="body"></slot>
      </template>
      <template #footer>
        <slot name="footer"></slot>
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
