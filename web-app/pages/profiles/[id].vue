<template>
  <div>
    <section class="p-3 sm:p-5 mb-7">
      <div class="mx-auto max-w-screen-xl px-4 lg:px-12">
        <h1
          class="text-3xl font-semibold text-teal-500 lg:text-4xl dark:text-white text-left py-5"
        >
          {{ userDetails.username }}
        </h1>
        <hr class="w-auto h-1 bg-teal-700 opacity-30 mb-8" />
        <div class="flex mb-4">
          <FancyImage></FancyImage>
          <div>
            <div class="flex mb-3">
              <p class="text-teal-500 dark:text-gray-300 mr-9">Description:</p>
              <p class="text-gray-500 dark:text-gray-300">
                {{
                  userDetails.description
                    ? userDetails.description
                    : "Description not yet added"
                }}
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
            Reviews
          </h2>
          <hr class="w-auto h-1 bg-teal-700 opacity-30 mb-5" />
          <ReviewTable v-if="reviews.length" :reviews="reviews"></ReviewTable>
          <p v-else class="text-gray-400">No reviews yet</p>
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
    const loginToken = useCookie("loginToken");
    const reviews = ref([]);
    const route = useRoute();
    async function getReviews() {
      await fetch(
        `http://localhost:9099/reviewapi/reviews/${route.params.id}`,
        {
          headers: {
            Authorization: `Bearer ${loginToken.value}`,
          },
        }
      )
        .then((response) => response.json())
        .then((data) => {
          reviews.value = data;
        });
    }

    getReviews();
    getUserDetails();

    async function getUserDetails() {
      await fetch(`http://localhost:9092/api/user/${route.params.id}`, {
        headers: {
          Authorization: `Bearer ${loginToken.value}`,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          userDetails.value = data;
        });
    }

    return {
      userDetails,
      reviews,
    };
  },
};
</script>
