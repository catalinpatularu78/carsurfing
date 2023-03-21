<template>
  <div>
    <div class="flex mb-3">
      <p class="text-gray-500 dark:text-gray-300 mr-4">Date/Time:</p>
      <p class="text-gray-500 dark:text-gray-300">
        {{ ride.dateOfDeparture }} {{ ride.estimatedDepartureTime }}
      </p>
    </div>
    <div class="flex mb-3">
      <p class="text-gray-500 dark:text-gray-300 mr-4">Spaces Left:</p>
      <p class="text-gray-500 dark:text-gray-300">
        {{ ride.spacesLeft }}
      </p>
    </div>
    <div v-if="ride.stop1" class="flex mb-3">
      <p class="text-gray-500 dark:text-gray-300 mr-4">Stops:</p>
      <p class="text-gray-500 dark:text-gray-300">
        {{ ride.stop1 }}
        <template v-if="ride.stop2">{{ "- " + ride.stop2 }}</template>
        <template v-if="ride.stop3">{{ "- " + ride.stop3 }}</template>
      </p>
    </div>
  </div>
</template>
<script>
import { ref } from "vue";
export default {
  props: {
    ride: {
      type: Object,
      default: () => ({}),
    },
  },
};
</script>
