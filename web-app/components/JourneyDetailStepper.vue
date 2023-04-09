<template>
  <ol
    class="relative text-gray-500 border-l border-gray-200 dark:border-gray-700 dark:text-gray-400"
  >
    <li class="mb-10 ml-6 flex items-center">
      <span
        class="absolute flex items-center justify-center w-8 h-8 bg-gray-200 rounded-full -left-4 ring-4 ring-white dark:ring-gray-900 dark:bg-green-900"
      >
        <Icon name="material-symbols:alarm" class="text-xl"></Icon>
      </span>
      <h3 class="font-medium leading-tight ml-1">
        {{ journeyCompleted ? "Departed at: " : "Depature Time: " }}
        {{ rideDetails.estimatedDepartureTime }}
      </h3>
    </li>
    <li class="mb-10 ml-6 flex items-center" v-if="rideDetails.stop1">
      <span
        class="absolute flex items-center justify-center w-8 h-8 bg-gray-100 rounded-full -left-4 ring-4 ring-white dark:ring-gray-900 dark:bg-gray-700"
      >
        <Icon name="bi:sign-stop" class="text-2xl"></Icon>
      </span>
      <h3 class="font-medium leading-tight ml-1">
        {{ journeyCompleted ? "Stopped at: " : "Stops: " }}
        {{ rideDetails.stop1 }}
        <span v-if="rideDetails.stop2">{{ ", " + rideDetails.stop2 }}</span>
        <span v-if="rideDetails.stop3">{{ ", " + rideDetails.stop3 }}</span>
      </h3>
    </li>
    <li class="ml-6 flex items-center">
      <span
        class="absolute flex items-center justify-center w-8 h-8 bg-gray-100 rounded-full -left-4 ring-4 ring-white dark:ring-gray-900 dark:bg-gray-700"
      >
        <Icon name="fluent:people-audience-20-regular" class="text-2xl"></Icon>
      </span>
      <h3 class="font-medium leading-tight ml-1">
        {{
          rideDetails.spacesLeft > 0
            ? " Spaces Left: " + rideDetails.spacesLeft
            : "Full Carpool - Nice Job!"
        }}
      </h3>
    </li>
  </ol>
</template>
<script>
export default {
  props: {
    rideDetails: {
      type: Object,
      default: () => ({}),
    },
    journeyCompleted: {
      type: Boolean,
      default: false,
    },
  },
};
</script>
