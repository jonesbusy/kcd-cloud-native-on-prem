<script setup>
const props = defineProps({
  width: {
    type: String,
    default: '225px',
  },
})
</script>

<template>
  <img src="/images/elca-logo-white.svg" :alt="'ELCA Logo'" :style="{ width }" />
</template>
