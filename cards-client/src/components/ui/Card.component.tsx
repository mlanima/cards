import React from "react";
import Link from "next/link";

interface CardProps {
  name?: string;
  amount?: number;
}

const Card = ({ name, amount }: CardProps) => {
  return (
    <Link href={"#"}>
      <div className="border-4 rounded-2xl bg-gray-800 border-gray-950 w-64 h-54 origin-top-left hover:rotate-6 transition-all overflow-hidden">
        <div className=" w-full h-2/3"></div>
        <div className=" p-4 m-0 h-1/3 bg-gray-900">
          <h1 className="">{name ?? "Card"}</h1>
          <p> {amount ?? "No cards"} </p>
        </div>
      </div>
    </Link>
  );
};

export default Card;
