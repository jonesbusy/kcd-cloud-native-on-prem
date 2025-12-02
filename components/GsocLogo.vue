<script setup>
const props = defineProps({
  height: { type: String, default: '24px' },
})
</script>

<template>
  <img
    src="/images/gsoc-logo-black.svg"
    alt="GSoC Logo"
    :style="{
      height,
      verticalAlign: 'middle',
      display: 'inline-block',
    }"
  />
</template>
