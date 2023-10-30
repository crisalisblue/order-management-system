/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/**/**.{js,ts,jsx,tsx}",
    "./src/components/*.css",
    "./src/**/*.css",
  ],
  daisyui: {
    themes: [
      {
        mytheme: {
          primary: "#001F3D",
          secondary: "#4C73A0",
          accent: "#00A4E6",
          neutral: "#272835",
          "base-100": "#CCE3ED",
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
