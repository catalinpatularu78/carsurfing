<template>
  <div class="overflow-x-auto">
    <table class="w-fit text-sm text-left text-gray-500 dark:text-gray-400">
      <thead
        class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
      >
        <tr>
          <th scope="col" class="px-4 py-3">Reviewer Name</th>
          <th scope="col" class="px-4 py-3">Comment</th>
          <th scope="col" class="px-4 py-3">Rating</th>
          <th scope="col" class="px-4 py-3"></th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(review, index) in reviews"
          :key="index"
          class="border-b dark:border-gray-700"
        >
          <th
            scope="row"
            class="px-4 py-3 font-medium text-gray-900 whitespace-nowrap dark:text-white"
          >
            {{ review.reviewerName }}
          </th>
          <td class="px-4 py-3">{{ review.comment }}</td>
          <td class="px-4 py-3">
            <div class="flex items-center mt-2.5 mb-5">
              <svg
                v-for="item in review.rating"
                :key="item"
                aria-hidden="true"
                class="w-5 h-5 text-yellow-300"
                fill="currentColor"
                viewBox="0 0 20 20"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"
                ></path>
              </svg>
            </div>
          </td>
          <td class="px-4 py-3">
            <span
              class="text-teal-600 hover:text-purple-600 cursor-pointer"
              @click="null"
              >View Profile</span
            >
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
export default {
  props: {
    reviews: {
      type: Array,
      default: () => [],
    },
  },
};
</script>
