/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        mytheme: {

          "primary": "#001f3d",

          "secondary": "#4c73a0",

          "accent": "#eaffff",

          "neutral": "#2a323c",

          "base-100": "#fff",

          "info": "#3abff8",

          "success": "#008a70",

          "warning": "#fbbd23",

          "error": "#f87272",
        },
      },
    },
  },
  plugins: [require("daisyui")],
};
