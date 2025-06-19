import React from "react";

const WordCloud = ({
  centerWord = "Світло",
  otherWords = [
    "темрява",
    "хаос",
    "ніч",
    "шум",
    "мовчання",
    "пітьма",
    "голос",
    "звук",
    "вогонь",
  ],
  width = 400,
  height = 400,
}: {
  centerWord?: string;
  otherWords?: string[];
  width?: number;
  height?: number;
}) => {
  const generateRandomPosition = () => ({
    top: Math.random() * height - height / 2,
    left: Math.random() * width - width / 2,
  });

  return (
    <div
      style={{
        position: "relative",
        width,
        height,
        overflow: "hidden",
        background: "#000",
        color: "#fff",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      {/* Інші слова */}
      {otherWords.map((word, index) => {
        const { top, left } = generateRandomPosition();
        const fontSize = Math.random() * 14 + 10; // 10px - 24px
        return (
          <div
            key={index}
            style={{
              position: "absolute",
              top: `calc(50% + ${top}px)`,
              left: `calc(50% + ${left}px)`,
              fontSize,
              whiteSpace: "nowrap",
              color: "#999",
            }}
          >
            {word}
          </div>
        );
      })}

      {/* Центральне слово */}
      <div
        style={{
          position: "absolute",
          color: "red",
          fontSize: 48,
          fontWeight: "bold",
          whiteSpace: "nowrap",
        }}
      >
        {centerWord}
      </div>
    </div>
  );
};

export default WordCloud;
