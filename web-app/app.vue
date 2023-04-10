<template>
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link
    href="https://fonts.googleapis.com/css2?family=League+Spartan:wght@200;300;400;500;600&display=swap"
    rel="stylesheet"
  />
  <NuxtLayout>
    <NuxtPage />
  </NuxtLayout>
</template>
<script>
export default {
  setup() {
    useHead({
      script: [
        {
          src: "https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.4/flowbite.min.js",
          body: true,
        },
      ],
    });
  },
};
</script>
