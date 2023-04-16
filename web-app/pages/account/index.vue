<template>
  <div>
    <section class="p-3 sm:p-5 mb-7">
      <div class="mx-auto max-w-screen-xl px-4 lg:px-12">
        <h1
          class="text-3xl font-semibold text-teal-500 lg:text-4xl dark:text-white text-left py-5"
        >
          My Account
        </h1>
        <hr class="w-auto h-1 bg-teal-700 opacity-30 mb-8" />
        <div class="flex mb-4 w-full items-start">
          <FancyImage></FancyImage>
          <div>
            <div class="flex mb-3">
              <p class="text-teal-500 dark:text-gray-300 basis-32">Username:</p>
              <p class="text-gray-500 dark:text-gray-300 basis-80">
                {{ userDetails.username }}
              </p>
            </div>
            <div class="flex mb-3">
              <p class="text-teal-500 dark:text-gray-300 basis-32">Email:</p>
              <p class="text-gray-500 dark:text-gray-300 basis-80">
                {{ userDetails.email }}
              </p>
            </div>
            <div class="flex mb-3">
              <p class="text-teal-500 dark:text-gray-300 basis-32">
                Description:
              </p>
              <p class="text-gray-500 dark:text-gray-300 basis-80">
                {{ userDetails.description }}
              </p>
            </div>
            <div class="flex mb-3">
              <p class="text-teal-500 dark:text-gray-300 basis-32">Phone:</p>
              <p class="text-gray-500 dark:text-gray-300 basis-80">
                {{ userDetails.phone }}
              </p>
            </div>
            <div class="flex mb-3">
              <p class="text-teal-500 dark:text-gray-300 basis-32">
                Avg. Rating:
              </p>
              <p class="text-gray-500 dark:text-gray-300 basis-80">
                {{ userDetails.rating ? userDetails.rating : "No ratings yet" }}
              </p>
            </div>
          </div>
          <div
            class="w-full md:w-auto flex flex-col md:flex-row space-y-2 md:space-y-0 items-stretch md:items-center justify-end md:space-x-3 flex-shrink-0 ml-auto"
          >
            <button
              type="button"
              class="ml-auto lex items-start justify-end text-white bg-teal-700 hover:bg-teal-800 focus:ring-4 focus:ring-teal-300 font-medium rounded-lg text-sm px-4 py-2 dark:bg-teal-600 dark:hover:bg-teal-700 focus:outline-none dark:focus:ring-primary-800"
            >
              <NuxtLink
                link="/"
                isactive="false"
                component="a"
                linkattr="href"
                to="/account/update"
                >Edit Profile</NuxtLink
              >
            </button>
          </div>
        </div>
        <div>
          <h2
            class="text-1xl font-semibold text-teal-500 lg:text-2xl dark:text-white pt-10 pb-5"
          >
            My Trips
          </h2>
          <hr class="w-auto h-1 bg-teal-700 opacity-30 mb-5" />
          <div
            v-for="(ride, index) in rides"
            :key="ride.id"
            class="mb-6 p-4 border-b-2 border-teal-500"
            :class="{ 'bg-teal-50': !journeyIsInThePast(ride) }"
          >
            <JourneyDetail
              :index="index"
              :ride="ride"
              :user-id="userId"
              :username="userDetails.username"
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
          <ReviewTable v-if="reviews.length" :reviews="reviews"></ReviewTable>
          <p v-else class="text-gray-500 dark:text-gray-300">No reviews yet</p>
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
    const loginToken = useCookie("loginToken");
    const reviews = ref([]);

    getRides();

    async function getRides() {
      await fetch(`http://localhost:9091/rideapi/rides/users/${userId.value}`, {
        headers: {
          Authorization: `Bearer ${loginToken.value}`,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          rides.value = data;
        });
    }

    async function getReviews() {
      await fetch(`http://localhost:9099/reviewapi/reviews/${userId.value}`, {
        headers: {
          Authorization: `Bearer ${loginToken.value}`,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          reviews.value = data;
        });
    }

    getReviews();

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
      reviews,
      rides,
      journeyIsInThePast,
      userId,
    };
  },
};
</script>
