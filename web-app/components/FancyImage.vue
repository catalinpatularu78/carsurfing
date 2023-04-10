<template>
  <img :src="imageUrl" class="mr-12" />
</template>
<script>
export default {
  props: {
    imageUrl: {
      type: String,
      default: "https://placebear.com/200/200",
    },
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
  --color: rgb(6, 148, 162);

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
