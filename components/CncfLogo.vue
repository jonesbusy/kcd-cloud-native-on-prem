<script setup>
const props = defineProps({
  height: { type: String, default: '24px' },
})
</script>

<template>
  <img
    src="/images/cncf-logo-black.svg"
    alt="CNCF Logo"
    :style="{
      height,
      verticalAlign: 'middle',
      display: 'inline-block',
    }"
  />
</template>
