import Card from "@/components/ui/Card.component";
import WordCloud from "@/components/ui/WordCloud.component";
import React from "react";

const page = () => {
  return (
    <div className="flex justify-center h-full w-full">
      <div className="sections">
        <section className="container">
          <h2>Decks</h2>
          <div className="flex flex-wrap gap-6">
            <WordCloud />
            <Card />
            <Card />
            <Card />
            <Card />
            <Card />
            <Card />
          </div>
        </section>
      </div>

      <div></div>
    </div>
  );
};

export default page;
