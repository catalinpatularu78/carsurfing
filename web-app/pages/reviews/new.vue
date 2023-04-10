<template>
  <section>
    <form
      v-if="!reviewCreated"
      class="flex flex-col items-center my-7"
      @submit.prevent="submitCreateReview"
    >
      <div class="max-w-sm">
        <h1
          class="text-3xl font-semibold text-teal-500 lg:text-4xl dark:text-white text-center py-5"
        >
          Review your driver
        </h1>
        <div class="grid grid-rows-1 grid-cols-8">
          <Icon
            name="ph:steering-wheel"
            class="w-48 h-48 mb-4 text-teal-200 z-10 col-start-1 col-end-4 row-span-full"
          ></Icon>
          <Icon
            name="ph:steering-wheel"
            class="w-48 h-48 mb-4 text-teal-400 z-20 col-start-3 col-end-6 row-span-full"
          ></Icon>
          <Icon
            name="ph:steering-wheel"
            class="w-48 h-48 mb-4 text-teal-600 z-30 col-start-5 col-end-8 row-span-full"
          ></Icon>
        </div>

        <div class="flex mb-5">
          <p class="text-sm text-gray-900 font-medium mr-4">Driver:</p>
          <p class="text-sm text-gray-900 font-medium">Add Driver name</p>
        </div>
        <div class="mb-6 max-w-full">
          <label
            for="comment"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Comment</label
          >
          <textarea
            v-model="comment"
            type="text"
            id="from-location"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-purple-500 focus:border-purple-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-purple-500 dark:focus:border-purple-500"
            placeholder="Tell us about your experience..."
          />
        </div>
        <div class="mb-6 max-w-full">
          <label
            for="rating"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Rating</label
          >
          <div class="flex items-center mt-2.5 mb-5">
            <svg
              v-for="item in 5"
              :key="item"
              aria-hidden="true"
              :class="item <= rating ? 'text-yellow-300' : 'text-gray-200'"
              class="w-8 h-8 cursor-pointer hover:text-yellow-300"
              fill="currentColor"
              viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg"
              @click="setRating(item)"
            >
              <path
                d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"
              ></path>
            </svg>
          </div>
        </div>
        <ErrorMessage
          v-if="showError"
          message="Review could not be created. Please try again."
          @hide-error="showError = false"
        ></ErrorMessage>
        <button
          type="submit"
          class="text-white bg-purple-800 hover:bg-purple-900 focus:ring-4 focus:outline-none focus:ring-purple-300 font-medium rounded-lg text-sm w-full sm:w-full px-5 py-2.5 text-center dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-800"
        >
          Submit
        </button>
      </div>
    </form>
    <SuccessMessage
      v-else
      title="Review sent"
      message="Thanks for taking the time to help other carpoolers"
    ></SuccessMessage>
  </section>
</template>
<script>
import { ref, computed } from "vue";
import ErrorMessage from "~~/components/ErrorMessage.vue";
import SuccessMessage from "~~/components/SuccessMessage.vue";
export default {
  components: {
    ErrorMessage,
    SuccessMessage,
  },
  setup() {
    const route = useRoute();
    const reviewCreated = ref(false);
    const showError = ref(false);
    const comment = ref("");
    const rating = ref(0);

    const data = computed(() => ({
      comment: comment.value,
      rating: rating.value,
      reviewerName: route.query.reviewer,
      reviewedId: 1,
    }));

    function setRating(item) {
      rating.value = item;
    }

    async function submitCreateReview() {
      await fetch("http://localhost:9099/reviewapi/reviews", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data.value),
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.id) {
            reviewCreated.value = true;
          } else {
            showError.value = true;
          }
        });
    }

    return {
      submitCreateReview,
      rating,
      comment,
      reviewCreated,
      showError,
      setRating,
      route,
    };
  },
};
</script>
