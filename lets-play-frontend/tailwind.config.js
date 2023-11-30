/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
    "./node_modules/flowbite/**/*.js" 
  ],
  theme: {
    extend: {
      textShadow: {
        sm: '0 1px 2px var(--tw-shadow-color)',
        DEFAULT: '0 2px 4px var(--tw-shadow-color)',
        lg: '0 8px 16px var(--tw-shadow-color)',
      },
    },
  },
  plugins: [
    require('flowbite/plugin')
  ],

  theme: {
    extend: {
      colors: {
        backgroundMain: '#C1BABA',
        backgroundTile: '#D9D9D9'
        // Ajoutez d'autres couleurs personnalisées au besoin
      },
    },
  },
}

