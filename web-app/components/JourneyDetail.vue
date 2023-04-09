<template>
  <div>
    <h3
      class="text-sm font-semibold text-purple-500 lg:text-xl dark:text-white text-l pb-5"
    >
      {{ index + 1 }}. Trip on {{ ride.dateOfDeparture }}
    </h3>
    <JourneyDetailStepper
      class="ml-8 mb-8"
      :ride-details="ride"
    ></JourneyDetailStepper>
    <hr class="w-auto h-0.5 bg-purple-500 opacity-50" />
  </div>
</template>
<script>
import JourneyDetailStepper from "~~/components/JourneyDetailStepper.vue";
export default {
  components: {
    JourneyDetailStepper,
  },
  props: {
    index: {
      type: Number,
      default: 0,
    },
    ride: {
      type: Object,
      default: () => ({}),
    },
  },
};
</script>
