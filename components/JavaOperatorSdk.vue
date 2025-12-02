<script setup>
const props = defineProps({
  height: { type: String, default: '24px' },
})
</script>

<template>
  <img
    src="/images/java-operator-sdk-color.svg"
    alt="Operator SDK Logo"
    :style="{
      height,
      verticalAlign: 'middle',
      display: 'inline-block',
    }"
  />
</template>
