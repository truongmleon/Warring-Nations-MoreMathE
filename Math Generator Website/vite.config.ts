import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  build: {
    rollupOptions: {
      input: {
        main: "index.html",
        arithmetic: 'arithmetic.html',
        geometry: "geometry.html",
        algebruh: "algebruh.html",
        calculus: "calculus.html",
        abstract: "abstract.html"
      },
    },
  },
})
