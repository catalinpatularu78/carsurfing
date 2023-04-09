<template>
  <div>
    <section class="p-3 sm:p-5 mb-7">
      <div class="mx-auto max-w-screen-xl px-4 lg:px-12">
        <h1
          class="text-3xl font-semibold text-teal-500 lg:text-4xl dark:text-white text-left py-5"
        >
          My Profile
        </h1>

        <div class="flex">
          <img src="https://placebear.com/200/200" class="mr-12" />
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
            class="text-1xl font-semibold text-teal-500 lg:text-2xl dark:text-white text- py-5"
          >
            Reviews
          </h2>
          <ReviewTable :reviews="fakeReviews"></ReviewTable>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import ReviewTable from "~~/components/ReviewTable.vue";
import { ref } from "vue";
export default {
  components: {
    ReviewTable,
  },
  setup() {
    const userDetails = ref({});

    const fakeReviews = ref([
      {
        reviewerName: "Mary",
        comment: "Really funny person",
        rating: 5,
      },
    ]);

    getUserDetails();
    async function getUserDetails() {
      await fetch("http://localhost:9092/userapi/users/2")
        .then((response) => response.json())
        .then((data) => {
          userDetails.value = data;
        });
    }

    return {
      userDetails,
      fakeReviews,
    };
  },
};
</script>
<style scoped>
@property --gap {
  syntax: "<length>";
  inherits: true;
  initial-value: 0;
}
@property --angle {
  syntax: "<angle>";
  inherits: true;
  initial-value: 0deg;
}

img {
  --border: 15px;
  --gap: 12px;
  --color: rgb(6 148 162);

  width: 200px;
  aspect-ratio: 1;
  box-sizing: border-box;
  border-radius: 50%;
  margin: 0 32px 0 0;
  padding: calc(var(--border) + var(--gap));

  --angle: 90deg;
  background: radial-gradient(farthest-side, var(--color) 97%, #0000 101%)
      85.35% 85.35%,
    conic-gradient(
      from calc(180deg - var(--angle) / 2),
      #0000 var(--angle),
      var(--color) 0
    ),
    radial-gradient(farthest-side, var(--color) 97%, #0000 101%) 14.65% 85.35%;
  background-size: var(--border) var(--border), auto;
  background-repeat: no-repeat;

  -webkit-mask: radial-gradient(
    farthest-side,
    #000 calc(99% - calc(var(--border) + var(--gap))),
    #0000 calc(100% - calc(var(--border) + var(--gap)))
      calc(99% - var(--border)),
    #000 calc(100% - var(--border))
  );
  mask: radial-gradient(
    farthest-side,
    #000 calc(99% - calc(var(--border) + var(--gap))),
    #0000 calc(100% - calc(var(--border) + var(--gap)))
      calc(99% - var(--border)),
    #000 calc(100% - var(--border))
  );
  transition: --angle 0.4s, --gap 0.4s;
}
img:hover {
  --angle: 0deg;
  --gap: 5px;
}
</style>
