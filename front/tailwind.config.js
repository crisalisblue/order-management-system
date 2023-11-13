/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/**/**.{js,ts,jsx,tsx}",
    "./src/components/*.css",
    "./src/**/*.css",
  ],
  theme: {
    extend: {
      fontFamily: {
        sans: ["Comic-sans", "Helvetica", "Arial", "sans-serif"], // Cambia Roboto por la fuente que desees
      },
    },
  },
  daisyui: {
    themes: [
      {
        mytheme: {
          primary: "#001F3D",
          secondary: "#4C73A0",
          accent: "#00A4E6",
          neutral: "#b4c4ca",
          "base-100": "#b4c4ca",
          "base-200": "#9dabb1",
          info: "#CCE3ED",
          success: "#008A70",
          warning: "#9c840d",
          error: "#F87272",
        },
      },
      "coffee",
    ],
  },
  plugins: [require("daisyui"), require("@tailwindcss/typography")],
};
