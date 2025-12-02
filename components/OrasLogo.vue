<script setup>
const props = defineProps({
  height: { type: String, default: '24px' },
})
</script>

<template>
  <img
    src="/images/oras-logo-black.svg"
    alt="ORAS Logo"
    :style="{
      height,
      verticalAlign: 'middle',
      display: 'inline-block',
    }"
  />
</template>
