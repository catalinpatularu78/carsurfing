<template>
  <div>
    <section class="p-3 sm:p-5 mb-7">
      <div class="mx-auto max-w-screen-xl px-4 lg:px-12">
        <h1
          class="text-3xl font-semibold text-teal-500 lg:text-4xl dark:text-white text-left py-5"
        >
          My Profile
        </h1>
        <hr class="w-auto h-1 bg-teal-700 opacity-30 mb-8" />
        <div class="flex mb-4">
          <FancyImage></FancyImage>
          <div>
            <div class="flex mb-3">
              <p class="text-teal-500 dark:text-gray-300 mr-16">Username:</p>
              <p class="text-gray-500 dark:text-gray-300">
                {{ userDetails.username }}
              </p>
            </div>
            <div class="flex mb-3">
              <p class="text-teal-500 dark:text-gray-300 mr-16">Email:</p>
              <p class="text-gray-500 dark:text-gray-300">
                {{ userDetails.email }}
              </p>
            </div>
            <div class="flex mb-3">
              <p class="text-teal-500 dark:text-gray-300 mr-16">Description:</p>
              <p class="text-gray-500 dark:text-gray-300">
                {{ userDetails.description }}
              </p>
            </div>
            <div class="flex mb-3">
              <p class="text-teal-500 dark:text-gray-300 mr-16">Phone:</p>
              <p class="text-gray-500 dark:text-gray-300">
                {{ userDetails.phone }}
              </p>
            </div>
            <div class="flex mb-3">
              <p class="text-teal-500 dark:text-gray-300 mr-16">Rating:</p>
              <p class="text-gray-500 dark:text-gray-300">
                {{ userDetails.rating ? userDetails.rating : "No ratings yet" }}
              </p>
            </div>
          </div>
        </div>
        <div>
          <h2
            class="text-1xl font-semibold text-teal-500 lg:text-2xl dark:text-white pt-10 pb-5"
          >
            Trips
          </h2>
          <hr class="w-auto h-1 bg-teal-700 opacity-30 mb-5" />
          <div
            v-for="(ride, index) in allRides"
            :key="ride.id"
            class="mb-6 p-4 border-b-2 border-teal-500"
            :class="{ 'bg-teal-50': !journeyIsInThePast(ride) }"
          >
            <JourneyDetail
              :index="index"
              :ride="ride"
              :user-id="userId"
            ></JourneyDetail>
          </div>
        </div>
        <div>
          <h2
            class="text-1xl font-semibold text-teal-500 lg:text-2xl dark:text-white pt-10 pb-5"
          >
            Reviews
          </h2>
          <hr class="w-auto h-1 bg-teal-700 opacity-30 mb-5" />
          <ReviewTable :reviews="fakeReviews"></ReviewTable>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import ReviewTable from "~~/components/ReviewTable.vue";
import FancyImage from "~~/components/FancyImage.vue";
import JourneyDetail from "~~/components/JourneyDetail.vue";
import { useMainStore } from "~~/MainStore";
import { ref } from "vue";
export default {
  components: {
    ReviewTable,
    FancyImage,
    JourneyDetail,
  },
  setup() {
    const userId = computed(() => useMainStore().userId);
    const userDetails = ref({});
    const rides = ref([]);
    const ridesAsPassenger = ref([]);
    const bookings = ref([]);
    const loginToken = useCookie("loginToken");
    const fakeReviews = ref([
      {
        reviewerName: "Mary",
        comment: "Really funny person",
        rating: 5,
      },
    ]);

    const ridesAsDriver = computed(() => {
      return rides.value.filter((ride) => ride.driverId === userId.value);
    });

    const allRides = computed(() => {
      return [...ridesAsDriver.value, ...ridesAsPassenger.value];
    });

    getRides();

    async function getRides() {
      await fetch("http://localhost:9091/rideapi/rides", {
        headers: {
          Authorization: `Bearer ${loginToken.value}`,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          rides.value = data;
        });
    }

    getBookings();

    async function getBookings() {
      await fetch(
        `http://localhost:9091/rideapi/rides/bookings/${userId.value}`,
        {
          headers: {
            Authorization: `Bearer ${loginToken.value}`,
          },
        }
      )
        .then((response) => response.json())
        .then((data) => {
          bookings.value = data;
          bookings.value.forEach((booking) => {
            getRide(booking.rideId);
          });
        });
    }

    async function getRide(id) {
      await fetch(`http://localhost:9091/rideapi/rides/${id}`, {
        headers: {
          Authorization: `Bearer ${loginToken.value}`,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          console.log(data);
          ridesAsPassenger.value.push(data);
        });
    }

    getUserDetails();
    async function getUserDetails() {
      await fetch(`http://localhost:9092/api/user/${userId.value}`, {
        headers: {
          Authorization: `Bearer ${loginToken.value}`,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          userDetails.value = data;
        });
    }

    function journeyIsInThePast(ride) {
      const journeyDateTime = new Date(
        `${ride.dateOfDeparture} ${ride.estimatedDepartureTime}`
      );
      const now = new Date();
      return journeyDateTime < now;
    }

    return {
      userDetails,
      fakeReviews,
      allRides,
      journeyIsInThePast,
      userId,
      bookings,
    };
  },
};
</script>
