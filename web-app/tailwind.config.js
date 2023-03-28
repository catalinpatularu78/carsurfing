module.exports = {
  content: [
    "./components/**/*.{js,vue,ts}",
    "./layouts/**/*.vue",
    "./pages/**/*.vue",
    "./plugins/**/*.{js,ts}",
    "./nuxt.config.{js,ts}",
    'node_modules/flowbite-vue/**/*.{js,jsx,ts,tsx}',
    'node_modules/flowbite/**/*.{js,jsx,ts,tsx}',
    "./node_modules/flowbite/**/*.js",
  ],
  theme: {
    colors: {
      teal: '#16B2AB',
    },
    extend: {
      spacing: {
        '128': '24rem',
      },
      fontFamily: {
        'sans': ['League Spartan', 'system-ui'],
      }
    },
  },
  plugins: [
    require('flowbite/plugin')
  ],
}
