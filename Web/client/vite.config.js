import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'
import tailwindcss from 'tailwindcss'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  // Point Vite to the MediLinkAI directory which contains your .env file
  envDir: './',
  css: {
    postcss: {
      plugins: [tailwindcss()],
    },
  }
})



