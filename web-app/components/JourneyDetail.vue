<template>
  <div>
    <div class="flex items-center justify-between mb-6">
      <div class="flex item-center pt-2">
        <h3 class="text-4xl text-teal-500 uppercase">
          {{ ride.fromLocation }}
        </h3>
        <Icon
          name="material-symbols:arrow-forward-ios"
          class="text-teal-800 h-8 w-8"
        ></Icon>
        <h3 class="text-4xl text-teal-500 uppercase">
          {{ ride.toLocation }}
        </h3>
      </div>
      <h3 class="text-xl text-teal-800">
        {{ formatDate(ride.dateOfDeparture) }}
      </h3>
    </div>
    <div class="flex justify-between">
      <JourneyDetailStepper
        class="ml-8 mb-8"
        :ride-details="ride"
        :journey-completed="journeyIsInThePast"
      ></JourneyDetailStepper>
      <div class="flex flex-col justify-between">
        <h4 class="text-lg text-right">
          {{ isDriver ? "Carpool Driver" : "Carpool Passenger" }}
        </h4>
        <NuxtLink
          v-if="journeyIsInThePast && !isDriver"
          link="/"
          isactive="false"
          component="a"
          linkattr="href"
          :to="`/reviews/new?reviewed=${ride.driverId}&reviewer=${username}`"
          class="pb-6 text-teal-500 text-right hover:underline cursor-pointer hover:text-teal-800"
          >Rate this journey</NuxtLink
        >
      </div>
    </div>
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
    driverId: {
      type: Number,
      default: 0,
    },
    username: {
      type: String,
      default: "",
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

    const isDriver = computed(() => props.ride.driverId === props.userId);

    function formatDate(date) {
      const dateLeaving = new Date(date);
      return dateLeaving.toDateString();
    }

    return {
      journeyIsInThePast,
      formatDate,
      isDriver,
    };
  },
};
</script>
