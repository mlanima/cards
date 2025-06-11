"use client";
import React from "react";
import Logo from "@/components/ui/Logo";
import { Button } from "@headlessui/react";

const Home = () => {
  return (
    <div className="flex justify-center items-center w-full text-white">
      <div className="flex flex-col items-center gap-12 p-8">
        <Logo className="text-6xl" />
        <p className="text-xl w-2xl">
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Nulla
          assumenda, accusantium impedit eum reprehenderit esse natus eius error
          tempora et dolores cumque voluptatem sit, fugiat voluptates
          consequuntur, quaerat vitae magnam.
        </p>
        <Button className="flex justify-center items-center gap-2 rounded-md bg-purple-500 px-3 py-1.5 text-xl font-semibold text-white shadow-inner shadow-white/10 focus:not-data-focus:outline-none data-focus:outline data-focus:outline-white data-hover:bg-gray-600 data-open:bg-gray-700">
          Get Started
        </Button>
      </div>
    </div>
  );
};

export default Home;
