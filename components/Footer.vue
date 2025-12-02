<script setup>
import { useSlideContext } from '@slidev/client'

const props = defineProps({
  height: { type: String, default: '24px' },
})

const { $slidev } = useSlideContext()
</script>

<template>
  <div v-if="$slidev.nav.currentPage > 1" class="fixed bottom-0 left-0 w-full z-50 text-center">
    <img
      src="/images/theme_bottom.png"
      alt="Footer"
      :style="{
        height,
        verticalAlign: 'middle',
        display: 'inline-block',
      }"
    />
  </div>
</template>
