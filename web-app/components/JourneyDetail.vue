<template>
  <div>
    <div class="flex items-center justify-between mb-6">
      <div class="flex item-center pt-2">
        <h3 class="text-6xl text-purple-500 uppercase">
          {{ ride.fromLocation }}
        </h3>
        <Icon
          name="material-symbols:arrow-forward-ios"
          class="text-purple-800 h-12 w-12"
        ></Icon>
        <h3 class="text-6xl text-purple-500 uppercase">
          {{ ride.toLocation }}
        </h3>
      </div>
      <h3 class="text-2xl text-purple-800">
        {{ formatDate(ride.dateOfDeparture) }}
      </h3>
    </div>
    <div class="flex justify-between">
      <JourneyDetailStepper
        class="ml-8 mb-8"
        :ride-details="ride"
        :has-started="journeyIsInThePast"
      ></JourneyDetailStepper>
      <div class="flex flex-col justify-between">
        <h4 class="text-lg text-right">Driver: {{ ride.driverId }}</h4>
        <a
          class="pb-6 text-purple-500 hover:underline cursor-pointer hover:text-purple-800"
          >Cancel booking</a
        >
      </div>
    </div>
    <hr class="w-auto h-0.5 bg-purple-500 opacity-50" />
  </div>
</template>
<script>
import { computed } from "vue";
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
  setup(props) {
    const journeyIsInThePast = computed(() => {
      const journeyDateTime = new Date(
        `${props.ride.dateOfDeparture} ${props.ride.estimatedDepartureTime}`
      );
      const now = new Date();
      return journeyDateTime < now;
    });

    function formatDate(date) {
      const dateLeaving = new Date(date);
      return dateLeaving.toDateString();
    }

    return {
      journeyIsInThePast,
      formatDate,
    };
  },
};
</script>
