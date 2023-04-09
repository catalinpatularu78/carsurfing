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
        <div class="flex">
          <FancyImage></FancyImage>
          <div>
            <div class="flex mb-3">
              <p class="text-gray-500 dark:text-gray-300 mr-4">Name:</p>
              <p class="text-gray-500 dark:text-gray-300">
                {{ userDetails.firstName }}
                <span v-if="userDetails.middleName">{{
                  " " + userDetails.middleName
                }}</span>
                {{ " " + userDetails.lastName }}
              </p>
            </div>
            <div class="flex mb-3">
              <p class="text-gray-500 dark:text-gray-300 mr-4">Email:</p>
              <p class="text-gray-500 dark:text-gray-300">
                {{ userDetails.email }}
              </p>
            </div>
            <div class="flex mb-3">
              <p class="text-gray-500 dark:text-gray-300 mr-4">Description:</p>
              <p class="text-gray-500 dark:text-gray-300">
                {{ userDetails.description }}
              </p>
            </div>
            <div class="flex mb-3">
              <p class="text-gray-500 dark:text-gray-300 mr-4">Phone:</p>
              <p class="text-gray-500 dark:text-gray-300">
                {{ userDetails.phone }}
              </p>
            </div>
            <div class="flex mb-3">
              <p class="text-gray-500 dark:text-gray-300 mr-4">Verified:</p>
              <p class="text-gray-500 dark:text-gray-300">
                {{ userDetails.verified }}
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
            v-for="(ride, index) in ridesForThisUser"
            :key="ride.id"
            class="mb-6"
          >
            <JourneyDetail :index="index" :ride="ride"></JourneyDetail>
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
import { ref } from "vue";
export default {
  components: {
    ReviewTable,
    FancyImage,
    JourneyDetail,
  },
  setup() {
    const userDetails = ref({});
    const rides = ref([]);
    const fakeReviews = ref([
      {
        reviewerName: "Mary",
        comment: "Really funny person",
        rating: 5,
      },
    ]);

    const ridesForThisUser = computed(() => {
      return rides.value.filter(
        (ride) => ride.driverId === userDetails.value.id
      );
    });

    getRides();

    async function getRides() {
      await fetch("http://localhost:9091/rideapi/rides")
        .then((response) => response.json())
        .then((data) => {
          rides.value = data;
        });
    }

    getUserDetails();
    async function getUserDetails() {
      await fetch("http://localhost:9092/userapi/users/1")
        .then((response) => response.json())
        .then((data) => {
          userDetails.value = data;
        });
    }

    return {
      userDetails,
      fakeReviews,
      ridesForThisUser,
    };
  },
};
</script>
